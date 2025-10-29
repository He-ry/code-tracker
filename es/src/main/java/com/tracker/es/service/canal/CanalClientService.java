package com.tracker.es.service.canal;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.tracker.es.domain.dto.es.ArticleDocument;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CanalClientService {

    private CanalConnector connector;

    @Value("${canal.host}")
    private String canalHost;

    @Value("${canal.port}")
    private Integer canalPort;

    @Value("${canal.destination}")
    private String canalDestination;

    @Value("${canal.username}")
    private String canalUsername;

    @Value("${canal.password}")
    private String canalPassword;

    @Value("${canal.subscribe}")
    private String canalSubscribe;

    @Resource
    private ElasticsearchClient elasticsearchClient;

    @PostConstruct
    public void start() {
        connector = CanalConnectors.newSingleConnector(
                new InetSocketAddress(canalHost, 11111), canalDestination, canalUsername, canalPassword
        );
        Thread.startVirtualThread(this::run);
    }

    private void run() {
        connector.connect();
        connector.subscribe(canalSubscribe);
        connector.rollback();

        while (true) {
            Message message = connector.get(1000); // 拉取1000条binlog
            List<CanalEntry.Entry> entries = message.getEntries();
            if (entries.isEmpty()) continue;

            List<ArticleDocument> batchInsertUpdateList = new ArrayList<>();
            List<String> batchDeleteList = new ArrayList<>();

            for (CanalEntry.Entry entry : entries) {
                if (entry.getEntryType() != CanalEntry.EntryType.ROWDATA) continue;
                try {
                    CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                    CanalEntry.EventType eventType = rowChange.getEventType();

                    rowChange.getRowDatasList().forEach(rowData -> {
                        switch (eventType) {
                            case INSERT, UPDATE ->
                                    batchInsertUpdateList.add(buildDocument(rowData.getAfterColumnsList()));
                            case DELETE -> {
                                rowData.getBeforeColumnsList().stream()
                                        .filter(c -> c.getName().equals("id"))
                                        .findFirst()
                                        .map(CanalEntry.Column::getValue)
                                        .ifPresent(batchDeleteList::add);
                            }
                        }
                    });
                } catch (Exception e) {
                    log.error("处理canal数据异常, entry:{}", entry, e);
                }
            }

            // 使用虚拟线程异步批量写入
            if (!batchInsertUpdateList.isEmpty() || !batchDeleteList.isEmpty()) {
                Thread.startVirtualThread(() -> batchWrite(batchInsertUpdateList, batchDeleteList));
            }
        }
    }

    private ArticleDocument buildDocument(List<CanalEntry.Column> columns) {
        ArticleDocument doc = new ArticleDocument();
        columns.forEach(column -> {
            String value = column.getValue();
            switch (column.getName()) {
                case "id" -> doc.setId(Long.valueOf(value));
                case "title" -> doc.setTitle(value);
                case "subtitle" -> doc.setSubtitle(value);
                case "content" -> doc.setContent(value);
                case "summary" -> doc.setSummary(value);
                case "author" -> doc.setAuthor(value);
                case "tags" -> doc.setTags(value);
                case "category" -> doc.setCategory(value);
                case "status" -> doc.setStatus(Integer.valueOf(value));
                case "view_count" -> doc.setViewCount(Integer.valueOf(value));
                case "like_count" -> doc.setLikeCount(Integer.valueOf(value));
                case "comment_count" -> doc.setCommentCount(Integer.valueOf(value));
                case "created_by" -> doc.setCreatedBy(value);
                case "updated_by" -> doc.setUpdatedBy(value);
                case "create_time" -> doc.setCreateTime(parseDateTime(value));
                case "update_time" -> doc.setUpdateTime(parseDateTime(value));
                case "deleted" -> doc.setDeleted(value.equals("1") || value.equalsIgnoreCase("true"));
            }
        });
        return doc;
    }

    private LocalDateTime parseDateTime(String value) {
        if (value == null || value.isEmpty()) return null;
        try {
            // 默认 ISO 格式 yyyy-MM-ddTHH:mm:ss
            return LocalDateTime.parse(value);
        } catch (Exception e) {
            log.warn("解析时间失败: {}", value, e);
            return null;
        }
    }


    private void batchWrite(List<ArticleDocument> insertOrUpdateDocs, List<String> deleteIds) {
        try {
            if (!insertOrUpdateDocs.isEmpty()) {
                BulkRequest.Builder bulkInsertBuilder = new BulkRequest.Builder();
                for (ArticleDocument doc : insertOrUpdateDocs) {
                    bulkInsertBuilder.operations(op -> op
                            .index(idx -> idx.index("article_index").id(String.valueOf(doc.getId())).document(doc))
                    );
                }
                BulkResponse insertResponse = elasticsearchClient.bulk(bulkInsertBuilder.build());
                if (insertResponse.errors()) {
                    log.warn("插入/更新批量操作有错误: {}", insertResponse.items());
                }
            }
            if (!deleteIds.isEmpty()) {
                BulkRequest.Builder bulkDeleteBuilder = new BulkRequest.Builder();
                for (String id : deleteIds) {
                    bulkDeleteBuilder.operations(op -> op.delete(d -> d.index("article_index").id(id)));
                }
                BulkResponse deleteResponse = elasticsearchClient.bulk(bulkDeleteBuilder.build());
                if (deleteResponse.errors()) {
                    log.warn("删除批量操作有错误: {}", deleteResponse.items());
                }
            }
        } catch (IOException e) {
            log.error("批量写入 Elasticsearch 异常", e);
        }
    }


}

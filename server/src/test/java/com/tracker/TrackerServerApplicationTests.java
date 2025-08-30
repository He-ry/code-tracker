package com.tracker;

import com.tracker.framework.utils.MinioUtil;
import com.tracker.server.TrackerServerApplication;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
@Slf4j
@SpringBootTest(classes = TrackerServerApplication.class)
class TrackerServerApplicationTests {

    @Resource
    private MinioUtil minioUtil;


    @Test
    void contextLoads() {
        File file = new File("D:\\nginx-1.26.2.zip");
        String originalFileName = file.getName();
        //获取文件输入流：
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            MinioUtil.FileInfo fileInfo = minioUtil.uploadFile(inputStream, originalFileName);
            log.info("上传成功：{}", fileInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

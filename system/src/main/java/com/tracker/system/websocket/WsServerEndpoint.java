package com.tracker.system.websocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket 服务端
 * 支持单发、群发，自动清理异常会话
 *
 * @author hry
 */
@Slf4j
@Component
@ServerEndpoint(value = "/api/websocket")
public class WsServerEndpoint {

    // 存放所有连接（key = sessionId）
    private static final ConcurrentHashMap<String, WsServerEndpoint> webSocketMap = new ConcurrentHashMap<>();

    private Session session;

    /**
     * 发送消息到指定客户端
     *
     * @param message   消息内容
     * @param sessionId WebSocket 会话 ID
     */
    public static void sendMessage(String message, String sessionId) {
        WsServerEndpoint webSocket = webSocketMap.get(sessionId);
        if (webSocket != null && webSocket.session.isOpen()) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("发送消息异常, session:{}", sessionId, e);
            }
        }
    }

    /**
     * 群发消息到所有客户端
     *
     * @param message 消息内容
     */
    public static void broadcast(String message) {
        webSocketMap.forEach((sid, endpoint) -> {
            if (endpoint.session.isOpen()) {
                try {
                    endpoint.session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    log.error("群发消息异常, session:{}", sid, e);
                }
            }
        });
    }

    /**
     * 连接建立
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        String sid = session.getId();
        webSocketMap.put(sid, this);
        log.info("WebSocket 连接成功, session:{}", sid);
        sendMessage("connect success", sid);
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(Session session) {
        String sid = session.getId();
        webSocketMap.remove(sid);
        log.info("WebSocket 连接关闭, session:{}", sid);
    }

    /**
     * 接收到消息
     */
    @OnMessage
    public void onMessage(String text, Session session) {
        String sid = session.getId();
        log.info("收到客户端消息, session:{} -> {}", sid, text);
        // 回显给客户端
        sendMessage("receive msg from client: " + text, sid);
    }

    /**
     * 异常处理
     */
    @OnError
    public void onError(Session session, Throwable error) {
        String sid = session != null ? session.getId() : "unknown";

        log.error("WebSocket 错误, session:{} -> {}", sid, error.getMessage());

        // 清理会话
        if (session != null) {
            webSocketMap.remove(sid);
            try {
                if (session.isOpen()) {
                    session.close();
                }
            } catch (IOException e) {
                log.error("关闭异常 session 失败, session:{}", sid, e);
            }
        }
    }
}

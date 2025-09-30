package com.tracker.system.websocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hry
 */
@Slf4j
@Component
@ServerEndpoint(value = "/api/websocket")
public class WsServerEndpoint {

    private static final ConcurrentHashMap<String, WsServerEndpoint> webSocketMap = new ConcurrentHashMap<>();

    private Session session;

    /**
     * 发送消息
     *
     * @param message  消息
     * @param sessionId WebSocket 的 sessionId
     */
    public static void sendMessage(String message, String sessionId) {
        WsServerEndpoint webSocket = webSocketMap.get(sessionId);
        if (webSocket != null) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("发送消息异常", e);
            }
        }
    }

    /**
     * 连接建立
     *
     * @param session  会话
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        String sid = session.getId();
        webSocketMap.put(sid, this);
        log.info("session:{} 连接成功", sid);
        sendMessage("connect success", sid);
    }

    /**
     * 连接关闭
     *
     * @param session  会话
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        String sid = session.getId();
        log.info("session:{} 连接关闭", sid);
        webSocketMap.remove(sid);
        session.close();
    }

    /**
     * 接收到消息
     *
     * @param text 消息
     * @param session 当前会话
     */
    @OnMessage
    public void onMessage(String text, Session session) {
        String sid = session.getId();
        log.info("收到客户端消息：{}", text);
        sendMessage("receive msg from client：" + text, sid);
    }

}

package com.example.helloworld.websocket;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketUtil {
    /**
     * 在线session管理
     */
    private static final Map<String, Session> ONLINE_SESSION = new ConcurrentHashMap<>();

    public static void addSession(String userNick, Session session) {
        //putIfAbsent 添加K-V时候，会先判断K 是否存在
        //不存在，新增，并返回Null
        //存在， 不覆盖，返回之前存在的V
        ONLINE_SESSION.putIfAbsent(userNick, session);
    }

    public static void removeSession(String userNick) {
        ONLINE_SESSION.remove(userNick);
    }

    /**
     * 向某个用户发送消息
     * @param session 某个用户的session对象
     * @param message
     */
    public static void sendMessage(Session session, String message) {
        if (session == null) {
            return;
        }
        //session.getBasicRemote();
        // getAsyncRemote  getBasicRemote   异步和同步
        RemoteEndpoint.Async asyncRemote = session.getAsyncRemote();
        asyncRemote.sendText(message);
    }

    /**
     * 向所有在线用户发送消息
     * @param message
     */
    public static void sendMessageForAll(String message) {
        ONLINE_SESSION.forEach((sessionId, session) -> sendMessage(session, message));
    }
}

package com.example.helloworld.controller;

import com.example.helloworld.websocket.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


//由于是websocket 所以原本是@RestController的http形式
//直接替换成@ServerEndpoint即可，作用是一样的 就是指定一个地址
//表示定义一个websocket的Server端

@Component
@Slf4j
@ServerEndpoint("/my-chat/{usernick}")
public class WebSocketController {

    /**
     * 连接事件，加入注解
     * @param userNick
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("usernick") String userNick, Session session) {
        String message = "有新游客[" + userNick + "]加入聊天室";
        log.info(message);
        WebSocketUtil.addSession(userNick, session);
        //向所有在线用户通知，***登录聊天室
        WebSocketUtil.sendMessageForAll(message);
    }

    @OnClose
    public void onClose(@PathParam("usernick") String userNick, Session session) {
        String message = "游客[" + userNick + "]退出聊天室";
        log.info(message);
        WebSocketUtil.removeSession(userNick);
        //通知所有人，***离开
        WebSocketUtil.sendMessageForAll(message);
    }

    @OnMessage
    public void onMessage(@PathParam("usernick") String userNick, String message) {
        //类似群发
        String info = "游客[" + userNick + "]:" + message;
        log.info(info);
        WebSocketUtil.sendMessageForAll(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("异常:", throwable);
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


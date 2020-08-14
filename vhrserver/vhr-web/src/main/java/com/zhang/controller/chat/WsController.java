package com.zhang.controller.chat;

import com.zhang.model.ChatMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Date;

/**
 * @Author:Zpg
 * @Date:2020/7/14 19:28
 * @Version:1.0
 * @Description: 消息处理
 */
@Controller
public class WsController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    /**
     *
     * @param principal : 用来获取当前登陆的用户
     */
    @MessageMapping("/ws/chat")
    public void handleMessage(Principal principal, ChatMsg chatMsg){
        chatMsg.setFrom(principal.getName());
        chatMsg.setDate(new Date());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue", chatMsg);
    }


}

package cn.lixingyu.im.controller;

import cn.lixingyu.im.model.ChatMessage;
import cn.lixingyu.im.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        // 使用标准格式的GMT+8时区
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        
        String timeStr = sdf.format(new Date());
        chatMessage.setTime(timeStr);
        
        // 保存消息到数据库（仅保存聊天消息，不保存系统消息）
        if (chatMessage.getType() == ChatMessage.MessageType.CHAT) {
            chatMessageService.saveMessage(chatMessage);
        }
        
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, 
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        
        // 使用标准格式的GMT+8时区
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        
        String timeStr = sdf.format(new Date());
        chatMessage.setTime(timeStr);
        
        return chatMessage;
    }
} 
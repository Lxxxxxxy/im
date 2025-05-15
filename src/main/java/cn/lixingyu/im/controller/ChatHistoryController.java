package cn.lixingyu.im.controller;

import cn.lixingyu.im.model.ChatMessage;
import cn.lixingyu.im.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatHistoryController {
    
    private final ChatMessageService chatMessageService;
    
    // 获取最近50条消息
    @GetMapping("/history")
    public List<ChatMessage> getChatHistory() {
        return chatMessageService.getRecentMessages();
    }
    
    // 获取所有历史消息
    @GetMapping("/all-history")
    public List<ChatMessage> getAllChatHistory() {
        return chatMessageService.getAllMessages();
    }
} 
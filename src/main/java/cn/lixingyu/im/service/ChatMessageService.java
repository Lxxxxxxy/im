package cn.lixingyu.im.service;

import cn.lixingyu.im.model.ChatMessage;
import cn.lixingyu.im.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    
    private final ChatMessageRepository messageRepository;
    
    public ChatMessage saveMessage(ChatMessage message) {
        // 获取标准格式的GMT+8时区当前时间
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        message.setTimestamp(calendar.getTime());
        return messageRepository.save(message);
    }
    
    public List<ChatMessage> getRecentMessages() {
        List<ChatMessage> messages = messageRepository.findTop50ByOrderByTimestampDesc();
        Collections.reverse(messages);
        return messages;
    }
    
    public List<ChatMessage> getAllMessages() {
        return messageRepository.findAllByOrderByTimestampAsc();
    }
} 
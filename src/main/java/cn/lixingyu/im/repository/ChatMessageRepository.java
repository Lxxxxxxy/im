package cn.lixingyu.im.repository;

import cn.lixingyu.im.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    
    List<ChatMessage> findTop50ByOrderByTimestampDesc();
    
    List<ChatMessage> findAllByOrderByTimestampAsc();
} 
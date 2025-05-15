package cn.lixingyu.im.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "messages")
public class ChatMessage {
    
    public enum MessageType {
        CHAT, JOIN, LEAVE
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private MessageType type;
    
    @Column(length = 5000)
    private String content;
    
    private String sender;
    
    private String time;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    
    @Transient
    private Boolean displayAsEncoded; // 仅用于前端显示，不存入数据库
    
    @Transient
    public boolean isSystemMessage() {
        return type == MessageType.JOIN || type == MessageType.LEAVE;
    }
} 
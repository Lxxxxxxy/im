-- 创建数据库
CREATE DATABASE IF NOT EXISTS im CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE im;

-- 创建messages表（对应ChatMessage实体）
CREATE TABLE IF NOT EXISTS messages (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(10) NOT NULL COMMENT '消息类型：CHAT, JOIN, LEAVE',
    content TEXT COMMENT '消息内容，原始格式存储',
    sender VARCHAR(100) NOT NULL COMMENT '发送者用户名',
    time VARCHAR(20) COMMENT '消息时间字符串，如HH:mm:ss',
    timestamp DATETIME NOT NULL COMMENT '消息发送时间戳',
    
    INDEX idx_timestamp (timestamp),
    INDEX idx_sender (sender)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聊天消息表';

-- 创建示例数据（可选）
INSERT INTO messages (type, content, sender, time, timestamp) VALUES
('CHAT', 'Hello Everyone!', 'admin', '12:00:00', NOW()),
('JOIN', NULL, 'user1', '12:01:00', NOW()),
('CHAT', '中文测试', 'user1', '12:02:00', NOW()),
('JOIN', NULL, 'user2', '12:03:00', NOW()),
('CHAT', 'Base64参数测试', 'user2', '12:04:00', NOW());

-- 查看消息
SELECT id, type, content, 
       sender, time, timestamp,
       CASE 
         WHEN type = 'CHAT' THEN CONCAT('消息内容: ', content)
         WHEN type = 'JOIN' THEN CONCAT(sender, ' 加入了聊天室')
         WHEN type = 'LEAVE' THEN CONCAT(sender, ' 离开了聊天室')
       END AS display_message
FROM messages
ORDER BY timestamp DESC
LIMIT 10; 
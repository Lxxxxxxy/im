# 简易聊天室应用

这是一个基于Spring Boot和WebSocket的简易聊天室应用，允许用户只需输入用户名即可加入聊天。

## 功能特点

- 无需注册和登录，只需输入用户名即可加入聊天
- 实时消息传递，基于WebSocket技术
- 显示用户加入和离开的系统消息
- 响应式设计，支持各种设备

## 技术栈

- Spring Boot 2.6.13
- Spring WebSocket
- SockJS
- STOMP
- Thymeleaf
- Bootstrap 5

## 如何运行

1. 确保安装了Java 8或更高版本
2. 克隆此仓库
3. 运行以下命令启动应用：
   ```bash
   ./mvnw spring-boot:run
   ```
   或使用IDE直接运行`ImApplication.java`

4. 在浏览器中打开 http://localhost:8080

## 项目结构

- `src/main/java/cn/lixingyu/im/config/WebSocketConfig.java`: WebSocket配置
- `src/main/java/cn/lixingyu/im/controller/ChatController.java`: 处理聊天消息
- `src/main/java/cn/lixingyu/im/controller/WebController.java`: 网页控制器
- `src/main/java/cn/lixingyu/im/event/WebSocketEventListener.java`: WebSocket事件监听器
- `src/main/java/cn/lixingyu/im/model/ChatMessage.java`: 聊天消息模型
- `src/main/resources/templates/index.html`: 登录页面
- `src/main/resources/templates/chat.html`: 聊天页面 
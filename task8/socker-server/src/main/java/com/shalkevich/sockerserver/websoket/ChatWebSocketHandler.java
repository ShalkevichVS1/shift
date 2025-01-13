package com.shalkevich.sockerserver.websoket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.shalkevich.sockerserver.model.Message;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;

/**
 * Обработчик WebSocket соединений и сообщений.
 * Управляет подключениями, передачей сообщений и обновлением списка участников.
 */
@RequiredArgsConstructor
public class ChatWebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final MessageHandler messageHandler;
    private final SessionManager sessionManager;

    /**
     * Вызывается при установлении нового соединения WebSocket.
     *
     * @param session текущая сессия WebSocket.
     */
    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        sessionManager.addSession(session);
    }

    /**
     * Обрабатывает входящие сообщения WebSocket.
     *
     * @param session текущая сессия WebSocket.
     * @param message текстовое сообщение WebSocket.
     * @throws Exception если возникает ошибка при обработке сообщения.
     */
    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, TextMessage message) throws Exception {
        Map<String, Object> messageMap = objectMapper.readValue(message.getPayload(), new TypeReference<>() {
        });
        String type = (String) messageMap.get("type");

        if ("join".equals(type)) {
            String username = (String) messageMap.get("username");
            messageHandler.handleJoinMessage(session, username);
        } else {
            Message chatMessage = objectMapper.convertValue(messageMap, Message.class);
            messageHandler.handleChatMessage(chatMessage);
        }
    }

    /**
     * Вызывается при закрытии соединения WebSocket.
     *
     * @param session текущая сессия WebSocket.
     * @param status  статус закрытия соединения.
     * @throws Exception если возникает ошибка при обработке закрытия соединения.
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, @NonNull org.springframework.web.socket.CloseStatus status) throws Exception {
        String username = (String) session.getAttributes().get("username");
        if (username != null) {
            messageHandler.handleLeaveMessage(session, username);
        }
        sessionManager.removeSession(session);
    }
}

package com.shalkevich.sockerserver.websoket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shalkevich.sockerserver.model.Message;
import com.shalkevich.sockerserver.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;

/**
 * Класс для обработки различных типов сообщений WebSocket.
 */
@RequiredArgsConstructor
public class MessageHandler {
    private final ObjectMapper objectMapper;
    private final MessageRepository messageRepository;
    private final SessionManager sessionManager;

    /**
     * Обрабатывает сообщение о присоединении пользователя.
     *
     * @param session  текущая сессия WebSocket.
     * @param username имя пользователя.
     * @throws Exception если возникает ошибка при обработке сообщения.
     */
    public void handleJoinMessage(WebSocketSession session, String username) throws Exception {
        if (sessionManager.containsUsername(username)) {
            sendErrorMessage(session);
            session.close();
        } else {
            sessionManager.addUsername(username, session);
            broadcastJoinMessage(username);
            broadcastParticipantsList();
        }
    }

    /**
     * Обрабатывает обычное сообщение чата.
     *
     * @param session     текущая сессия WebSocket.
     * @param chatMessage сообщение чата.
     * @throws Exception если возникает ошибка при обработке сообщения.
     */
    public void handleChatMessage(WebSocketSession session, Message chatMessage) throws Exception {
        chatMessage.setDate(LocalDateTime.now());
        messageRepository.saveMessage(chatMessage);
        broadcastMessage(chatMessage);
    }

    /**
     * Обрабатывает сообщение о выходе пользователя.
     *
     * @param session  текущая сессия WebSocket.
     * @param username имя пользователя.
     * @throws Exception если возникает ошибка при обработке сообщения.
     */
    public void handleLeaveMessage(WebSocketSession session, String username) throws Exception {
        sessionManager.removeSession(session);
        Message leaveMessage = new Message();
        leaveMessage.setName("Сервер");
        leaveMessage.setText(username + " отключился.");
        leaveMessage.setDate(LocalDateTime.now());

        messageRepository.saveMessage(leaveMessage);
        broadcastMessage(leaveMessage);
        broadcastParticipantsList();
    }

    /**
     * Отправляет сообщение об ошибке пользователю.
     *
     * @param session текущая сессия WebSocket.
     * @throws Exception если возникает ошибка при отправке сообщения.
     */
    private void sendErrorMessage(WebSocketSession session) throws Exception {
        Message message = new Message();
        message.setName("Сервер");
        message.setText("Имя пользователя уже занято. Попробуйте другое имя.");
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
    }

    /**
     * Рассылает сообщение о присоединении пользователя всем клиентам.
     *
     * @param username имя пользователя.
     * @throws Exception если возникает ошибка при рассылке сообщения.
     */
    private void broadcastJoinMessage(String username) throws Exception {
        Message joinMessage = new Message();
        joinMessage.setName("Сервер");
        joinMessage.setText(username + " присоединился к чату.");
        joinMessage.setDate(LocalDateTime.now());

        messageRepository.saveMessage(joinMessage);
        broadcastMessage(joinMessage);
    }

    /**
     * Рассылает сообщение всем подключенным клиентам.
     *
     * @param message сообщение для рассылки.
     * @throws Exception если возникает ошибка при рассылке сообщения.
     */
    private void broadcastMessage(Message message) throws Exception {
        String broadcastMessage = objectMapper.writeValueAsString(message);
        for (WebSocketSession webSocketSession : sessionManager.getSessions()) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(new TextMessage(broadcastMessage));
            }
        }
    }

    /**
     * Отправляет обновленный список участников всем подключенным клиентам.
     *
     * @throws Exception если возникает ошибка при отправке списка участников.
     */
    private void broadcastParticipantsList() throws Exception {
        String participantsMessage = objectMapper.writeValueAsString(sessionManager.getUsernames());
        for (WebSocketSession webSocketSession : sessionManager.getSessions()) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(new TextMessage(participantsMessage));
            }
        }
    }
}

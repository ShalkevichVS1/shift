package com.shalkevich.sockerserver.websoket;

import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс для управления сессиями WebSocket.
 */
public class SessionManager {

    /**
     * -- GETTER --
     * Возвращает список всех сессий WebSocket.
     */
    @Getter
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final Map<String, WebSocketSession> usernameSessionMap = new ConcurrentHashMap<>();

    /**
     * Добавляет новую сессию WebSocket.
     *
     * @param session текущая сессия WebSocket.
     */
    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    /**
     * Удаляет сессию WebSocket.
     *
     * @param session текущая сессия WebSocket.
     */
    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
        String username = (String) session.getAttributes().get("username");
        if (username != null) {
            usernameSessionMap.remove(username);
        }
    }

    /**
     * Добавляет имя пользователя и сессию WebSocket в карту.
     *
     * @param username имя пользователя.
     * @param session  текущая сессия WebSocket.
     */
    public void addUsername(String username, WebSocketSession session) {
        usernameSessionMap.put(username, session);
        session.getAttributes().put("username", username);
    }

    /**
     * Проверяет, содержится ли имя пользователя в карте.
     *
     * @param username имя пользователя.
     * @return true, если имя пользователя содержится в карте, иначе false.
     */
    public boolean containsUsername(String username) {
        return usernameSessionMap.containsKey(username);
    }

    /**
     * Возвращает список всех имен пользователей.
     *
     * @return список имен пользователей.
     */
    public List<String> getUsernames() {
        return new ArrayList<>(usernameSessionMap.keySet());
    }
}

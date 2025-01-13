package com.shalkevich.sockerserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shalkevich.sockerserver.repository.MessageRepository;
import com.shalkevich.sockerserver.websoket.ChatWebSocketHandler;
import com.shalkevich.sockerserver.websoket.MessageHandler;
import com.shalkevich.sockerserver.websoket.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Конфигурационный класс для настройки WebSocket.
 * Включает поддержку WebSocket и регистрирует обработчики для указанных путей.
 */
@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final MessageRepository messageRepository;
    private final ObjectMapper objectMapper;

    /**
     * Регистрирует обработчики WebSocket для указанных путей.
     *
     * @param registry реестр обработчиков WebSocket.
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebSocketHandler(), "/ws").setAllowedOrigins("*");
    }

    /**
     * Создает и настраивает ChatWebSocketHandler.
     *
     * @return настроенный ChatWebSocketHandler.
     */
    @Bean
    public ChatWebSocketHandler chatWebSocketHandler() {
        return new ChatWebSocketHandler(objectMapper, messageHandler(), sessionManager());
    }

    /**
     * Создает и настраивает MessageHandler.
     *
     * @return настроенный MessageHandler.
     */
    @Bean
    public MessageHandler messageHandler() {
        return new MessageHandler(objectMapper, messageRepository, sessionManager());
    }

    /**
     * Создает и настраивает SessionManager.
     *
     * @return настроенный SessionManager.
     */
    @Bean
    public SessionManager sessionManager() {
        return new SessionManager();
    }
}

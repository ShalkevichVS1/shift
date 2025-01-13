package com.shalkevich.sockerserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс для настройки ObjectMapper.
 * Регистрирует модуль JavaTime для работы с LocalDateTime
 * и настраивает ObjectMapper на игнорирование неизвестных полей
 * при десериализации JSON объектов.
 */
@Configuration
public class JacksonConfig {

    /**
     * Создает и настраивает ObjectMapper.
     *
     * @return настроенный ObjectMapper.
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // Игнорировать неизвестные поля
        return mapper;
    }
}

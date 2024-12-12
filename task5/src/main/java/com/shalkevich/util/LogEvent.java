package com.shalkevich.util;

import lombok.Getter;

/**
 * Enum LogEvent представляет события логирования.
 */
@Getter
public enum LogEvent {
    PRODUCED("произведен"),
    CONSUMED("потреблен"),
    WAITING("ожидание"),
    RESUMED("возобновление работы");

    /**
     * -- GETTER --
     * Возвращает описание события.
     */
    private final String description;

    /**
     * Конструктор enum LogEvent.
     *
     * @param description Описание события.
     */
    LogEvent(String description) {
        this.description = description;
    }

}

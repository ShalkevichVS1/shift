package com.shalkevich.util;

import lombok.Getter;

import java.util.UUID;

/**
 * Resource представляет ресурс, производимый и потребляемый в системе.
 * Каждый ресурс имеет уникальный идентификатор.
 */
@Getter
public class Resource {
    private final String id;

    /**
     * Конструктор класса Resource.
     * Генерирует уникальный идентификатор для ресурса.
     */
    public Resource() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Возвращает строковое представление ресурса.
     *
     * @return строковое представление ресурса.
     */
    @Override
    public String toString() {
        return "Resource{" +
                "id='" + id + '\'' +
                '}';
    }
}

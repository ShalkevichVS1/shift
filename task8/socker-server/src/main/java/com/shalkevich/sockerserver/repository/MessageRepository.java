package com.shalkevich.sockerserver.repository;

import com.shalkevich.sockerserver.model.Message;

import java.util.List;

/**
 * Интерфейс репозитория для работы с сообщениями.
 * Определяет методы для получения последних сообщений и сохранения новых.
 */
public interface MessageRepository {

    /**
     * Возвращает последние сообщения.
     *
     * @param offset смещение для пагинации.
     * @param amount количество сообщений для получения.
     * @return список последних сообщений.
     */
    List<Message> findLastMessages(int offset, int amount);

    /**
     * Сохраняет новое сообщение.
     *
     * @param message сообщение для сохранения.
     * @return сохраненное сообщение.
     */
    Message saveMessage(Message message);
}

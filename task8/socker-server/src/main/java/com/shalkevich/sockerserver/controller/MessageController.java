package com.shalkevich.sockerserver.controller;

import com.shalkevich.sockerserver.model.Message;
import com.shalkevich.sockerserver.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Контроллер для работы с сообщениями.
 * Предоставляет REST API для получения последних сообщений и добавления новых.
 */
@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository messageRepository;

    /**
     * Возвращает последние сообщения.
     *
     * @param offset смещение для пагинации.
     * @param amount количество сообщений для получения.
     * @return список последних сообщений.
     */
    @GetMapping
    public List<Message> getLastMessages(@RequestParam int offset, @RequestParam int amount) {
        return messageRepository.findLastMessages(offset, amount);
    }

    /**
     * Добавляет новое сообщение.
     *
     * @param message сообщение для добавления.
     * @return добавленное сообщение.
     */
    @PostMapping
    public Message addMessage(@RequestBody Message message) {
        message.setDate(LocalDateTime.now());
        return messageRepository.saveMessage(message);
    }
}

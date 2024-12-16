package com.shalkevich.sockerserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Модель сообщения в чате.
 * Содержит информацию об имени отправителя, тексте сообщения, типе и времени отправки.
 */
@Data
public class Message {
    private String name;
    private String text;
    private String type;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime date;
}

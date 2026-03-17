package com.example.backend.data.dto.chat;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatDto {
    private Long id;

    private String chatTitle;

    private LocalDateTime time;

}

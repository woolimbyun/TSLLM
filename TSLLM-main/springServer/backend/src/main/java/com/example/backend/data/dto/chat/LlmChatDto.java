package com.example.backend.data.dto.chat;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class LlmChatDto {
    Long id;
    String response;
    LocalDateTime time;
}
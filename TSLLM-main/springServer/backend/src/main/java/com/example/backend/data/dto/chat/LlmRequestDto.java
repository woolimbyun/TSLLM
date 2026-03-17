package com.example.backend.data.dto.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LlmRequestDto {
    private Long userId;
    private String chat;
}

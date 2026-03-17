package com.example.backend.data.dto.chat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LlmResponseDto {
    private String[] generated_text;
}

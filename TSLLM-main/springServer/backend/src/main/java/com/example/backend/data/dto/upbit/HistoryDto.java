package com.example.backend.data.dto.upbit;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HistoryDto {
    private String market;

    private String side;

    private double price;

    private String state;

    private String created_at;

    private double volume;

    private double executed_volume;

    private double remaining_volume;

}

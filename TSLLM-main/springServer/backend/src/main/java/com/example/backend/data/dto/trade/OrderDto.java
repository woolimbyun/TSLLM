package com.example.backend.data.dto.trade;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter

public class OrderDto {
    Long id;
    String orderCode;
    float price;
    String side;
    int amount;
    float purchaseAmount;
    String state;
    LocalDateTime time;
    String orgNumber;
}
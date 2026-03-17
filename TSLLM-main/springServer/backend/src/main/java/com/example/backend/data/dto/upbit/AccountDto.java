package com.example.backend.data.dto.upbit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
    private String currency;

    private double balance;

    private double avg_buy_price;

    private double now_price;
    private double buyAssets;
    private double nowAssets;

    private double return_rate;


}

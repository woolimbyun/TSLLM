package com.example.backend.data.entity.trading;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "`order`")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auto_trade_id", nullable = false)
    private AutoTrade autoTradeId;

    @Column(name = "order_code", nullable = false, length = 100)
    private String orderCode;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "side", nullable = false,length = 10)
    private String side;

    @Column(name = "amount",nullable = false)
    private int amount;

    @Column(name = "purchase_amount",nullable = false)
    private float purchaseAmount;

    @Column(name = "state",nullable = false,length = 10)
    private String state;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "org_number",nullable = false,length = 100)
    private String orgNumber;
}

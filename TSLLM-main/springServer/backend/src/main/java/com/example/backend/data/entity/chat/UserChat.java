package com.example.backend.data.entity.chat;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_chat")
@Data
public class UserChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Chat chatTitle;

    @Column(name = "request",nullable = false,length = 5000)
    private String request;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;
}

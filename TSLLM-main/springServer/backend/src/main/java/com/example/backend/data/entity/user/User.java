package com.example.backend.data.entity.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, length = 500)
    private String userId;

    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @Column(name = "access_key",nullable = true,length = 1000)
    private String accessKey;

    @Column(name = "secret_key", nullable = true, length = 1000)
    private String secretKey;
}

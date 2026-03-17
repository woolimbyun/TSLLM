package com.example.backend.data.entity.trading;

import com.example.backend.data.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "auto_trade")
@Data
public class AutoTrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Column(name = "target", nullable = false, length = 20)
    private String target;

    @Column(name = "state", nullable = false, length = 20)
    private String state;
}

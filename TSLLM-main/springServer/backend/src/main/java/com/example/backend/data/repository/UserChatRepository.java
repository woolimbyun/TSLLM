package com.example.backend.data.repository;

import com.example.backend.data.entity.chat.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserChatRepository extends JpaRepository<UserChat, Long> {
    List<UserChat> findByChatTitle_UserId_IdAndChatTitle_Id(Long userId, Long chatId);
}

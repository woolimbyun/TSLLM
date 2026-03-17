package com.example.backend.data.repository;

import com.example.backend.data.entity.chat.LlmChat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LlmChatRepository extends JpaRepository<LlmChat,Long> {

    List<LlmChat> findByChatTitle_UserId_IdAndChatTitle_Id(Long userId, Long chatId);

}

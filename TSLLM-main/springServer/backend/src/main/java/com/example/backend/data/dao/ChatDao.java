package com.example.backend.data.dao;

import com.example.backend.data.entity.chat.Chat;

import java.util.List;

public interface ChatDao {
    List<Chat> getChatList(Long userId);

    void deleteChatHistory(Long chatId) throws Exception;

    Chat updateChat(Long chatId, String title) throws Exception;
}

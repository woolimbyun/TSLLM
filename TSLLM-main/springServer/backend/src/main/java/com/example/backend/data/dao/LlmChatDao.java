package com.example.backend.data.dao;

import com.example.backend.data.entity.chat.LlmChat;
import com.example.backend.data.entity.chat.UserChat;

import java.util.List;

public interface LlmChatDao {
    List<LlmChat> getLlmChat(Long userId, Long chatId);

    LlmChat insetChat(Long userId, String response, UserChat userChat) throws Exception;
}

package com.example.backend.data.dao;

import com.example.backend.data.entity.chat.UserChat;
import com.example.backend.data.entity.user.User;

import java.util.List;

public interface UserChatDao {
    List<UserChat> getUserChat(Long userId, Long chatId);

    UserChat insertUserChat(Long userId, String chat) throws Exception;
}

package com.example.backend.data.dao.impl;

import com.example.backend.data.dao.UserChatDao;
import com.example.backend.data.entity.chat.Chat;
import com.example.backend.data.entity.chat.UserChat;
import com.example.backend.data.entity.user.User;
import com.example.backend.data.repository.ChatRepository;
import com.example.backend.data.repository.UserChatRepository;
import com.example.backend.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UserChatDaoImpl implements UserChatDao {
    private final UserChatRepository userChatRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserChatDaoImpl(UserChatRepository userChatRepository, ChatRepository chatRepository, UserRepository userRepository) {
        this.userChatRepository = userChatRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<UserChat> getUserChat(Long userId, Long chatId) {
        return userChatRepository.findByChatTitle_UserId_IdAndChatTitle_Id(userId,chatId);
    }

    @Override
    public UserChat insertUserChat(Long userId, String chat)throws Exception {
        Optional<User> user = userRepository.findById(userId);
        User userData = new User();
        if(user.isPresent()){
            userData = user.get();
        }else{
            new Exception();
        }

        Chat newChat = new Chat();

        if (chat.length() <= 10) {
            newChat.setChatTitle(chat);
        } else {
            newChat.setChatTitle(chat.substring(0, 10));
        }
        newChat.setTime(LocalDateTime.now());
        newChat.setUserId(userData);
        newChat.setTime(LocalDateTime.now());
        newChat.setUserId(userData);

        Chat insertChat = chatRepository.save(newChat);

        UserChat insertUserChat = new UserChat();
        insertUserChat.setChatTitle(insertChat);
        insertUserChat.setTime(LocalDateTime.now());
        insertUserChat.setRequest(chat);

        return userChatRepository.save(insertUserChat);
    }
}

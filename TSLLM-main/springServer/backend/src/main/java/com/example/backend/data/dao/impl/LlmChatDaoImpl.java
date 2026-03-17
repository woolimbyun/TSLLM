package com.example.backend.data.dao.impl;

import com.example.backend.data.dao.LlmChatDao;
import com.example.backend.data.entity.chat.Chat;
import com.example.backend.data.entity.chat.LlmChat;
import com.example.backend.data.entity.chat.UserChat;
import com.example.backend.data.entity.user.User;
import com.example.backend.data.repository.ChatRepository;
import com.example.backend.data.repository.LlmChatRepository;
import com.example.backend.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class LlmChatDaoImpl implements LlmChatDao {
    private final LlmChatRepository llmChatRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Autowired
    public LlmChatDaoImpl(LlmChatRepository llmChatRepository, ChatRepository chatRepository, UserRepository userRepository) {
        this.llmChatRepository = llmChatRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<LlmChat> getLlmChat(Long userId, Long chatId) {
        return llmChatRepository.findByChatTitle_UserId_IdAndChatTitle_Id(userId, chatId);
    }

    @Override
    public LlmChat insetChat(Long userId, String response, UserChat userChat) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        User userData = new User();
        if(user.isPresent()){
            userData = user.get();
        }else{
            new Exception();
        }

        LlmChat llmChat = new LlmChat();

        llmChat.setChatTitle(userChat.getChatTitle());
        llmChat.setTime(LocalDateTime.now());
        llmChat.setResponse(response);

        return llmChatRepository.save(llmChat);
    }
}

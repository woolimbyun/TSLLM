package com.example.backend.data.dao.impl;

import com.example.backend.data.dao.ChatDao;
import com.example.backend.data.entity.chat.Chat;
import com.example.backend.data.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ChatDaoImpl implements ChatDao {
    private final ChatRepository chatRepository;

    @Autowired
    public ChatDaoImpl(ChatRepository chatRepository) {

        this.chatRepository = chatRepository;
    }

    @Override
    public List<Chat> getChatList(Long userId) {
        return chatRepository.findByUserId_Id(userId);
    }

    @Override
    public void deleteChatHistory(Long chatId) throws Exception {
        Optional<Chat> selectedChat = chatRepository.findById(chatId);
        System.out.println("selectedChat = " + selectedChat);
        if(selectedChat.isPresent()){
            Chat chat = selectedChat.get();
            chatRepository.delete(chat);
        }else{
            throw new Exception();
        }
    }

    @Override
    public Chat updateChat(Long chatId, String title) throws Exception {
        Optional<Chat> selectedChat = chatRepository.findById(chatId);

        Chat updatedChat ;
        if(selectedChat.isPresent()){
            Chat chat = selectedChat.get();

            chat.setChatTitle(title);
            chat.setTime(LocalDateTime.now());

            updatedChat = chatRepository.save(chat);
        }else{
            throw new Exception();
        }
        return updatedChat;
    }
}

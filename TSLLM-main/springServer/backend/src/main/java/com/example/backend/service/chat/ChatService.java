package com.example.backend.service.chat;

import com.example.backend.data.dao.ChatDao;
import com.example.backend.data.dao.LlmChatDao;
import com.example.backend.data.dao.UserChatDao;
import com.example.backend.data.dto.chat.ChatDto;
import com.example.backend.data.dto.chat.LlmChatDto;
import com.example.backend.data.dto.chat.UserChatDto;
import com.example.backend.data.entity.chat.Chat;
import com.example.backend.data.entity.chat.LlmChat;
import com.example.backend.data.entity.chat.UserChat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private final UserChatDao userChatDao;
    private final ChatDao chatDao;
    private final ModelMapper modelMapper;
    private final LlmChatDao llmChatDao;
    private final ObjectMapper objectMapper;
    private final String HUGGINGFACE_API_KEY;
    private final String HUGGINGFACE_URL;

    @Autowired
    public ChatService(UserChatDao userChatDao, ChatDao chatDao, ModelMapper modelMapper, LlmChatDao llmChatDao, ObjectMapper objectMapper, @Value("${spring.HUGGINGFACE_API_KEY.key}") String HUGGINGFACE_API_KEY, @Value("${spring.HUGGINGFACE_URL.url}") String HUGGINGFACE_URL) {
        this.userChatDao = userChatDao;
        this.chatDao = chatDao;
        this.modelMapper = modelMapper;
        this.llmChatDao = llmChatDao;
        this.objectMapper = objectMapper;
        this.HUGGINGFACE_API_KEY = HUGGINGFACE_API_KEY;
        this.HUGGINGFACE_URL = HUGGINGFACE_URL;
    }

    public List<ChatDto> getChatList(Long userId){
        List<Chat> chats = chatDao.getChatList(userId);
        return chats.stream()
                .map(chat -> modelMapper.map(chat, ChatDto.class))
                .collect(Collectors.toList());
    }

    public void deleteChat(Long chatId) throws Exception{
        chatDao.deleteChatHistory(chatId);
    }

    public List<UserChatDto> getUserChat (Long userId, Long chatId){
        List<UserChat> chats = userChatDao.getUserChat(userId, chatId);

        return chats.stream()
                .map(userChat -> modelMapper.map(userChat, UserChatDto.class))
                .collect(Collectors.toList());
    }

    public List<LlmChatDto> getLlmChat(Long userId, Long chatId) {
        List<LlmChat> chats = llmChatDao.getLlmChat(userId, chatId);

        return chats.stream()
                .map(llmChat -> modelMapper.map(llmChat, LlmChatDto.class))
                .collect(Collectors.toList());
    }

    public ChatDto updateChatTitle(Long chatId, String title) throws Exception {
        Chat data = chatDao.updateChat(chatId,title);

        ChatDto updatedChat = new ChatDto();

        updatedChat.setId(data.getId());
        updatedChat.setTime(data.getTime());
        updatedChat.setChatTitle(data.getChatTitle());

        return updatedChat;
    }

    public LlmChatDto requestLlm(Long userId, String input) throws Exception {
        UserChat userChat = userChatDao.insertUserChat(userId, input);

        // 요청 본문을 JSON 형식으로 만듭니다.
        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("inputs", input);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("top_k", 50);
        parameters.put("top_p", 0.95);
        parameters.put("temperature", 0.7);
        parameters.put("early_stopping", true); 
        // parameters.put("max_new_tokens", 1000);
        parameters.put("do_sample", true);
        parameters.put("return_full_text",true);
        requestBodyMap.put("parameters", parameters);

        String requestBody = objectMapper.writeValueAsString(requestBodyMap);

        URI uri = UriComponentsBuilder
                .fromUriString(HUGGINGFACE_URL)
                .encode()
                .build()
                .toUri();

        RequestEntity<String> requestEntity = RequestEntity
                .post(uri)
                .header("Authorization", "Bearer "+HUGGINGFACE_API_KEY)
                .header("Content-Type", "application/json")  // 요청 본문이 JSON 형식임을 명시합니다.
                .body(requestBody);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        String responseBody = responseEntity.getBody();

        JsonNode jsonNode = objectMapper.readTree(responseBody);

        String generatedText = jsonNode.get(0).get("generated_text").asText();

        LlmChat llmChat = llmChatDao.insetChat(userId, generatedText, userChat);

        LlmChatDto llmChatDto = new LlmChatDto();

        llmChatDto.setId(llmChat.getId());
        llmChatDto.setResponse(llmChat.getResponse());
        llmChatDto.setTime(llmChat.getTime());

        return llmChatDto;
    }

}

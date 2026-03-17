package com.example.backend.controller.chat;

import com.example.backend.data.dto.chat.*;
import com.example.backend.data.entity.chat.LlmChat;
import com.example.backend.data.entity.chat.UserChat;
import com.example.backend.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/llm")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/history")
    public ResponseEntity<List<ChatDto>> getChatList(@RequestParam Long userId) {
        List<ChatDto> data = chatService.getChatList(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }

    @DeleteMapping("/history")
    public ResponseEntity deleteChatHistory(@RequestBody ChatRequestDto chatRequestDto) throws Exception {
        chatService.deleteChat(chatRequestDto.getChatId()); // 채팅 삭제 메서드를 호출하도록 수정

        return ResponseEntity
                .status(HttpStatus.OK)
                .build(); // build() 메서드를 사용하여 ResponseEntity 생성
    }

    @GetMapping("/user-chat")
    public ResponseEntity<List<UserChatDto>> getUserChatList(@RequestParam Long userId, Long chatId) {
        List<UserChatDto> data = chatService.getUserChat(userId, chatId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }

    @GetMapping("/llm-chat")
    public ResponseEntity<List<LlmChatDto>> getLlmChatList(@RequestParam Long userId, Long chatId) {
        List<LlmChatDto> data = chatService.getLlmChat(userId, chatId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }
    @PutMapping("/history")
    public ResponseEntity<ChatDto> updateChatTitle(@RequestBody ChatUpdateDto chatUpdateDto) throws Exception {
        ChatDto chatDto = chatService.updateChatTitle(chatUpdateDto.getId(), chatUpdateDto.getChatTitle());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(chatDto);
    }

    @PostMapping("/request")
    public ResponseEntity<LlmChatDto> requestLlm(@RequestBody LlmRequestDto llmRequestDto)throws Exception{
        LlmChatDto data = chatService.requestLlm(llmRequestDto.getUserId(),llmRequestDto.getChat());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }
    //테스트용 가짜 api
//    @PostMapping("/request")
//    @ResponseBody
//    public String requestLlm(@RequestBody LlmRequestDto llmRequestDto) {
//        // 현재 시간을 ISO 형식으로 포맷팅
//        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
//
//        // 원하는 JSON 형식으로 수동으로 문자열을 작성
//        String jsonResponse = "{\"id\": 0, \"response\": \"string\", \"time\": \"" + currentTime + "\"}";
//
//        return jsonResponse;
//    }

}

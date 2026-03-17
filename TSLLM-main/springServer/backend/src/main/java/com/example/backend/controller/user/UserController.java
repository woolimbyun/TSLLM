package com.example.backend.controller.user;

import com.example.backend.data.dto.user.ApiKeyDto;
import com.example.backend.data.dto.user.JoinDto;
import com.example.backend.data.dto.user.JoinResponseDto;
import com.example.backend.security.jwt.JwtUtil;
import com.example.backend.service.user.JoinService;
import com.example.backend.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final JoinService joinService;
    private final UserService userService;

    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(JoinService joinService, UserService userService, JwtUtil jwtUtil) {
        this.joinService = joinService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<JoinResponseDto> signupProcess(@RequestBody JoinDto joinDto) {
        JoinResponseDto responseData = joinService.JoinProcess(joinDto);
        if(responseData == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build(); // ResponseEntity를 직접 생성하여 반환
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseData);
    }


    @PutMapping("/update-apikey")
    public ResponseEntity<ApiKeyDto> updateApiKeyProcess(@RequestBody ApiKeyDto apiKeyDto) throws Exception {
        ApiKeyDto responseData = joinService.updateApiKey(apiKeyDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseData);
    }
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        return userService.reissue(request, response);
    }
}

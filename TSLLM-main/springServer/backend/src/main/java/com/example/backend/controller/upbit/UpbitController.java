package com.example.backend.controller.upbit;

import com.example.backend.data.dto.upbit.AccountDto;
import com.example.backend.data.dto.upbit.HistoryDto;
import com.example.backend.service.upbit.UpbitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/upbit")
public class UpbitController {
    private final UpbitService upbitService;

    public UpbitController(UpbitService upbitService) {
        this.upbitService = upbitService;
    }

    @GetMapping("/account")
    public ResponseEntity<List<AccountDto>> accountController(@RequestParam Long userId){
        List<AccountDto> account = upbitService.getAccount(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(account);
    }

    @GetMapping("/history")
    public ResponseEntity<List<HistoryDto>> historyController(@RequestParam Long userId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        List<HistoryDto> history = upbitService.getHistory(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(history);
    }

}

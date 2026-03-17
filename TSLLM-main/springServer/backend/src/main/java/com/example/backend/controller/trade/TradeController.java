package com.example.backend.controller.trade;

import com.example.backend.data.dto.trade.AutoTradeDto;
import com.example.backend.data.dto.trade.OrderDto;
import com.example.backend.service.trade.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trade")
public class TradeController {
    private final TradeService tradeService;
    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping("/open-trade")
    public ResponseEntity<List<AutoTradeDto>> searchOpenTrade(@RequestParam Long userId){
        List<AutoTradeDto> data = tradeService.findAutoTrade(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }
    @GetMapping("/order")
    public ResponseEntity<List<OrderDto>> searchOder(@RequestParam Long userId){
        List<OrderDto> data = tradeService.findOrder(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }
    @GetMapping("/history")
    public ResponseEntity<List<OrderDto>> searchHistory(@RequestParam Long userId){
        List<OrderDto> data = tradeService.findCompleteOrder(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }
}

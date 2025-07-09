package com.example.CryptoInterview.controller;

import com.example.CryptoInterview.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping("/buy")
    public String buy(@RequestBody Map<String, Object> payload) {
        return tradeService.buy(1, payload);
    }

    @PostMapping("/sell")
    public String sell(@RequestBody Map<String, Object> payload) {
        return tradeService.sell(1, payload);
    }
}

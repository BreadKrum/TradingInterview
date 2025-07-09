package com.example.CryptoInterview.controller;


import com.example.CryptoInterview.websocket.KrakenWebSocketClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PriceController {

    @GetMapping("/api/crypto/prices")
    public Map<String, Double> getPrices() {
        return KrakenWebSocketClient.getLatestPrices();
    }
}

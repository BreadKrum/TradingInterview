package com.example.CryptoInterview.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class KrakenPriceService {

    private final ConcurrentHashMap<String, Double> prices = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        // Fake prices for development.
        prices.put("BTC/USD", 62783.12);
        prices.put("ETH/USD", 3456.77);
        prices.put("ADA/USD", 0.382);
    }

    public Map<String, Double> getPrices() {
        return prices;
    }
}

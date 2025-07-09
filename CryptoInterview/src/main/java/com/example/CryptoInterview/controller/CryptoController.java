package com.example.CryptoInterview.controller;
import com.example.CryptoInterview.service.KrakenPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    @Autowired
    private KrakenPriceService priceService;

    @GetMapping("/prices")
    public Map<String, Double> getPrices(){
        return priceService.getPrices();
    }
}

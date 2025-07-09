package com.example.CryptoInterview.controller;

import com.example.CryptoInterview.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public Map<String, Object> getAccount(){
        return accountService.getAccountDetails(1);
    }

    @PostMapping("/reset")
    public void resetAccount(){
        accountService.resetAccount(1);
    }
}

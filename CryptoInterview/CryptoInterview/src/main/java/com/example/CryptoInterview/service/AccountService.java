package com.example.CryptoInterview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getAccountDetails(int userId){
        Map<String, Object> result = new HashMap<>();
        Double balance = jdbcTemplate.queryForObject(
                "select balance from users where id = ?", new Object[]{userId}, Double.class);
        List<Map<String, Object>> holdings = jdbcTemplate.queryForList(
                "select crypto_symbol, quantity from holdings where user_id = ?", userId);
        result.put("balance", balance);
        result.put("holdings", holdings);
        return result;
    }

    public void resetAccount(int userId){
        jdbcTemplate.update("update users set balance = 10000.00 where id = ?", userId);
        jdbcTemplate.update("delete from holdings where user_id = ?", userId);
        jdbcTemplate.update("delete from transactions where user_id = ?", userId);
    }
}

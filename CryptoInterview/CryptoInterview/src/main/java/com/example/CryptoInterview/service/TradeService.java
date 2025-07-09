package com.example.CryptoInterview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TradeService {

    @Autowired
    private JdbcTemplate jdbc;

    public String buy(int userId, Map<String, Object> data) {
        String symbol = (String) data.get("symbol");
        double quantity = Double.parseDouble(data.get("quantity").toString());
        double price = Double.parseDouble(data.get("price").toString());
        double cost = quantity * price;

        Double balance = jdbc.queryForObject("SELECT balance FROM users WHERE id = ?", new Object[]{userId}, Double.class);
        if (balance < cost) return "Not enough balance.";

        jdbc.update("UPDATE users SET balance = balance - ? WHERE id = ?", cost, userId);

        int updated = jdbc.update("UPDATE holdings SET quantity = quantity + ? WHERE user_id = ? AND crypto_symbol = ?",
                quantity, userId, symbol);
        if (updated == 0) {
            jdbc.update("INSERT INTO holdings(user_id, crypto_symbol, quantity) VALUES (?, ?, ?)", userId, symbol, quantity);
        }

        jdbc.update("INSERT INTO transactions(user_id, crypto_symbol, type, quantity, price, total) VALUES (?, ?, 'BUY', ?, ?, ?)",
                userId, symbol, quantity, price, cost);

        return "Buy successful.";
    }

    public String sell(int userId, Map<String, Object> data) {
        String symbol = (String) data.get("symbol");
        double quantity = Double.parseDouble(data.get("quantity").toString());
        double price = Double.parseDouble(data.get("price").toString());
        double total = quantity * price;

        Double held = jdbc.queryForObject("SELECT quantity FROM holdings WHERE user_id = ? AND crypto_symbol = ?",
                new Object[]{userId, symbol}, Double.class);

        if (held == null || held < quantity) return "Not enough holdings.";

        jdbc.update("UPDATE users SET balance = balance + ? WHERE id = ?", total, userId);
        jdbc.update("UPDATE holdings SET quantity = quantity - ? WHERE user_id = ? AND crypto_symbol = ?",
                quantity, userId, symbol);
        jdbc.update("INSERT INTO transactions(user_id, crypto_symbol, type, quantity, price, total) VALUES (?, ?, 'SELL', ?, ?, ?)",
                userId, symbol, quantity, price, total);

        return "Sell successful.";
    }
}

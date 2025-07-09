//package com.example.CryptoInterview.dao;
//
//import com.example.CryptoInterview.model.Transaction;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//@Repository
//public class TransactionDaoImpl implements TransactionDao {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Override
//    public void saveTransaction(Transaction transaction) {
//        jdbcTemplate.update("INSERT INTO transactions(user_id, type, symbol, quantity, price, timestamp) VALUES (?, ?, ?, ?, ?, ?)",
//                transaction.getUserId(), transaction.getType(), transaction.getSymbol(),
//                transaction.getQuantity(), transaction.getPrice(), Timestamp.valueOf(transaction.getTimestamp()));
//    }
//
//    @Override
//    public List<Transaction> getTransactionsByUserId(int userId) {
//        return jdbcTemplate.query("SELECT * FROM transactions WHERE user_id = ? ORDER BY timestamp DESC",
//                new Object[]{userId}, (rs, rowNum) -> {
//                    Transaction t = new Transaction();
//                    t.setId(rs.getInt("id"));
//                    t.setUserId(rs.getInt("user_id"));
//                    t.setType(rs.getString("type"));
//                    t.setSymbol(rs.getString("symbol"));
//                    t.setQuantity(rs.getDouble("quantity"));
//                    t.setPrice(rs.getDouble("price"));
//                    t.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
//                    return t;
//                });
//    }
//}
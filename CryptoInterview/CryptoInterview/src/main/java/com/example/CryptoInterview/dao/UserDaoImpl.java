//package com.example.CryptoInterview.dao;
//
//import com.example.CryptoInterview.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class UserDaoImpl implements UserDao {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Override
//    public User getUserById(int id) {
//        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new Object[]{id}, (rs, rowNum) -> {
//            User user = new User();
//            user.setId(rs.getInt("id"));
//            user.setBalance(rs.getDouble("balance"));
//            return user;
//        });
//    }
//
//    @Override
//    public void updateBalance(int userId, double newBalance) {
//        jdbcTemplate.update("UPDATE users SET balance = ? WHERE id = ?", newBalance, userId);
//    }
//
//    @Override
//    public void resetBalance(int userId, double startingBalance) {
//        jdbcTemplate.update("UPDATE users SET balance = ? WHERE id = ?", startingBalance, userId);
//    }
//}
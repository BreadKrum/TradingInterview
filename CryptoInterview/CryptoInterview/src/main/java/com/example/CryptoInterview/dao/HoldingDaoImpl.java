//package com.example.CryptoInterview.dao;
//
//import com.example.CryptoInterview.dao.HoldingDao;
//import com.example.CryptoInterview.model.Holding;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class HoldingDaoImpl implements HoldingDao {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Override
//    public List<Holding> getHoldingsByUserId(int userId) {
//        return jdbcTemplate.query("SELECT * FROM holdings WHERE user_id = ?", new Object[]{userId}, (rs, rowNum) -> {
//            Holding h = new Holding();
//            h.setId(rs.getInt("id"));
//            h.setUserId(rs.getInt("user_id"));
//            h.setSymbol(rs.getString("symbol"));
//            h.setQuantity(rs.getDouble("quantity"));
//            return h;
//        });
//    }
//
//    @Override
//    public Holding getHoldingByUserAndSymbol(int userId, String symbol) {
//        List<Holding> results = jdbcTemplate.query("SELECT * FROM holdings WHERE user_id = ? AND symbol = ?",
//                new Object[]{userId, symbol}, (rs, rowNum) -> {
//                    Holding h = new Holding();
//                    h.setId(rs.getInt("id"));
//                    h.setUserId(rs.getInt("user_id"));
//                    h.setSymbol(rs.getString("symbol"));
//                    h.setQuantity(rs.getDouble("quantity"));
//                    return h;
//                });
//
//        return results.isEmpty() ? null : results.get(0);
//    }
//
//    @Override
//    public void upsertHolding(Holding holding) {
//        Holding existing = getHoldingByUserAndSymbol(holding.getUserId(), holding.getSymbol());
//        if (existing == null) {
//            jdbcTemplate.update("INSERT INTO holdings(user_id, symbol, quantity) VALUES (?, ?, ?)",
//                    holding.getUserId(), holding.getSymbol(), holding.getQuantity());
//        } else {
//            jdbcTemplate.update("UPDATE holdings SET quantity = ? WHERE user_id = ? AND symbol = ?",
//                    holding.getQuantity(), holding.getUserId(), holding.getSymbol());
//        }
//    }
//
//    @Override
//    public void deleteHolding(int userId, String symbol) {
//        jdbcTemplate.update("DELETE FROM holdings WHERE user_id = ? AND symbol = ?", userId, symbol);
//    }
//}

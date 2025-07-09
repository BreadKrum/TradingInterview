package com.example.CryptoInterview.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class KrakenWebSocketClient extends WebSocketClient {

    private static final String WS_URI = "wss://ws.kraken.com";
    private static final String[] SYMBOLS = {
            "BTC/USD", "ETH/USD", "ADA/USD", "SOL/USD", "XRP/USD",
            "DOT/USD", "DOGE/USD", "LTC/USD", "LINK/USD", "AVAX/USD",
            "MATIC/USD", "BCH/USD", "TRX/USD", "ATOM/USD", "ETC/USD",
            "XLM/USD", "NEAR/USD", "FIL/USD", "EOS/USD", "XTZ/USD"
    };

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Map<String, Double> prices = new ConcurrentHashMap<>();

    public KrakenWebSocketClient() throws Exception {
        super(new URI(WS_URI));
    }

    @PostConstruct
    public void init() {
        this.connect();
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        System.out.println("[KrakenWS] Connected");

        try {
            // Subscribe to ticker channel
            String payload = objectMapper.writeValueAsString(Map.of(
                    "event", "subscribe",
                    "pair", SYMBOLS,
                    "subscription", Map.of("name", "ticker")
            ));
            send(payload);
        } catch (Exception e) {
            System.err.println("[KrakenWS] Subscription error: " + e.getMessage());
        }
    }

    @Override
    public void onMessage(String message) {
        try {
            if (message.startsWith("[")) {
                JsonNode root = objectMapper.readTree(message);
                String pair = root.get(root.size() - 1).asText();
                double price = root.get(1).get("c").get(0).asDouble(); // last trade closed price
                prices.put(pair, price);
            }
        } catch (Exception e) {
            System.err.println("[KrakenWS] Message parse error: " + e.getMessage());
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("[KrakenWS] Closed: " + reason + " - reconnecting...");
        try {
            Thread.sleep(5000);
            this.reconnect();
        } catch (Exception ignored) {}
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("[KrakenWS] Error: " + ex.getMessage());
    }

    public static Map<String, Double> getLatestPrices() {
        return prices;
    }
}

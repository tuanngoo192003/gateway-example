package com.example.helper;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class TokenHelper {
    
    public String extractToken(ServerHttpRequest request) {
        String token = "";
        String rawToken = request.getHeaders().getFirst("Authorization");
        if (rawToken != null && rawToken.startsWith("Bearer ")) {
            token = rawToken.substring(7);
        }
        return token;
    }

    public Boolean isValid(String token) {
        return token != null && !token.trim().isEmpty();
    }
}

package com.example.client;

import org.springframework.stereotype.Service;

@Service
public class AuthClient {
    
    public Boolean hasPermission(String token, String uri, String method) {
        return true;
    }
}

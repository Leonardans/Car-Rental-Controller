package org.rent_master.car_rental_reservation_system.auth.authservice;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistService {
    // Future enhancement: Implement Redis for token blacklisting and enable horizontal scaling
    // for improved performance and scalability.
    private final Set<String> blacklistedTokens = ConcurrentHashMap.newKeySet();

    public void invalidateToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        System.out.println(blacklistedTokens);
        blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return blacklistedTokens.contains(token);
    }

}


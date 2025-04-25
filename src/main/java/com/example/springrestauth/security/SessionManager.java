package com.example.springrestauth.security;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class SessionManager

{

    // this is inmemmeory map 
    private final Map<String, String> tokenToUser = new ConcurrentHashMap<>();
    private final Map<String, Long> tokenExpiry = new ConcurrentHashMap<>(); // Stores expiration time for tokens

    private static final long EXPIRATION_TIME_MS = TimeUnit.MINUTES.toMillis(30);  // 30 minutes expiration

    // creting session and its expire time 
    public String createSession(String username)
    {
        String token = UUID.randomUUID().toString();
        tokenToUser.put(token, username);
        tokenExpiry.put(token, System.currentTimeMillis() + EXPIRATION_TIME_MS); // Set expiration time
        return token;
    }

    // retrieve  all data
    public String getUserForToken(String token)
    {
        System.out.println("Looking up token: " + token); 
        if (isTokenExpired(token)) 
        {
            System.out.println("Token expired: " + token); 
            invalidateToken(token);
            return null;
        }
        String user = tokenToUser.get(token);
        System.out.println("User found for token: " + user); 
        return user;
    }
    // token is exxpiredd or not
    private boolean isTokenExpired(String token) 
    {
        Long expiryTime = tokenExpiry.get(token);
        if (expiryTime == null) {
            return true;
        }
        return System.currentTimeMillis() > expiryTime;  // Checking if the current time exceed expirations times
    }

    // invalid
    public void invalidateToken(String token) 
    {
        tokenToUser.remove(token);
        tokenExpiry.remove(token);  // remove  experation timee
    }
}

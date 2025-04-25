package com.example.springrestauth.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.springrestauth.entity.User;

@Repository
public class InMemoryUserRepository 
{
    
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public boolean addUser(User user) 
    {
        if (users.containsKey(user.getUsername())) 
        {
            return false; // user alreadyy exist
        }
        users.put(user.getUsername(), user);
        return true;
    }

    public User getUser(String username) 
    {
        return users.get(username);
    }
}

package com.example.springrestauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springrestauth.entity.User;
import com.example.springrestauth.repository.InMemoryUserRepository;
import com.example.springrestauth.security.SessionManager;

@Service
public class AuthService 
{
    @Autowired
    private InMemoryUserRepository userRepo;

    @Autowired
    private SessionManager sessionManager;

    public boolean register(User user) 
    {
        return userRepo.addUser(user);
    }

    public String login(User user) 
    {
        User stored = userRepo.getUser(user.getUsername());
        if (stored != null && stored.getPassword().equals(user.getPassword())) {
            return sessionManager.createSession(user.getUsername());
        }
        return null;
    }
}

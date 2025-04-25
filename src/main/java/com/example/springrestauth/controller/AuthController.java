package com.example.springrestauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springrestauth.entity.User;
import com.example.springrestauth.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController 
{

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) 
    {
        boolean success = authService.register(user);
        return success
                ? ResponseEntity.ok("User registered")
                : ResponseEntity.badRequest().body("User already exists");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user)
    {
        String token = authService.login(user);
        return token != null
                ? ResponseEntity.ok(token)
                : ResponseEntity.status(401).body("Invalid credentials");
    }
}

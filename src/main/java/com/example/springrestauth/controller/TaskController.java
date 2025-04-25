package com.example.springrestauth.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrestauth.entity.Task;
import com.example.springrestauth.security.SessionManager;
import com.example.springrestauth.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController 
{

    @Autowired
    private TaskService taskService;

    @Autowired
    private SessionManager sessionManager;

    private String getUser(String token) 
    {
        return sessionManager.getUserForToken(token);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTask(@RequestHeader("X-Session-Token") String token, @RequestBody Task task) 
    {
        String username = getUser(token);
        if (username == null)
        {
            return ResponseEntity.status(403).body("Forbidden: Invalid or expired token");
        }
        task.setUsername(username); // automatically attached with the userss
        taskService.addTask(task);
        return ResponseEntity.ok("Task added");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getTasks(@RequestHeader("X-Session-Token") String token)
    {
        String username = getUser(token);
        if (username == null) 
        {
            return ResponseEntity.status(403).body("Forbidden: Invalid or expired token");
        }
        List<Task> tasks = taskService.getTasks(username);
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@RequestHeader("X-Session-Token") String token, @PathVariable UUID id)
    {
        String username = getUser(token);
        if (username == null) {
            return ResponseEntity.status(403).body("Forbidden: Invalid or expired token");
        }
        boolean removed = taskService.deleteTask(username, id);
        return removed ? ResponseEntity.ok("Task deleted") : ResponseEntity.badRequest().body("Task not found");
    }
}
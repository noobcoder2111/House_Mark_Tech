package com.example.springrestauth.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springrestauth.entity.Task;
import com.example.springrestauth.repository.InMemoryTaskRepository;

@Service
public class TaskService
{
    @Autowired
    private InMemoryTaskRepository taskRepo;

    public void addTask(Task task)
    {
        taskRepo.addTask(task);
    }

    public List<Task> getTasks(String username) 
    {
        return taskRepo.getTasks(username);
    }

    public boolean deleteTask(String username, UUID taskId)
    {
        return taskRepo.deleteTask(username, taskId);
    }
}
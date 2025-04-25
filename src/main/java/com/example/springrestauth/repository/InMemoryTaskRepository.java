package com.example.springrestauth.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.example.springrestauth.entity.Task;

@Repository
public class InMemoryTaskRepository 
{
    private final Map<String, List<Task>> userTasks = new ConcurrentHashMap<>();

    public void addTask(Task task) 
    {
        userTasks.computeIfAbsent(task.getUsername(), k -> new ArrayList<>()).add(task);
    }

    public List<Task> getTasks(String username) 
    {
        return userTasks.getOrDefault(username, new ArrayList<>());
    }

    public boolean deleteTask(String username, UUID taskId) 
    {
        List<Task> tasks = userTasks.get(username);
        if (tasks != null) {
            return tasks.removeIf(task -> task.getId().equals(taskId));
        }
        return false;
    }
}
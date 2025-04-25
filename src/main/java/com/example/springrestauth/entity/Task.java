package com.example.springrestauth.entity;

import java.util.UUID;

public class Task 
{
    private UUID id;
    private String title;
    private String description;
    private String username;

    public Task() 
    {
        this.id = UUID.randomUUID();
    }

    public UUID getId() 
    {
        return id;
    }

    public void setId(UUID id) 
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getUsername() 
    {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }
}
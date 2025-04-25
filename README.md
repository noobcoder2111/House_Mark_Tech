# Task Management REST API

A REST API built with Java and Spring Boot for user registration, login, and task management.

## Features

- User registration and login
- Add, view, and delete user-specific tasks
- Custom token-based authentication
- In-memory data storage

## Setup

1. Clone the repository: `git clone <your-repo-link>`
2. Navigate to the project directory: `cd <project-name>`
3. Run the application: `mvn spring-boot:run`
4. Access the API at `http://localhost:8080`

## API Endpoints

- **POST /auth/register**: Register a user
  - Request: `{"username": "afsar", "password": "ak12345"}`
  - Response: `User registered`
- **POST /auth/login**: Login and get a token
  - Request: `{"username": "afsar", "password": "ak12345"}`
  - Response: `<token>`
- **POST /tasks/add**: Add a task (requires `X-Session-Token` header)
  - Request: `{"title": "Finish project", "description": "Complete API"}`
  - Response: `Task added`
- **GET /tasks/all**: View all tasks (requires `X-Session-Token` header)
  - Response: `[ { "id": "...", "title": "...", "description": "...", "username": "afsar" } ]`
- **DELETE /tasks/delete/{id}**: Delete a task (requires `X-Session-Token` header)
  - Response: `Task deleted`

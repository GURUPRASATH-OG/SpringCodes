# 🚀 Spring Boot Backend Projects

This repository contains two Java Spring Boot backend projects:

1. **🗓 Task Tracker API** - A RESTful API to manage tasks.
2. **🔐 JWT Role-Based Authentication API** - Secure user login system with JWT and role-based access control.

---

## 🗂 Project 1: Task Tracker API (Spring Boot)

### 📌 Description
A simple CRUD-based RESTful web service for managing tasks. Built using Spring Boot following best practices such as:
- Layered architecture (Controller, Service, Repository)
- DTO usage
- Entity relationships
- Rest Apis
- Global Exception Handler
- MVC design pattern.

### 🛠 Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA
- H2 / MySQL
- Lombok

### 🔁 Endpoints

| Method | Endpoint         | Description         |
|--------|------------------|---------------------|
| GET    | `/tasks`         | Get all tasks       |
| GET    | `/tasks/{id}`    | Get task by ID      |
| POST   | `/tasks/`        | Create a task       |
| PUT    | `/tasks/{id}`    | Update a task       |
| DELETE | `/tasks/{id}`    | Delete a task       |
| GET    | `/task-list/`     | Get all tasklist    |
| POST   |`/task-list/`      | Create a TaskList   |
| PUT    |`/task-list/{taskListId}`           | Update Tasklist     |

### 🔗 Swagger UI
Access API docs at:  
`http://localhost:8080/`

---

## 🔐 Project 2: JWT Role-Based Auth API

### 📌 Description
A secure backend login/register system with:
- JWT token-based authentication
- Role-based access (USER, ADMIN)
- Spring Security integration

### 🛠 Technologies Used
- Java 17+
- Spring Boot
- Spring Security
- JWT (io.jsonwebtoken)
- H2 / MySQL
- Lombok

### 📦 API Endpoints

| Method | Endpoint        | Description                |
|--------|-----------------|----------------------------|
| POST   | `/auth/register`| Register a new user        |
| POST   | `/auth/login`   | Authenticate and get token |
| GET    | `/admin/data`   | Admin-only endpoint        |
| GET    | `/user/data`    | User-only endpoint         |

### 🔐 Roles
- `ROLE_USER` – can access basic features
- `ROLE_ADMIN` – can manage users and access all endpoints

---

## 📦 Running the Project

```bash
# Clone repo
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name

# Build & run
./mvnw spring-boot:run


# 🚀 Spring Boot Backend Projects

This repository contains two Java Spring Boot backend projects:

1. **🗓 Task Tracker API** - A RESTful API to manage tasks.
2. **🔐 JWT Role-Based Authentication API** - Secure user login system with JWT and role-based access control.
3. **🗨️Blog Post Platform API** - RESTful API to create, edit maintain blogs Posts.
---

## 🗂 Project 1: Task Tracker API (Spring Boot)

### 📌 Description
A simple CRUD-based RESTful web service for managing tasks. Built using Spring Boot following best practices such as:
- Layered architecture (Controller, Service, Repository,Mappers,Global Exception Handler)
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
- 
###RelationShip
We have 2 entity one is tasklist and the other one is tasks so. One task list can have many task associated with it
there by forming @OneToMany with Task and Task have ManyToOne RelationShip with TaskList


###Diagrams
![System Diagrams](pics/Spring%20Boot%20Task%20Tracker%20App.png)
### 🔁 Endpoints

| Method | Endpoint         | Description         |
|--------|------------------|---------------------|
| GET    | `/task-list/{task_list_id}/task`         | Get all tasks       |
| GET    | `/task-list/{task_list_id}/task`    | Get task by ID      |
| POST   | `/task-list/{task_list_id}/task`        | Create a task       |
| PUT    | `/task-list/{task_list_id}/task`    | Update a task       |
| DELETE | `/task-list/{task_list_id}/task`    | Delete a task       |
| GET    | `/task-list/`     | Get all tasklist    |
| POST   |`/task-list/`      | Create a TaskList   |
| PUT    |`/task-list/{taskListId}` | Update Tasklist     |
|DELETE |`task-list/{task-list-id}`|Delete task list|

### 🔗 Acess Endpoints.
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
| GET    | `/api/protext-data`    | User-only endpoint         |

### 🔐 Roles
- `ROLE_USER` – can access his content
- `ROLE_ADMIN` – can access admin specfic content

---
##📄 Project 3: Blog PlatForm Rest Api (Role Based Access Using JWT)
###📝Blog Platform

###📌Description
A Secure BlogPost System which user can manage blog post Built Using Best Standards.
-Exposed RESTFUL API, Controller, Global error Handler, Dtos, Mappers.
-Utitlizied Lombok and mapstruct libraries.
-Role based access (USER,ADMIN)
-Spring Security and JWT Auth RBAC
-Api versioning.

###👪 RelationShip Between Entites
--Entity
-Post 
-Tag
-Categories
--Relationship 
✅Post 👉 Tag @ManyToMany
✅Post 👉 Categories @ManyToOne One categories can have  many post associated with it
❌Categories and Tags cannot be deleted if Post associated with it.

###🖌️Diagrams
![Blog App Diagrams](/pics/Blog%20Platform.png)


###📦️API Endpoints
| Method | Endpoint | Description|
| POST  | `api/v1/auth/login` | Authenticate and receive JWT Token|
| POST   |`api/v1/categories/`|Create new Category  |
| GET   | `api/v1/categories`  |Get All Categories  |
| DELETE|`api/v1/categories/{id}`|Delete Existing Category  |
| POST   |`api/v1/tags`  |Create New Tags  |
| GET   |`api/v1/tags`  |Get List of Tags  |
| DELETE  | `api/v1/tag/{tagId}`  | Delete Existing Tag   |
| POST   | `api/v1/posts`    | Create New Post   |
| GET   |`api/v1/posts`  | Get all Published Posts   |
| GET   |`api/v1/posts/drafts`    | Get all Draft Posts   |
| GET   | `api/v1/posts/{id}`     | Get Post By id     |
|  PUT  | `api/v1/posts/{id}`     | Update Existing Post   |
|  DELETE    | `api/v1/posts/{id}`     | Delete Existing Post   |


---

## 📦 Running the Project

```bash
# Clone repo
git clone https://github.com/guruprasath-og/springcodes
cd your-repo-name

# Build & run
./mvnw spring-boot:run


# Task and Project Manager
This project is a REST API for managing teams and projects, reducing the workload of team leaders and project managers when assigning tasks to team members.

## Technology
- Java version 27
- Docker
- PostgreSQL version 16
- Spring Boot
- Flyway
- Maven

## Requirements
- JDK version 27
- Docker

## How to run
  1. Clone the repository to your local machine
  2. Open the Docker application
  3. In the IDE terminal run: docker compose up -d
  4. To run the application:
    - PowerShell(Windows): .\mvnw spring-boot:run
    - CMD(Windows): mvnw.cmd spring-boot:run
    - Linux or macOS: ./mvnw spring-boot:run
  5. Open http://localhost:8080/swagger-ui/index.html#/ in your browser (to check that the application is running)

## Endpoints
- TaskController:
  - POST: /api/projects/{projectId}/tasks
  - GET: /api/projects/{projectId}/tasks
  - GET: /api/projects/{projectId}/tasks/{taskId}
  - PUT: /api/projects/{projectId}/tasks/{taskId}
  - DELETE: /api/projects/{projectId}/tasks/{taskId}
- ProjectController:
  - POST: /api/projects
  - GET: /api/projects
  - GET: /api/projects/{id}
  - PUT: /api/projects/{id}
  - DELETE: /api/projects/{id}
- PingController
  - GET: /api/ping

## Status
- [x] Project and task CRUD
- [x] Request validation with detailed error responses
- [x] Database migrations with Flyway
- [x] API documentation with Swagger
- [ ] Authentication (JWT with refresh tokens)
- [ ] Per-project roles and permissions (owner, admin, member)
- [ ] Task filtering, search and pagination
- [ ] Task comments
- [ ] Audit log
- [ ] Automated tests (Testcontainers)
- [ ] CI with GitHub Actions
- [ ] Dockerfile

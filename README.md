# Task and Project Manager
This project has the objective of creating an application for managing a team or a project in an easier way, making the Team Leader or Project Manager have less
work when assigning a task to a coworker.

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
  5. Open your browser in http://localhost:8080/api/ping (for you to see if the application is running)

## Status
In the starting point, just have the first migration, and the application working.

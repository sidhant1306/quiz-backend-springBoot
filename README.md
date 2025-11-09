Quiz Backend – Spring Boot

This project is a Spring Boot REST API for managing quizzes and questions.
It includes question management, quiz creation, secure DTO-based question delivery, scoring logic, exception handling, and full Swagger documentation.
Built as a learning-focused backend project with proper architecture and clean code practices.

============================================================================================================================================================

Features

Add and fetch questions

Create a quiz by selecting specific question IDs

Retrieve quiz questions without exposing correct answers

Submit answers and receive a score with total questions

Global exception handling

Input validation

MySQL database integration

Swagger UI for API documentation and testing

========================================================================================================================================================================================================


Tech Stack

Java 17

Spring Boot

Spring Data JPA

MySQL

Jakarta Validation

Swagger / OpenAPI

Lombok

========================================================================================================================================================================================================

PROJECT STRUCTURE : 
src/
 ├── main/java/org/example/springboot_project_first
 │     ├── Controller/         # REST endpoints
 │     ├── services/           # Business logic
 │     ├── repository/         # JPA repositories
 │     ├── model/              # Entities
 │     ├── dto/                # Data transfer objects
 │     └── exceptionHandling/  # Custom exceptions and global handler
 │
 └── main/resources/
       ├── application-example.properties
       └── (real application.properties is ignored for security)

========================================================================================================================================================================================================


API Endpoints :

{
  "title": "Java Basics Quiz",
  "questionIds": [1, 2, 3]
}

Submit Quiz

POST /quiz/submit/{quizId}

Request:

[
  { "questionId": 1, "submittedAnswer": "public" },
  { "questionId": 2, "submittedAnswer": "Object Oriented" }
]

========================================================================================================================================================================================================

Swagger Documentation

Once the application is running locally:

http://localhost:8080/swagger-ui.html

========================================================================================================================================================================================================

MySQL Configuration

CREATE DATABASE quizdb;

spring.datasource.url=jdbc:mysql://localhost:3306/quizdb
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

========================================================================================================================================================================================================

Error Handling

All errors are handled by a global exception handler and return clear JSON responses.
Example:

{
  "status": 404,
  "error": "NOT_FOUND",
  "message": "Quiz not found with ID: 10"
}

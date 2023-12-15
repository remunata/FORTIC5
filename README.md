# FORTIC5
FORTIC5 is an e-commerce website built with java Spring Boot framework as backend and server, MySQL as database, Thymeleaf as template engine and Tailwindd CSS.

## Requirement
- Java Development Kit 17
- Maven (optional, you can use maven wrapper that included in this repository)
- MySQL Server

## How to Run
1. Setup MySQL
2. Configure MySQL credentials in [application.yml](src/main/resources/application.yml)
3. Start application with
```
mvn spring-boot:run
```
or
```
./mvnw spring-boot:run (for linux)
./mvnw.cmd spring-boot:run (for windows)
```
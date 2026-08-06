# REST API обліку витрат
Застосунок для обліку особистих доходів та витрат

- Java: 17  
- Framework: 
- Spring Boot 4.1.0  
- Modules: Spring WebMVC, Spring Data JPA, Spring Security  
- Database: PostgreSQL (драйвер версії 42.7.12)  
- Database Migrations: Liquibase  
- Mapping & Boilerplate: MapStruct (1.5.5.Final), Lombok 
- Validation: Spring Boot Validation

# How to Run Locally
1. ```bash  
    git clone https://github.com/chupa-ilona/test-task-blago.git
   
2. Set up the database:
   Ensure you have PostgreSQL installed. Create a database named expenses_tracker.
   For security reasons, the password is moved to environment variables. Create a .env file in the root of the project(or configure it via your IDE settings):
   ```bash  
   DB_PASSWORD=your_db_password
   
3. Run the application:
    ```bash  
   ./mvnw spring-boot:run

# Postman Collection
1. download the file [Expense Tracker Postman Collection](Expenses Tracker API.postman_collection.json)
2. Іmport it into Postman.



# Banking Management System

A RESTful Banking Management System developed using Java and Spring Boot. The application provides APIs for managing customers, bank accounts, deposits, withdrawals, money transfers, and transaction history.

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- Spring Security
- MySQL
- Maven
- REST API
- Postman
- Eclipse IDE

## Features

### Customer Management

- Create customer
- Get all customers
- Get customer by ID
- Update customer
- Delete customer

### Account Management

- Create bank account
- Get all accounts
- Get account by ID
- Get account by account number
- Update account
- Delete account

### Banking Operations

- Deposit money
- Withdraw money
- Transfer money between accounts
- Check account balance
- Account status validation

### Transaction Management

- Record deposits
- Record withdrawals
- Record transfers
- View transaction history by account

### Validation and Exception Handling

- Invalid amount validation
- Insufficient balance validation
- Account status validation
- Same-account transfer validation
- Customer/account not found handling
- Global exception handling

## Project Structure

```text
src/main/java/com/banking
│
├── controller
├── entity
├── exception
├── repository
├── security
└── service

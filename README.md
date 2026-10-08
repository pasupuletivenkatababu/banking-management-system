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

## REST API Endpoints

### Customer APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/customers` | Create customer |
| GET | `/api/customers` | Get all customers |
| GET | `/api/customers/{id}` | Get customer by ID |
| PUT | `/api/customers/{id}` | Update customer |
| DELETE | `/api/customers/{id}` | Delete customer |

### Account APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/accounts` | Create account |
| GET | `/api/accounts` | Get all accounts |
| GET | `/api/accounts/{id}` | Get account by ID |
| GET | `/api/accounts/number/{accountNumber}` | Get account by number |
| PUT | `/api/accounts/{id}` | Update account |
| DELETE | `/api/accounts/{id}` | Delete account |
| POST | `/api/accounts/{id}/deposit` | Deposit money |
| POST | `/api/accounts/{id}/withdraw` | Withdraw money |
| POST | `/api/accounts/transfer` | Transfer money |

### Transaction APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/transactions/account/{accountId}` | Get transaction history |

## Database

The application uses MySQL.

**Database:** `banking_db`

Main tables:

- customers
- accounts
- transactions

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/pasupuletivenkatababu/banking-management-system.git
```

### 2. Open the project

Open the project in Eclipse or IntelliJ IDEA.

### 3. Create the database

```sql
CREATE DATABASE banking_db;
```

### 4. Configure MySQL

Update your MySQL username and password in `application.properties`.

### 5. Run the application

Run `BankingManagementSystemApplication.java`.

The application will run on:

`http://localhost:8080`

## Testing

The APIs were tested using Postman.

Tested operations include:

- Customer CRUD
- Account CRUD
- Deposit
- Withdrawal
- Money transfer
- Transaction history
- Validation
- Exception handling

## Future Enhancements

- JWT authentication
- Role-based authorization
- Swagger/OpenAPI documentation
- Unit testing
- Integration testing
- Account statements
- Pagination and sorting
- Docker deployment

## Author

**Pasupuleti Venkata Babu**

Java Backend Developer | Spring Boot | SQL

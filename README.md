# E-commerce Item REST API

A Java Spring Boot RESTful API for managing e-commerce items. This application was developed as a technical assignment demonstrating backend development skills with Java and Spring Boot.

## 📋 Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Testing with Postman](#testing-with-postman)
- [Sample Data](#sample-data)
- [Error Handling](#error-handling)

## Overview

This REST API provides comprehensive CRUD (Create, Read, Update, Delete) operations for managing items in an e-commerce system. It uses an in-memory data store with pre-loaded sample data for demonstration purposes.

## Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 | Programming Language |
| Spring Boot | 4.0.2 | Application Framework |
| Spring Web | - | REST API Development |
| Spring Validation | - | Input Validation |
| Lombok | - | Boilerplate Code Reduction |
| Maven | - | Build & Dependency Management |

## Features

- ✅ **RESTful API Design** - Clean and intuitive API endpoints
- ✅ **CRUD Operations** - Full Create, Read, Update, Delete functionality
- ✅ **Input Validation** - Request body validation with meaningful error messages
- ✅ **Exception Handling** - Global exception handler with structured error responses
- ✅ **In-Memory Storage** - ArrayList-based data storage with auto-incrementing IDs
- ✅ **Pre-loaded Data** - Sample items loaded on application startup
- ✅ **Logging** - Comprehensive logging using SLF4J
- ✅ **CORS Enabled** - Cross-Origin Resource Sharing support

## Project Structure

```
ecommerce/
├── src/main/java/com/task/ecommerce/
│   ├── EcommerceApplication.java      # Main application entry point
│   ├── controller/
│   │   └── itemController.java        # REST API endpoints
│   ├── model/
│   │   └── Item.java                  # Item entity with validation
│   ├── service/
│   │   └── ItemService.java           # Business logic layer
│   └── exceptions/
│       ├── GlobalExceptionHandler.java # Centralized exception handling
│       └── itemNotFoundException.java  # Custom exception
├── src/main/resources/
│   └── application.properties          # Application configuration
└── pom.xml                             # Maven dependencies
```

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Installation & Running

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ecommerce/ecommerce
   ```

2. **Build the project**
   ```bash
   ./mvnw clean install
   ```
   On Windows:
   ```bash
   mvnw.cmd clean install
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```
   On Windows:
   ```bash
   mvnw.cmd spring-boot:run
   ```

4. **Verify the application is running**
   
   Open your browser and navigate to: `http://localhost:8080/api/items`

The server will start on **port 8080** by default.

## API Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| `GET` | `/api/items` | Welcome message & API info | - |
| `GET` | `/api/items/all` | Get all items | - |
| `GET` | `/api/items/{id}` | Get item by ID | - |
| `POST` | `/api/items/addItem` | Add a new item | JSON (Item) |
| `DELETE` | `/api/items/{id}` | Delete an item | - |

### Request/Response Examples

#### GET /api/items (Welcome)
**Response:**
```json
{
  "message": "Welcome to E-commerce API",
  "version": "1.0.0",
  "totalItems": 3,
  "endpoints": {
    "GET /api/items": "Get this welcome message",
    "GET /api/items/all": "Get all items",
    "POST /api/items/addItem": "Add a new item",
    "GET /api/items/{id}": "Get item by ID",
    "DELETE /api/items/{id}": "Delete an item"
  }
}
```

#### GET /api/items/all
**Response:**
```json
[
  {
    "id": 1,
    "name": "Laptop",
    "description": "High-performance gaming laptop",
    "price": 1299.99,
    "category": "Electronics",
    "stockQuantity": 15
  },
  ...
]
```

#### GET /api/items/{id}
**Response (200 OK):**
```json
{
  "id": 1,
  "name": "Laptop",
  "description": "High-performance gaming laptop",
  "price": 1299.99,
  "category": "Electronics",
  "stockQuantity": 15
}
```

#### POST /api/items/addItem
**Request Body:**
```json
{
  "name": "Gaming Keyboard",
  "description": "Mechanical RGB keyboard with Cherry MX switches",
  "price": 79.99,
  "category": "Electronics",
  "stockQuantity": 25
}
```

**Response (201 Created):**
```json
{
  "id": 4,
  "name": "Gaming Keyboard",
  "description": "Mechanical RGB keyboard with Cherry MX switches",
  "price": 79.99,
  "category": "Electronics",
  "stockQuantity": 25
}
```

#### DELETE /api/items/{id}
**Response (200 OK):**
```json
{
  "message": "Item deleted successfully",
  "deletedId": 4,
  "timestamp": "2026-02-07T10:30:00"
}
```

## Testing with Postman

A comprehensive Postman collection is provided for testing all API endpoints.

### Setup Instructions

1. **Import the Collection**
   - Open Postman
   - Click `Import` button
   - Import the provided `E-commerce Item API - Complete Collection.json` file

2. **Configure Environment Variable**
   - The collection uses `{{base_url}}` variable
   - Default value: `http://localhost:8080`
   - Modify if running on a different port

3. **Run Tests**
   - Execute requests individually or use Collection Runner
   - Each request includes automated tests for validation

### Postman Collection Contents

| # | Request Name | Method | Tests Included |
|---|--------------|--------|----------------|
| 1 | Welcome & API Info | GET | Status 200, Message validation |
| 2 | Get All Items | GET | Status 200, Array validation, Required fields |
| 3 | Get Item by ID (Valid) | GET | Status 200, Field validation |
| 4 | Add New Item (Valid) | POST | Status 201, ID generation, Data validation |
| 7 | Get Item by ID (Not Found) | GET | Status 404, Error message |
| 12 | Delete Item | DELETE | Status 200, Success message |
| 13 | Verify Deleted Item | GET | Status 404, Confirmation |
| - | Update Item by ID | GET | - |

### Collection Variables

| Variable | Default Value | Description |
|----------|---------------|-------------|
| `base_url` | `http://localhost:8080` | API base URL |
| `new_item_id` | (dynamic) | Stores ID of newly created item |

## Sample Data

The application initializes with the following sample items:

| ID | Name | Description | Price | Category | Stock |
|----|------|-------------|-------|----------|-------|
| 1 | Laptop | High-performance gaming laptop | $1299.99 | Electronics | 15 |
| 2 | Smartphone | Latest model with 5G support | $899.99 | Electronics | 30 |
| 3 | Headphones | Wireless noise-cancelling headphones | $299.99 | Electronics | 50 |

## Error Handling

The API implements a global exception handler providing consistent error responses.

### Item Not Found (404)
```json
{
  "timestamp": "2026-02-07T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Product not found with id: 999"
}
```

### Validation Error (400)
```json
{
  "timestamp": "2026-02-07T10:30:00",
  "status": 400,
  "error": "Validation Failed",
  "validationErrors": {
    "name": "Item name is required",
    "price": "Price must be greater than zero"
  }
}
```

### Validation Rules

| Field | Validation | Message |
|-------|------------|---------|
| `name` | Not blank | "Item name is required" |
| `description` | Not blank | "Item description is required" |
| `price` | Not null, Positive | "Price is required", "Price must be greater than zero" |
| `category` | Not blank | "Category is required" |
| `stockQuantity` | Optional | Defaults to 0 if not provided |

## Item Model

```java
public class Item {
    Long id;                    // Auto-generated
    String name;                // Required
    String description;         // Required
    Double price;               // Required, must be > 0
    String category;            // Required
    Integer stockQuantity;      // Optional, defaults to 0
}
```

---

## Author

Developed as a technical assignment for job recruitment process.

## License

This project is created for assessment purposes.

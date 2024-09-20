# Billing System

This project is a billing system designed to manage invoices, customers, products, and companies. The system is built with **Java**, **Spring Boot**, and **jOOQ**, providing a robust solution for handling complex billing scenarios.

## Features

- **Invoice Management**: Create, update, and retrieve invoices.
- **Customer Management**: Handle customer records, including name, address, and other microtypes.
- **Product Management**: Manage product details and prices.
- **Company Management**: Support for multiple companies and their roles in transactions.
- **Microtypes Implementation**: Strong typing for entities using microtypes (e.g., `InvoicePrice`, `ProductName`, `CompanyId`).

## Technologies Used

- **Java**
- **Spring Boot**
- **jOOQ** (Java Object Oriented Querying)
- **JUnit** for testing
- **Gradle** as a build tool

## Getting Started

### Prerequisites

- Java 17+
- Gradle
- Docker (if using a containerized database)

### Installation

1. Clone the repository:
    ```
    git clone https://github.com/your-username/billing-system.git
    cd billing-system
    ```

2. Build the project:
    ```
    ./gradlew build
    ```

3. Run the application:
    ```
    ./gradlew bootRun
    ```

### Running Tests

To execute the tests:
```
./gradlew test
```

## Contributing

Feel free to submit issues or contribute via pull requests.


# E-Commerce Management System

## Part E: User Documentation

### 1. Description of Main Features
This is a Spring Boot application simulating an E-Commerce Management System. It supports:
- User creation and roles.
- Product catalog management.
- Cart and Order placement.
- Applying discounts (Strategy Pattern).
- Adding additional services like Gift Wrapping (Decorator Pattern).
- Processing payments via a simulated external bank API (Adapter Pattern).

### 2. Prerequisites
- Java 21 or higher
- PostgreSQL database
- Maven

### 3. Installation and Running Instructions
1. **Database Setup:**
   Open your terminal or pgAdmin and create a new PostgreSQL database named `ecommerce_db`:

   CREATE DATABASE ecommerce_db;

2. **Configuration:**
   Navigate to `src/main/resources/application.properties`. Update the `spring.datasource.username` and `spring.datasource.password` with your local PostgreSQL credentials.

3. **Running the Application:**
   Run the `EcommerceApplication.java` main class. The application uses Spring Data JPA (`ddl-auto=update`) to automatically generate the required database tables on startup.

4. **Sample Data:**
   The `AppRunner` class automatically creates a sample user, simulates a shopping cart, applies a discount, adds gift wrapping, processes payment, and persists the order to the database. Check the console output after running to see the simulated workflow.

### 4. Design Patterns Implemented
- **Strategy Pattern:** Used for calculating different types of discounts (`PercentageDiscountStrategy`, `FixedDiscountStrategy`).
- **Decorator Pattern:** Used to dynamically add services to an order, such as `GiftWrapDecorator`.
- **Adapter Pattern:** Used to integrate a simulated, incompatible third-party banking service (`ExternalBankApi`) via `BankPaymentAdapter`.
Cinema Booking Prototype (Java)
=================================

This is a prototype for a cinema booking management system.

Features:
- Accepts one movie title as input.
- Accepts a seating map size (rows x columns).
- Displays a seating map.
- Allows booking available seats with customer names.
- Allows inspecting the status of a seat.
- Command-line interface.

## Moving from Prototype to Production-Level System

If we wanted to build this for **production use**, here’s what would be needed:

### 1. Architecture & Framework
- Convert to a **Spring Boot** application for REST API or web interface.
- Expose endpoints like:
  - `POST /movies/{id}/bookings`
  - `GET /movies/{id}/seats`
- Optionally add a React or Angular frontend for seat selection.

### 2. Persistence & Scalability
- Store movies, halls, and bookings in a **relational database** (MySQL, PostgreSQL).
- Use **JPA/Hibernate** for ORM mapping.
- Ensure transactions and concurrency handling for seat booking (avoid double-booking).

### 3. Authentication & Authorization
- Add user accounts (customers, admins).
- Secure with **JWT authentication**.
- Different roles (admin manages movies, customers book tickets).

### 4. Validation & Error Handling
- Input validation on seat selection, movie IDs, and booking requests.
- Centralized error handling for consistent responses.

### 5. Testing & CI/CD
- Create Unit and integration tests.
- Integration tests with Spring Boot Test.

### 6. Exception Handling
- Add global exception handling for the Spring Boot application to handle exception (RestControllerAdvice)
- Extend Java exception classes to create custom exceptions with meaningful names

---

This prototype demonstrates the **core logic**, while a production-grade system would focus on scalability, security, and robustness.

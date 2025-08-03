# Car API – NOVI Backend Assignment

## Overview

This project is a small RESTFUL web service built as part of the Backend module at [Novi University](https://www.novi.nl). 
It allows creating, reading, updating, and deleting (CRUD) **Cars** and their **Registrations**. The application demonstrates separation of concerns using controllers, services, repositories, DTOs, and mappers.

---

## Table of Contents

- [Tech Stack](#tech-stack)
- [Key Features](#key-features)
- [API Endpoints](#api-endpoints)
- [How to Run](#how-to-run)
- [Credits](#credits)
- [License](#license)

---

## Tech Stack

- **Java 17+**
- **Spring Boot** (REST API)
- **Maven**
- **JPA** / **Hibernate**

---

## Features

- Create, update, delete and fetch `Car` entities
- Register a `Car` with additional `CarRegistration` data
- Use of DTOs to decouple internal model from API layer
- Input validation using `@Valid` and `BindingResult`
- Proper use of `ResponseEntity` with appropriate HTTP status codes
- Mapping logic abstracted using dedicated Mapper classes

---

## API Endpoints

### Car Controller (`/cars`)
| Method | Endpoint     | Description                    |
|--------|--------------|--------------------------------|
| GET    | `/cars`      | Get all cars (optional filter) |
| POST   | `/cars`      | Create a new car               |
| PUT    | `/cars/{id}` | Update existing car            |
| DELETE | `/cars/{id}` | Delete car by ID               |

Sample JSON (CarCreateDTO)
```json
{
"brand": "Toyota",
"model": "Corolla"
}
```

### Car Registration Controller (`/cars/{carId}/carregistrations`)
| Method | Endpoint                                                      | Description                        |
|--------|---------------------------------------------------------------|------------------------------------|
| POST   | `/cars/{carId}/carregistrations`                              | Create a new registration for car  |
| GET    | `/cars/{carId}/carregistrations/{registrationId}`             | Get a specific registration        |
| PUT    | `/cars/{carId}/carregistrations/{registrationId}`             | Update a registration              |
| DELETE | `/cars/{carId}/carregistrations/{registrationId}`             | Delete a registration              |


Sample JSON (CarRegistrationCreateDTO)
```json
{
  "plateNumber": "XYZ-1234",
  "registrationDate": "2024-01-10"
}
```

---

## How to Run

### Using IntelliJ IDEA

## How to Run

1. Clone the repository or open the project in your IDE (IntelliJ IDEA)
2. Navigate to the `CarApplication` main class. 
3. Click the green play️ button to run the application or use the terminal:
    ```bash
    mvn spring-boot:run
   ```
4. The server will start at: `http://localhost:8080`


## Credits
> "This assignment was developed as part of the Backend Java module in the NOVI Software Development program. All instructions, logic, and structure are part of the official coursework."

## License
> "This repository is intended for educational purposes only. You are welcome to use the code for learning, but not for commercial use."
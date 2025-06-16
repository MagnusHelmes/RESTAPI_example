# SectorChoice

**SectorChoice** is a Spring Boot web application that allows users to select one or more hierarchical sectors of interest, provide their name, agree to terms, and submit their selection. The form uses server-side validation and persists user choices across sessions.

---

## ✨ Features

- ✅ Dynamic hierarchical sector selection
- ✅ User input validation with error feedback
- ✅ Thymeleaf-based frontend templating
- ✅ Session persistence of user data
- ✅ Custom global exception handling
- ✅ Unit & integration test coverage

---

## 📦 Tech Stack

- Java 17
- Spring Boot 3.4.6
- Thymeleaf
- Spring MVC & Validation
- Spring Data JPA (in-memory H2 database)
- Maven
- JUnit 5, Mockito, MockMvc

---

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8+

### Run the Application

```bash
mvn spring-boot:run
```
or

```bash
java -jar target/sectorchoice-0.1.4-SNAPSHOT.jar
``` 


Then open your browser at:

### [http://localhost:8080](http://localhost:8080)

---

## 🧪 Running Tests

Run the full suite:

```bash
mvn test
```

Tests include:
- Unit tests (`UserService`, `SectorService`, `UserFormController`, `GlobalExceptionHandler`)
- DTO validation test (`UserSelectionDTOTest`)
- Session logic test (`SessionExpiryTest`)
- Integration test (`SectorChoiceIntegrationTest`) using MockMvc


---

## 🗂 Project Structure

```
src/
├── main/
│   ├── java/com/helmes/sectorchoice/
│   │   ├── controller/        # Web controllers (UserFormController)
│   │   ├── service/           # Business logic (UserService, SectorService)
│   │   ├── model/             # Domain models (Sector, UserSelection)
│   │   ├── dto/               # Form data transfer object (UserSelectionDTO)
│   │   ├── repository/        # Spring Data interfaces
│   │   └── exception/         # Global error handling
│   └── resources/
│       ├── templates/         # Thymeleaf templates
│       ├── static/            # CSS/images
│       └── application.properties
└── test/
    └── java/com/helmes/sectorchoice/
        ├── controller/
        ├── service/
        ├── exception/
        └── SectorChoiceIntegrationTest.java
```

---

## 📖 Usage Flow

1. User opens the form at `/`
2. Selects one or more sectors
3. Enters their name
4. Accepts terms
5. Clicks submit
6. Data is saved and displayed again with a success message

---

## 📄 License

This project is licensed under the MIT License. See `LICENSE` for details.

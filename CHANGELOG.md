<h1 align="center">📝 SectorChoice Changelog</h1>

<p align="center">All notable changes to this project are documented here.</p>

---

## 🧪 [0.1.4]
- ✔️ Unit tests for `SectorService` to validate sector hierarchy logic.
- ✔️ Validation tests for `UserSelectionDTO` to ensure field constraints behave as expected.
- ✔️ Session expiry test for `UserFormController` to simulate behavior when no session user ID exists.

---

## 🧠 [0.1.3]
- 🧩 Introduced `SectorService` to encapsulate sector hierarchy logic.
- 🧼 Refactored `UserFormController` to delegate to `SectorService` for cleaner design and improved separation of concerns.

---

## 📦 [0.1.2] – *Tests Added*
- ✅ Introduced comprehensive JUnit test suite
- ➕ Added unit tests for services, controllers, and error handlers
- 🧪 Integration test using MockMvc for form flow

---

## 🛡️ [0.1.1] – *Error Handling Improvements*
- 🌐 Implemented global exception handling with `@ControllerAdvice`
- 🧾 Created custom `error.html` view
- 🪵 Added logging for form and error flow

---

## 🧼 [0.1.0] – *DTO & Interface Cleanup*
- 🔁 Renamed repository interfaces for clarity
- 🧱 Renamed DTO for form data to improve semantic meaning

---

## 🍃 [0.0.9] – *Lombok Refactor*
- 🧹 Replaced boilerplate with Lombok annotations
- ✨ Introduced `@Getter`, `@Setter`, `@ToString`, `@NoArgsConstructor`

---

## 🔧 [0.0.6] – *Code Refactor*
- 💡 Improved naming for services and repositories
- 🧠 Better variable and instance naming for maintainability

---

## 🎨 [0.0.5] – *Thymeleaf UI Structure*
- 🧩 Added Thymeleaf fragments
- 🎨 Split out `style.css` for UI styling

---

## 🚧 [0.0.1 – 0.0.4] – *Initial Setup*
- 📁 Created base project structure
- 🧪 Set up packages, Thymeleaf templates, static resources
- 🧰 Added project scaffolding

---

<p align="center">— End of changelog —</p>
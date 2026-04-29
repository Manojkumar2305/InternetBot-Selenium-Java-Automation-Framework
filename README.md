# InternetBot-Selenium-Java-Automation-Framework
InternetBot is a robust Selenium-Java automation framework built to test advanced web interaction scenarios on the The Internet practice application. The framework follows industry-standard design patterns like Page Object Model (POM) and integrates TestNG, WebDriverManager, and ExtentReports for scalable and maintainable test automation.

It automates key web functionalities including authentication, alerts handling, file operations, dynamic content, and UI element interactions. The framework is designed to be reusable, modular, and easy to extend for additional test cases.

Key highlights:

* Clean separation of Page classes, Test classes, and utilities
* Fully configurable using `config.properties`
* Screenshot capture on failure using TestNG listeners
* HTML reporting with ExtentReports
* Explicit wait strategy using WebDriverWait (no Thread.sleep)
* Data-driven testing using TestNG DataProvider

---

## 📖 README.md

# 🚀 InternetBot – Selenium Automation Framework

## 📌 Overview

InternetBot is a Selenium-Java based automation framework developed to test various functionalities of the demo site:

👉 https://the-internet.herokuapp.com

It is built using:

* Selenium WebDriver
* TestNG
* WebDriverManager
* ExtentReports

and follows **Page Object Model (POM)** for clean and maintainable code.

---

## 🧱 Framework Architecture

```
InternetBot/
│── src/
│   ├── base/           → Base classes (driver setup)
│   ├── pages/          → Page Object classes
│   ├── tests/          → Test classes
│   ├── utils/          → Utilities (ConfigReader, Waits, etc.)
│
│── resources/
│   ├── config.properties
│   ├── testng.xml
│
│── screenshots/        → Failure screenshots
│── reports/            → ExtentReports output
```

---

## ⚙️ Features

✔ Page Object Model (POM) design
✔ TestNG framework with annotations & DataProviders
✔ WebDriverManager (no manual driver setup)
✔ Explicit waits (WebDriverWait)
✔ Screenshot capture on failure
✔ ExtentReports HTML reporting
✔ Config-driven execution
✔ Modular & reusable code

---

## 🧪 Test Coverage

### 1. Authentication

* Valid & invalid login
* Logout verification

### 2. JavaScript Alerts

* Alert, Confirm, Prompt handling

### 3. UI Elements

* Checkboxes
* Dropdowns
* Hover interactions

### 4. File Handling

* File upload
* Validation of uploaded file

### 5. Dynamic Content

* Dynamic loading
* Disappearing elements

---

## ⚠️ Known Issues

❗ Some modules are **not fully working** due to:

* Dynamic element instability on the test site
* Timing issues in certain scenarios
* Partial implementation of advanced cases

These modules may fail intermittently and are under improvement.

---

## ▶️ How to Run

### Prerequisites

* Java (JDK 8+)
* Maven
* Chrome/Firefox

### Run Tests

```
mvn test
```

or via TestNG XML:

```
testng.xml
```

---

## 🔧 Configuration

Edit `config.properties`:

```
browser=chrome
baseUrl=https://the-internet.herokuapp.com
timeout=10
```

---

## 📊 Reports

After execution:

* 📄 HTML Report → `/reports/`
* 📸 Screenshots → `/screenshots/`

---

## 🚫 Rules Followed

* ❌ No Thread.sleep()
* ❌ No hardcoded values
* ✅ Proper wait strategy
* ✅ Clean POM structure

---

This project was built as part of a **Selenium Automation Hackathon** focusing on real-world framework design and automation best practices. 

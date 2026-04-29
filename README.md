#  InternetBot – Selenium Automation Framework

InternetBot is a robust Selenium-based automation testing framework developed using Java and TestNG. The framework is designed to automate UI testing for real-world web applications by following industry best practices such as the Page Object Model (POM), data-driven testing, and modular architecture.

The framework targets the practice application: https://the-internet.herokuapp.com

---

##  Features

-  Authentication Testing (Valid & Invalid Login)
-  JavaScript Alerts Handling (Alert, Confirm, Prompt)
-  File Upload Automation (Supported & Unsupported files)
-  UI Element Interaction (Checkboxes, Dropdowns, Hover)
-  Dynamic Content Handling (Dynamic loading & disappearing elements)
-  Broken Elements Detection
-  Screenshot Capture on Test Failure
-  ExtentReports HTML Reporting
-  Configurable via `config.properties`
-  Data-driven testing using TestNG DataProvider

---

##  Framework Architecture
```
InternetBot/
│
├── src/main/java/com/srm/
│ ├── core/ # Config & Driver management
│ │ ├── ConfigReader.java
│ │ └── DriverFactory.java
│ │
│ ├── pages/ # Page classes (POM)
│ │ ├── BasePage.java
│ │ ├── LoginPage.java
│ │ ├── AlertPage.java
│ │ ├── CheckboxPage.java
│ │ ├── DropdownPage.java
│ │ ├── UploadPage.java
│ │ ├── DynamicPage.java
│ │ ├── DynamicContentPage.java
│ │ ├── BrokenImagesPage.java
│ │ ├── HoverPage.java
│ │ ├── SecureAreaPage.java
│ │ └── DisappearingElementsPage.java
│ │
│ └── utils/
│ └── ProcessBuilderFileUpload.java
│
├── src/test/java/com/srm/
│ ├── tests/ # Test classes
│ │ ├── BaseTest.java
│ │ ├── AuthenticationTests.java
│ │ ├── AlertTests.java
│ │ ├── CheckboxDropdownTests.java
│ │ ├── FileUploadTests.java
│ │ ├── DynamicLoadingTests.java
│ │ └── BrokenElementsTests.java
│ │
│ ├── listeners/ # TestNG Listener
│ │ └── TestListener.java
│ │
│ └── utils/
│ ├── ExtentManager.java
│ └── ScreenshotUtils.java
│
├── src/test/resources/
│ ├── config.properties # Configuration file
│ └── test-data/ # Sample test files
│
├── reports/ # ExtentReports output
├── screenshots/ # Failure screenshots
└── pom.xml # Maven dependencies
```

---

##  Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager
- ExtentReports

---

##  Configuration

All configurations are handled via:

src/test/resources/config.properties

Example:

-browser=chrome
-baseUrl=https://the-internet.herokuapp.com
-timeout=10


---

##  Test Coverage

### Authentication
- Valid login
- Invalid login
- Logout verification

### Alerts
- Alert accept
- Confirm dismiss
- Prompt input

### UI Elements
- Checkbox selection
- Dropdown selection

### File Upload
- Upload valid file
- Validate uploaded filename

### Dynamic Content
- Wait for dynamic elements
- Handle disappearing elements

### Broken Elements
- Detect broken images/elements

---

##  Screenshot on Failure

- Captured automatically using TestNG Listener
- Stored in `/screenshots/`
- Timestamp added to filename

---

##  Reporting

- ExtentReports generates HTML report after execution
- Includes pass/fail status and screenshots

Report location:
/reports/extent-report.html

---

##  How to Run

### Prerequisites
- Java (JDK 8 or above)
- Maven installed

### Steps
git clone https://github.com/Deepika307/InternetBot.git

cd InternetBot
mvn clean test

---

##  Best Practices Followed

- Page Object Model (POM)
- No hardcoded values
- Explicit waits (WebDriverWait)
- Clean and reusable code
- Separation of concerns
- Data-driven testing



---

##  Author

Deepika Kantheti  


---

##  Conclusion

This project demonstrates a scalable Selenium automation framework with real-world testing scenarios, focusing on maintainability, reusability, and best practices.

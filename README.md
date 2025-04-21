# SCM (Smart Contact Manager)

## Overview
SCM (Smart Contact Manager) is a web-based application designed to help users manage their contacts efficiently. It provides features like adding, editing, searching, and organizing contacts in a secure and user-friendly interface.

---

## Features
- **User Authentication**: Secure login and registration with email verification.
- **Contact Management**: Add, edit, delete, and search contacts.
- **Secure Storage**: Contacts are stored securely with encryption.
- **Responsive Design**: Fully responsive UI for all devices.
- **OAuth Integration**: Login with Google and GitHub.
- **File Uploads**: Upload and manage profile pictures and contact images.
- **Pagination and Sorting**: Efficiently handle large contact lists.

---

## Technologies Used
- **Backend**: Spring Boot, Spring Security, Hibernate, JPA
- **Frontend**: Thymeleaf, Tailwind CSS, JavaScript
- **Database**: MySQL
- **Cloud Services**: Cloudinary for image storage
- **Build Tool**: Maven

---

## Project Structure
```
SCM/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── scm/
│   │   │           ├── ScmApplication.java
│   │   │           ├── config/
│   │   │           ├── controllers/
│   │   │           ├── entities/
│   │   │           ├── forms/
│   │   │           ├── helpers/
│   │   │           ├── repositories/
│   │   │           ├── services/
│   │   │           └── validators/
│   │   ├── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       │   ├── css/
│   │       │   ├── images/
│   │       │   └── js/
│   │       └── templates/
│   │           ├── base.html
│   │           ├── home.html
│   │           └── user/
│   │               ├── dashboard.html
│   │               ├── profile.html
│   │               └── contacts.html
│   └── test/
│       └── java/
│           └── com/
│               └── scm/
│                   └── ScmApplicationTests.java
├── pom.xml
├── README.md
└── package.json
```

---

## Installation

### Prerequisites
- Java 17 or higher
- Maven
- MySQL
- Node.js (for managing frontend dependencies)

### Steps
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd scm
   ```
2. Configure the database in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/scm
   spring.datasource.username=<your-username>
   spring.datasource.password=<your-password>
   ```
3. Install dependencies:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
5. Access the application at `http://localhost:8080`.

---

## Usage

### User Roles
- **Admin**: Manage all users and contacts.
- **User**: Manage personal contacts.

### Key Endpoints
- `/register`: User registration
- `/login`: User login
- `/user/dashboard`: User dashboard
- `/user/contacts`: Manage contacts

---

## Contributing
We welcome contributions! Please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes with clear messages.
4. Push to your fork and submit a pull request.

---

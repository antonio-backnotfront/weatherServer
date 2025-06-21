# 🌤️ weatherServer

A lightweight Spring Boot microservice designed to handle user authentication (sign-up and login).

---

## 🚀 Features

* 🔐 **User Authentication**
  Supports user registration and login using secure password hashing.

* 📆 **RESTful Design**
  Built with clean, modular controller/service architecture following REST principles.

* ✅ **Input Validation**
  Handles invalid or missing fields gracefully, with informative error responses.

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot
* Spring Security
* Lombok
* Maven
* External weather API (e.g., OpenWeatherMap)

---

## 📁 Project Structure

```
src
├── main
│   ├── java/com/example/weatherServer
│   │   ├── controller
│   │   ├── dto
│   │   ├── model
│   │   ├── service
│   │   └── config
│   └── resources
│       └── application.yml
└── test
```

---

## 🔧 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/antonio-backnotfront/weatherServer.git
cd weatherServer
```

### 2. Run the application

```bash
./mvnw spring-boot:run
```

---

## 📬 API Endpoints (Sample)

| Method | Endpoint         | Description                |
| ------ | ---------------- | -------------------------- |
| POST   | `/api/auth/signup`   | Register a new user        |
| POST   | `/api/auth/signin`   | Authenticate existing user |
| GET    | `/api/user` | Get current user     |
| GET    | `/api/user/allUser` | Get all users     |
| POST    | `/api/user/byId/{id}` | Update current user     |

---

## 🧪 Testing

You can test the API using Postman or curl. Make sure to include the necessary headers for authenticated routes.

---

## 📄 License

This project is open-source and available under the MIT License.

---

## 🤝 Contributions

Feel free to open issues or pull requests. Any feedback or improvements are welcome!

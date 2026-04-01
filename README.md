# 🧾 Bill Generation System (Spring Boot)

A simple and efficient **Bill Generation System** built using **Spring Boot**, designed to generate bills, manage products, and calculate totals automatically.

This project demonstrates backend concepts like REST APIs, data handling, and basic business logic implementation.

---

## 🚀 Features

### 🛍️ Product Management

* Add products
* View product list
* Update product details
* Delete products

### 🧾 Bill Generation

* Generate bill for selected products
* Calculate total price automatically
* Store billing records

### 📊 Basic Operations

* CRUD operations
* Data persistence using database

---

## 🏗️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven

### Database

* MySQL / H2 (configurable)

---

## 📁 Project Structure

Bill-Generation-System/
│
├── src/
│   ├── main/
│   │   ├── java/com/bill/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── model/
│   │   │   └── config/
│   │   └── resources/
│   │       ├── application.properties
│
├── pom.xml
└── README.md
```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone Repository

git clone https://github.com/Vaidik-7474/billing-management-system.git
cd Bill-Generation-System
```

---

### 2️⃣ Configure Database

Update `application.properties`:

```properties id="c6y8mn"
spring.datasource.url=jdbc:mysql://localhost:3306/billdb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3️⃣ Run Application

./mvnw spring-boot:run
```

---

## 🔌 API Endpoints

### 🛍️ Product APIs

| Method | Endpoint           | Description      |
| ------ | ------------------ | ---------------- |
| GET    | /api/products      | Get all products |
| POST   | /api/products      | Add product      |
| PUT    | /api/products/{id} | Update product   |
| DELETE | /api/products/{id} | Delete product   |

---

### 🧾 Bill APIs

| Method | Endpoint           | Description      |
| ------ | ------------------ | ---------------- |
| POST   | /api/bill/generate | Generate bill    |
| GET    | /api/bill/{id}     | Get bill details |

---

## 🔄 Application Flow

1. Admin adds products
2. User selects products
3. System calculates total price
4. Bill is generated and stored
```

---

## 🧪 Testing APIs

Use:

* Postman
* Thunder Client (VS Code)

---

## 📌 Future Improvements

* PDF bill generation
* GST/Tax calculation
* User authentication (JWT)
* Frontend UI integration

---

## 👨‍💻 Author

Vaidik Lathiya

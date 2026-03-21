# 🔗 URL Shortener API

A scalable and efficient URL Shortener backend built with **Spring Boot**.
This service allows users to generate short links, create custom aliases, and manage link expiry.

---

## 🚀 Features

* 🔗 Shorten long URLs
* ⚡ Base62 encoded short links
* ✏️ Custom short URLs (user-defined aliases)
* ⏳ Expiry time for links
* 📊 Click tracking
* 🔁 Automatic redirection
* 🛡️ Input validation and error handling

---

## 🛠️ Tech Stack

* **Java 21**
* **Spring Boot**
* **Spring Data JPA**
* **PostgreSQL**
* **Hibernate**

---

## 📂 Project Structure

```
src/
 ├── controller/
 ├── service/
 ├── repository/
 ├── entity/
 ├── utils/
 └── UrlShortenerApplication.java
```

---

## ⚙️ Setup Instructions

### 1. Clone the Repository

```
git clone https://github.com/your-username/url-shortener.git
cd url-shortener
```

---

### 2. Configure Database

Update `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/url_shortener_db
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3. Run the Application

```
mvn spring-boot:run
```

App will start at:

```
http://localhost:8080
```

---

## 📡 API Endpoints

---

### 🔹 1. Create Short URL

**POST** `/shorten`

#### Request Body:

```
{
  "url": "https://example.com",
  "customCode": "optional-code"
}
```

#### Response:

```
{
  "shortUrl": "http://localhost:8080/abc123"
}
```

---

### 🔹 2. Redirect to Original URL

**GET** `/{shortCode}`

#### Example:

```
http://localhost:8080/abc123
```

➡️ Redirects to original URL

---

## ⚠️ Error Handling

| Scenario                   | Response        |
| -------------------------- | --------------- |
| Invalid URL                | 400 Bad Request |
| Custom code already exists | 409 Conflict    |
| URL not found              | 404 Not Found   |
| Link expired               | 410 Gone        |

---

## 🧠 How It Works

1. URL is stored in database
2. Unique ID is generated
3. ID is encoded using Base62
4. Short code is mapped to original URL
5. On access, user is redirected using HTTP 302

---

## 📊 Future Improvements

* 📈 Analytics dashboard
* 🔐 JWT Authentication
* 🌍 Custom domains
* 🚀 Rate limiting
* 📦 Docker support

---

## 🤝 Contributing

Contributions are welcome! Feel free to fork and improve.

---

## 📄 License

This project is open-source and available under the MIT License.

---

## 👨‍💻 Author

**Shivam Malvadkar**

* Backend Developer | Java | Spring Boot
* Building scalable backend systems

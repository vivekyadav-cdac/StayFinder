# 📘 `sf-usermanagement` - API Documentation

**Microservice** responsible for managing user-related operations in the StayFinder platform.

- **Base URL:** `/api/v1/user`
- **Version:** v1
- **Consumes:** `application/json`
- **Produces:** `application/json`
- **Security:** JWT-based Authentication (except for `/register`)

---

## 📑 Table of Contents

1. [Register New User](https://www.notion.so/sf-usermanagement-244ae9cb7bca805cba6ec14cdfeddbeb?pvs=21)
2. [Get All Users](https://www.notion.so/sf-usermanagement-244ae9cb7bca805cba6ec14cdfeddbeb?pvs=21)
3. [Get User by Email](https://www.notion.so/sf-usermanagement-244ae9cb7bca805cba6ec14cdfeddbeb?pvs=21)
4. [Get User by ID](https://www.notion.so/sf-usermanagement-244ae9cb7bca805cba6ec14cdfeddbeb?pvs=21)
5. [Update User by Email](https://www.notion.so/sf-usermanagement-244ae9cb7bca805cba6ec14cdfeddbeb?pvs=21)
6. [Update User by ID](https://www.notion.so/sf-usermanagement-244ae9cb7bca805cba6ec14cdfeddbeb?pvs=21)
7. [Delete User by Email](https://www.notion.so/sf-usermanagement-244ae9cb7bca805cba6ec14cdfeddbeb?pvs=21)
8. [Delete User by ID](https://www.notion.so/sf-usermanagement-244ae9cb7bca805cba6ec14cdfeddbeb?pvs=21)

---

## 1. ✅ Register New User

```
POST /api/v1/user/register

```

### 🔐 Auth Required: ❌ No

### 📥 Request Body

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "phone": "9876543210",
  "email": "john@example.com",
  "password": "securePassword123",
  "role": "TENANT"
}

```

### 📤 Response

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "phone": "9876543210",
  "email": "john@example.com",
  "role": "TENANT"
}

```

### 🔁 Status Codes

- `201 Created` – User successfully registered
- `400 Bad Request` – Email already exists or validation failed

---

## 2. 📄 Get All Users

```
GET /api/v1/user/users

```

### 🔐 Auth Required: ✅ Yes (ADMIN)

### 📤 Response

```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "phone": "9876543210",
    "email": "john@example.com",
    "role": "TENANT"
  }
]

```

---

## 3. 🔍 Get User by Email

```
GET /api/v1/user/email/{email}

```

### 🔐 Auth Required: ✅ Yes (ADMIN, TENANT, OWNER)

### 📤 Response

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "phone": "9876543210",
  "email": "john@example.com",
  "role": "TENANT"
}

```

---

## 4. 🔍 Get User by ID

```
GET /api/v1/user/{id}

```

### 🔐 Auth Required: ✅ Yes (ADMIN, TENANT, OWNER)

---

## 5. 📝 Update User by Email

```
PUT /api/v1/user/email/{email}

```

### 🔐 Auth Required: ✅ Yes (TENANT, OWNER)

### 📥 Request Body

```json
{
  "firstName": "Updated",
  "lastName": "Name",
  "phone": "1234567890",
  "email": "updated@example.com",
  "password": "newPassword123",
  "role": "OWNER"
}

```

---

## 6. 📝 Update User by ID

```
PUT /api/v1/user/{id}

```

### 🔐 Auth Required: ✅ Yes (TENANT, OWNER)

---

## 7. ❌ Delete User by Email

```
DELETE /api/v1/user/email/{email}

```

### 🔐 Auth Required: ✅ Yes (ADMIN, TENANT, OWNER)

---

## 8. ❌ Delete User by ID

```
DELETE /api/v1/user/{id}

```

### 🔐 Auth Required: ✅ Yes (ADMIN, TENANT, OWNER)

---

## 🛡️ Role-based Access

| Endpoint | ADMIN | TENANT | OWNER |
| --- | --- | --- | --- |
| `GET /users` | ✅ | ❌ | ❌ |
| `GET /email/{email}` | ✅ | ✅ | ✅ |
| `GET /{id}` | ✅ | ✅ | ✅ |
| `DELETE /email/{email}` | ✅ | ✅ | ✅ |
| `DELETE /{id}` | ✅ | ✅ | ✅ |
| `PUT /email/{email}` | ❌ | ✅ | ✅ |
| `PUT /{id}` | ❌ | ✅ | ✅ |
| `POST /register` | ✅ | ✅ | ✅ |

---

## ⚙️ Technologies Used

- Spring Boot
- Spring Security + JWT
- PostgreSQL
- Hibernate / JPA
- Bean Validation
- RESTful Design

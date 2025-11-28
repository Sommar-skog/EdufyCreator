# EdufyCreator
[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)  
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen.svg)](https://spring.io/projects/spring-boot)

## 🎨 Overview
EdufyCreator manages all creators across the Edufy media services.  
A creator can be linked to videos, music, podcasts and more.  
This service is part of the full Edufy microservice ecosystem and communicates with other services via Eureka, Gateway and Docker Compose.

---

## 🧩 Related Projects

### Organization
- [EdufyProjects](https://github.com/EudfyProjects)

### Connections
- [Edufy-infra](https://github.com/EudfyProjects/Edufy-infra) – Docker-compose + init.db
- [EdufyEurekaServer](https://github.com/Sommar-skog/EdufyEurekaServer) – Service discovery
- [Gateway](https://github.com/SaraSnail/EdufyGateway) – Entry point for all requests
- [EdufyUser](https://github.com/Jamtgard/EdufyUser) – User service
- [EdufyKeycloak](https://github.com/Sommar-skog/EdufyKeycloak) – Auth pipeline

### Media connections
- [EdufyCreator](https://github.com/Sommar-skog/EdufyCreator) – Creators
- [EdufyGenre](https://github.com/a-westerberg/EdufyGenre) – Genres
- [EdufyThumb](https://github.com/a-westerberg/EdufyThumb) – Thumbs up/down records
- [EdufyUtility](https://github.com/a-westerberg/EdufyUtility) – Placeholder for algorithms

### Media Services
- [EdufyMusic](https://github.com/Jamtgard/EdufyMusic) - Music
- [EdufyVideo](https://github.com/Sommar-skog/EdufyVideo) - Video
- [EdufyPod](https://github.com/SaraSnail/EdufyPod) - Pod


---

## 🚀 Tech Stack

- **Language:** Java 21
- **Build Tool:** Maven
- **Framework:** Spring Boot 3.5.7
    - Spring Web
    - Spring Data JPA
    - Spring Security
    - Eureka Client
    - Spring Cloud Loadbalancer
- **Databases:**
    - MySQL 8.0 (Docker)
    - H2 (development)
- **Security:**
    - OAuth2 Resource Server

---

## 🏁 Getting Started

### Prerequisites

- Java 21
- Maven
- Docker
- Postman
- Keycloak

---

### 🔌 Ports

#### Connections
- **Eureka:** `8761`
- **Gateway:** `4545`
- **MySQL:** `3307`
- **User:** `8686`
- **Keycloak:** `8080`

#### Media Services
- **Creator:** `8787`
- **Genre:** `8585`
- **Thumb:** `8484`
- **Utility:** `8888`
- **Video:** `8383`
- **Music:** `8181`
- **Pod:** `8282`

---

## 🔒 Authentication & Roles

Edufy Creator uses **OAuth2 + Keycloak**.

### User Roles

- **edufy_realm_admin** – Full admin access across all services
- **creator_admin** – Create and manage creators
- **creator_user** – View creators
- **microservice_access** – Internal communication between services


>_Note: These are not "real" users/admin. They are placeholders for production and used under development._


| Role                | Username            | Password |
|---------------------|---------------------|----------|
| creator_admin       | creator_admin       | admin    |
| creator_user        | creator_user        | creator  |
| edufy_realm_admin   | edufy_realm_admin   | admin    |
| microservice_access | –                   | –        |

> Note: Unauthenticated requests will receive a `401 Unauthorized` response.

> `microservice_access` is a role that clients uses between each other to authorize access

---

## 📚 API Endpoints
*(Extracted from the real project controllers)*

### 🛠️ Admin – Roles: `creator_admin`, `edufy_realm_admin`

| Method   | Endpoint                  | Description          |
|----------|---------------------------|----------------------|
| **GET**  | `/creator/{id}`           | Get creator by ID    |
| **POST** | `/creator/create-creator` | Create a new creator |

---

### 🔗 Client – Role: `microservice_access`

| Method  | Endpoint                                          | Description                                         |
|---------|---------------------------------------------------|-----------------------------------------------------|
| **GET** | `/creator/creators-mediaid`                       | Get creators linked to a media ID (via query param) |
| **PUT** | `/creator/media/record`                           | Record media–creator relationship                   |
| **GET** | `/creator/mediabycreator/{creatorId}/{mediaType}` | Get media of a specific type by creator             |
| **GET** | `/creator/creator/{id}/clientcall`                | Internal call to retrieve creator information       |

---

## 🐳 Docker

- The service is started using `docker-compose.yml` in **Edufy-infra**
- Docker network: `edufy-network`

---

## 🛢️ MySQL

| Name               | User | Pass | Database |
|--------------------|------|------|----------|
| edufy_mysql        | assa | assa | main     |
| edufy_creator_db   | assa | assa | creator  |

- **Version:** 8.0
- **SQL Files:**
    - Init scripts managed in Edufy-infra
- **Default port:** `3306` → mapped as `3307:3306`

- **Connection Example :**
  ```
    spring.datasource.url=jdbc:mysql://edufy-mysql:3306/edufy_pod_db
    spring.datasource.username=assa
    spring.datasource.password=assa
    spring.jpa.hibernate.ddl-auto=update
  ```

> _README made by [Sommar-skog](https://github.com/Sommar-skog)_

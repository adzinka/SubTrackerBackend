# SubTracker Backend

REST API backend for [SubTracker](https://github.com/adzinka/SubTracker) — an Android app for tracking subscriptions.

## Tech Stack

- **Kotlin** + **Spring Boot 4**
- **Spring Data JPA** + **PostgreSQL 17**
- **Bean Validation** (`@Valid`, `@NotBlank`)
- **Docker** for running PostgreSQL locally

## Architecture

```
Controller → Service → Repository
```

- **Controller** — handles HTTP requests and responses
- **Service** — business logic, DTO mapping
- **Repository** — data access via Spring Data JPA
- **DTOs** — separate request/response models with validation

## Endpoints

### Subscriptions

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/subscriptions` | Get all subscriptions |
| GET | `/subscriptions/{id}` | Get subscription by ID |
| POST | `/subscriptions` | Create subscription |
| PUT | `/subscriptions/{id}` | Update subscription |
| DELETE | `/subscriptions/{id}` | Delete subscription |

### Payments

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/subscriptions/{id}/payments` | Get payments for subscription |
| POST | `/subscriptions/{id}/payments` | Add payment |
| PUT | `/subscriptions/{id}/payments/{paymentId}` | Update payment |
| DELETE | `/subscriptions/{id}/payments/{paymentId}` | Delete payment |

## Running Locally

### Prerequisites

- JDK 21
- Docker

### 1. Start PostgreSQL

```bash
docker run --name subtracker-db \
  -e POSTGRES_DB=subtracker \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -d postgres:17
```

### 2. Run the app

```bash
./gradlew bootRun
```

The API will be available at `http://localhost:8080`.

## Related

- [SubTracker Android App](https://github.com/adzinka/SubTracker)

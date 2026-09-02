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
docker compose up -d
```

### 2. Run the app

```bash
./gradlew bootRun
```

The API will be available at `http://localhost:8081`.
From an Android emulator the host machine is `10.0.2.2`, so the API is `http://10.0.2.2:8081`.

### Configuration

Settings are read from the environment; the values in `application.properties` are local-development defaults.

| Variable | Default |
|---|---|
| `SERVER_PORT` | `8081` |
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://localhost:5432/subtracker` |
| `SPRING_DATASOURCE_USERNAME` | `postgres` |
| `SPRING_DATASOURCE_PASSWORD` | `postgres` |

Never commit real credentials — use environment variables or a local `.env` file (git-ignored).

## Related

- [SubTracker Android App](https://github.com/adzinka/SubTracker)

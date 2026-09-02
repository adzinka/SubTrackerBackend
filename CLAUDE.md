# SubTracker — Backend

REST API for the SubTracker Android app: <https://github.com/adzinka/SubTracker>

---

## Working agreement

The owner of this repo is learning **Java and Spring** on this codebase, and is learning to work with coding
agents. The existing Kotlin implementation works; a Java rewrite is planned **as a learning exercise**, not as a
technical improvement. Speed is not the goal.

**Learning mode** (default here — this whole repo is the learning surface)
- Do not write production code.
- Explain, compare options, sketch skeletons and signatures, write *failing* tests, review what the owner wrote.
- Ask questions back ("why `@Transactional` here?", "what happens to `payments` on update?") instead of
  silently fixing.
- When porting Kotlin to Java, point at the Kotlin original and name the concept that changes
  (data class -> record, `?` -> `Optional`/`@Nullable`, extension function -> static mapper, default args -> overloads).

**Delivery mode** (only when explicitly requested)
- Boilerplate the owner already understands: CI, Docker, config, Flyway scaffolding, docs, tests by an example.

**Language:** explanations and reviews in **Russian**; everything committed in **English**.

---

## Workflow

- One task = one branch = one PR. Never commit directly to `main`.
- Branch names: `feat/<slug>`, `fix/<slug>`, `refactor/<slug>`, `chore/<slug>`, `test/<slug>`, `build/<slug>`.
- Conventional Commits, imperative mood, one logical change per commit.
- State the plan before writing code; wait for confirmation.
- A PR is not done until `./gradlew build` is green.

---

## Stack

- Kotlin 2.2 (Java rewrite in progress), JDK 21 toolchain
- **Spring Boot 4.0.x** — note: most tutorials and books target Boot 3.x. Starter names and some APIs differ
  (`spring-boot-starter-webmvc`, `spring-boot-starter-webmvc-test`). Prefer the official Boot 4 docs; treat 3.x
  material as a translation exercise.
- Spring Data JPA + PostgreSQL 17
- Bean Validation
- Gradle 9.4.1, Kotlin DSL

## Architecture

```
Controller -> Service -> Repository -> Postgres
              |
              DTO (request/response) — entities never leave the service layer
```

```
src/main/kotlin/com/subtracker/backend/
├── controller/   HTTP only: routing, status codes, @Valid
├── service/      business logic, DTO <-> entity mapping, transactions
├── repository/   Spring Data JPA interfaces
├── dto/          SubscriptionDto, SubscriptionRequestDto, PaymentDto + mappers
├── model/        JPA entities
├── GlobalExceptionHandler.kt
└── WebConfig.kt  CORS
```

Rules:
- Entities never cross the controller boundary — DTOs only.
- Validation annotations live on the request DTOs, not on entities.
- Business rules live in services; controllers stay thin.

## API

`/subscriptions` CRUD; payments nested under `/subscriptions/{id}/payments`. Port `8081`.
See `README.md` for the endpoint table.

---

## Running locally

```bash
docker compose up -d          # Postgres 17 on 5432
./gradlew bootRun             # API on http://localhost:8081
./gradlew build               # compile + tests
```

Configuration is read from environment variables with local-dev defaults (`SPRING_DATASOURCE_URL`,
`SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`). Never commit real credentials.

From the Android emulator the host is `10.0.2.2`, so the API is `http://10.0.2.2:8081`.

---

## Known debt — context, not a to-do list

Each gets its own PR.

- **Dates are `String`** on entities and DTOs. Target: `LocalDate` + ISO-8601 on the wire, matching the app.
- **Money is inconsistent**: `Subscription.price` is `Double`, `Payment.amount` is `Int`, the app uses `Int`.
  `Double` must not be used for money. Target: one representation agreed with the app (minor units as `Long`,
  or `BigDecimal(19,2)`), decided before the API contract is frozen.
- **`spring.jpa.hibernate.ddl-auto=update`** — schema is whatever Hibernate guesses. Flyway is required before
  the app depends on this API.
- **`SubscriptionService.update()` returns `null`** when the id is unknown, so the controller answers `200` with
  an empty body instead of `404`. It also rebuilds the entity with `copy(id = id)`, dropping the `payments`
  association.
- **No `@Transactional`** on service methods that write.
- **No tests** beyond the generated `contextLoads`.
- **No pagination** on collection endpoints.
- **No OpenAPI** — springdoc will become the shared contract with the Android app.
- **No auth.** Deliberate for now; the API is single-user and local. Needed before any deployment.

## Planned direction

1. Java rewrite, module by module, with the Kotlin version kept alongside as the reference.
2. Flyway migrations, `LocalDate`, agreed money type.
3. Tests: JUnit 5 + AssertJ, `@WebMvcTest` slices, `@DataJpaTest` + Testcontainers.
4. springdoc-openapi -> contract for the Android client.
5. Sync-friendly model (client-generated UUIDs, `updatedAt`, soft delete) for offline-first.

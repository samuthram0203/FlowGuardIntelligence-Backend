# Delivery Risk Intelligence Platform — Backend

Spring Boot REST API backing the dashboard, built to mirror the risk logic in
the React frontend's `src/lib/riskEngine.js` (see `RiskEngineService`).

Runs on an in-memory H2 database seeded with the same sample data used by the
frontend's mock API, so both sides show matching numbers out of the box.
Swap H2 for Postgres/MySQL (see `application.properties`) when you're ready
for real persistence.

## Stack
- Java 17
- Spring Boot 3.3 (Web, Data JPA, Validation)
- H2 in-memory database
- Maven

## Run it

```bash
mvn spring-boot:run
```

API is available at `http://localhost:8080`. H2 console (for poking at the
data) is at `http://localhost:8080/h2-console` — JDBC URL
`jdbc:h2:mem:deliveryrisk`, user `sa`, no password.

## Build a jar

```bash
mvn clean package
java -jar target/platform-0.1.0.jar
```

## Endpoints

| Method | Path                     | Description                                  |
|--------|--------------------------|-----------------------------------------------|
| GET    | `/api/projects`          | List all projects                             |
| GET    | `/api/projects/{id}`     | Get one project                               |
| GET    | `/api/sprints`           | List all sprints (`?projectId=` to filter)    |
| GET    | `/api/bugs`              | List all bugs (`?projectId=`, `?openOnly=true`)|
| GET    | `/api/dashboard/summary` | Portfolio health, sprint & delivery rates, high-risk count — powers the dashboard stat cards |
| GET    | `/api/dashboard/risk`    | All projects ranked by computed risk score — powers the Risk page |

## Connecting the React frontend

In the frontend's `src/api/portfolio.js`, replace the mock functions with
real `fetch()` calls to this API, e.g.:

```js
export async function fetchProjects() {
  const res = await fetch("http://localhost:8080/api/projects");
  return res.json();
}
```

Default CORS is configured to allow `http://localhost:5173` (the Vite dev
server) — adjust `app.cors.allowed-origins` in `application.properties` for
other origins or production.

## Project structure

```
src/main/java/com/deliveryrisk/platform/
  model/        JPA entities: Project, Sprint, Bug + enums
  repository/   Spring Data JPA repositories
  service/      RiskEngineService (core scoring logic), ProjectService,
                SprintService, BugService, DashboardService (aggregation)
  controller/   REST controllers + global exception handler
  dto/          Response DTOs
  config/       CORS config, DataSeeder (sample data on startup)
src/test/java/  Unit tests for RiskEngineService
```

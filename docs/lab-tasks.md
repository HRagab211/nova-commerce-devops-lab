# Lab Tasks

## Phase 1 — Application understanding

- Read the Java package structure.
- Run the tests.
- Start the app locally.
- Call `/actuator/health` and `/api/devops/lab-check`.
- Create one tenant, one customer, one product, and one order.

## Phase 2 — Vagrant topology

- Boot all machines with `vagrant up --no-parallel`.
- Verify each hostname and private IP.
- Verify SSH access to every machine.
- Document the network map.

## Phase 3 — Database node

- Install PostgreSQL on `db01`.
- Create the application database and user.
- Restrict external access to the private lab network.
- Verify the app can run Flyway migrations against `db01`.

## Phase 4 — Cache node

- Install Redis on `cache01`.
- Bind Redis to the private network.
- Configure the application to use this host.
- Prove that cache service failures do not fully break health checks unless readiness is configured to require it.

## Phase 5 — Queue node

- Install RabbitMQ on `mq01`.
- Create the application user and vhost.
- Enable management UI only inside the private network.
- Enable `LAB_RABBITMQ_ENABLED=true` and verify order events publish.

## Phase 6 — App node

- Install Java 21 runtime on `app01`.
- Build the JAR from the repository.
- Create a systemd service for the Spring Boot app.
- Move secrets and URLs into environment files.
- Configure logs and restart behavior.

## Phase 7 — Web node

- Install Nginx on `web01`.
- Reverse proxy traffic to `app01:8080`.
- Add an upstream block.
- Add health endpoint routing.
- Add basic security headers.

## Phase 8 — Monitoring node

- Install Prometheus and Grafana on `monitor01`.
- Scrape `/actuator/prometheus` from the application.
- Add dashboards for JVM, HTTP latency, error rates, and host resources.
- Add at least two alert rules.

## Phase 9 — CI/CD

- Run tests on each push.
- Package the app as a JAR.
- Add build artifacts.
- Add a deployment strategy to `app01`.
- Add rollback notes.

## Phase 10 — Failure drills

- Stop PostgreSQL and observe application readiness.
- Stop RabbitMQ and create orders with publishing disabled/enabled.
- Break Nginx upstream and identify the error path.
- Fill disk on a test machine and document the failure.
- Restart app service under load and observe recovery.

# Acceptance Criteria

## Application

- `mvn test` passes.
- `/actuator/health` is reachable without API key.
- `/api/devops/lab-check` requires `X-Lab-Key`.
- Tenants, customers, products, orders, and shipments APIs work.
- Order creation reduces product stock.
- Paid orders can create shipments.

## Infrastructure

- All Vagrant machines boot independently.
- Machines can resolve and reach each other using private IPs.
- PostgreSQL runs only on `db01`.
- Redis runs only on `cache01`.
- RabbitMQ runs only on `mq01`.
- Spring Boot app runs only on `app01`.
- Nginx runs only on `web01`.
- Prometheus/Grafana run only on `monitor01`.

## Automation

- Ansible roles are idempotent.
- Secrets are not hardcoded in playbooks.
- Service units restart automatically on failure.
- Configuration changes trigger handlers.

## Observability

- Prometheus scrapes application metrics.
- Grafana dashboard shows JVM memory, HTTP requests, error rate, and uptime.
- Logs can be inspected from the app machine.

## Documentation

- Architecture diagram is added by the student.
- Deployment runbook is added by the student.
- Failure drill report is added by the student.

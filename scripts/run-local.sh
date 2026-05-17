#!/usr/bin/env bash
set -euo pipefail

export DB_URL="${DB_URL:-jdbc:postgresql://localhost:5432/nova_commerce}"
export DB_USERNAME="${DB_USERNAME:-nova}"
export DB_PASSWORD="${DB_PASSWORD:-nova_secret}"
export LAB_API_KEY="${LAB_API_KEY:-devops-lab-key}"
export LAB_RABBITMQ_ENABLED="${LAB_RABBITMQ_ENABLED:-false}"

mvn spring-boot:run

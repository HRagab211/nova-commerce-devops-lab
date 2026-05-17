#!/usr/bin/env bash
set -euo pipefail

BASE_URL="${BASE_URL:-http://localhost:8080}"
API_KEY="${LAB_API_KEY:-devops-lab-key}"

curl -fsS "${BASE_URL}/actuator/health" >/dev/null
curl -fsS -H "X-Lab-Key: ${API_KEY}" "${BASE_URL}/api/devops/lab-check" >/dev/null

echo "Smoke test passed for ${BASE_URL}"

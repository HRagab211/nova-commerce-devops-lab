#!/usr/bin/env bash
set -euo pipefail

HOSTNAME_ARG="${1:-unknown}"
ROLE_ARG="${2:-unknown}"

echo "[nova-lab] bootstrapping ${HOSTNAME_ARG} role=${ROLE_ARG}"

export DEBIAN_FRONTEND=noninteractive
apt-get update -y
apt-get install -y curl vim git unzip net-tools ca-certificates gnupg lsb-release python3 python3-pip

cat >/etc/motd <<MOTD
Nova Commerce DevOps Lab
Machine: ${HOSTNAME_ARG}
Role: ${ROLE_ARG}

This machine is intentionally not fully provisioned.
Use the PDF/tasks/docs and Ansible skeleton to complete the lab.
MOTD

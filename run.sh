#!/bin/bash

# Load .env
if [ -f .env ]; then
  export $(grep -v '^#' .env | xargs)
fi

# Ensure a service name was provided
if [ -z "$1" ]; then
  echo "Usage: ./run.sh <service-name>"
  exit 1
fi

SERVICE=$1
SCRIPT_PATH="./$SERVICE-service/$SERVICE.sh"

if [ -f "$SCRIPT_PATH" ]; then
  echo "🔧 Running $SERVICE-service..."
  (cd "$SERVICE-service" && bash "$SERVICE.sh")
else
  echo "❌ No script found for service: $SERVICE"
  exit 1
fi
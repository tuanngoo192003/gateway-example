# Use Eclipse Temurin JDK 21.0.3 with Ubuntu 22.04
FROM eclipse-temurin:21.0.3_9-jdk-jammy

# Install base development tools
RUN apt-get update && apt-get install -y \
    maven \
    git \
    curl \
    wget \
    unzip \
    nano \
    vim \
    net-tools \
    iputils-ping \
    && rm -rf /var/lib/apt/lists/*

# Set working directory
RUN mkdir -p /workspace
WORKDIR /workspace

# Spring Boot Certificate Authentication MVP

A minimal Spring Boot application demonstrating **certificate-based authentication** with management features for:
- Allowed Certificate Authorities (CAs)
- Allowed certificates
- Mapping certificates to application users
- Secured REST endpoints for testing authentication

This is an MVP intended for experimentation, learning, and as a starting point for production-ready implementations.

---

## Features
- 🔐 **X.509 client certificate authentication** via HTTPS/TLS
- 📋 Manage **allowed Certificate Authorities**
- 👤 Manage **allowed certificates** and their associated users
- 🛡 Example **secured resources** that require valid client certificates

---

## Requirements
- Java 21+
- Maven 3.8+
- A valid CA and client certificates for testing
- (Optional) Docker for running in a container

---

You can check this [file](scripts/certificates.md) for more information on how to generate the certificates.
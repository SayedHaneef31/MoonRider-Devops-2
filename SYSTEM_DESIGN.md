# System Design: Product Catalogue Microservice


## 📂 Directory Structure
.
├── src/
├── k8s/
│   ├── v1.0.0/
│   ├── v1.1.0/
│   ├── v2.0.0/
│   └── ingress.yaml
├── Dockerfile
├── README.md
├── SYSTEM_DESIGN.md
└── CHANGELOG.md


---

## 🎯 Objective

Design a versioned product catalogue microservice that supports isolated deployment of multiple API versions via Kubernetes and allows smooth routing using NGINX Ingress.

---

## 🧱 Architecture Overview

### 1. **Microservice Structure**

- Built using **Spring Boot**
- REST API exposing:
  - `/health`
  - `/products`
  - `/products/search`

### 2. **Versioning Strategy**

- Semantic Versioning: `v1.0.0`, `v1.1.0`, `v2.0.0`
- Each version is maintained in a separate Docker image
- Deployed in **separate namespaces** in Kubernetes

### 3. **Containerization**

- Docker multi-stage builds
- Slim base image (e.g., OpenJDK Alpine)
- Secure environment variable usage via `.env` or ConfigMap

### 4. **Kubernetes Setup**

- **Namespace Isolation**: Each version in its own namespace
- **Deployment YAMLs**:
  - Resource limits defined
  - Readiness/liveness probes
- **HPA** enabled for auto-scaling based on CPU
- **Services** exposed as ClusterIP

### 5. **Ingress Routing**

- NGINX Ingress Controller configured via `ingress.yaml`
- Routes:
  - `/v1` → `product-service` in `product-v1`
  - `/v1.1` → `product-service` in `product-v1-1`
  - `/v2` → `product-service` in `product-v2`

---

## 🔁 CI/CD Pipeline

- GitHub Actions configured to:
  - Trigger on version tags (vX.Y.Z)
  - Build and push Docker image to Docker Hub
- Can be extended to apply K8s manifests via `kubectl` or ArgoCD

---

## 📈 Scalability

- HPA configured with:
  - CPU target utilization: 50%
  - Min 1 pod, Max 5 pods
- Future ready for multi-node clusters

---

## 📜 Assumptions

- Only 3 versions active at any time
- No breaking changes across minor versions
- Manual DB migrations (can add Flyway later)

---

## 🔒 Security (Future Scope)

- Add Spring Security for basic auth/token validation
- Use Kubernetes secrets instead of hardcoded env vars

---

## 🛠 Improvements

- Switch to PostgreSQL StatefulSet for DB persistence
- Add Prometheus + Grafana monitoring stack
- Use Helm Charts for deployment templating

---

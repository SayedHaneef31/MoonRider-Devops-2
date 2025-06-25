# Product Catalogue Microservice

A versioned product catalogue microservice implemented using Spring Boot, Docker, and deployed on Kubernetes with NGINX Ingress, HPA, and resource limits.

---

## 🚀 Features

- **v1.0.0**: `/health`, `/products`
- **v1.1.0**: Adds `/products/search?keyword=...`
- **v2.0.0**: Enhances search with query parameters, filters, and error handling

---

## 🛠️ Technologies

- Java + Spring Boot
- PostgreSQL
- Docker (multi-stage builds)
- Kubernetes (Minikube)
- NGINX Ingress Controller
- Horizontal Pod Autoscaler (HPA)
- GitHub Actions (for CI/CD)

---

## 📦 Local Development

1. **Start PostgreSQL**:
   ```bash
   docker run --name postgres -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres
   
2. **Run the app:**
   ```bash
   ./mvnw spring-boot:run

3. **Test Endpoints:**
   ```bash
   http://localhost:9191/health
   http://localhost:9191/products

## 🐳 Docker Build
```bash
docker build -t sayed390/product-catalogue:v1.0.0 .
docker push sayed390/product-catalogue:v1.0.0
```

☸️ Kubernetes Deployment

1. **Start Minikube & Tunnel:**:
   ```bash
   minikube start
   minikube tunnel
 
2. **Create Namespaces:**
   ```bash
   kubectl create ns product-v1
   kubectl create ns product-v1-1
   kubectl create ns product-v2
   
3. **Apply Ingress:**
   ```bash
   kubectl apply -f k8s/ingress.yaml

4. **Add Host Mapping (Windows):**
   Open Notepad as Admin
   Add this line to C:\Windows\System32\drivers\etc\hosts:  192.168.49.2 product.local
   
3. **Apply Ingress:**
   ```bash
   http://product.local/v1/health
   http://product.local/v1.1/health
   http://product.local/v2/health


   kubectl apply -f k8s/ingress.yaml

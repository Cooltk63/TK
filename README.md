# ------------------------------------------------------------
# a separate namespace to keep everything in one “room”
# ------------------------------------------------------------
apiVersion: v1
kind: Namespace
metadata:
  name: fincore

xxxx

# ------------------------------------------------------------
# 1) Secret that stores the Redis password (do NOT commit real passwords)
#    We use stringData for readability; K8s will base64 it.
# ------------------------------------------------------------
apiVersion: v1
kind: Secret
metadata:
  name: redis-auth
  namespace: fincore
type: Opaque
stringData:
  REDIS_PASSWORD: "ChangeThis_Prod_Strong_Pass123!"

---
# ------------------------------------------------------------
# 2) PersistentVolumeClaim for Redis data (AppendOnlyFile + RDB)
#    - storageClassName: "" means: use default StorageClass
#      (your cluster usually has one; leave it empty to take default)
# ------------------------------------------------------------
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: redis-data
  namespace: fincore
spec:
  accessModes: [ ReadWriteOnce ]
  resources:
    requests:
      storage: 5Gi

---
# ------------------------------------------------------------
# 3) Deployment for redis/redis-stack-server
#    - runs Redis OSS + modules (Search, JSON, Bloom, TS, Gears)
#    - sets password via --requirepass
#    - enables AOF for durability (appendonly yes)
#    - mounts PVC at /data (default Redis dir)
# ------------------------------------------------------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: redis
  namespace: fincore
spec:
  replicas: 1
  selector:
    matchLabels:
      app: redis
  template:
    metadata:
      labels:
        app: redis
    spec:
      containers:
        - name: redis
          image: redis/redis-stack-server:7.4.0-v0
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 6379
          # REDIS_ARGS lets us pass normal "redis-server" flags
          env:
            - name: REDIS_ARGS
              value: >-
                --appendonly yes
                --requirepass $(REDIS_PASSWORD)
            - name: REDIS_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: redis-auth
                  key: REDIS_PASSWORD
          volumeMounts:
            - name: redis-data
              mountPath: /data
          # simple liveness/readiness: PING redis over TCP
          # (for deep checks, use a sidecar that runs redis-cli AUTH + PING)
          readinessProbe:
            tcpSocket:
              port: 6379
            initialDelaySeconds: 5
            periodSeconds: 10
          livenessProbe:
            tcpSocket:
              port: 6379
            initialDelaySeconds: 15
            periodSeconds: 20
          resources:
            requests:
              cpu: "100m"
              memory: "256Mi"
            limits:
              cpu: "500m"
              memory: "1Gi"
      volumes:
        - name: redis-data
          persistentVolumeClaim:
            claimName: redis-data

---
# ------------------------------------------------------------
# 4) ClusterIP Service for Redis
#    - other pods will connect to "redis:6379"
# ------------------------------------------------------------
apiVersion: v1
kind: Service
metadata:
  name: redis
  namespace: fincore
spec:
  type: ClusterIP
  selector:
    app: redis
  ports:
    - name: redis
      port: 6379
      targetPort: 6379

xxx

# ------------------------------------------------------------
# JWT secret for HS256 (base64-encoded 32+ bytes)
# Replace the value with YOUR real base64 secret
# ------------------------------------------------------------
apiVersion: v1
kind: Secret
metadata:
  name: jwt-secret
  namespace: fincore
type: Opaque
stringData:
  HMAC_SECRET: "bWV0aGlvbnlsdGhyZW9ueWx0aHJlb255bGdsdXRhbWlueWxhbGFueWw="

xxx


# ------------------------------------------------------------
# Product Service Deployment
# ------------------------------------------------------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: product-service
  namespace: fincore
spec:
  replicas: 2
  selector:
    matchLabels:
      app: product-service
  template:
    metadata:
      labels:
        app: product-service
    spec:
      containers:
        - name: product-service
          image: <your-registry>/fincore/product-service:1.0.0  # <-- change this
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8081
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: "prod"
          readinessProbe:
            httpGet:
              path: /actuator/health/readiness
              port: 8081
            initialDelaySeconds: 10
          livenessProbe:
            httpGet:
              path: /actuator/health/liveness
              port: 8081
            initialDelaySeconds: 20
          resources:
            requests: { cpu: "100m", memory: "128Mi" }
            limits:   { cpu: "300m", memory: "256Mi" }
---
# ------------------------------------------------------------
# Product Service ClusterIP
# DNS: "product-service.fincore.svc.cluster.local"
# ------------------------------------------------------------
apiVersion: v1
kind: Service
metadata:
  name: product-service
  namespace: fincore
spec:
  type: ClusterIP
  selector:
    app: product-service
  ports:
    - name: http
      port: 8081
      targetPort: 8081

xxx

# ------------------------------------------------------------
# Fincore Service Deployment
# ------------------------------------------------------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: fincore-service
  namespace: fincore
spec:
  replicas: 2
  selector:
    matchLabels:
      app: fincore-service
  template:
    metadata:
      labels:
        app: fincore-service
    spec:
      containers:
        - name: fincore-service
          image: <your-registry>/fincore/fincore-service:1.0.0  # <-- change this
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8089
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: "prod"
          readinessProbe:
            httpGet:
              path: /actuator/health/readiness
              port: 8089
            initialDelaySeconds: 10
          livenessProbe:
            httpGet:
              path: /actuator/health/liveness
              port: 8089
            initialDelaySeconds: 20
          resources:
            requests: { cpu: "100m", memory: "128Mi" }
            limits:   { cpu: "300m", memory: "256Mi" }
---
# ------------------------------------------------------------
# Fincore Service ClusterIP
# DNS: "fincore-service.fincore.svc.cluster.local"
# ------------------------------------------------------------
apiVersion: v1
kind: Service
metadata:
  name: fincore-service
  namespace: fincore
spec:
  type: ClusterIP
  selector:
    app: fincore-service
  ports:
    - name: http
      port: 8089
      targetPort: 8089


xxxx


# ------------------------------------------------------------
# API Gateway Deployment
# ------------------------------------------------------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: api-gateway
  namespace: fincore
spec:
  replicas: 2
  selector:
    matchLabels:
      app: api-gateway
  template:
    metadata:
      labels:
        app: api-gateway
    spec:
      containers:
        - name: api-gateway
          image: <your-registry>/fincore/api-gateway:1.0.0  # <-- change this
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8080
          env:
            # ---- Activate prod profile
            - name: SPRING_PROFILES_ACTIVE
              value: "prod"

            # ---- JWT settings (non-secret values)
            - name: SECURITY_JWT_MODE
              value: "hmac"
            - name: SECURITY_JWT_TTL_SECONDS
              value: "900"
            - name: SECURITY_JWT_BYPASS_PATHS
              value: "/auth/login,/actuator/**"

            # ---- JWT secret (SECRET)
            - name: SECURITY_JWT_HMAC_BASE64_SECRET
              valueFrom:
                secretKeyRef:
                  name: jwt-secret
                  key: HMAC_SECRET

            # ---- Redis wiring: talk to the "redis" service on 6379
            - name: SPRING_DATA_REDIS_HOST
              value: "redis"
            - name: SPRING_DATA_REDIS_PORT
              value: "6379"
            - name: SPRING_DATA_REDIS_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: redis-auth
                  key: REDIS_PASSWORD

          readinessProbe:
            httpGet:
              path: /actuator/health/readiness
              port: 8080
            initialDelaySeconds: 10
            periodSeconds: 10
            failureThreshold: 6
          livenessProbe:
            httpGet:
              path: /actuator/health/liveness
              port: 8080
            initialDelaySeconds: 20
            periodSeconds: 20
          resources:
            requests:
              cpu: "200m"
              memory: "256Mi"
            limits:
              cpu: "500m"
              memory: "512Mi"

---
# ------------------------------------------------------------
# API Gateway ClusterIP Service (internal)
# We'll expose it via Ingress next.
# ------------------------------------------------------------
apiVersion: v1
kind: Service
metadata:
  name: api-gateway
  namespace: fincore
spec:
  type: ClusterIP
  selector:
    app: api-gateway
  ports:
    - name: http
      port: 8080
      targetPort: 8080


xxx

# ------------------------------------------------------------
# Ingress that routes external traffic to the API Gateway Service
# - Requires an Ingress Controller (e.g. nginx)
# - Replace "api.example.com" with your real host
# ------------------------------------------------------------
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: api-gateway
  namespace: fincore
  annotations:
    nginx.ingress.kubernetes.io/proxy-body-size: "10m"
spec:
  ingressClassName: nginx
  rules:
    - host: api.example.com   # <-- change this
      http:
        paths:
          - path: /
            pathType: Prefix
            backend:
              service:
                name: api-gateway
                port:
                  number: 8080

xxx

# ------------------------------------------------------------
# Default deny ALL ingress in the namespace
# (Nothing can talk to anything unless allowed later)
# Requires a CNI that enforces NetworkPolicies (Calico, Antrea, Cilium, etc.)
# ------------------------------------------------------------
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: default-deny
  namespace: fincore
spec:
  podSelector: {}
  policyTypes:
    - Ingress

---
# ------------------------------------------------------------
# Allow Product Service ONLY from API Gateway pods (port 8081)
# ------------------------------------------------------------
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: product-allow-from-gateway
  namespace: fincore
spec:
  podSelector:
    matchLabels:
      app: product-service
  policyTypes: [Ingress]
  ingress:
    - from:
        - podSelector:
            matchLabels:
              app: api-gateway
      ports:
        - protocol: TCP
          port: 8081

---
# ------------------------------------------------------------
# Allow Fincore Service ONLY from API Gateway pods (port 8089)
# ------------------------------------------------------------
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: fincore-allow-from-gateway
  namespace: fincore
spec:
  podSelector:
    matchLabels:
      app: fincore-service
  policyTypes: [Ingress]
  ingress:
    - from:
        - podSelector:
            matchLabels:
              app: api-gateway
      ports:
        - protocol: TCP
          port: 8089

---
# ------------------------------------------------------------
# Allow Redis ONLY from API Gateway pods (port 6379)
# (adjust if you need admin pods to access Redis too)
# ------------------------------------------------------------
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: redis-allow-from-gateway
  namespace: fincore
spec:
  podSelector:
    matchLabels:
      app: redis
  policyTypes: [Ingress]
  ingress:
    - from:
        - podSelector:
            matchLabels:
              app: api-gateway
      ports:
        - protocol: TCP
          port: 6379

xxx

# 1) namespace
kubectl apply -f 00-namespace.yaml

# 2) redis (secret, pvc, deployment, service)
kubectl apply -f 10-redis.yaml
kubectl get pods -n fincore
kubectl logs -n fincore deploy/redis

# 3) jwt secret
kubectl apply -f 20-jwt-secret.yaml

# 4) product & fincore services
kubectl apply -f 30-product.yaml
kubectl apply -f 31-fincore.yaml
kubectl get svc,pods -n fincore

# 5) api-gateway
kubectl apply -f 40-api-gateway.yaml
kubectl logs -n fincore deploy/api-gateway

# 6) ingress (if you have one)
kubectl apply -f 50-ingress.yaml
kubectl get ingress -n fincore


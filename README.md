# namespace.yaml
apiVersion: v1
kind: Namespace
metadata:
  name: dev

xxx

# jwt-secret.yaml
apiVersion: v1
kind: Secret
metadata:
  name: jwt-secret
  namespace: dev
type: Opaque
data:
  hmac-base64-secret: c29tZXNlY3JldGtleQ==   # base64("somesecretkey")

xxxx

# redis.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: redis
  namespace: dev
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
          image: redis/redis-stack-server:latest
          ports:
            - containerPort: 6379
---
apiVersion: v1
kind: Service
metadata:
  name: redis
  namespace: dev
spec:
  type: ClusterIP
  ports:
    - port: 6379
      targetPort: 6379
  selector:
    app: redis

xxx


# product-service.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: product-service
  namespace: dev
spec:
  replicas: 1
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
          image: product-service:latest   # your docker built image
          ports:
            - containerPort: 8081
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: dev
            - name: GATEWAY_BASE_URL
              value: http://api-gateway:8080
---
apiVersion: v1
kind: Service
metadata:
  name: product-service
  namespace: dev
spec:
  type: ClusterIP
  ports:
    - port: 8081
      targetPort: 8081
  selector:
    app: product-service

xxx


# fincore-service.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: fincore-service
  namespace: dev
spec:
  replicas: 1
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
          image: fincore-service:latest   # your docker built image
          ports:
            - containerPort: 8089
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: dev
            - name: GATEWAY_BASE_URL
              value: http://api-gateway:8080
---
apiVersion: v1
kind: Service
metadata:
  name: fincore-service
  namespace: dev
spec:
  type: ClusterIP
  ports:
    - port: 8089
      targetPort: 8089
  selector:
    app: fincore-service

xxz

# api-gateway.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: api-gateway
  namespace: dev
spec:
  replicas: 1
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
          image: api-gateway:latest   # your docker built image
          ports:
            - containerPort: 8080
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: dev
            - name: SPRING_DATA_REDIS_HOST
              value: redis
            - name: SPRING_DATA_REDIS_PORT
              value: "6379"
            - name: SECURITY_JWT_MODE
              value: hmac
            - name: SECURITY_JWT_HMAC-BASE64-SECRET
              valueFrom:
                secretKeyRef:
                  name: jwt-secret
                  key: hmac-base64-secret
---
apiVersion: v1
kind: Service
metadata:
  name: api-gateway
  namespace: dev
spec:
  type: NodePort
  ports:
    - port: 8080
      targetPort: 8080
      nodePort: 30080   # Expose gateway on localhost:30080
  selector:
    app: api-gateway


xxx


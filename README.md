lapiVersion: apps/v1
kind: Deployment
metadata:
  name: redis
  labels:
    app: redis
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
          image: redis:7
          ports:
            - containerPort: 6379
---
apiVersion: v1
kind: Service
metadata:
  name: redis
  labels:
    app: redis
spec:
  selector:
    app: redis
  ports:
    - port: 6379
      targetPort: 6379
  type: ClusterIP

xx

apiVersion: apps/v1
kind: Deployment
metadata:
  name: fincore-service
  labels:
    app: fincore-service
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
          image: fincore-service:latest
          imagePullPolicy: Never
          ports:
            - containerPort: 8089
---
apiVersion: v1
kind: Service
metadata:
  name: fincore-service
  labels:
    app: fincore-service
spec:
  selector:
    app: fincore-service
  ports:
    - port: 8089
      targetPort: 8089
  type: ClusterIP

xx

apiVersion: apps/v1
kind: Deployment
metadata:
  name: product-service
  labels:
    app: product-service
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
          image: product-service:latest
          imagePullPolicy: Never
          ports:
            - containerPort: 8090
---
apiVersion: v1
kind: Service
metadata:
  name: product-service
  labels:
    app: product-service
spec:
  selector:
    app: product-service
  ports:
    - port: 8090
      targetPort: 8090
  type: ClusterIP

xxx


apiVersion: apps/v1
kind: Deployment
metadata:
  name: api-gateway
  labels:
    app: api-gateway
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
          image: api-gateway:latest
          imagePullPolicy: Never
          ports:
            - containerPort: 8080
---
apiVersion: v1
kind: Service
metadata:
  name: api-gateway
  labels:
    app: api-gateway
spec:
  selector:
    app: api-gateway
  ports:
    - port: 8080
      targetPort: 8080
  type: NodePort   # 👈 exposed to Postman

xx



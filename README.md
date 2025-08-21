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
          image: product-service:latest
          imagePullPolicy: Never
          ports:
            - containerPort: 8081
---
apiVersion: v1
kind: Service
metadata:
  name: product-service
  namespace: dev
spec:
  type: ClusterIP
  selector:
    app: product-service
  ports:
    - port: 8081
      targetPort: 8081

xxx

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
          image: fincore-service:latest
          imagePullPolicy: Never
          ports:
            - containerPort: 8089
---
apiVersion: v1
kind: Service
metadata:
  name: fincore-service
  namespace: dev
spec:
  type: ClusterIP
  selector:
    app: fincore-service
  ports:
    - port: 8089
      targetPort: 8089

xxx

kubectl apply -f product-service.yaml -n dev
kubectl apply -f fincore-service.yaml -n dev
kubectl get svc -n dev
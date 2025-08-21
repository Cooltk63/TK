lets just focus on one thing i have already running the 4 containers
1- name : redis,Port: 6379, Image name :redis/redis-stack-server
2- name : product-service, Port:8081, Image name :product-service
3-name :fincore-service, Port: 8089, Image name :fincore-service 
4-name: api-gateway,Port: 8080, Image name :api-gateway

I am using the docker desktop kubernates for testing on local machine and currently i wanted the endpoint only on postman which is local machine like localhost:30020/Auth/login like endpoint
Right now I dont have the domain access or not yet created so guide me how did i deploy my conatiners or images on docker kuberntes and test using the postman (Only exposing the api-gateway port 8080 rest of the services call go though api-gateway as per requirement or use authentication JWT so do not access any other service endpoint directly only via port 8080 that is via api-gateway)

Some of Yaml File for Dev Environment I have created please guide me if anything wrong or changes required in yaml files

# namespace.yaml
apiVersion: v1
kind: Namespace
metadata:
  name: dev
 ------------------------------------------- 
  
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
 ------------------------------------------- 
	# jwt-secret.yaml
apiVersion: v1
kind: Secret
metadata:
  name: jwt-secret
  namespace: dev
type: Opaque
data:
  hmac-base64-secret: bWV0aGlvbnlsdGhyZW9ueWx0aHJlb255bGdsdXRhbWlueWxhbGFueWw=
   ------------------------------------------- 
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
   ------------------------------------------- 
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
	
	 ------------------------------------------- 
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

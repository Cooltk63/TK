@Bean
public WebClient gatewayWebClient(WebClient.Builder builder,
                                  @Value("${gateway.base-url:http://localhost:8080}") String baseUrl) {
    return builder.baseUrl(baseUrl).build();
}

xxx

@Autowired
private WebClient gatewayWebClient;

@PostMapping("/getFincoreList")
public Mono<List<String>> getFincoreList(@RequestHeader(value = "Authorization", required = false) String auth) {
    return gatewayWebClient.post()
            .uri("/fincore/getFinList")
            .headers(h -> { if (auth != null) h.set(HttpHeaders.AUTHORIZATION, auth); })
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<String>>() {});
}

xxx


# Product service
spring.cloud.gateway.routes[0].id=product-service
spring.cloud.gateway.routes[0].uri=http://product-service:8080
spring.cloud.gateway.routes[0].predicates[0]=Path=/products/**

# Fincore service
spring.cloud.gateway.routes[1].id=fincore-service
spring.cloud.gateway.routes[1].uri=http://fincore-service:8080
spring.cloud.gateway.routes[1].predicates[0]=Path=/fincore/**

# Orders service
spring.cloud.gateway.routes[2].id=orders-service
spring.cloud.gateway.routes[2].uri=http://orders-service:8080
spring.cloud.gateway.routes[2].predicates[0]=Path=/orders/**


xxxx

version: "3.9"
services:
  redis:
    image: redis:7
    command: ["redis-server", "--appendonly", "yes"]
    networks:
      - appnet
    ports:
      - "6379:6379"

  api-gateway:
    image: api-gateway:latest
    depends_on:
      - redis
      - product-service
      - fincore-service
    environment:
      - SPRING_PROFILES_ACTIVE=local
      - SPRING_DATA_REDIS_HOST=redis
    networks:
      - appnet
    ports:
      - "8080:8080"      # only gateway is exposed to host

  product-service:
    image: product-service:latest
    networks:
      - appnet
    environment:
      - SPRING_PROFILES_ACTIVE=local
      - GATEWAY_BASE_URL=http://api-gateway:8080
    # do NOT publish ports for this service

  fincore-service:
    image: fincore-service:latest
    networks:
      - appnet
    environment:
      - SPRING_PROFILES_ACTIVE=local
      - GATEWAY_BASE_URL=http://api-gateway:8080

networks:
  appnet:
    driver: bridge


xxx

apiVersion: apps/v1
kind: Deployment
metadata:
  name: api-gateway
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
          image: <your-docker-username>/api-gateway:latest   # <-- replace
          ports:
            - containerPort: 8080
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: "dev"
---
apiVersion: v1
kind: Service
metadata:
  name: api-gateway
spec:
  selector:
    app: api-gateway
  ports:
    - port: 8080
      targetPort: 8080
      nodePort: 30080   # expose outside cluster
  type: NodePort


xxxx


apiVersion: apps/v1
kind: Deployment
metadata:
  name: redis
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
          image: redis:7.2   # use official Redis
          ports:
            - containerPort: 6379
          resources:
            requests:
              memory: "256Mi"
              cpu: "250m"
            limits:
              memory: "512Mi"
              cpu: "500m"
---
apiVersion: v1
kind: Service
metadata:
  name: redis
spec:
  selector:
    app: redis
  ports:
    - port: 6379
      targetPort: 6379
  type: ClusterIP


xxx

apiVersion: apps/v1
kind: Deployment
metadata:
  name: fincore-service
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
          image: <your-docker-username>/fincore-service:latest   # <-- replace
          ports:
            - containerPort: 8080
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: "dev"
            - name: REDIS_HOST
              value: "redis"
            - name: REDIS_PORT
              value: "6379"
---
apiVersion: v1
kind: Service
metadata:
  name: fincore-service
spec:
  selector:
    app: fincore-service
  ports:
    - port: 8080
      targetPort: 8080
  type: ClusterIP


xxxx
apiVersion: apps/v1
kind: Deployment
metadata:
  name: product-service
spec:
  replicas: 2   # scale to 2 pods (you can change)
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
          image: <your-docker-username>/product-service:latest   # <-- replace
          ports:
            - containerPort: 8080
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: "dev"
            - name: REDIS_HOST
              value: "redis"   # Redis service name inside cluster
            - name: REDIS_PORT
              value: "6379"
---
apiVersion: v1
kind: Service
metadata:
  name: product-service
spec:
  selector:
    app: product-service
  ports:
    - port: 8080
      targetPort: 8080
  type: ClusterIP
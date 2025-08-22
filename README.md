product-service-prod.yaml ::
# product-service.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: product-service
  namespace: prod
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
          imagePullPolicy: Never
          ports:
            - containerPort: 8081
          envFrom:
            - configMapRef:
                name: api-gateway-config
            - secretRef:
                name: api-gateway-secrets
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: prod
            - name: GATEWAY_BASE_URL
              value: http://api-gateway:8080 

---
apiVersion: v1
kind: Service
metadata:
  name: product-service
  namespace: prod
spec:
  type: ClusterIP
  ports:
    - port: 8081
      targetPort: 8081
  selector:
    app: product-service

    prod-config.yaml ::
    apiVersion: v1
kind: ConfigMap
metadata:
name: api-gateway-config
namespace: prod
data:
DB_HOST: "My_SECRET"
DB_PORT: "My_SECRET"
DB_SID: "My_SECRET"
DB_USER: "My_SECRET"
REDIS_HOST: "redis"
REDIS_PORT: "6379"
REDIS_DB: "0"

prod-secrets.yaml ::
apiVersion: v1
kind: Secret
metadata:
  name: api-gateway-secrets
  namespace: prod
type: Opaque
stringData:
  JWT_SECRET: bWV0aGlvbnlsdGhyZW9ueWx0aHJlb255bGdsdXRhbWlueWxhbGFueWw=
  REDIS_PASSWORD: Cooltcs@@RedisPassHere
  DB_PASS: My_SECRET


getting console issue ::

E:\Kubernates Yaml Files\Prod-Grade>kubectl apply -f prod-config.yaml
error: error when retrieving current configuration of:
Resource: "/v1, Resource=configmaps", GroupVersionKind: "/v1, Kind=ConfigMap"
Name: "", Namespace: "default"
from server for: "prod-config.yaml": resource name may not be empty


isthere any issue in yaml files or anything else we can improve make code easy and unserstandable

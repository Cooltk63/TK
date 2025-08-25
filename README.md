# Ingress for DEV environment
# This will expose API Gateway on a fake host (api.local.test)
# You must add "127.0.0.1 api.local.test" in your /etc/hosts file
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: api-gateway-ingress
  namespace: dev
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  ingressClassName: nginx
  rules:
    - host: api.local.test   # fake hostname for local testing
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

# Deny all traffic by default in namespace
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: deny-all
  namespace: dev
spec:
  podSelector: {}   # apply to all pods in dev namespace
  policyTypes:
    - Ingress
    - Egress

xxx

# Allow ingress traffic ONLY to API Gateway (from ingress controller)
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: allow-api-gateway-ingress
  namespace: dev
spec:
  podSelector:
    matchLabels:
      app: api-gateway
  policyTypes:
    - Ingress
  ingress:
    - from:
        - podSelector:
            matchLabels:
              app.kubernetes.io/component: controller  # NGINX ingress controller pods
      ports:
        - protocol: TCP
          port: 8080


xxx

# Allow API Gateway -> Product Service & Fincore Service
# Allow Product Service -> Fincore Service (service-to-service)
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: allow-internal-calls
  namespace: dev
spec:
  podSelector: {}
  policyTypes:
    - Ingress
  ingress:
    - from:
        - podSelector:
            matchLabels:
              app: api-gateway
        - podSelector:
            matchLabels:
              app: product-service
        - podSelector:
            matchLabels:
              app: fincore-service

xxx

# Allow services to connect to external DB (outside cluster)
# Example: Oracle/MySQL/Postgres running on 10.20.30.40:1521
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: allow-egress-db
  namespace: dev
spec:
  podSelector: {}
  policyTypes:
    - Egress
  egress:
    - to:
        - ipBlock:
            cidr: 10.20.30.40/32   # Replace with your DB IP
      ports:
        - protocol: TCP
          port: 1521               # Replace with your DB port


# Allow access to Redis pod (inside cluster)
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: allow-redis
  namespace: dev
spec:
  podSelector:
    matchLabels:
      app: redis
  policyTypes:
    - Ingress
  ingress:
    - from:
        - podSelector:
            matchLabels:
              app: api-gateway
      ports:
        - protocol: TCP
          port: 6379


xxx


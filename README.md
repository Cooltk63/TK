# ensure k8s is running (Docker Desktop -> Kubernetes ON)
kubectl version --short
kubectl config current-context

# create namespace
kubectl apply -f namespace.yaml

# apply secret first (so deployment env can resolve)
kubectl apply -f jwt-secret.yaml

# apply redis, product-service, fincore-service, api-gateway
kubectl apply -f redis.yaml
kubectl apply -f product-service.yaml
kubectl apply -f fincore-service.yaml
kubectl apply -f api-gateway.yaml

# watch rollout
kubectl -n dev get pods -w
# in another terminal you can check services
kubectl -n dev get svc


xx

# forward local 30020 to service/api-gateway:8080
kubectl -n dev port-forward svc/api-gateway 30020:8080

xxx

kubectl -n dev get pods,svc
kubectl -n dev logs deploy/api-gateway
kubectl -n dev exec -it <pod-of-api-gateway> -- curl -sv http://product-service:8081/health

xxx

kubectl -n dev exec -it deploy/api-gateway -- sh -c "curl -sS http://product-service:8081/actuator/health"
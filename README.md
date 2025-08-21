
E:\Kubernates Yaml Files\Dev-Deployment\New Test>kubectl get pods -n dev
NAME                               READY   STATUS             RESTARTS        AGE
api-gateway-d9c699469-8ghd7        1/1     Running            0               10m
fincore-service-54cdd58949-kqnq9   0/1     CrashLoopBackOff   6 (3m31s ago)   10m
product-service-5c96b57f6b-fkgmp   0/1     CrashLoopBackOff   6 (3m10s ago)   10m
redis-7c5ff8ffcf-chpst             1/1     Running            0               10m

E:\Kubernates Yaml Files\Dev-Deployment\New Test>
E:\Kubernates Yaml Files\Dev-Deployment\New Test>
E:\Kubernates Yaml Files\Dev-Deployment\New Test>
E:\Kubernates Yaml Files\Dev-Deployment\New Test>kubectl describe pod fincore-service-54cdd58949-kqnq9 -n dev
Name:             fincore-service-54cdd58949-kqnq9
Namespace:        dev
Priority:         0
Service Account:  default
Node:             docker-desktop/192.168.65.3
Start Time:       Thu, 21 Aug 2025 14:37:30 +0530
Labels:           app=fincore-service
                  pod-template-hash=54cdd58949
Annotations:      <none>
Status:           Running
IP:               10.1.0.113
IPs:
  IP:           10.1.0.113
Controlled By:  ReplicaSet/fincore-service-54cdd58949
Containers:
  fincore-service:
    Container ID:   docker://4d44b333cae06f70cb8de8ed5e9a49da5f114df6dc5202e6fe9da36c5fa84a3f
    Image:          fincore-service:latest
    Image ID:       docker-pullable://fincore-service@sha256:23ae1801f8bdff62ecaaac32fe980f581128e1fdddb59d6ae676f0ea24a11d8d
    Port:           8089/TCP
    Host Port:      0/TCP
    State:          Waiting
      Reason:       CrashLoopBackOff
    Last State:     Terminated
      Reason:       Error
      Exit Code:    1
      Started:      Thu, 21 Aug 2025 14:44:11 +0530
      Finished:     Thu, 21 Aug 2025 14:44:16 +0530
    Ready:          False
    Restart Count:  6
    Environment:    <none>
    Mounts:
      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-5lngx (ro)
Conditions:
  Type                        Status
  PodReadyToStartContainers   True
  Initialized                 True
  Ready                       False
  ContainersReady             False
  PodScheduled                True
Volumes:
  kube-api-access-5lngx:
    Type:                    Projected (a volume that contains injected data from multiple sources)
    TokenExpirationSeconds:  3607
    ConfigMapName:           kube-root-ca.crt
    ConfigMapOptional:       <nil>
    DownwardAPI:             true
QoS Class:                   BestEffort
Node-Selectors:              <none>
Tolerations:                 node.kubernetes.io/not-ready:NoExecute op=Exists for 300s
                             node.kubernetes.io/unreachable:NoExecute op=Exists for 300s
Events:
  Type     Reason     Age                  From               Message
  ----     ------     ----                 ----               -------
  Normal   Scheduled  10m                  default-scheduler  Successfully assigned dev/fincore-service-54cdd58949-kqnq9 to docker-desktop
  Normal   Pulled     4m (x7 over 10m)     kubelet            Container image "fincore-service:latest" already present on machine
  Normal   Created    3m59s (x7 over 10m)  kubelet            Created container: fincore-service
  Normal   Started    3m59s (x7 over 10m)  kubelet            Started container fincore-service
  Warning  BackOff    10s (x45 over 10m)   kubelet            Back-off restarting failed container fincore-service in pod fincore-service-54cdd58949-kqnq9_dev(abc475aa-5ec5-48e7-81ba-7b21753f8325)

E:\Kubernates Yaml Files\Dev-Deployment\New Test>kubectl describe pod product-service-5c96b57f6b-fkgmp -n dev
Name:             product-service-5c96b57f6b-fkgmp
Namespace:        dev
Priority:         0
Service Account:  default
Node:             docker-desktop/192.168.65.3
Start Time:       Thu, 21 Aug 2025 14:37:23 +0530
Labels:           app=product-service
                  pod-template-hash=5c96b57f6b
Annotations:      <none>
Status:           Running
IP:               10.1.0.112
IPs:
  IP:           10.1.0.112
Controlled By:  ReplicaSet/product-service-5c96b57f6b
Containers:
  product-service:
    Container ID:   docker://c644097af3bcaa76d70e8e59293eed0b4ee1ef1ebdde4d33d4099a3d103430de
    Image:          product-service:latest
    Image ID:       docker-pullable://product-service@sha256:ad4ebe7533b0823757675d12aad17a740ecb2d7150cc60b619125effe6c47f9c
    Port:           8081/TCP
    Host Port:      0/TCP
    State:          Waiting
      Reason:       CrashLoopBackOff
    Last State:     Terminated
      Reason:       Error
      Exit Code:    1
      Started:      Thu, 21 Aug 2025 14:44:27 +0530
      Finished:     Thu, 21 Aug 2025 14:44:37 +0530
    Ready:          False
    Restart Count:  6
    Environment:    <none>
    Mounts:
      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-pblpm (ro)
Conditions:
  Type                        Status
  PodReadyToStartContainers   True
  Initialized                 True
  Ready                       False
  ContainersReady             False
  PodScheduled                True
Volumes:
  kube-api-access-pblpm:
    Type:                    Projected (a volume that contains injected data from multiple sources)
    TokenExpirationSeconds:  3607
    ConfigMapName:           kube-root-ca.crt
    ConfigMapOptional:       <nil>
    DownwardAPI:             true
QoS Class:                   BestEffort
Node-Selectors:              <none>
Tolerations:                 node.kubernetes.io/not-ready:NoExecute op=Exists for 300s
                             node.kubernetes.io/unreachable:NoExecute op=Exists for 300s
Events:
  Type     Reason     Age                 From               Message
  ----     ------     ----                ----               -------
  Normal   Scheduled  11m                 default-scheduler  Successfully assigned dev/product-service-5c96b57f6b-fkgmp to docker-desktop
  Normal   Pulled     4m9s (x7 over 11m)  kubelet            Container image "product-service:latest" already present on machine
  Normal   Created    4m8s (x7 over 11m)  kubelet            Created container: product-service
  Normal   Started    4m8s (x7 over 11m)  kubelet            Started container product-service
  Warning  BackOff    26s (x44 over 10m)  kubelet            Back-off restarting failed container product-service in pod product-service-5c96b57f6b-fkgmp_dev(ed1c857b-7fcd-4199-9de8-c3f25c28af9b)

E:\Kubernates Yaml Files\Dev-Deployment\New Test>

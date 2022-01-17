::For deployed applications to communicate with each other, you need to select a message broker. Rabbit MQ in this case
kubectl apply -f rabbitmq/

:: Maria DB
kubectl apply -f mariadb/

:: Prometheus and Grafana
kubectl apply -f prometheus/prometheus-clusterroles.yaml
kubectl apply -f prometheus/prometheus-clusterrolebinding.yaml
kubectl apply -f prometheus/prometheus-serviceaccount.yaml

kubectl apply -f prometheus-proxy/

kubectl apply -f prometheus/prometheus-configmap.yaml
kubectl apply -f prometheus/prometheus-deployment.yaml
kubectl apply -f prometheus/prometheus-service.yaml

kubectl apply -f grafana/

kubectl apply -f server/server-roles.yaml
kubectl apply -f server/server-rolebinding.yaml
kubectl apply -f server/service-account.yaml

kubectl apply -f skipper/

kubectl apply -f server/server-config.yaml

kubectl apply -f server/server-svc.yaml
kubectl apply -f server/server-deployment.yaml

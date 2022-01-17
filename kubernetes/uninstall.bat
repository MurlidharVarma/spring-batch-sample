::For deployed applications to communicate with each other, you need to select a message broker. Rabbit MQ in this case
kubectl delete -f rabbitmq/

:: Maria DB
kubectl delete -f mariadb/

kubectl delete -f server/server-roles.yaml
kubectl delete -f server/server-rolebinding.yaml
kubectl delete -f server/service-account.yaml

kubectl delete -f skipper/

kubectl delete -f server/server-config.yaml

kubectl delete -f server/server-svc.yaml
kubectl delete -f server/server-deployment.yaml

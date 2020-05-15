### ECS Service Autoscaling
This project consists of following modules:
- Spring boot module which exposes an API to get free memory in JVM
- Gatling performance testing module which put load on memory API
#### Build
Build images in the local registry:
```
mvn package
```
Build images in the local registry and push into the remote registry:
```
mvn deploy
```
Verify application:
```
http://localhost:8080/api/v1/autoscaling/memory
http://localhost:8081/actuator/health
```
#### Docker Compose
Bring up all services:
```
docker-compose up -d
```
Bring down all services:
```
docker-compose down
```
Status of all services:
```
docker-compose ps
```
Logs:
```
docker-compose logs -f --tail="all"
docker-compose logs -f --tail="all" ecs-memory
```
Get into the container:
```
docker exec -it ecsautoscaling_ecs-memory_1 /bin/sh
```
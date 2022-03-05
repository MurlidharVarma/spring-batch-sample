# Sample Spring Boot Batch

## Run as standalone job during startup

application.yml should have below 2 properties set
```properties
spring:
  batch:
    job:
      enabled: true
  main:
    web-application-type: none
```

## Run via Rest API
application.yml should have below 1 properties set. Remove the other by commenting it out.
```properties
spring:
  batch:
    job:
      enabled: false
#  main:
#    web-application-type: none

```

Access Batch trigger REST API
```
http://localhost:8080/batch/start/{chunkSize}
```
## About
Metrics monitoring example. 
Contains two microservices - **producer** and **consumer**.
Spring Kafka is used to pass metrics between microservices.
#### producer 
Exposes REST API endpoint to gather Actuator application metrics by given name and sending to Kafka topic.
#### consumer
Listens to the Kafka topic and stores the metric statistic in embedded H2 database.
Exposes REST API for browsing, searching, filtering, aggregation the metrics statistic.

---
### Kafka
Current configuration set up to use locally started Kafka server at *localhost:9092*
The microservices send/listen to topic with name configured by following property:
```
metrics.topic=metrics-topic
```
Kafka topic is created with default parameters.

---
### Build/Run application
The repo contains two microservices(producer and consumer), each uses Maven build.
To build each microservice, use mvnw (the Maven wrapper) at the command line:
```
./mvnw clean package
```
After building:
*Please see run details separately per each microservice.*
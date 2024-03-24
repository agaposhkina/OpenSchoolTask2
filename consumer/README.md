### Run
```
java -jar consumer/target/consumer-0.0.1-SNAPSHOT.jar
```
### After running
**H2** database console: http://localhost:8081/h2-console/

**REST API** documentation: http://localhost:8081/swagger-ui/index.html

### Filtering and searching:
For searching and filtering metrics and statistic, use **/api/v1/metrics/filter** endpoint.

Example of filter to get metrics with name "spring.kafka.template" and fetch its statistic types containing "O" and with value less than 3:
```
{
  "filters": [
    {
      "key": "name",
      "operator": "EQUAL",
      "value": "spring.kafka.template"
    },
    {
      "key": "statistic",
      "joinTable": "measurements",
      "operator": "MATCH",
      "value": "O"
    },
    {
      "key": "value",
      "joinTable": "measurements",
      "operator": "LESS",
      "value": 3
    }
  ],
  "pageNumber": 0,
  "pageSize": 20
}
```

Use "joinTable": "measurements" when filter applied to statistics, skip this to put condition on metric parameters.
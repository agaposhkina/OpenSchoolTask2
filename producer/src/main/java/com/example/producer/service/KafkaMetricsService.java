package com.example.producer.service;

import com.example.producer.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint.MetricDescriptor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMetricsService implements MetricsService {
    @Value("${metrics.topic}")
    private String metricsTopic;

    private final KafkaTemplate<Long, MetricDescriptor> kafkaTemplate;
    private final MetricsEndpoint actuatorMetrics;
    @Override
    public void sendMetric(String metricName) {
        MetricDescriptor metric = actuatorMetrics.metric(metricName, null);
        if (metric == null)
            throw new ResourceNotFoundException("Metric with name=" + metricName + " not found.");

        kafkaTemplate.send(metricsTopic, metric);
    }
}

package com.example.producer.config;

import com.example.producer.service.KafkaMetricsService;
import com.example.producer.service.MetricsService;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint.MetricDescriptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class AppConfig {
    @Bean
    public MetricsService metricsService(KafkaTemplate<Long, MetricDescriptor> kafkaTemplate,
                                         MetricsEndpoint actuatorMetrics) {
        return new KafkaMetricsService(kafkaTemplate, actuatorMetrics);
    }
}

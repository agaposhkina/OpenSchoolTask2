package com.example.consumer.config;

import com.example.consumer.repository.MeasurementRepository;
import com.example.consumer.repository.MetricRepository;
import com.example.consumer.service.MeasurementsService;
import com.example.consumer.service.MeasurementsServiceImpl;
import com.example.consumer.service.MetricsService;
import com.example.consumer.service.MetricsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MetricsService metricsService(MetricRepository metricRepository) {
        return new MetricsServiceImpl(metricRepository);
    }

    @Bean
    public MeasurementsService measurementsService(MeasurementRepository measurementRepository) {
        return new MeasurementsServiceImpl(measurementRepository);
    }
}

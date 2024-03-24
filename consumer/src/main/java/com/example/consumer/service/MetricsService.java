package com.example.consumer.service;

import com.example.consumer.domain.Metric;
import com.example.consumer.service.filter.FilterRequest;
import org.springframework.data.domain.Page;

public interface MetricsService {
    Metric saveMetric(Metric metric);
    Page<Metric> getAllMetrics(Integer pageNumber, Integer pageSize);
    Metric getMetricById(Long id);
    Page<Metric> filter(FilterRequest filter);
}

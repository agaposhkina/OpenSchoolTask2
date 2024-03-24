package com.example.consumer.service;

import com.example.consumer.domain.Metric;
import com.example.consumer.exception.ResourceNotFoundException;
import com.example.consumer.repository.MetricRepository;
import com.example.consumer.service.filter.FilterRequest;
import com.example.consumer.service.filter.FilterSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {
    private final MetricRepository metricRepository;

    @Override
    public Metric saveMetric(Metric metric) {
        return metricRepository.save(metric);
    }

    @Override
    public Page<Metric> getAllMetrics(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return metricRepository.findAll(pageable);
    }

    @Override
    public Metric getMetricById(Long id) {
        return metricRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No metric found for id = " + id));
    }

    @Override
    public Page<Metric> filter(FilterRequest filter) {
        FilterSpecification<Metric> specification = new FilterSpecification<>(filter);
        Pageable pageable = PageRequest.of(filter.getPageNumber(), filter.getPageSize());
        return metricRepository.findAll(specification, pageable);
    }
}

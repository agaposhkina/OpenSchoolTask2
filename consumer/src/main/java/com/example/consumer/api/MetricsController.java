package com.example.consumer.api;

import com.example.consumer.domain.Metric;
import com.example.consumer.service.MetricsService;
import com.example.consumer.service.filter.FilterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Metrics")
@RestController
@RequestMapping(path = "/api/v1/metrics")
@RequiredArgsConstructor
public class MetricsController {
    private final MetricsService metricsService;

    @Operation(summary = "Get metric by the given id")
    @GetMapping(path="/{id}")
    public Metric getMetricById(@PathVariable Long id) {
        return metricsService.getMetricById(id);
    }

    @Operation(summary = "Get all metrics")
    @GetMapping
    public Page<Metric> getAllMetrics(@RequestParam Integer pageNumber,
                                      @RequestParam Integer pageSize) {
        return metricsService.getAllMetrics(pageNumber, pageSize);
    }

    @Operation(summary = "Filter metrics by the provided conditions")
    @PostMapping(value = "/filter")
    public Page<Metric> filter(@Parameter(description = "Filtering conditions on metric fields and its set of measurements")
            @RequestBody FilterRequest filter) {
        return metricsService.filter(filter);
    }
}

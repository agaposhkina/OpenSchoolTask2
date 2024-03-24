package com.example.producer.api;

import com.example.producer.service.MetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Metrics")
@RestController
@RequestMapping(path = "/api/v1/metrics")
@RequiredArgsConstructor
public class MetricsController {
    private final MetricsService metricsService;

    @Operation(summary = "Sends an Actuator metric with the provided name to consumers")
    @PostMapping
    public void sendMetric(String metricName) {
        metricsService.sendMetric(metricName);
    }
}

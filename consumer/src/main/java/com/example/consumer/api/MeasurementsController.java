package com.example.consumer.api;

import com.example.consumer.domain.StatisticAvg;
import com.example.consumer.service.MeasurementsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Statistics")
@RestController
@RequestMapping(path = "/api/v1/statistics")
@RequiredArgsConstructor
public class MeasurementsController {
    private final MeasurementsService measurementsService;

    @Operation(summary = "Get average value per statistic type")
    @GetMapping(path = "/avg")
    public Iterable<StatisticAvg> getStatisticsAvg() {
        return measurementsService.getStatisticsAvg();
    }
}

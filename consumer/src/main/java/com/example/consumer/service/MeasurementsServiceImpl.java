package com.example.consumer.service;

import com.example.consumer.domain.StatisticAvg;
import com.example.consumer.repository.MeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasurementsServiceImpl implements MeasurementsService {
    private final MeasurementRepository measurementRepository;
    @Override
    public Iterable<StatisticAvg> getStatisticsAvg() {
        return measurementRepository.countAvgValueByStatisticType();
    }
}

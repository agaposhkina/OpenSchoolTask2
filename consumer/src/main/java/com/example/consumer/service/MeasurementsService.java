package com.example.consumer.service;

import com.example.consumer.domain.StatisticAvg;

public interface MeasurementsService {
    Iterable<StatisticAvg> getStatisticsAvg();
}

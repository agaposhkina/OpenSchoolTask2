package com.example.consumer.repository;

import com.example.consumer.domain.Measurement;
import com.example.consumer.domain.StatisticAvg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    @Query("SELECT m.statistic AS statistic, AVG(m.value) AS avgValue " +
            "FROM Measurement AS m GROUP BY m.statistic")
    Iterable<StatisticAvg> countAvgValueByStatisticType();
}

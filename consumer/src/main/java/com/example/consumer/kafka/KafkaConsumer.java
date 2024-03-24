package com.example.consumer.kafka;

import com.example.consumer.domain.Metric;
import com.example.consumer.service.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@EnableKafka
@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final MetricsService metricsService;
    @KafkaListener(topics="${metrics.topic}")
    public void listenMetrics(ConsumerRecord<Long, Metric> record) {
        Metric metric = record.value();
        log.info("New metric with name = {} received, timestamp = {}", metric.getName(), record.timestamp());

        metric.setTimestamp(record.timestamp());
        metricsService.saveMetric(metric);
    }
}

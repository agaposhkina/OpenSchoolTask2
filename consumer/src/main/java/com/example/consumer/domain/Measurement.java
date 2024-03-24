package com.example.consumer.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class Measurement {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String statistic;
    private Double value;

    @ManyToOne
    @JoinColumn(name= "metric_id", nullable = false)
    @JsonBackReference
    private Metric metric;
}

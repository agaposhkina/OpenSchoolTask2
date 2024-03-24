package com.example.consumer.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class Metric {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String baseUnit;
    private long timestamp;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "metric")
    @JsonManagedReference
    private List<Measurement> measurements;
}

package com.example.consumer.repository;

import com.example.consumer.domain.Metric;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Long>, JpaSpecificationExecutor<Metric> {
    @EntityGraph(attributePaths = "measurements") // Avoiding N+1 problem
    Page<Metric> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "measurements")
    Page<Metric> findAll(Specification<Metric> specification, Pageable pageable);
}

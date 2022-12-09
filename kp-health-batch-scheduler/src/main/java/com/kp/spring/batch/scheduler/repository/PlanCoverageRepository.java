package com.kp.spring.batch.scheduler.repository;

import java.util.List;

import com.kp.spring.batch.scheduler.model.PlanCoverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanCoverageRepository extends JpaRepository<PlanCoverage, Long> {
    List<PlanCoverage> findAll();
}
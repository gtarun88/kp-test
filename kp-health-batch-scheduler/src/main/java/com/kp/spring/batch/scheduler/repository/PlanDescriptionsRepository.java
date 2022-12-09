package com.kp.spring.batch.scheduler.repository;

import com.kp.spring.batch.scheduler.model.PlanDescriptions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanDescriptionsRepository extends JpaRepository<PlanDescriptions, Long> {
    List<PlanDescriptions> findAll();
}
package com.kp.spring.batch.scheduler.repository;

import com.kp.spring.batch.scheduler.model.PolicyData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessedTransactionRepository extends JpaRepository<PolicyData, Long> {

}
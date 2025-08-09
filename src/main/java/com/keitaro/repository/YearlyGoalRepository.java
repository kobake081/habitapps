package com.keitaro.repository;

import com.keitaro.model.YearlyGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearlyGoalRepository extends JpaRepository<YearlyGoal, Long> {
}

package com.keitaro.repository;

import com.keitaro.model.MonthlyGoal;
import com.keitaro.model.QuarterlyGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MonthlyGoalRepository extends JpaRepository<MonthlyGoal, Long> {
    List<MonthlyGoal> findByQuarterlyGoal(QuarterlyGoal quarterlyGoal);
}

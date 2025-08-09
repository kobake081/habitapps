package com.keitaro.repository;

import com.keitaro.model.QuarterlyGoal;
import com.keitaro.model.YearlyGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuarterlyGoalRepository extends JpaRepository<QuarterlyGoal, Long> {
    List<QuarterlyGoal> findByYearlyGoal(YearlyGoal yearlyGoal);
}

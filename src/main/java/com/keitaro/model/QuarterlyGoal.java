package com.keitaro.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class QuarterlyGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quarter; // 1〜4

    private String goal;

    @ManyToOne
    @JoinColumn(name = "yearly_goal_id")
    private YearlyGoal yearlyGoal;

    @OneToMany(mappedBy = "quarterlyGoal", cascade = CascadeType.ALL)
    private List<MonthlyGoal> monthlyGoals;

    // --- コンストラクタ ---
    public QuarterlyGoal() {}

    public QuarterlyGoal(int quarter, String goal, YearlyGoal yearlyGoal) {
        this.quarter = quarter;
        this.goal = goal;
        this.yearlyGoal = yearlyGoal;
    }

    // --- getter / setter ---
    public Long getId() {
        return id;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public YearlyGoal getYearlyGoal() {
        return yearlyGoal;
    }

    public void setYearlyGoal(YearlyGoal yearlyGoal) {
        this.yearlyGoal = yearlyGoal;
    }

    public List<MonthlyGoal> getMonthlyGoals() {
        return monthlyGoals;
    }

    public void setMonthlyGoals(List<MonthlyGoal> monthlyGoals) {
        this.monthlyGoals = monthlyGoals;
    }
}

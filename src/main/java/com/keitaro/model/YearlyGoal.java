package com.keitaro.model;

import jakarta.persistence.*;

@Entity
public class YearlyGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "goal_year", nullable = false)
    private int goalYear;

    @Column(columnDefinition = "TEXT")
    private String be; // どうなりたいか（内面）

    @Column(columnDefinition = "TEXT")
    private String what; // 何を達成したいか（外的成果）

    // --- コンストラクタ ---
    public YearlyGoal() {
    }

    public YearlyGoal(int goalYear, String be, String what) {
        this.goalYear = goalYear;
        this.be = be;
        this.what = what;
    }

    // --- getter / setter ---
    public Long getId() {
        return id;
    }

    public int getGoalYear() {
        return goalYear;
    }

    public void setGoalYear(int goalYear) {
        this.goalYear = goalYear;
    }

    public String getBe() {
        return be;
    }

    public void setBe(String be) {
        this.be = be;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }
}

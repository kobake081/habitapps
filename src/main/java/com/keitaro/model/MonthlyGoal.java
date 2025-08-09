package com.keitaro.model;

import jakarta.persistence.*;

@Entity
public class MonthlyGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "goal_month")
    private int month;

    private String goal;

    private String comment; // ← ✅ コメント欄を追加！

    @ManyToOne
    @JoinColumn(name = "quarterly_goal_id")
    private QuarterlyGoal quarterlyGoal;

    // --- コンストラクタ ---
    public MonthlyGoal() {}

    public MonthlyGoal(int month, String goal, QuarterlyGoal quarterlyGoal) {
        this.month = month;
        this.goal = goal;
        this.quarterlyGoal = quarterlyGoal;
    }

    // --- getter / setter ---
    public Long getId() {
        return id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public QuarterlyGoal getQuarterlyGoal() {
        return quarterlyGoal;
    }

    public void setQuarterlyGoal(QuarterlyGoal quarterlyGoal) {
        this.quarterlyGoal = quarterlyGoal;
    }
}

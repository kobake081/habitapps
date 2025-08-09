package com.keitaro.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String task;

    @Column(nullable = false)
    private LocalDate date;

    private boolean done;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "monthly_goal_id")
    private MonthlyGoal monthlyGoal;

    public Goal() {}

    public Goal(String task, LocalDate date) {
        this.task = task;
        this.date = date;
    }

    public Long getId() { return id; }
    public String getTask() { return task; }
    public void setTask(String task) { this.task = task; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public MonthlyGoal getMonthlyGoal() { return monthlyGoal; }
    public void setMonthlyGoal(MonthlyGoal monthlyGoal) { this.monthlyGoal = monthlyGoal; }
}

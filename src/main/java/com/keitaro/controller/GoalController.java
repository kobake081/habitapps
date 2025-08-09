package com.keitaro.controller;

import com.keitaro.model.Goal;
import com.keitaro.repository.GoalRepository;
import com.keitaro.repository.MonthlyGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GoalController {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private MonthlyGoalRepository monthlyGoalRepository;

    // 今日のやること表示
    @GetMapping("/today")
    public String showTodayTasks(Model model) {
        LocalDate today = LocalDate.now();
        List<Goal> tasks = goalRepository.findByDate(today);
        model.addAttribute("today", today);
        model.addAttribute("tasks", tasks);
        return "today";
    }

    // 新しいタスク追加
    @PostMapping("/add")
    public String addTask(@RequestParam String task) {
        Goal newGoal = new Goal();
        newGoal.setTask(task);
        newGoal.setDate(LocalDate.now());
        goalRepository.save(newGoal);
        return "redirect:/today";
    }

    // チェック or コメント更新
    @PostMapping("/update")
    public String updateTask(@RequestParam Long id,
                             @RequestParam(required = false) boolean done,
                             @RequestParam(required = false) String comment) {
        Goal goal = goalRepository.findById(id).orElseThrow();
        goal.setDone(done);
        goal.setComment(comment);
        goalRepository.save(goal);
        return "redirect:/today";
    }

    // 履歴表示（任意）
    @GetMapping("/history")
    public String showHistory(Model model) {
        List<Goal> history = goalRepository.findAll();
        model.addAttribute("history", history);
        return "history";
    }
}

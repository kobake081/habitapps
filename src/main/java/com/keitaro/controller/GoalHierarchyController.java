package com.keitaro.controller;

import com.keitaro.model.*;
import com.keitaro.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GoalHierarchyController {

    @Autowired
    private YearlyGoalRepository yearlyGoalRepository;

    @Autowired
    private QuarterlyGoalRepository quarterlyGoalRepository;

    @Autowired
    private MonthlyGoalRepository monthlyGoalRepository;

    // 年間目標 一覧 & 登録フォーム
    @GetMapping("/goals/yearly")
    public String showYearlyGoals(Model model) {
        List<YearlyGoal> yearlyGoals = yearlyGoalRepository.findAll();
        model.addAttribute("yearlyGoals", yearlyGoals);
        model.addAttribute("newYearlyGoal", new YearlyGoal());
        return "yearly-goals";
    }

    // 年間目標 新規登録 or 更新
    @PostMapping("/goals/yearly")
    public String saveYearlyGoal(@ModelAttribute("newYearlyGoal") YearlyGoal goal) {
        yearlyGoalRepository.save(goal);
        return "redirect:/goals/yearly";
    }

    // 年間目標 編集フォーム表示
    @GetMapping("/goals/yearly/edit/{id}")
    public String showYearlyEditForm(@PathVariable Long id, Model model) {
        YearlyGoal goal = yearlyGoalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid goal ID: " + id));
        model.addAttribute("newYearlyGoal", goal); // 変数名を統一
        model.addAttribute("yearlyGoals", yearlyGoalRepository.findAll());
        return "yearly-goals";
    }

    // 年間目標 削除
    @GetMapping("/goals/yearly/delete/{id}")
    public String deleteYearlyGoal(@PathVariable Long id) {
        yearlyGoalRepository.deleteById(id);
        return "redirect:/goals/yearly";
    }

    // 四半期目標 表示
    @GetMapping("/goals/yearly/{yearlyGoalId}/quarterly")
    public String showQuarterlyGoals(@PathVariable Long yearlyGoalId, Model model) {
        YearlyGoal yearlyGoal = yearlyGoalRepository.findById(yearlyGoalId)
                .orElseThrow(() -> new IllegalArgumentException("指定された年間目標が存在しません: ID=" + yearlyGoalId));
        List<QuarterlyGoal> quarterlyGoals = quarterlyGoalRepository.findByYearlyGoal(yearlyGoal);
        model.addAttribute("yearlyGoal", yearlyGoal);
        model.addAttribute("quarterlyGoals", quarterlyGoals);
        model.addAttribute("newQuarterlyGoal", new QuarterlyGoal());
        return "quarterly-goals";
    }

    // 四半期目標 登録
    @PostMapping("/goals/yearly/{yearlyGoalId}/quarterly")
    public String addQuarterlyGoal(@PathVariable Long yearlyGoalId, @ModelAttribute QuarterlyGoal newGoal) {
        YearlyGoal yearlyGoal = yearlyGoalRepository.findById(yearlyGoalId)
                .orElseThrow(() -> new IllegalArgumentException("指定された年間目標が存在しません: ID=" + yearlyGoalId));
        newGoal.setYearlyGoal(yearlyGoal);
        quarterlyGoalRepository.save(newGoal);
        return "redirect:/goals/yearly/" + yearlyGoalId + "/quarterly";
    }

    // 月間目標 表示
    @GetMapping("/goals/quarterly/{quarterlyGoalId}/monthly")
    public String showMonthlyGoals(@PathVariable Long quarterlyGoalId, Model model) {
        QuarterlyGoal quarterlyGoal = quarterlyGoalRepository.findById(quarterlyGoalId).orElseThrow();
        List<MonthlyGoal> monthlyGoals = monthlyGoalRepository.findByQuarterlyGoal(quarterlyGoal);
        model.addAttribute("quarterlyGoal", quarterlyGoal);
        model.addAttribute("monthlyGoals", monthlyGoals);
        model.addAttribute("newMonthlyGoal", new MonthlyGoal());
        return "monthly-goals";
    }

    // 月間目標 登録
    @PostMapping("/goals/quarterly/{quarterlyGoalId}/monthly")
    public String addMonthlyGoal(@PathVariable Long quarterlyGoalId, @ModelAttribute MonthlyGoal newGoal) {
        QuarterlyGoal quarterlyGoal = quarterlyGoalRepository.findById(quarterlyGoalId).orElseThrow();
        newGoal.setQuarterlyGoal(quarterlyGoal);
        monthlyGoalRepository.save(newGoal);
        return "redirect:/goals/quarterly/" + quarterlyGoalId + "/monthly";
    }
}

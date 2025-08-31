package com.keitaro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // ルートにアクセスしたら templates/index.html を表示
    @GetMapping("/")
    public String home() {
        return "index";
    }
}

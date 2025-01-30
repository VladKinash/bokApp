package org.libapp.libapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.libapp.libapp.service.StatisticsService;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public String showStatisticsPage(Model model) {
        return "statistics";
    }

    @GetMapping("/authors-statistics")
    public String showAuthorStatisticsPage() {
        return "authors-statistics";
    }
}
package org.libapp.libapp.controller;

import org.libapp.libapp.dto.MonthlyBorrowingStatsDTO;
import org.libapp.libapp.dto.TopBorrowedBookDTO;
import org.libapp.libapp.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsApiController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/total-books")
    public long getTotalBooks() {
        return statisticsService.getTotalBookCount();
    }

    @GetMapping("/overdue-books")
    public long getOverdueBooks() {
        return statisticsService.getOverdueBookCount();
    }

    @GetMapping("/currently-borrowed")
    public long getCurrentlyBorrowedBooks() {
        return statisticsService.getCurrentlyBorrowedBookCount();
    }

    @GetMapping("/borrowings-per-month")
    public List<MonthlyBorrowingStatsDTO> getBorrowingsPerMonth() {
        return statisticsService.getBorrowingsPerMonth();
    }

    @GetMapping("/borrowings-per-month-last-year")
    public List<MonthlyBorrowingStatsDTO> getBorrowingsPerMonthForLastYear() {
        return statisticsService.getBorrowingsPerMonthForLastYear();
    }

    @GetMapping("/top-borrowed-books")
    public List<TopBorrowedBookDTO> getTopBorrowedBooks(@RequestParam(defaultValue = "10") int limit) {
        return statisticsService.getTopBorrowedBooks(limit);
    }
}
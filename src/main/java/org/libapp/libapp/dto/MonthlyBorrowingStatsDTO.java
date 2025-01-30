package org.libapp.libapp.dto;

public class MonthlyBorrowingStatsDTO {
    private String yearMonth; // e.g., "2023-10"
    private long count;

    public MonthlyBorrowingStatsDTO(String yearMonth, long count) {
        this.yearMonth = yearMonth;
        this.count = count;
    }

    // Getters
    public String getYearMonth() {
        return yearMonth;
    }

    public long getCount() {
        return count;
    }

    // Setters
    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
package org.libapp.libapp.service;

import org.libapp.libapp.dto.MonthlyBorrowingStatsDTO;
import org.libapp.libapp.dto.TopBorrowedBookDTO;
import org.libapp.libapp.repository.BookRepo;
import org.libapp.libapp.repository.BorrowedBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private BorrowedBookRepo borrowedBookRepo;

    public long getTotalBookCount() {
        return bookRepo.count();
    }

    public long getOverdueBookCount() {
        return borrowedBookRepo.countByDueDateBeforeAndReturnDateIsNull(LocalDate.now());
    }

    public long getCurrentlyBorrowedBookCount() {
        return borrowedBookRepo.countByReturnDateIsNull();
    }

    public List<MonthlyBorrowingStatsDTO> getBorrowingsPerMonth() {
        List<Object[]> rawResult = borrowedBookRepo.countBorrowingsByMonth();
        List<MonthlyBorrowingStatsDTO> result = new ArrayList<>();
        for (Object[] row : rawResult) {
            result.add(new MonthlyBorrowingStatsDTO((String) row[0], (Long) row[1]));
        }
        return result;
    }

    public List<MonthlyBorrowingStatsDTO> getBorrowingsPerMonthForLastYear() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusYears(1);
        List<Object[]> rawResult = borrowedBookRepo.countBorrowingsByMonthBetweenDates(startDate, endDate);
        List<MonthlyBorrowingStatsDTO> result = new ArrayList<>();
        for (Object[] row : rawResult) {
            result.add(new MonthlyBorrowingStatsDTO((String) row[0], (Long) row[1]));
        }
        return result;
    }

    public List<TopBorrowedBookDTO> getTopBorrowedBooks(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Object[]> rawResult = borrowedBookRepo.findTopBorrowedBooks(pageable);
        List<TopBorrowedBookDTO> result = new ArrayList<>();
        for (Object[] row : rawResult) {
            Long bookId = ((Number) row[0]).longValue();
            String title = (String) row[1];
            Long borrowCount = ((Number) row[2]).longValue();
            result.add(new TopBorrowedBookDTO(bookId, title, borrowCount));
        }
        return result;
    }
}



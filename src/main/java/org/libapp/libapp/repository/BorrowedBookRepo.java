package org.libapp.libapp.repository;

import org.libapp.libapp.entity.BorrowedBook;
import org.libapp.libapp.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowedBookRepo extends JpaRepository<BorrowedBook, Integer> {

    List<BorrowedBook> findByUser(User user);

    long countByDueDateBeforeAndReturnDateIsNull(LocalDate today);

    long countByReturnDateIsNull();

    @Query("SELECT function('date_format', b.borrowDate, '%Y-%m') AS month, COUNT(b) FROM BorrowedBook b GROUP BY month")
    List<Object[]> countBorrowingsByMonth();

    @Query("SELECT function('date_format', b.borrowDate, '%Y-%m') AS month, COUNT(b) " +
            "FROM BorrowedBook b " +
            "WHERE b.borrowDate BETWEEN :startDate AND :endDate " +
            "GROUP BY month")
    List<Object[]> countBorrowingsByMonthBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


    @Query("SELECT b.book.id, b.book.title, COUNT(b) AS borrowCount " +
            "FROM BorrowedBook b " +
            "GROUP BY b.book.id, b.book.title " +
            "ORDER BY CASE WHEN COUNT(b) IS NULL THEN 0 ELSE COUNT(b) END DESC")
    List<Object[]> findTopBorrowedBooks(Pageable pageable);
}
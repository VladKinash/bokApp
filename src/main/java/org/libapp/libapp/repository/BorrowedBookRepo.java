package org.libapp.libapp.repository;

import org.libapp.libapp.entity.BorrowedBook;
import org.libapp.libapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowedBookRepo extends JpaRepository<BorrowedBook, Integer> {
    List<BorrowedBook> findByUser(User user);
}
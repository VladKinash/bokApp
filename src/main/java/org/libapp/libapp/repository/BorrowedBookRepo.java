package org.libapp.libapp.repository;

import org.libapp.libapp.entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowedBookRepo extends JpaRepository<BorrowedBook, Integer> {

}
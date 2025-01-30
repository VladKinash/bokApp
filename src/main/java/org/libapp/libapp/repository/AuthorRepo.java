package org.libapp.libapp.repository;

import org.libapp.libapp.entity.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {

    @Query("SELECT a.id, a.firstName, a.lastName, COUNT(ba) AS bookCount " +
            "FROM Author a LEFT JOIN a.bookAuthors ba " +
            "GROUP BY a.id, a.firstName, a.lastName " +
            "ORDER BY bookCount DESC")
    List<Object[]> findAuthorsWithMostBooks(Pageable pageable);

    @Query("SELECT a.id, a.firstName, a.lastName, COUNT(bb) AS borrowCount " +
            "FROM Author a LEFT JOIN a.bookAuthors ba LEFT JOIN ba.book b LEFT JOIN BorrowedBook bb ON b.id = bb.book.id " +
            "GROUP BY a.id, a.firstName, a.lastName " +
            "ORDER BY borrowCount DESC")
    List<Object[]> findMostBorrowedAuthors(Pageable pageable);
}
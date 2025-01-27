package org.libapp.libapp.repository;

import org.libapp.libapp.entity.BookAuthor;
import org.libapp.libapp.entity.BookAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface BookAuthorRepo extends JpaRepository<BookAuthor, BookAuthorId> {
    List<BookAuthor> findByAuthorId(Integer authorId);
    List<BookAuthor> findByBookId(Integer bookId);}

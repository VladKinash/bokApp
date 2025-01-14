package org.libapp.libapp.repository;

import org.libapp.libapp.entity.BookAuthor;
import org.libapp.libapp.entity.BookAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorRepo extends JpaRepository<BookAuthor, BookAuthorId> {
}

package org.libapp.libapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.libapp.libapp.entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {
}

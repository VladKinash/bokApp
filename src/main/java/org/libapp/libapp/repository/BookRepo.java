package org.libapp.libapp.repository;

import org.libapp.libapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor; // Add this import
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> { // Add JpaSpecificationExecutor
}
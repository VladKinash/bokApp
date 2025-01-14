package org.libapp.libapp.repository;

import org.libapp.libapp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {
    //genuinely clueless as to why do i need this
}

package org.libapp.libapp.repository;

import org.libapp.libapp.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepo extends JpaRepository<Rating, Integer> {
    @Query("SELECT COALESCE(AVG(r.rating), 0.0) FROM Rating r WHERE r.book.id = :bookId")
    Double findAverageRatingByBookId(@Param("bookId") Integer bookId);

    boolean existsByBookIdAndUserId(Integer bookId, Integer userId);
}
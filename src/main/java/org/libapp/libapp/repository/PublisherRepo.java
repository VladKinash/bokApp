package org.libapp.libapp.repository;

import org.libapp.libapp.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Integer> {
    Publisher findByPublisherName(String publisherName);
}

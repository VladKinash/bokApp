package org.libapp.libapp.service;

import org.libapp.libapp.entity.Publisher;
import org.libapp.libapp.exception.ResourceNotFoundException;
import org.libapp.libapp.repository.PublisherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepo publisherRepo;

    @Autowired
    public PublisherService(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepo.findAll();
    }

    public Publisher addPublisher(Publisher publisher) {
        return publisherRepo.save(publisher);
    }

    public Publisher getPublisherById(Integer id) {
        return publisherRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + id));
    }

    public Publisher updatePublisher(Integer id, Publisher updatedPublisher) {
        Publisher existingPublisher = getPublisherById(id);
        existingPublisher.setPublisherName(updatedPublisher.getPublisherName());
        existingPublisher.setAddress(updatedPublisher.getAddress());
        existingPublisher.setWebsite(updatedPublisher.getWebsite());
        return publisherRepo.save(existingPublisher);
    }

    public void deletePublisher(Integer id) {
        Publisher publisher = getPublisherById(id);
        publisherRepo.delete(publisher);
    }
}

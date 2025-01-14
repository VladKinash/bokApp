package org.libapp.libapp.controller;

import org.libapp.libapp.entity.Publisher;
import org.libapp.libapp.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }


    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }


    @PostMapping
    public Publisher createPublisher(@RequestBody Publisher publisher) {
        return publisherService.addPublisher(publisher);
    }


    @GetMapping("/{id}")
    public Publisher getPublisherById(@PathVariable Integer id) {
        return publisherService.getPublisherById(id);
    }


    @PutMapping("/{id}")
    public Publisher updatePublisher(@PathVariable Integer id, @RequestBody Publisher updatedPublisher) {
        return publisherService.updatePublisher(id, updatedPublisher);
    }


    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable Integer id) {
        publisherService.deletePublisher(id);
    }
}

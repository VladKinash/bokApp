package org.libapp.libapp.controller;

import org.libapp.libapp.entity.Author;
import org.libapp.libapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }


    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Integer id) {
        return authorService.getAuthorById(id);
    }


    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Integer id, @RequestBody Author updatedAuthor) {
        return authorService.updateAuthor(id, updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Integer id) {
        authorService.deleteAuthor(id);
    }
}

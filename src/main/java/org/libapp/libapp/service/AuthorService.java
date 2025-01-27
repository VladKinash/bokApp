package org.libapp.libapp.service;

import org.libapp.libapp.entity.Author;
import org.libapp.libapp.entity.Book;
import org.libapp.libapp.entity.BookAuthor;
import org.libapp.libapp.exception.ResourceNotFoundException;
import org.libapp.libapp.repository.AuthorRepo;
import org.libapp.libapp.repository.BookAuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepo authorRepo;
    private final BookAuthorRepo bookAuthorRepo;

    @Autowired
    public AuthorService(AuthorRepo authorRepo, BookAuthorRepo bookAuthorRepo) {
        this.authorRepo = authorRepo;
        this.bookAuthorRepo = bookAuthorRepo;
    }

    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    public Author addAuthor(Author author) {
        return authorRepo.save(author);
    }

    public Author getAuthorById(Integer id) {
        return authorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
    }

    public Author updateAuthor(Integer id, Author updatedAuthor) {
        Author existingAuthor = getAuthorById(id);
        existingAuthor.setFirstName(updatedAuthor.getFirstName());
        existingAuthor.setLastName(updatedAuthor.getLastName());
        existingAuthor.setBiography(updatedAuthor.getBiography());
        existingAuthor.setDateOfBirth(updatedAuthor.getDateOfBirth());
        existingAuthor.setDateOfDeath(updatedAuthor.getDateOfDeath());
        return authorRepo.save(existingAuthor);
    }

    public void deleteAuthor(Integer id) {
        Author existingAuthor = getAuthorById(id);
        authorRepo.delete(existingAuthor);
    }

    public List<Book> getBooksByAuthorId(Integer authorId) {
        return bookAuthorRepo.findByAuthorId(authorId)
                .stream()
                .map(BookAuthor::getBook)
                .collect(Collectors.toList());
    }
}

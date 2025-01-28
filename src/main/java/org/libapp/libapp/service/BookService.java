package org.libapp.libapp.service;

import org.libapp.libapp.entity.Author;
import org.libapp.libapp.entity.Book;
import org.libapp.libapp.entity.BookAuthor;
import org.libapp.libapp.entity.BookAuthorId;
import org.libapp.libapp.exception.ResourceNotFoundException;
import org.libapp.libapp.repository.BookAuthorRepo;
import org.libapp.libapp.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepo bookRepo;
    private final BookAuthorRepo bookAuthorRepo;
    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepo bookRepo, BookAuthorRepo bookAuthorRepo, AuthorService authorService) {
        this.bookRepo = bookRepo;
        this.bookAuthorRepo = bookAuthorRepo;
        this.authorService = authorService;
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book addBook(Book book) {
        // Save the book first to generate the ID
        Book savedBook = bookRepo.save(book);
        return savedBook;
    }

    public Book getBookById(Integer id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }

    @Transactional
    public Book updateBook(Integer id, Book updatedBook) {
        Book existingBook = getBookById(id);
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setPublicationDate(updatedBook.getPublicationDate());
        existingBook.setPublisher(updatedBook.getPublisher());
        existingBook.setDescription(updatedBook.getDescription());
        existingBook.setCoverImageUrl(updatedBook.getCoverImageUrl());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setCopiesAvailable(updatedBook.getCopiesAvailable());


        existingBook.getBookAuthors().clear();


        return bookRepo.save(existingBook);
    }

    @Transactional
    public void deleteBook(Integer id) {
        Book existingBook = getBookById(id);
        bookRepo.delete(existingBook);
    }

    @Transactional
    public void incrementCopiesAvailable(Integer bookId, int amount) {
        Book book = getBookById(bookId);
        book.setCopiesAvailable(book.getCopiesAvailable() + amount);
        bookRepo.save(book);
    }
    @Transactional
    public void decrementCopiesAvailable(Integer bookId, int amount) {
        Book book = getBookById(bookId);
        if (book.getCopiesAvailable() < amount) {
            throw new RuntimeException("Not enough copies available");
        }
        book.setCopiesAvailable(book.getCopiesAvailable() - amount);
        bookRepo.save(book);
    }
}

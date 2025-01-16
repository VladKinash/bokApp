package org.libapp.libapp.service;

import org.libapp.libapp.entity.Book;
import org.libapp.libapp.repository.BookRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    public Book getBookById(Integer id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
    }

    public Book updateBook(Integer id, Book updatedBook) {
        Book existingBook = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setPublicationDate(updatedBook.getPublicationDate());
        existingBook.setPublisher(updatedBook.getPublisherId());
        existingBook.setDescription(updatedBook.getDescription());
        existingBook.setCoverImageUrl(updatedBook.getCoverImageUrl());
        existingBook.setAuthorId(updatedBook.getAuthorId());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setCopiesAvailable(updatedBook.getCopiesAvailable());

        return bookRepo.save(existingBook);
    }

    public void deleteBook(Integer id) {
        bookRepo.deleteById(id);
    }
}

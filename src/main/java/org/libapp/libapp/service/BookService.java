package org.libapp.libapp.service;

import org.libapp.libapp.entity.Book;
import org.libapp.libapp.entity.Publisher;
import org.libapp.libapp.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo;

    @Autowired
    private PublisherService publisherService;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book addBook(Book book) {
        // Ensure Publisher is set
        if (book.getPublisher() != null && book.getPublisher().getId() != null) {
            Publisher publisher = publisherService.getPublisherById(book.getPublisher().getId());
            book.setPublisher(publisher);
        }
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

            if (updatedBook.getPublisher() != null && updatedBook.getPublisher().getId() != null) {
            Publisher publisher = publisherService.getPublisherById(updatedBook.getPublisher().getId());
            existingBook.setPublisher(publisher);
        }

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

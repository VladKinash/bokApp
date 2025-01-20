package org.libapp.libapp.service;

import org.libapp.libapp.entity.Book;
import org.libapp.libapp.entity.BorrowedBook;
import org.libapp.libapp.entity.User;
import org.libapp.libapp.repository.BorrowedBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowedBookService {

    private final BorrowedBookRepo borrowedBookRepo;
    private final BookService bookService;

    @Autowired
    public BorrowedBookService(BorrowedBookRepo borrowedBookRepo, BookService bookService) {
        this.borrowedBookRepo = borrowedBookRepo;
        this.bookService = bookService;
    }


    public List<BorrowedBook> getAllBorrowedBooks() {
        return borrowedBookRepo.findAll();
    }


    public BorrowedBook getBorrowedBookById(Integer id) {
        return borrowedBookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrowed Book not found with ID: " + id));
    }


    public List<BorrowedBook> getBorrowedBooksByUser(User user) {
        return borrowedBookRepo.findByUser(user);
    }


    @Transactional
    public BorrowedBook borrowBook(User user, Book book) {
        if (book.getCopiesAvailable() <= 0) {
            throw new RuntimeException("No copies available for this book.");
        }

        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setUser(user);
        borrowedBook.setBook(book);
        borrowedBook.setBorrowDate(LocalDate.now());
        borrowedBook.setDueDate(LocalDate.now().plusDays(14)); // Example: 2 weeks

        book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        bookService.updateBook(book.getId(), book); // Ensure BookService has an update method

        return borrowedBookRepo.save(borrowedBook);
    }


    public BorrowedBook returnBook(Integer borrowId) {
        BorrowedBook borrowedBook = getBorrowedBookById(borrowId);
        borrowedBook.setReturnDate(LocalDate.now());
        return borrowedBookRepo.save(borrowedBook);
    }


    @Transactional
    public BorrowedBook updateBorrowedBook(Integer id, BorrowedBook updatedBorrowedBook) {
        BorrowedBook existingBorrowedBook = getBorrowedBookById(id);
        existingBorrowedBook.setBorrowDate(updatedBorrowedBook.getBorrowDate());
        existingBorrowedBook.setDueDate(updatedBorrowedBook.getDueDate());
        existingBorrowedBook.setReturnDate(updatedBorrowedBook.getReturnDate());
        existingBorrowedBook.setReturnRequestedAt(updatedBorrowedBook.getReturnRequestedAt()); // Update return request date
        return borrowedBookRepo.save(existingBorrowedBook);
    }


    public void deleteBorrowedBook(Integer id) {
        BorrowedBook borrowedBook = getBorrowedBookById(id);
        borrowedBookRepo.delete(borrowedBook);
    }

    public List<BorrowedBook> getBooksWithReturnRequested() {
        return borrowedBookRepo.findAll().stream()
                .filter(borrowedBook -> borrowedBook.getReturnRequestedAt() != null)
                .collect(Collectors.toList());
    }
}

create table borrowed_book
(
    borrow_id           int auto_increment
        primary key,
    user_id             int  not null,
    book_id             int  not null,
    borrow_date         date not null,
    return_date         date null,
    due_date            date not null,
    return_requested_at date null,
    constraint borrowed_book_books_book_id_fk
        foreign key (book_id) references books (book_id),
    constraint borrowed_book_users_user_id_fk
        foreign key (user_id) references users (user_id)
);

INSERT INTO bookapp.borrowed_book (borrow_id, user_id, book_id, borrow_date, return_date, due_date, return_requested_at) VALUES (1, 1, 13, '2025-01-19', '2025-01-20', '2025-02-02', null);
INSERT INTO bookapp.borrowed_book (borrow_id, user_id, book_id, borrow_date, return_date, due_date, return_requested_at) VALUES (2, 1, 13, '2025-01-19', '2025-01-21', '2025-02-02', null);
INSERT INTO bookapp.borrowed_book (borrow_id, user_id, book_id, borrow_date, return_date, due_date, return_requested_at) VALUES (3, 1, 14, '2025-01-19', '2025-01-29', '2025-02-02', null);
INSERT INTO bookapp.borrowed_book (borrow_id, user_id, book_id, borrow_date, return_date, due_date, return_requested_at) VALUES (4, 1, 12, '2025-01-19', '2025-01-20', '2025-02-02', null);
INSERT INTO bookapp.borrowed_book (borrow_id, user_id, book_id, borrow_date, return_date, due_date, return_requested_at) VALUES (5, 1, 11, '2025-01-20', '2025-01-31', '2025-02-03', null);
INSERT INTO bookapp.borrowed_book (borrow_id, user_id, book_id, borrow_date, return_date, due_date, return_requested_at) VALUES (6, 1, 13, '2025-01-21', null, '2025-02-04', '2025-01-29');
INSERT INTO bookapp.borrowed_book (borrow_id, user_id, book_id, borrow_date, return_date, due_date, return_requested_at) VALUES (7, 2, 13, '2025-01-27', null, '2025-02-10', '2025-01-29');
INSERT INTO bookapp.borrowed_book (borrow_id, user_id, book_id, borrow_date, return_date, due_date, return_requested_at) VALUES (8, 1, 11, '2025-01-28', null, '2025-02-11', null);
INSERT INTO bookapp.borrowed_book (borrow_id, user_id, book_id, borrow_date, return_date, due_date, return_requested_at) VALUES (9, 2, 11, '2025-01-29', null, '2025-02-12', null);
INSERT INTO bookapp.borrowed_book (borrow_id, user_id, book_id, borrow_date, return_date, due_date, return_requested_at) VALUES (10, 1, 13, '2025-01-29', null, '2025-02-12', null);
INSERT INTO bookapp.borrowed_book (borrow_id, user_id, book_id, borrow_date, return_date, due_date, return_requested_at) VALUES (11, 1, 14, '2025-01-29', null, '2025-02-12', null);
INSERT INTO bookapp.borrowed_book (borrow_id, user_id, book_id, borrow_date, return_date, due_date, return_requested_at) VALUES (12, 7, 12, '2025-01-31', null, '2025-02-14', null);

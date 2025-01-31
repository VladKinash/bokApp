create table ratings
(
    rating_id  int auto_increment
        primary key,
    book_id    int                                 not null,
    user_id    int                                 not null,
    rating     decimal                             not null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    constraint ratings_books_book_id_fk
        foreign key (book_id) references books (book_id),
    constraint ratings_users_user_id_fk
        foreign key (user_id) references users (user_id)
);

INSERT INTO bookapp.ratings (rating_id, book_id, user_id, rating, created_at) VALUES (1, 11, 1, 5, '2025-01-29 16:07:46');
INSERT INTO bookapp.ratings (rating_id, book_id, user_id, rating, created_at) VALUES (2, 11, 2, 1, '2025-01-29 16:07:58');

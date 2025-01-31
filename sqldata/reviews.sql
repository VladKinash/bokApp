create table reviews
(
    review_id   int auto_increment
        primary key,
    book_id     int                                null,
    user_id     int                                null,
    rating      int                                null invisible,
    review_text int                                null,
    review_date datetime default CURRENT_TIMESTAMP null invisible,
    constraint reviews_pk
        unique (book_id, user_id),
    constraint reviews_books_book_id_fk
        foreign key (book_id) references books (book_id),
    constraint reviews_users_user_id_fk
        foreign key (user_id) references users (user_id)
);


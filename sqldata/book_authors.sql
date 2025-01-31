create table book_authors
(
    book_id   int not null,
    author_id int not null,
    primary key (book_id, author_id),
    constraint book_authors_authors_author_id_fk
        foreign key (author_id) references authors (author_id),
    constraint book_authors_books_book_id_fk
        foreign key (book_id) references books (book_id)
);

INSERT INTO bookapp.book_authors (book_id, author_id) VALUES (11, 1);
INSERT INTO bookapp.book_authors (book_id, author_id) VALUES (12, 1);
INSERT INTO bookapp.book_authors (book_id, author_id) VALUES (24, 2);

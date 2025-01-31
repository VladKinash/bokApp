create table books
(
    book_id          int auto_increment
        primary key,
    title            tinytext      not null,
    isbn             tinytext      not null,
    publication_date date          null,
    publisher_id     int           null,
    description      tinytext      null,
    cover_image_url  tinytext      null,
    genre            tinytext      null,
    copies_available int default 0 not null,
    total_copies     int default 0 not null,
    constraint isbn
        unique (isbn(20)),
    constraint publisher
        foreign key (publisher_id) references publishers (publisher_id)
);

INSERT INTO bookapp.books (book_id, title, isbn, publication_date, publisher_id, description, cover_image_url, genre, copies_available, total_copies) VALUES (11, 'All Yesterdays', '978-0988699214', null, 1, 'A book showcasing unique and unconventional depictions of dinosaurs and other prehistoric life.', 'https://example.com/all_yesterdays_cover.jpg', 'Paleontology', 11, 20);
INSERT INTO bookapp.books (book_id, title, isbn, publication_date, publisher_id, description, cover_image_url, genre, copies_available, total_copies) VALUES (12, 'All Tomorrows', '978-0988699221', null, 2, 'A science fiction book that describes the future evolution of humanity.', 'https://example.com/all_tomorrows.jpg', 'Science Fiction', 4, 15);
INSERT INTO bookapp.books (book_id, title, isbn, publication_date, publisher_id, description, cover_image_url, genre, copies_available, total_copies) VALUES (13, 'Snaiad', '978-1-481889009', null, 2, 'C.M. Kosemen\'s Snaiad is a worldbuilding project hosted exclusively on his website. The project concerns a fictional exoplanet named Snaiad, with much of the focus being on its diverse and highly derived fauna and the complex ecosystem they inhabit.', 'https://example.com/snaiad.jpg', 'Speculative Evolution', 0, 10);
INSERT INTO bookapp.books (book_id, title, isbn, publication_date, publisher_id, description, cover_image_url, genre, copies_available, total_copies) VALUES (14, 'I Have No Mouth, and I Must Scream', '978-0345324580', '1967-03-01', 5, 'A collection of seven stories with a post-apocalyptic theme.', 'https://example.com/i_have_no_mouth.jpg', 'Science Fiction', 19, 25);
INSERT INTO bookapp.books (book_id, title, isbn, publication_date, publisher_id, description, cover_image_url, genre, copies_available, total_copies) VALUES (15, 'Deathbird Stories', '978-0060912097', '1975-06-01', 6, 'A collection of short stories centered around the theme of modern-day gods.', 'https://example.com/deathbird_stories.jpg', 'Fantasy', 15, 15);
INSERT INTO bookapp.books (book_id, title, isbn, publication_date, publisher_id, description, cover_image_url, genre, copies_available, total_copies) VALUES (16, 'A Boy and His Dog', '978-1618731917', '1969-01-01', 4, 'A novella about a boy communicating telepathically with his dog in a post-apocalyptic world.', 'https://example.com/boy_and_his_dog.jpg', 'Science Fiction', 8, 12);
INSERT INTO bookapp.books (book_id, title, isbn, publication_date, publisher_id, description, cover_image_url, genre, copies_available, total_copies) VALUES (17, 'The Metamorphosis of Prime Intellect', '978-1425954895', '2002-01-01', 2, 'A science fiction novel exploring the consequences of a powerful AI that grants humanity immortality and godlike powers.', 'https://example.com/prime_intellect.jpg', 'Science Fiction', 25, 30);
INSERT INTO bookapp.books (book_id, title, isbn, publication_date, publisher_id, description, cover_image_url, genre, copies_available, total_copies) VALUES (18, 'The Second Book of General Ignorance: Everything You Think You Know Is (Still) Wrong', '978-0307946746', '2011-10-25', 3, 'Presents additional commonly misunderstood facts on a range of topics, including history, science, and sports.', 'https://example.com/general_ignorance.jpg', 'Humor', 2, 12);
INSERT INTO bookapp.books (book_id, title, isbn, publication_date, publisher_id, description, cover_image_url, genre, copies_available, total_copies) VALUES (19, 'The Slave Garden', '978-0899190310', '1986-04-01', 2, 'In a future where human slaves are property and entertainment, where the elite have achieved functional immortality and godlike power, one man dares to question the system.', 'https://example.com/slave_garden.jpg', 'Science Fiction', 6, 18);
INSERT INTO bookapp.books (book_id, title, isbn, publication_date, publisher_id, description, cover_image_url, genre, copies_available, total_copies) VALUES (24, 'Shatterday', '234141239', '1975-12-17', 1, '', '', 'Sci-Fi', 10, 0);

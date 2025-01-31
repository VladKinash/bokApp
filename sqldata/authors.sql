create table authors
(
    author_id     int auto_increment
        primary key,
    first_name    tinytext not null,
    last_name     tinytext null,
    biography     tinytext null,
    date_of_birth date     null,
    date_of_death date     null
);

INSERT INTO bookapp.authors (author_id, first_name, last_name, biography, date_of_birth, date_of_death) VALUES (1, 'C.M.', 'Kosemen', 'C.M. Kosemen is a Turkish artist, author, and independent researcher, known for his surreal art and speculative evolution work.', '1984-10-25', null);
INSERT INTO bookapp.authors (author_id, first_name, last_name, biography, date_of_birth, date_of_death) VALUES (2, 'Harlan', 'Ellison', 'Harlan Ellison was an American writer, known for his prolific and influential work in speculative fiction.', null, null);
INSERT INTO bookapp.authors (author_id, first_name, last_name, biography, date_of_birth, date_of_death) VALUES (5, 'Roger', 'Williams', 'Roger Williams is a science fiction author best known for his novel "The Metamorphosis of Prime Intellect", a story exploring the implications of a powerful AI and technological singularity.', null, null);

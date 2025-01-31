create table roles
(
    id   int      not null
        primary key,
    name tinytext null
);

INSERT INTO bookapp.roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO bookapp.roles (id, name) VALUES (2, 'LIBRARIAN');
INSERT INTO bookapp.roles (id, name) VALUES (3, 'USER');

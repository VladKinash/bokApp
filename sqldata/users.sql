create table users
(
    user_id       int auto_increment
        primary key,
    username      tinytext                           not null,
    email         tinytext                           not null,
    password_hash tinytext                           not null,
    date_joined   datetime default CURRENT_TIMESTAMP null,
    profile_pic   tinytext                           null,
    bio           tinytext                           null
);

INSERT INTO bookapp.users (user_id, username, email, password_hash, date_joined, profile_pic, bio) VALUES (1, 'dudeeeee', 'dude@gmail.com', '$2a$10$LNSYYoRbbJsS/ksdUtLlwuztSgo3TIo0qK62FaH8hjjFOZIJHXMV.', null, null, null);
INSERT INTO bookapp.users (user_id, username, email, password_hash, date_joined, profile_pic, bio) VALUES (2, 'admin1', 'libraryadmin@mail.com', '$2a$10$kkJL/dpoz4xgeYCGRmvkAewMa.epTSsG9QNlrCEQ1YiMR6E5hIbiG', null, null, null);
INSERT INTO bookapp.users (user_id, username, email, password_hash, date_joined, profile_pic, bio) VALUES (5, 'librarian', 'librarian@lib.com', '$2a$10$V.uU/IPhYsQ1VCuDSlafuewAeZ99A.aWHa.kRMBv10URbmXzCyKgm', '2025-01-31 09:22:39', null, null);
INSERT INTO bookapp.users (user_id, username, email, password_hash, date_joined, profile_pic, bio) VALUES (6, 'librariannew', 'librarian@gmail.com', '$2a$10$8IpBBQkOg6TTJnwDSDGxm.blvi96yKlxYqAPcWC9vDjdve4WWvKV6', '2025-01-31 09:25:20', null, null);
INSERT INTO bookapp.users (user_id, username, email, password_hash, date_joined, profile_pic, bio) VALUES (7, 'newuser1', 'newuser@gmail.com', '$2a$10$eVwMUqgAD9tAf3RLXIsYwuEE7TbbRv5l4nS1IMYjmJvIWAIqc8K.a', '2025-01-31 16:07:57', null, null);

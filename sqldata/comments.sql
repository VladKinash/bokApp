create table comments
(
    comment_id   int auto_increment
        primary key,
    user_id      int      null,
    comment_text tinytext not null,
    comment_date date     not null,
    review_id    int      null,
    constraint comments___fk
        foreign key (review_id) references reviews (review_id),
    constraint user
        foreign key (user_id) references users (user_id)
);


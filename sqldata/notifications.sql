create table notifications
(
    notification_id int auto_increment
        primary key,
    user_id         int          not null,
    message         varchar(255) not null,
    created_at      timestamp    null,
    is_read         tinyint(1)   null,
    constraint notifications_users_user_id_fk
        foreign key (user_id) references users (user_id)
);


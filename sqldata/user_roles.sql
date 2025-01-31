create table user_roles
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    constraint user_roles_roles_id_fk
        foreign key (role_id) references roles (id),
    constraint user_roles_users_user_id_fk
        foreign key (user_id) references users (user_id)
);

INSERT INTO bookapp.user_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO bookapp.user_roles (user_id, role_id) VALUES (5, 2);
INSERT INTO bookapp.user_roles (user_id, role_id) VALUES (6, 2);
INSERT INTO bookapp.user_roles (user_id, role_id) VALUES (1, 3);
INSERT INTO bookapp.user_roles (user_id, role_id) VALUES (7, 3);

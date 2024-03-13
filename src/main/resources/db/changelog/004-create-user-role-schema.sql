--liquibase formatted sql

--changeset author:llav3ji2019
CREATE TABLE user_roles (
    client_id int not null,
    role_id int not null,
    primary key (client_id, role_id),
    foreign key (client_id) references client (id),
    foreign key (role_id) references roles (id)
);

-----

-- rollback drop table user_roles;

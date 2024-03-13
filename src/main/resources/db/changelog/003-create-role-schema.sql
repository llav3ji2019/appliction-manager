--liquibase formatted sql

--changeset author:llav3ji2019
create table if not exists roles (
    id serial primary key,
    name varchar(32) not null unique
);

-----

-- rollback drop table roles;

--liquibase formatted sql

--changeset author:llav3ji2019
create table if not exists client (
    id serial primary key,
    username varchar(128) not null unique,
    password varchar not null,
    role varchar(32) not null,
    email varchar(32) not null
);

-----

-- rollback drop table client;

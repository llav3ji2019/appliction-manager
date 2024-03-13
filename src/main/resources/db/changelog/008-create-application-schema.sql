--liquibase formatted sql

--changeset author:llav3ji2019
create extension if not exists "uuid-ossp";

create table if not exists application (
    id uuid primary key default gen_random_uuid(),
    text varchar not null,
    username varchar(128) not null unique,
    phone_number varchar(32) not null,
    status varchar(32) not null,
    creation_time timestamp
);

-----

-- rollback drop table application;

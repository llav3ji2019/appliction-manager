--liquibase formatted sql

--changeset author:llav3ji2019
insert into roles(name)
values ('USER'),
    ('OPERATOR'),
    ('ADMIN');

-----

-- rollback DELETE FROM roles WHERE name='USER';;
-- rollback DELETE FROM roles WHERE name='OPERATOR';;
-- rollback DELETE FROM roles WHERE name='ADMIN';;

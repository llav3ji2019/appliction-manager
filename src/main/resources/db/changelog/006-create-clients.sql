--liquibase formatted sql

--changeset author:llav3ji2019
insert into client (username, password, email)
values ('user', '$2a$12$7jVY3YXcmYWSS898oQqQY.H8saFliSAnKeSbIF9P1mTVUANOxx/eS', 'user@mail.ru'),
       ('operator', '$2a$12$zVvq6bBF5kB8VmYuXDhvaeQeeQ9t0y9P0w/nMD71HXnp5pOzGTt2m', 'operator@mail.ru'),
       ('admin', '$2a$12$pK2jp9Z5e1nDLGmDXHD0D.9ssH0C5wI56.0VMv7FxV/AKBXQ.TFPi', 'admin@mail.ru');

-----

-- rollback DELETE FROM client WHERE username='user';
-- rollback DELETE FROM client WHERE username='operator';
-- rollback DELETE FROM client WHERE username='admin';

--liquibase formatted sql

--changeset author:llav3ji2019
insert into user_roles (client_id, role_id)
values (1, 1),
       (2, 2),
       (3, 3);

-----

-- rollback DELETE FROM client WHERE username='user';
-- rollback DELETE FROM client WHERE username='operator';
-- rollback DELETE FROM client WHERE username='admin';

--liquibase formatted sql

--changeset author:llav3ji2019
alter table application drop constraint application_username_key;
-----

-- rollback alter table application add constraint application_username_key unique (username)

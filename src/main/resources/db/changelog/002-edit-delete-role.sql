--liquibase formatted sql

--changeset author:llav3ji2019
ALTER TABLE client DROP COLUMN role;

-----
-- rollback alter table client add column role varchar(32) NOT NULL;

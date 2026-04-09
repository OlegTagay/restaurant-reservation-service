--liquibase formatted sql
--changeset oleg:4
ALTER TABLE reservation_table
DROP COLUMN is_available;
--rollback ALTER TABLE reservation_table ADD COLUMN is_available;
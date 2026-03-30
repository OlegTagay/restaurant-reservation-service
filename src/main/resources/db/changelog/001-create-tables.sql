--liquibase formatted sql

--changeset oleg:1
CREATE TABLE contact_info
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(255)
);
--rollback DROP TABLE contact_info;

--changeset oleg:2
CREATE TABLE reservation_user
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    contact_info_id BIGINT REFERENCES contact_info(id)
);
--rollback DROP TABLE reservation_user;

--changeset oleg:3
CREATE TABLE reservation_table
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    number_of_seats INT,
    is_available BOOLEAN DEFAULT TRUE,
    reservation_date DATE,
    reservation_time TIME,
    user_id BIGINT REFERENCES reservation_user(id),
    UNIQUE(user_id, reservation_date, reservation_time)
);
--rollback DROP TABLE reservation_table;
--liquibase formatted SQL

--changeset Suman:1
CREATE TABLE user_table(
    user_id serial primary key not null ,
    user_name varchar(255),
    user_phone_number bigint,
    user_email varchar(255)
);

--changeset Suman:2
ALTER table user_table add column  user_dob date;

--changeset Suman:3
ALTER table  user_table add  column  user_password varchar(255);

--changeset Suman:4
ALTER TABLE user_table add column address_id INTEGER;

--changeset Suman:6
ALTER TABLE user_table ADD CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES address_table(address_id);

--changeset Suman:7
ALTER TABLE user_table ADD CONSTRAINT unique_address_id UNIQUE (address_id);
--liquibase formatted SQL

--changeset Suman:5
CREATE TABLE address_table(
  address_id SERIAL primary key not null ,
  country varchar(50) not null ,
  city varchar(50) not null ,
  house_number int
);

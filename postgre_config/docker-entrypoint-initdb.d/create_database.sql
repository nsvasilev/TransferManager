CREATE DATABASE bank_users OWNER nikita_admin;

\c bank_users

CREATE TABLE bank_user(
    id serial PRIMARY KEY,
    lastname varchar(64) not null,
    firstname varchar(64) not null,
    patronymic varchar(64),
    gender varchar(6) not null,
    birth_date date not null,
    balance decimal(10,2) not null
);

create table failed_file(
    id serial PRIMARY KEY,
    path_of_file varchar(255),
    date_of_appearance timestamp,
    size bigint
);
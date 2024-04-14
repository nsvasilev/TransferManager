CREATE DATABASE bank_users OWNER nikita_admin;

\c bank_users

CREATE TABLE bank_user(
    id serial not null,
    lastname varchar(64) not null,
    firstname varchar(64) not null,
    patronymic varchar(64),
    gender varchar(6) not null,
    birthDate date not null
);
CREATE SCHEMA komidinhas;

CREATE TABLE IF NOT EXISTS komidinhas.users (
    username VARCHAR ( 50 ) PRIMARY KEY,
    password VARCHAR ( 50 ) NOT NULL,
    email VARCHAR ( 255 ) UNIQUE NOT NULL,
    created_on TIMESTAMP NOT NULL
);
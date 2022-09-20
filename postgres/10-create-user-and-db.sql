-- file: 10-create-user-and-db.sql
CREATE DATABASE persons;
CREATE ROLE program WITH PASSWORD 'test';
GRANT ALL PRIVILEGES ON DATABASE persons TO program;
ALTER ROLE program WITH LOGIN;

CREATE TABLE personsTable (
    id serial PRIMARY KEY,
    name VARCHAR (50),
    age INT,
    address VARCHAR(100),
    work VARCHAR(100)
);

INSERT INTO personsTable(name, age, address, work) VALUES
    ('lox', 30, 'hz', 'yandex');

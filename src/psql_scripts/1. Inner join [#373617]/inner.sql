CREATE DATABASE library;

CREATE TABLE author(
id SERIAL PRIMARY KEY,
first_name VARCHAR(255),
last_name VARCHAR(255),
country VARCHAR(255)
);

CREATE TABLE book(
id SERIAL PRIMARY KEY,
book_name VARCHAR(255),
genre VARCHAR(255),
author_id int REFERENCES author(id)
);

INSERT INTO author(first_name, last_name, country) VALUES ('GREG', 'GREEN', 'US');
INSERT INTO author(first_name, last_name, country) VALUES ('BOB', 'BLACK', 'NA');
INSERT INTO author(first_name, last_name, country) VALUES ('NICK', 'WHITE', 'NA');

INSERT INTO book(book_name, genre, author_id) VALUES ('shantaram', 'novel', 1);
INSERT INTO book(book_name, genre, author_id) VALUES ('alice', 'novel', 2);
INSERT INTO book(book_name, genre, author_id) VALUES ('mandalorian', 'fiction', 3);
INSERT INTO book(book_name, genre, author_id) VALUES ('dune', 'fiction', 1);
INSERT INTO book(book_name, genre, author_id) VALUES ('ODESY', 'adventure', 2);

SELECT book_name AS "Название Книги", first_name Имя , last_name Фамилия 
FROM book AS b JOIN author as a ON b.id = a.id;

SELECT book_name Название_книги, country Страна
FROM book b JOIN author a ON b.id = a.id;

SELECT * FROM book b JOIN author a ON b.id = a.id;
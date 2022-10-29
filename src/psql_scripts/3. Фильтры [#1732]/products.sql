CREATE DATABASE products;

create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price int
);

insert into type(name) 
values ('СЫР'), ('МОЛОКО'), ('КОЛБАСА'), ('ШОКОЛАД');

insert into product(name, type_id, expired_date, price)
values ('Российский', 1, date '2022-10-10', 650);
insert into product(name, type_id, expired_date, price)
values ('Гауда', 1, date '2022-11-24', 856);
insert into product(name, type_id, expired_date, price)
values ('Камамбер', 1, date '2022-10-14', 932);
insert into product(name, type_id, expired_date, price)
values ('Стародубский', 1, date '2022-11-07', 558);
insert into product(name, type_id, expired_date, price)
values ('Мороженое', 2, date '2022-10-13', 77);
insert into product(name, type_id, expired_date, price)
values ('Сметана', 2, date '2022-08-11', 97);
insert into product(name, type_id, expired_date, price)
values ('Кефир', 2, date '2022-08-12', 67);
insert into product(name, type_id, expired_date, price)
values ('Столичная', 3, date '2022-11-02', 487);
insert into product(name, type_id, expired_date, price)
values ('Докторская', 3, date '2022-08-12', 312);
insert into product(name, type_id, expired_date, price)
values ('Охотничья', 3, date '2022-11-21', 397);
insert into product(name, type_id, expired_date, price)
values ('Аленка', 4, date '2022-08-14', 75);
insert into product(name, type_id, expired_date, price)
values ('Dove', 4, date '2022-11-03', 98);
insert into product(name, type_id, expired_date, price)
values ('Milka', 4, date '2022-08-16', 82);

SELECT * FROM product p JOIN type t ON p.type_id = t.id WHERE t.name LIKE  'СЫР';
SELECT * FROM product WHERE name LIKE '%Мороженое%';
SELECT * FROM product WHERE price = (SELECT MAX(price) FROM product);

SELECT t.name имя_типа, COUNT(type_id) количество FROM product p
JOIN type t ON p.type_id = t.id
GROUP BY t.id;

SELECT * FROM product p JOIN type t ON p.type_id = t.id 
WHERE t.name LIKE  'СЫР' OR t.name LIKE 'МОЛОКО';

SELECT t.name FROM product p JOIN type t ON p.type_id = t.id
GROUP BY t.id
HAVING COUNT(type_id) < 10;

SELECT p.name продукт, t.name тип FROM product p JOIN type t ON p.type_id = t.id
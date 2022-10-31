create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

INSERT INTO devices (name, price) VALUES ('fridge', 4000);
INSERT INTO devices (name, price) VALUES ('router', 5000);
INSERT INTO devices (name, price) VALUES ('tv', 2000);
INSERT INTO devices (name, price) VALUES ('monitor', 6000);
INSERT INTO devices (name, price) VALUES ('keyboard', 7000);
INSERT INTO devices (name, price) VALUES ('phone', 4500);
INSERT INTO devices (name, price) VALUES ('headphone', 6600);
INSERT INTO devices (name, price) VALUES ('laptop', 5500);

INSERT INTO people (name) VALUES ('BOB');
INSERT INTO people (name) VALUES ('MIKE');
INSERT INTO people (name) VALUES ('KATE');

INSERT INTO devices_people (device_id, people_id) VALUES (1, 1);
INSERT INTO devices_people (device_id, people_id) VALUES (2, 2);
INSERT INTO devices_people (device_id, people_id) VALUES (3, 3);
INSERT INTO devices_people (device_id, people_id) VALUES (4, 1);
INSERT INTO devices_people (device_id, people_id) VALUES (5, 2);
INSERT INTO devices_people (device_id, people_id) VALUES (6, 3);
INSERT INTO devices_people (device_id, people_id) VALUES (7, 1);
INSERT INTO devices_people (device_id, people_id) VALUES (8, 2);
INSERT INTO devices_people (device_id, people_id) VALUES (1, 3);
INSERT INTO devices_people (device_id, people_id) VALUES (2, 1);

SELECT AVG(price) avg_price FROM devices;

SELECT p.name, AVG(d.price) FROM devices as d
JOIN devices_people AS dp ON d.id = dp.device_id
JOIN people AS p ON p.id = dp.people_id
GROUP by p.name;

SELECT p.name, AVG(d.price) FROM devices as d
JOIN devices_people AS dp ON d.id = dp.device_id
JOIN people AS p ON p.id = dp.people_id
GROUP by p.name
HAVING AVG(d.price) > 5000;
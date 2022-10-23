create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('fish', 5000, null);
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('fish', 5000, null);
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('cow', 10500, '1949-02-03');
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('cat', 50000, 'J51187');
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('dog', 13000, '1939-11-11');
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('crab', 11000, '1989-03-08');
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('bird', 13000, null);
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('rat', 23000, '1993-01-08');
INSERT INTO fauna(name, avg_age, discovery_date) VALUES ('monkey', 22000, '1299-01-08');

SELECT * FROM fauna WHERE name LIKE '%fish%';
SELECT * FROM fauna WHERE avg_age > 10000 AND avg_age < 21000;
SELECT * FROM fauna WHERE discovery_date IS NULL;
SELECT * FROM fauna WHERE discovery_date < '01-01-1950';
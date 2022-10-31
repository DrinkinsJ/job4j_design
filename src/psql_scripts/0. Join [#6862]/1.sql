CREATE TABLE departments(
id SERIAL PRIMARY KEY,
name VARCHAR(255)
);

CREATE TABLE employees(
id SERIAL PRIMARY KEY,
name VARCHAR(255),
department_id int REFERENCES departments(id)
);

INSERT INTO departments (name) VALUES ('Marketing');
INSERT INTO departments (name) VALUES ('Operations');
INSERT INTO departments (name) VALUES ('Finance');
INSERT INTO departments (name) VALUES ('Sales');
INSERT INTO departments (name) VALUES ('Human');

INSERT INTO employees (name, department_id) VALUES ('emp1', 1);
INSERT INTO employees (name, department_id) VALUES ('emp2', 2);
INSERT INTO employees (name, department_id) VALUES ('emp3', 3);

SELECT d.name, e.name FROM employees AS e RIGHT JOIN departments AS d ON d.id = e.department_id;
SELECT d.name, e.name FROM employees AS e LEFT JOIN departments AS d ON d.id = e.department_id;
SELECT d.name, e.name FROM employees AS e FULL JOIN departments AS d ON d.id = e.department_id;
SELECT d.name, e.name FROM employees AS e CROSS JOIN departments AS d;

SELECT d.name, e.name FROM departments AS d LEFT JOIN employees AS e ON d.id = e.department_id
WHERE e.name IS NULL;

SELECT d.name, e.name FROM employees AS e RIGHT JOIN departments AS d ON d.id = e.department_id;
SELECT d.name, e.name FROM departments AS d LEFT JOIN employees AS e ON d.id = e.department_id;


CREATE TABLE teens(
id SERIAL PRIMARY KEY,
name VARCHAR(255),
gender VARCHAR(255)
);

INSERT INTO teens(name, gender) VALUES ('Daria', 'f');
INSERT INTO teens(name, gender) VALUES ('Igor', 'm');
INSERT INTO teens(name, gender) VALUES ('Konstantin', 'm');
INSERT INTO teens(name, gender) VALUES ('Alina', 'f');
INSERT INTO teens(name, gender) VALUES ('Tatiana', 'f');
INSERT INTO teens(name, gender) VALUES ('Alla', 'f');
INSERT INTO teens(name, gender) VALUES ('Ivan', 'm');
INSERT INTO teens(name, gender) VALUES ('Pavel', 'm');
INSERT INTO teens(name, gender) VALUES ('Nikita', 'm');

SELECT t1.name, t1.gender f, t2.name, t2.gender m
FROM teens t1 CROSS JOIN teens t2
WHERE t1.gender LIKE 'f' and t2.gender LIKE 'm' ;
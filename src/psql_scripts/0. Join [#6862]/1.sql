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
INSERT INTO employees (name, department_id) VALUES ('emp4', 3);
INSERT INTO employees (name, department_id) VALUES ('emp5', 2);
INSERT INTO employees (name, department_id) VALUES ('emp6', 3);
SELECT d.name, e.name FROM employees AS e RIGHT JOIN departments AS d ON d.id = e.department_id;
SELECT d.name, e.name FROM employees AS e LEFT JOIN departments AS d ON d.id = e.department_id;
SELECT d.name, e.name FROM employees AS e FULL JOIN departments AS d ON d.id = e.department_id;
SELECT d.name, e.name FROM employees AS e CROSS JOIN departments AS d;

SELECT d.name, e.name FROM departments AS d LEFT JOIN employees AS e ON d.id = e.department_id
WHERE e.name IS NULL;

SELECT d.name, e.name FROM employees AS e RIGHT JOIN departments AS d ON d.id = e.department_id;
SELECT d.name, e.name FROM departments AS d LEFT JOIN employees AS e ON d.id = e.department_id;


CREATE TABLE role(
id serial PRIMARY KEY,
name varchar(255)
);

CREATE TABLE users(
id serial PRIMARY KEY,
name varchar(255),
role_id int REFERENCES role(id)	
);

CREATE TABLE rules(
id serial PRIMARY KEY,
name varchar(255)
);

CREATE TABLE rules_role(
id serial PRIMARY KEY,
role_id int REFERENCES role(id),
rules_id int REFERENCES rules(id),
name varchar(255)
);

CREATE TABLE category(
id serial PRIMARY KEY,
name varchar(255)
);

CREATE TABLE state(
id serial PRIMARY KEY,
name varchar(255)
);

CREATE TABLE item(
id serial PRIMARY KEY,
users_id int REFERENCES users(id),
category_id int REFERENCES category(id),
state_id int REFERENCES state(id),
name varchar(255)
);

CREATE TABLE comments(
id serial PRIMARY KEY,
name varchar(255),
item_id int REFERENCES item(id)
);

CREATE TABLE attachs(
id serial PRIMARY KEY,
name varchar(255),
item_id int REFERENCES item(id)
);
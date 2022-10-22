create table cats(
    id serial PRIMARY KEY,
    breed varchar(255) unique
);

create table cat_female(
    id serial PRIMARY KEY,
    name varchar(255),
    breed varchar(255) references cats(breed),
	passport_id int 
);

create table cat_male(
	id serial PRIMARY KEY,
    name varchar(255),
    breed varchar(255) references cats(breed)
);

create table cats_liks(
	id serial primary key,
	cat_male_id int references cat_male(id),
	cat_female_id int references cat_female(id)
);

create table cat_family(
	id serial PRIMARY KEY,
	cat_male int references cat_male(id) unique,
	cat_female int references cat_female(id) unique
);

INSERT INTO cats(breed) VALUES ('Siberia');
INSERT INTO cats(breed) VALUES ('British');
INSERT INTO cat_male(name, breed) VALUES ('Tom', 'Siberia');
INSERT INTO cat_male(name, breed) VALUES ('Motya', 'British');
INSERT INTO cat_female(name, breed) VALUES ('Kata', 'Siberia');
INSERT INTO cat_female(name, breed) VALUES ('Masya', 'British');
INSERT INTO cats_liks(cat_male_id, cat_female_id) VALUES (1, 1);
INSERT INTO cats_liks(cat_male_id, cat_female_id) VALUES (1, 2);
INSERT INTO cats_liks(cat_male_id, cat_female_id) VALUES (2, 1);
INSERT INTO cats_liks(cat_male_id, cat_female_id) VALUES (2, 2);
INSERT INTO cat_family(cat_male, cat_female) VALUES (1, 2);
INSERT INTO cat_family(cat_male, cat_female) VALUES (2, 1);
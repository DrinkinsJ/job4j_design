CREATE TABLE car_bodies(
id SERIAL PRIMARY KEY,
name VARCHAR(255)
);

CREATE TABLE car_engines(
id SERIAL PRIMARY KEY,
name VARCHAR(255)
);

CREATE TABLE car_transmissions(
id SERIAL PRIMARY KEY,
name VARCHAR(255)
);

CREATE TABLE cars(
id SERIAL PRIMARY KEY,
name VARCHAR(255),
body_id INT REFERENCES car_bodies(id),
engine_id INT REFERENCES car_engines(id),
transmission_id INT REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies(name) VALUES ('SEDAN');
INSERT INTO car_bodies(name) VALUES ('COUPE');
INSERT INTO car_bodies(name) VALUES ('SPORTS CAR');
INSERT INTO car_bodies(name) VALUES ('STATION WAGON');
INSERT INTO car_bodies(name) VALUES ('HATCHBACK');
INSERT INTO car_bodies(name) VALUES ('CONVERTIBLE');
INSERT INTO car_bodies(name) VALUES ('SPORT-UTILITY VEHICLE (SUV)');
INSERT INTO car_bodies(name) VALUES ('MINIVAN');
INSERT INTO car_bodies(name) VALUES ('пикап');

INSERT INTO car_engines(name) VALUES ('GAS');
INSERT INTO car_engines(name) VALUES ('DISEL');
INSERT INTO car_engines(name) VALUES ('ELECTRO');
INSERT INTO car_engines(name) VALUES ('HYBRID');

INSERT INTO car_transmissions(name) VALUES ('Automatic Transmission (AT)');
INSERT INTO car_transmissions(name) VALUES ('Manual Transmission (MT)');
INSERT INTO car_transmissions(name) VALUES ('Automated Manual Transmission (AM)');
INSERT INTO car_transmissions(name) VALUES ('Continuously Variable Transmission (CVT)');

INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Presence', 1, 1, 3);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Renegade', 2, 2, 2);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Fang', 3, 3, 1);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Voyage', 4, 2, 2);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Universe', 5, 3, 1);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Dusk', 2, 3, 1);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Epiphany', 3, 1, 3);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Tigress', 4, 2, 2);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Prospect', 5, 3, 1);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Orbit', 3, 2, 1);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Falcon', 4, 3, 2);
INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES ('Shadow', 5, 3, 1);

INSERT INTO cars(name, body_id, engine_id) VALUES ('Eminence', 5, 3);
INSERT INTO cars(name, body_id, engine_id) VALUES ('Dominion', 4, 2);

INSERT INTO cars(name, body_id, transmission_id) VALUES ('Scorpion', 1, 1);
INSERT INTO cars(name, body_id, transmission_id) VALUES ('Trailblazer', 2, 2);

INSERT INTO cars(name, body_id) VALUES ('Flux', 3);
INSERT INTO cars(name, body_id) VALUES ('Meridian', 1);
INSERT INTO cars(name, body_id) VALUES ('Empyrean', 6);
INSERT INTO cars(name, body_id) VALUES ('Vagabond', 7);
INSERT INTO cars(name, body_id) VALUES ('Eon', 8);

SELECT c.id id, c.name car_name, cb.name body_name, ce.name engine_name, ct.name transmission_name FROM cars c 
LEFT JOIN car_bodies cb ON c.body_id = cb.id
LEFT JOIN car_engines ce ON c.engine_id = ce.id
LEFT JOIN car_transmissions ct ON c.transmission_id = ct.id;


SELECT cb.name FROM car_bodies cb LEFT JOIN cars c ON c.body_id = cb.id
WHERE cb.name NOT IN (
SELECT name FROM car_bodies WHERE c.body_id = cb.id
);
/* equal */
SELECT cb.name FROM car_bodies cb LEFT JOIN cars c ON c.body_id = cb.id
WHERE c.name IS NULL;

SELECT ce.name FROM car_engines ce LEFT JOIN cars c ON c.engine_id = ce.id
WHERE ce.name NOT IN (
SELECT name FROM car_engines WHERE c.engine_id = ce.id
);
/* equal */
SELECT ce.name FROM car_engines ce LEFT JOIN cars c ON  c.engine_id = ce.id
WHERE c.name IS NULL;

SELECT ct.name FROM car_transmissions ct LEFT JOIN cars c ON c.transmission_id = ct.id
WHERE ct.name NOT IN (
SELECT name FROM car_transmissions WHERE c.transmission_id = ct.id
);
/* equal */
SELECT ct.name FROM car_transmissions ct LEFT JOIN cars c ON  c.transmission_id = ct.id
WHERE c.name IS NULL;

CREATE VIEW show_all_cars_with_body_and_no_other_details AS
SELECT c.name car_name, cb.name body, ce.name engine, ct.name transmission FROM cars c 
LEFT JOIN car_bodies cb ON cb.id = c.body_id
LEFT JOIN car_engines ce ON ce.id = c.engine_id
LEFT JOIN car_transmissions ct ON ct.id = c.transmission_id
WHERE ce.name NOT IN (
SELECT name FROM car_engines WHERE c.engine_id = ce.id)
AND ct.name NOT IN (
SELECT name FROM car_transmissions WHERE c.transmission_id = ct.id);

SELECT * FROM show_all_cars_with_body_and_no_other_details;

SELECT * FROM show_all_cars_with_body_and_no_other_details
WHERE car_name LIKE 'Flux';
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
create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

INSERT INTO products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
INSERT INTO products (name, producer, count, price) VALUES ('product_2', 'producer_2', 6, 150);
INSERT INTO products (name, producer, count, price) VALUES ('product_3', 'producer_3', 7, 250);
INSERT INTO products (name, producer, count, price) VALUES ('product_5', 'producer_1', 9, 350);
INSERT INTO products (name, producer, count, price) VALUES ('product_6', 'producer_2', 11, 450);
INSERT INTO products (name, producer, count, price) VALUES ('product_7', 'producer_1', 2, 70);
INSERT INTO products (name, producer, count, price) VALUES ('product_8', 'producer_4', 4, 120);
INSERT INTO products (name, producer, count, price) VALUES ('product_9', 'producer_2', 6, 50);
INSERT INTO products (name, producer, count, price) VALUES ('product_10', 'producer_3', 8, 10);

create or replace function tax_after_insert()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
		where id = (SELECT id FROM inserted);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_after_insert
after insert on products
    referencing new table as inserted
    for each statement
execute PROCEDURE tax_after_insert();

create or replace function tax_before_insert()
    returns trigger as
$$
    BEGIN
        new.price = new.price + new.price * 0.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER tax_trigger_before_insert
BEFORE insert
ON products
FOR EACH ROW EXECUTE PROCEDURE tax_before_insert();


create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function history_insert()
    returns trigger as
$$
    BEGIN
        INSERT INTO history_of_price (name, price, date) VALUES(NEW.name, NEW.price, CURRENT_TIMESTAMP);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER history_trigger_price_insert_date
BEFORE insert
ON products
FOR EACH ROW EXECUTE PROCEDURE history_insert();
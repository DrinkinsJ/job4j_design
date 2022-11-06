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

create or replace function f_delete_data(u_id integer)
returns void
language 'plpgsql'
as
$$
    BEGIN
        DELETE FROM products WHERE id = u_id;
    END;
$$;

create or replace procedure p_delete_data(i_count integer)
language 'plpgsql'
as $$
    BEGIN
    DELETE FROM products WHERE count < 5;
    END
$$;
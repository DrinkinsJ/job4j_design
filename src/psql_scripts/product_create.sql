create table Product (
    id serial primary key, 
    model varchar(6),
	CP int,
	price decimal(6,2)
	
);
insert into product (model, cp, price) values ('model1', 100, 125.55);
update product set CP = 400;
update product set price = 425.55;
delete from product;
select * from product;
drop table product;
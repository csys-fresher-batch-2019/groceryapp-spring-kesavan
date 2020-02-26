
create table products(
 product_name varchar(30) not null,
 product_id int primary key,
 manufacturer varchar(30),
 quantity float,
 unit varchar(25),
 price_Rs int not null,
 stock int,
 status varchar(25)
 );
 
 create table usersdata(
user_id int primary key auto_increment,
user_name varchar(25) unique,
password varchar(15) not null,
mail_id varchar(30) unique,
phone_no bigint(10) unique ,
delivery_address varchar(60)
);

create table orderdata(
user_id int not null,
order_id int primary key auto_increment,
product_id int not null,
no_of_items  int not null,
price_per_item int,
total_amount int,
order_date date  not null,
delivery_date date,
order_status varchar(30) default 'NOT ORDERED',
payment varchar(30) default 'COD',
transaction_id int,
constraint product_id_fk foreign key(product_id) references products(product_id),
constraint user_id_fk foreign key(user_id) references usersdata(user_id)
);

create table proreview(
product_id int,
rating float default 0,
review varchar(10)
);

create table review(
order_id int unique,
product_id int not null,
rating float not null,
constraint product_fk foreign key(product_id) references products(product_id)
);

drop table usersdata;
drop table orderdata;
drop table usersdata;
 

select * from orderdata;
select * from usersdata;
select * from products;
select * from proreview;
select * from review;

 insert into proreview(product_id,rating,review)
values(2123,4,'Good');
delete from proreview where product_id=2123 and rating=0
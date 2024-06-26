create database tabling;

use tabling;

create table category (
    category_id int primary key AUTO_INCREMENT,
    category_name varchar(50) not null
);

create table location(
	location_id int primary key auto_increment,
    location_name varchar(50)
);

create table customer(
	customer_id int primary key auto_increment,
    customer_name varchar(50),
    phone varchar(50) not null unique,
    state enum('Y', 'N') default 'N',
    location_id int,
    foreign key(location_id) references location(location_id)
);

create table restaurant(
	restaurant_id int primary key auto_increment,
    restaurant_name varchar(100) not null,
    phone varchar(50),
    address varchar(255) not null,
    content text,
    open_time time,
    close_time time,
    rating decimal(2, 1),
    rest_day varchar(5),
    location_id int,
    category_id int,
    foreign key(location_id) references location(location_id),
    foreign key(category_id) references category(category_id)
);

create table food (
    food_id int primary key AUTO_INCREMENT,
    food_name VARCHAR(100) not null,
    category_id int,
    FOREIGN KEY(category_id) REFERENCES category(category_id)
);

create table reservation(
	reservation_id int primary key auto_increment,
    reservation_state enum('Y', 'N') default 'Y',
    reservation_time timestamp default current_timestamp,
    customer_id int,
    restaurant_id int,
    foreign key(customer_id) references customer(customer_id) on delete set null,
    foreign key(restaurant_id) references restaurant(restaurant_id) on delete set null
);

create table menu (
    restaurant_id int,
    food_id int,
    price int not null,
    PRIMARY KEY(restaurant_id, food_id),
    FOREIGN KEY(restaurant_id) REFERENCES restaurant(restaurant_id),
    FOREIGN KEY(food_id) REFERENCES food(food_id)
);

create table likes(
	customer_id int,
    restaurant_id int,
    primary key(customer_id, restaurant_id),
    foreign key(customer_id) references customer(customer_id) on delete cascade,
    foreign key(restaurant_id) references restaurant(restaurant_id) on delete cascade
);
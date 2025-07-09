drop database if exists interviewTask;

create database interviewTask;

use interviewTask;

create table users (
	id int primary key auto_increment,
    username varchar(50) not null,
    balance decimal(18, 2) not null default 10000.00
);

create table holdings (
	id int primary key auto_increment,
    user_id int,
    crypto_symbol varchar(10),
    quantity decimal(18,8),
    foreign key (user_id) references users(id)
);

create table transactions (
	id int primary key auto_increment,
    user_id int,
    crypto_symbol varchar(10),
    type enum('BUY', 'SELL'),
    quantity decimal(18,8),
    price decimal(18,2),
    total decimal(18,2),
    time_stamp timestamp default current_timestamp,
    foreign key (user_id) references users(id)
);
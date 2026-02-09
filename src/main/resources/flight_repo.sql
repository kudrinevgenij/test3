create table if not exists airport (
code char(3) primary key,
country varchar(256) not null,
city varchar(128) not null
);

create table if not exists aircraft (
id integer primary key,
model varchar(128) not null,
image bytea
);

create table if not exists seat (
aircraft_id integer not null,
seat_no varchar(4) not null,
primary key(aircraft_id, seat_no)
);

create table if not exists ticket (
id bigint primary key not null,
passenger_no varchar(32) not null,
passenger_name varchar(128) not null,
flight_id bigint references flight(id) not null,
seat_no varchar(4) not null,
coast numeric(8, 2) not null
);

create table if not exists flight (
id bigint primary key,
flight_no varchar(16) not null,
departure_date timestamp not null,
departure_airport_code char(3) not null references airport(code),
arrival_date timestamp not null,
arrival_airport_code varchar(3) not null references airport(code),
aircraft_id integer not null references aircraft(id),
status varchar(32) not null
);

create table if not exists users (
id serial primary key,
name varchar(124) not null,
birthday date not null,
email varchar(124) not null unique,
password varchar(32) not null,
role varchar(32) not null,
gender varchar(32) not null
);
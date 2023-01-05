create database room_inventory;

create table room_type(id integer primary key, name varchar(50), num_of_beds integer, ac_type enum('Non-AC', 'AC', 'Air Cooler'), price integer);

create table room_details(room_no integer primary key, room_type integer, constraint room_type_foreign_key foreign key(room_type) references room_type(id));

create table booking_history(booking_id varchar(200), room_no integer, start_date date, end_date date, booking_status enum ('PENDING', 'BOOKED', 'CANCELLED'), price integer, constraint booking_room foreign key (room_no) references room_details(room_no));

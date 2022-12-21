create database user_inventory;
use user_inventory;

create table user_details (id varchar(100) primary key, name varchar(255), age integer, date_of_birth date, password varchar(255), role_id integer, 
manager_id varchar(100), date_of_joining date, email varchar(255), phone_no integer, constraint manager_reference_id foreign key(manager_id) references user_details(id));

create table user_attendance(id integer primary key, user_id varchar(100), attendance_date date, in_time time, out_time time, 
constraint attendance_user_id_foreign_key foreign key(user_id) references user_details(id));

create table leave_type(id integer primary key, leave_name varchar(100) unique not null, total integer, description varchar(255));

create table leave_history (id integer primary key, user_id varchar(100), from_date date, to_date date, leave_type integer, leave_status enum("Pending", "Cancelled", "Approved"), approver varchar(100), 
constraint approver_foreign_key foreign key(approver) references user_details(id), 
constraint leaver_type_foreign_key foreign key(leave_type) references leave_type(id), 
constraint user_leave_foreign_key foreign key(user_id) references user_details(id));

create table leave_management(id integer primary key, user_id varchar(100) unique not null, leave_type integer, availed integer, pending integer, balance integer, 
constraint leave_type_foreign_key foreign key(leave_type) references leave_type(id), 
constraint user_id_leave_foreign_key foreign key(user_id) references user_details(id));

create table payroll(id integer primary key, user_id varchar(100) unique not null, basic integer, gross integer, variable integer, nps boolean, 
constraint payroll_employee_foreign_key foreign key(user_id) references user_details(id));
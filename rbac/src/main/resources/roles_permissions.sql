# Create a new database for role_base_access_control
create database role_base_database;

use role_base_database;

# create a table for roles
create table roles (id integer primary key, role_name varchar(100) unique not null);

# create a table defining services
create table service (id integer primary key, suffix_path varchar(255), http_method varchar(255));

# create a table to define permissions
# 0 - no permission
# 1 - partial permission (self and managerial access)
# 2 - direct access
create table permission (
id integer primary key, 
role_id integer, 
service_id integer, 
permission enum ('0','1','2'), 
constraint service_id_key foreign key(service_id) references service(id),
constraint role_id_key foreign key(role_id) references roles(id))
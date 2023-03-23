insert into department (name) values ('r&d');
insert into department (name) values ('production');
insert into department (name) values ('marketing');

-- R&D
insert into employee(first_name, last_name, salary, department_id)
SELECT 'Anna', 'Alberti', 3000.0, id from DEPARTMENT d where d.NAME='r&d';
insert into employee(first_name, last_name, salary, department_id)
SELECT 'Barbara', 'Bandini', 2000.0, id from DEPARTMENT d where d.NAME='r&d';
insert into employee(first_name, last_name, salary, department_id)
SELECT 'Carlo', 'Castelli', 1000.0, id from DEPARTMENT d where d.NAME='r&d';

-- production
insert into employee(first_name, last_name, salary, department_id)
SELECT 'Davide', 'Dominici', 350.0, id from DEPARTMENT d where d.NAME='production';
insert into employee(first_name, last_name, salary, department_id)
SELECT 'Elena', 'Evangelista', 3000.0, id from DEPARTMENT d where d.NAME='production';



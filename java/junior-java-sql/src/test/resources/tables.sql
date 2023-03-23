create table employee (
    id integer identity primary key,
    first_name varchar(128),
    last_name varchar(128),
    salary double precision,
    department_id integer
);

create table department (
    id integer identity primary key,
    name varchar(128)
);

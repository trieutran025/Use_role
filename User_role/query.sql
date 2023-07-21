CREATE DATABASE  user_role;
CREATE TABLE User(
 id_user INT PRIMARY KEY ,
 full_name VARCHAR(45),
 code VARCHAR(45),
 birth DATE,
 start_date DATE
);
CREATE TABLE role(
	id_role INT PRIMARY KEY ,
    role_name VARCHAR(45)
);
create table user_role(
	id_user INT,
    id_role INT,
    primary key(id_user,id_role),
    FOREIGN KEY(id_user) REFERENCES user(id_user),
    FOREIGN KEY(id_role) REFERENCES role(id_role)
);
	

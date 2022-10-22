CREATE DATABASE languages CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE language
(
    id int(11) AUTO_INCREMENT,
    `welcomemsg` VARCHAR(100) not null ,
    code VARCHAR(3) not null

);

SHOW databases ;

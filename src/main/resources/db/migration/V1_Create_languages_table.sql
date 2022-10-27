

CREATE TABLE languages (
    id INT  unsigned primary key auto_increment,
    welcomeMsg varchar (100) not null,
    code varchar (3)
);
SELECT *FROM languages;
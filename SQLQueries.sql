CREATE DATABASE thinkdb;

CREATE TABLE Inventory (
    Id int NOT NULL AUTO_INCREMENT,
    Name varchar(100) NOT NULL,
    Description varchar(1000),
    Price float,
    PRIMARY KEY (Id)
);
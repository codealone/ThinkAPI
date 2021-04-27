CREATE DATABASE thinkdb;

CREATE TABLE Inventory (
    Id int NOT NULL AUTO_INCREMENT,
    Name varchar(100) NOT NULL,
    Description varchar(1000),
    Price float,
    PRIMARY KEY (Id)
);

Stored Procedures:

1. Create procedure DeleteItem    (     Id int     )      BEGIN            Delete from StudentReg where Id=Id;    END //

2.Create procedure ListAll()    BEGIN            select * from inventory;    END //

3.Create procedure AddNewItem    (         Name nvarchar (100),         Description nvarchar (1000),         Price float      )      BEGIN         Insert into inventory (Name, Description, Price)        values (Name, Description, Price) ;    END //

4.Create procedure UpdateItem    (     Id int,       Name nvarchar (100),         Description nvarchar (1000),         Price float      )      BEGIN            Update inventory     set Name= Name,    Description=Description,                Price=Price            where Id=Id;    END //


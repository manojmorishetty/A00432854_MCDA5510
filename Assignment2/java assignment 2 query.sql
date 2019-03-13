use java_assignment2;
create table Transaction( ID int(11) PRIMARY KEY, 
NameOnCard varchar(256), 
CardNumber varchar(45), 
UnitPrice decimal(10,0), 
Quantity int(11), 
TotalPrice decimal(10,0), 
ExpDate varchar(16), 
CreatedOn datetime, 
CreatedBy varchar(45)
);
select * from transaction;
CREATE TABLE CARDTYPE (CREDITCARD VARCHAR(30),PREFIX INT(15),LENGTH INT(15));
insert into CARDTYPE(CREDITCARD,PREFIX,LENGTH) VALUES('MasterCard',51,16);
insert into CARDTYPE(CREDITCARD,PREFIX,LENGTH) VALUES('MasterCard',52,16);
insert into CARDTYPE(CREDITCARD,PREFIX,LENGTH) VALUES('MasterCard',53,16);
insert into CARDTYPE(CREDITCARD,PREFIX,LENGTH) VALUES('MasterCard',54,16);
insert into CARDTYPE(CREDITCARD,PREFIX,LENGTH) VALUES('MasterCard',55,16);
insert into CARDTYPE(CREDITCARD,PREFIX,LENGTH) VALUES('AmericanExpress',34,15);
insert into CARDTYPE(CREDITCARD,PREFIX,LENGTH) VALUES('AmericanExpress',37,15);
insert into CARDTYPE(CREDITCARD,PREFIX,LENGTH) VALUES('Visa',4,16);
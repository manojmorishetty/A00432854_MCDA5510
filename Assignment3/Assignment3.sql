create table person(pid int IDENTITY(1,1), FName varchar(50), LName varchar(50), Street_Number varchar(50), Street varchar(50), City varchar(50),
					Province varchar(50), Country varchar(50), Postal_Code varchar(50), Phone_Number bigint, email varchar(50)) 

--Drop table person
truncate table person 
insert into person values('Manoj5','Kumar5','1-2-3','Sount Park','Halifax','NS','Canada','B3J2K9',9885045451,'manoj@gmail.com')
insert into person values('Manoj5','Kumar5','1-2-3','Sount Park','Halifax','NS','Canada','B3J2K9',988545451,'manoj@gmail.c')
insert into person values('Manoj5','Kumar5','1-2-3','Sount Park','Halifax','NS','Canada','B33J2K9',98845451,'manoj@gmail.c')
insert into person values('Manoj5','Kumar5','1-2-3','Sount Park','Halifax','NS','Canada','B3J 2K9',0885045451,'manoj@gmail.c')
insert into person values('Manoj5','Kumar5','1-2-3','Sount Park','Halifax','NS','Canada','B3J2K9',9885045451,'m@gmail.c0n')
insert into person values('Manoj5','Kumar5','1-2-3','Sount Park','Halifax','NS','Canada','B3J2K9',9885045451,'90@gmail.c0n')
insert into person values('Manoj5','Kumar5','1-2-3','Sount Park','Halifax','NS','Canada','B332K9',9885045451,'m@gmail.c0n')
select * from person
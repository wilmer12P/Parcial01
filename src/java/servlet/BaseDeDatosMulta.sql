create database multa;
use multa;
create table detalle
(codigo int auto_increment,
DNI int,
multa varchar(30),
monto numeric(6,2),
PRIMARY KEY (codigo)
);
insert into detalle(DNI,multa,monto)
 values(78653745,'Pasa luz roja',550.00);

select*from detalle;


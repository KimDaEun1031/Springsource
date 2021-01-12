create table person(
	id nvarchar2(50) not null,
	name nvarchar2(50) not null
);

select * from person;

insert into person(id,name) values('kong','김다은');

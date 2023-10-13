				-- setup --
set echo on
set serveroutput on

				--- DDL ---
drop table grade;
drop table student;
drop table course;

create table student (
s#    char(4),
sname varchar2(10),
age   int,
primary key (s#));

create table course (
c#    char(5),
cname varchar2(10),
loc   varchar2(10),
primary key (c#));

create table grade (
s#    char(4),
c#    char(5),
mark  int,
primary key (s#,c#),
foreign key (s#) references student (s#),
foreign key (c#) references course (c#));

				--- DML ---
insert into student 
values ('1000', 'John', 25);

insert into student 
values ('2000', 'Mary', 30);

insert into student 
values ('3000', 'Jack', 20);

insert into course 
values ('CS300', 'OS', 'ME300');

insert into course 
values ('CS305', 'DB', 'ME400');

insert into course 
values ('MT230', 'AL', 'ME300');

insert into grade
values ('1000', 'CS300', 90);
insert into grade
values ('1000', 'CS305', 85);
insert into grade
values ('2000', 'CS305', 80);

select * from student;
select * from course;
select * from grade;


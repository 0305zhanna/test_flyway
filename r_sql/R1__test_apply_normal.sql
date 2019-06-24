
create table test ( name1 varchar2(10) );

insert into test (name1) VALUES ('hello1');
insert into test (name1) VALUES ('hello2');
insert into test (name1) VALUES ('hello3');

update test set name1 = 'hello333' where name1 = 'hello3';

insert into test (name1) VALUES ('iamhere');
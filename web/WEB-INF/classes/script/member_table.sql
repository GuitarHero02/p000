CREATE TABLE member (
	id INTEGER PRIMARY KEY, 
	name VARCHAR(20) NOT NULL, 
	joined DATE
); 

insert into member values (1, 'leeseungchan', SYSDATE);
insert into member values (2, 'junjuho', SYSDATE);
insert into member values (3, 'leejaeyong', SYSDATE);
insert into member values (4, 'hanchulhyun', SYSDATE);
insert into member values (5, 'choimunsuk', SYSDATE);
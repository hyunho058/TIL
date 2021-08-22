users 테이블 생성 구문 
     id          
     password   
     name        
     role      // user 가 관리자인지 사용자인지
     
     board 테이블 생성 구문 
     seq          pk
     title        개시글 제목
     content      내용
     regdate     올린 날짜
     cnt         조회횟수
     id        kf

drop table board;
drop table users;

create table users(
	id varchar2(40) CONSTRAINT users_pk primary key,
	password varchar2(20),
	name varchar2(20),
	role varchar2(20)
);
create table board(
	seq number(5) CONSTRAINT board_pk primary key,
	title varchar2(40),
	content varchar2(40),
	regdate date default sysdate,
	cnt number(5) ,
	id varchar2(40) CONSTRAINT fk_id REFERENCES users
);
--회원 정보 등록
insert into users(id, password, name, role)
values ('a', 'a123', '에이', 'user');
insert into users(id, password, name, role)
values ('b', 'b123', '비', 'user');
insert into users(id, password, name, role)
values ('c', 'c123', '씨', 'user');
insert into users(id, password, name, role)
values ('d', 'd123', '디', 'user');
insert into users(id, password, name, role)
values ('e', 'e123', '이', 'user');
--회원 정보 수정
update users set password = 'c123!' where id = 'c';
--게시판 등록
insert into board(seq, title, content, cnt, id)
values (1, 'a성장', '성장 이야기', 10, 'a');
insert into board(seq, title, content, cnt, id)
values (2, 'b성장', '성장 이야기', 10, 'b');
insert into board(seq, title, content, cnt, id)
values (3, 'c성장', '성장 이야기', 10, 'c');
insert into board(seq, title, content, cnt, id)
values (4, 'd성장', '성장 이야기', 10, 'd');
insert into board(seq, title, content, cnt, id)
values (5, 'e성장', '성장 이야기', 10, 'e');
insert into board(seq, title, content, cnt, id)
values (6, 'c텅장', '성장 이야기', 10, 'c');
insert into board(seq, title, content, cnt, id)
values (6, 'a두번', '성장 이야기', 10, 'a');
insert into board(seq, title, content, cnt, id)
values (7, 'a세번', '성장 이야기', 18, 'a');
--게시물 수정
update board set title = 'c취업' where id = 'c';
--게시글 삭제
delete from board where title = 'c텅장';


--로그인
select *
from users
where id = 'a' and password = 'a123';






  
  
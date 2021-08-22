
alter user hr idnetified by hr account unlook;

conn hr/hr
sqlplus hr/hr

select * from tab;
select * from employees;


** TABLESPACE **

create TABLESPACE mc
datafile 'C:\oraclexe\app\oracle\oradata\XE\mc.dbf'
size 10M
autoextend on next 1M maxsize UNLIMITED;


drop TABLESPACE mc INCLUDING CONTENTS AND Datafiles;


**계정 생성** 권한을 부여 해줘야 login 가능
create user test01 identified by 1234
default TABLESPACE mc;

*권한부여*
grant connect,resource,dba to test01;

*권한 제거*
revoke dba from test01;
revoke connect from test01;

*계정 삭제
drop user test01 cascade;

**SCOTT/TIGER 계정 생성**
create user SCOTT identified by TIGER
default TABLESPACE mc;

selec * from tab;

@C:\lib/scott.sql;

select * from tab; //환경 셋팅 명령어

set linesize 300;
set pagesize 20;

select * from emp;

col ename for a10;
col job for a12;
col deptno for 9999;
col sal for 9999;
col mqr for 9999;
col comm for 9999;




conn SCOTT/TIGER 스캇 테이블로 이동

select * from emp;
select * from dept;
select * from salgrade;

desc emp;  // emp table 구조 보여줘


select ename,sal,deptno
from emp; 

select ename,hiredate
from emp;

select deptno,dname
from dept;

select job
from emp;

select distinct job
from emp;

select ename, sal, sal*12
from emp;

select ename, sal, sal*12 as "연봉"
from emp;

select ename,job, sal, comm
from emp;

select ename,job, sal, comm, sal+comm as "실급여"
from emp;

select ename,job, sal, comm, sal+comm, nvl(comm, -1)
from emp;

select ename,job, sal, comm, sal+nvl(comm, 0) as "총 급여"
from emp;

select empno, ename, nvl(to_char(sal), 'CEO') as "상사 code"
from emp;

select ename, job
from emp;

select ename||' '||job
from emp;

select 20*20*4
from emp;

select 20*20*4
from dual;  

select sysdate
from dual;  

select user
from dual; 

-- where --

select ename, job, deptno
from emp
where deptno = 10;

 -- 양쪽 type을 대문자나 소문자로 일치 시켜줘야함 --
select ename, job, deptno
from emp
where UPPER(job) = UPPER('MANAGEr');

select sysdate
from dual;

select ename, hiredate
from emp
where hiredate > '82/01/01';

select ename, hiredate
from emp
where hiredate != '81/05/01';

select ename, hiredate
from emp
where hiredate <> '81/05/01';


-- between and 연산자

select ename, sal
from emp
where sal >= 2450 and sal <=3000;

select ename, sal
from emp
where sal between 2450 and 3000;

select ename, job, deptno
from emp
where deptno = 10 or deptno =20;

select ename, job, deptno
from emp
where deptno = in(10m20);

select *
from dept;
-- 20이면서 DALLAS, 30이면서 CHICAGO 인
select *
from dept
where (deptno,loc) in((20,'DALLAS'), (30,'CHICAGO'));

-- like 연산자 --

select * 
from emp
where ename = 'A';

select *
from emp
where ename like 'A%';

select *
from emp
where ename like '%A';

select *
from emp
where ename like '%A%';

select *
from emp
where upper(ename) like upper('%A%');

select *
from emp
where ename like '%A__';

-- 81년생 출력 --
select *
from emp
where hiredate like '81%';

-- where null --
select *
from emp
where mgr is null;

select *
from emp
where mgr is not null;

--총 급여가 2000 이상
select ename,sal,comm,sal+nvl(comm, 0) as"총 급여"
from emp
where "총 급여" >= 2000;

select ename,sal,comm,sal+nvl(comm, 0) as"총 급여"
from emp
where sal+nvl(comm, 0) >= 2000;

--정렬
select
from
where
order by


select ename,sal,comm,sal+nvl(comm, 0) as"총 급여"
from emp
order by sal;

--내림차순 정렬
select ename,sal,comm,sal+nvl(comm, 0) as"총 급여"
from emp
order by sal desc;

--오름차순 정렬
select ename,sal,comm,sal+nvl(comm, 0) as"총 급여"
from emp
order by sal asc;

select ename,sal,comm,sal+nvl(comm, 0) as"총 급여"
from emp
order by sal asc , comm desc;

select ename,sal,sal+nvl(comm, 0) as"총 급여"
from emp
where comm is not null
order by sal asc , comm desc;


select ename,sal,sal+nvl(comm, 0) as"총 급여"
from emp
where sal+nvl(comm, 0) >= 2000
order by 3;

select * from employees;

sonn SCOTT/TIGER
-- 단일행 함수 --
select dname,lower(dname) ,loc, lower(loc)
from dept;

select round(44.55), round(44.55,1), trunc(44.55) from dual;

select sal, trunc(sal*0.03) as "TAX"
from emp;

select ename, hiredate, substr(hiredate,4,2) as "입사 월"
from emp;
--12월 입사 정보 출력
select ename, hiredate, substr(hiredate,4,2) as "입사 월"
from emp
where substr(hiredate,4,2) = 12;

select ename, hiredate as "입사 일"
from emp
where substr(hiredate,4,2) = 12;

select sysdate, sysdate+30 from dual;

select sysdate, substr(sysdate, 4,2) from dual;
select sysdate, to_char(sysdate,'yy') from dual;
select sysdate, to_char(sysdate,'yyyy') from dual;
select sysdate, to_char(sysdate,'day') from dual;
select sysdate, to_char(sysdate,'mm') from dual;
select sysdate, to_char(sysdate,'dd') from dual;

--emp 에서 사원들의 입사월 정보 출력
select ename,to_char(hiredate,'mm') as "입사 월 ", to_char(hiredate,'day') as "입사 요일"
from emp
order by to_char(hiredate,'dd') asc;

select sysdate,to_date('2019/12/24') from dual;
select sysdate,to_date('2019-12-24') from dual;
select sysdate,to_date('2019 12 24') from dual;

select sysdate,to_date('12/12/19','mm/dd/yy') from dual;

--decode 함수
select ename, sal deptno,decode(deptno, 10, sal*1.2, 20,sal*0.7, sal)as "보너스"
from emp
order by deptno;
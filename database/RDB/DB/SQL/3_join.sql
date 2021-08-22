-- JOIN --
select * from dept;
select * from emp;

----------------------------------------
--oracle join
----------------------------------------
select ename,dname,loc
from emp,dept
where emp.deptno = dept.deptno;

select ename,emp.deptno, dname,loc
from emp,dept
where emp.deptno = dept.deptno;

select ename,dept.deptno, dname,loc
from emp,dept
where emp.deptno = dept.deptno;

-- 테이블 이름을 별칭 지정하여 사용
select ename,e.deptno, dname,loc
from emp e,dept d
where e.deptno = d.deptno;

--sal > 2000 and deptno 가 20번 부서에 근무하는 사원이름, sal,loc 출력
select ename, sal, loc
from emp, dept
where emp.deptno = dept.deptno and sal > 2000 and emp.deptno = '20';

----------------------------------------
--Ansi join DB를 가리지 않는 표준 Join
-- , 를 join 으로 where 를 on 으로 표시
----------------------------------------

select ename,e.deptno, dname,loc
from emp e,dept d
where e.deptno = d.deptno;

select ename,e.deptno, dname,loc
from emp e join dept d
on e.deptno = d.deptno;

select ename,e.deptno, dname,loc
from emp e inner join dept d
on e.deptno = d.deptno;

select ename, sal, loc
from emp join dept
on emp.deptno = dept.deptno 
where sal > 2000 and emp.deptno = '20';

--outer join
-- null 쪽에 '+' 마킹
select ename, d.deptno, dname, loc
from emp e, dept d
where e.deptno(+) = d.deptno;

--outer Ansi Join

select ename, d.deptno, dname, loc
from emp e right outer join dept d
on e.deptno = d.deptno
order by d.deptno;

-----------------------------
-- non equi join
-----------------------------
select *
from salgrade;

--oracle join
select ename, sal, grade
from emp,salgrade
where sal between losal and hisal;

--ansi join
select ename, sal, grade
from emp join salgrade
on sal between losal and hisal;


---------------------
--n개의 tabel join
---------------------
--사원명, sal, 부서명, salgrade
select ename, sal, dname ,grade
from emp NATURAL JOIN dept JOIN salgrade
on sal between losal and hisal ;

select ename, sal, dname ,grade
from emp e JOIN dept d on e.deptno = d.deptno JOIN salgrade
on sal between losal and hisal;

select ename, sal, dname ,grade
from emp e , dept d , salgrade
where e.deptno = d.deptno and sal between losal and hisal; 

--sal > 1500 사원명, sal, 부서명, salgrade
select ename, sal, dname ,grade
from emp NATURAL JOIN dept JOIN salgrade
on sal between losal and hisal 
where sal > 1500;

select ename, sal, dname ,grade
from emp e JOIN dept d on e.deptno = d.deptno JOIN salgrade
on sal between losal and hisal
where sal>1500;

select ename, sal, dname ,grade
from emp e , dept d , salgrade
where e.deptno = d.deptno and sal between losal and hisal and sal > 1500; 

-------------------
--oracle self join
-------------------
select e.ename as "사원명", em.ename as "상사 이름"
from emp e, emp em
where e.mgr = em.empno(+);

select e.ename as "사원명", em.ename as "상사 이름"
from emp e, emp em
where e.mgr = nvl(em.empno, 'CEO');

select e.ename as "사원명",e.sal, em.ename as "상사 이름",em.sal
from emp e, emp em
where e.mgr = em.empno and e.sal >= em.sal;

--------------------------------------------
--ansi join
--------------------------------------------
select e.ename as "사원명", em.ename as "상사 이름"
from emp e left join emp em
on e.mgr = em.empno;

select e.ename as "사원명",e.sal, em.ename as "상사 이름",em.sal
from emp e join emp em
on e.mgr = em.empno 
where e.sal >= em.sal;

--집계 함수

select ename, round(sal) from emp;

select ename, avg(sal) from emp; --err 발생

select round(avg(sal))||'원'as "전체 평균 급여" from emp;
select deptno, round(avg(sal)) from emp group by deptno ;  --부서별 평균 급여

select round(avg(sal)) 
from emp 
group by deptno ; 

select avg(sal), count(*),min(sal),max(sal),count(mgr)
from emp;

select avg(sal), count(*),min(sal),max(sal),count(mgr)
from emp
where deptno = 10;

select avg(sal), count(*),min(sal),max(sal),count(mgr)
from emp
where deptno = 20;

select deptno,avg(sal), count(*),min(sal),max(sal),count(mgr)
from emp
group by deptno;

select d.deptno,dname,avg(sal), count(*),min(sal),max(sal)
from emp e, dept d
where e.deptno = d.deptno
group by d.deptno,dname;

--부서별 평균 급여 2000이상인 목록 출력
select d.deptno,dname,round(avg(sal)) as "평균 급여"
from emp e, dept d
where e.deptno = d.deptno
group by d.deptno,dname
having round(avg(sal)) > 2000;

--subQuery
-- ford 보다 급여가 많은 사원 목록
select sal
from emp
where ename = 'FORD';

select *
from emp
where sal >3000;

select *
from emp
where sal > (select sal from emp where ename = 'FORD');

select *
from emp
where sal < (select avg(sal) from emp);
--최소급여 목록
select *
from emp
where sal = (select min(sal) from emp);

-- 부서별고고 급여 목룍
select *
from emp
where sal in (select max(sal) from emp group by deptno);
-- 부서별고고 급여 목룍
select *
from emp
where (deptno,sal) in (select deptno, max(sal) from emp group by deptno)
order by deptno;


----------
--rowNum--
----------
select rownum, ename, job, sal
from emp;

select rownum, ename, job, sal
from emp
order by sal;

select rownum, ename, job, sal
from (select * from emp order by sal);

select rownum, ename, job, sal
from (select * from emp order by sal desc)
where rownum <4;

select rownum, ename, job, sal
from (select * from emp order by sal desc)
where rownum between 1 and 4;

--------------------
--oracle page 처리 --
--------------------
select rownum, ename, job, sal
from (select * from emp order by sal desc)
where rownum between 1 and 5;

--값이 출력 되지 않음--
select rownum, ename, job, sal
from (select * from emp order by sal desc)
where rownum between 6 and 10;

select *
from(
	select rownum row#, ename, job, sal
	from (select * from emp order by sal desc)
)
where row# between 6 and 10;



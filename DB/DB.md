[web 프로그램](https://www.w3schools.com/js/default.asp)

# SQL 

[Oracle DateBase](https://www.oracle.com/database/technologies/xe-downloads.html)

![image-20191223094942291](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20191223094942291.png)

SQL Developer

![image-20191223094856069](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20191223094856069.png)

![image-20191223095535889](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20191223095535889.png)

```
할당 Port 
Listener 1521
Listener 8080
```





## 시스템 계정 접속 확인 DCM

```bash
# sqlplus system/1234
C:\Users\student>sqlplus system/1234
SQL*Plus: Release 11.2.0.2.0 Production on 월 12월 23 10:10:41 2019
Copyright (c) 1982, 2014, Oracle.  All rights reserved.

Connected to:
Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production

# conn hr/hr //인사 관리 기본 sample table
SQL> conn hr/hr
ERROR:
ORA-28000: the account is locked
# hr/hr 계정으로 로그인 할건데 락이 걸려있는 상태.
Warning: You are no longer connected to ORACLE.

# 락 푸는 작업
alter user hr identified by hr account unlock; 
# 관리자만 설정할 수 있는 명령어

C:\Users\student>sqlplus

SQL*Plus: Release 11.2.0.2.0 Production on 월 12월 23 10:20:12 2019
Copyright (c) 1982, 2014, Oracle.  All rights reserved.

Enter user-name: system
Enter password:1234

Connected to:
Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production

ORA-00921: unexpected end of SQL command

SQL> alter user hr identified by hr account unlock;
User altered.


```

```bash
C:\Users\student>sqlplus system/1234

SQL*Plus: Release 11.2.0.2.0 Production on 월 12월 23 10:26:51 2019

Copyright (c) 1982, 2014, Oracle.  All rights reserved.


Connected to:
Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production

--------------------------------------------------------------

# hr/hr 계정 (table 관리 계정)
C:\Users\student>sqlplus hr/hr

SQL*Plus: Release 11.2.0.2.0 Production on 월 12월 23 10:27:13 2019

Copyright (c) 1982, 2014, Oracle.  All rights reserved.


Connected to:
Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production


#select * from tab;
SQL> select * from tab;

TNAME                                                        TABTYPE
------------------------------------------------------------ --------------
 CLUSTERID
----------
COUNTRIES                                                    TABLE

DEPARTMENTS                                                  TABLE

EMPLOYEES                                                    TABLE

TNAME                                                        TABTYPE
------------------------------------------------------------ --------------
 CLUSTERID
----------
EMP_DETAILS_VIEW                                             VIEW

JOBS                                                         TABLE

JOB_HISTORY                                                  TABLE

TNAME                                                        TABTYPE
------------------------------------------------------------ --------------
 CLUSTERID
----------
LOCATIONS                                                    TABLE
REGIONS                                                      TABLE
8 rows selected.

# select * from employees;
```

## 계정 생성및 관리

 * DCL

### 계정 파일 만들기

```bash
data 관련 파일(table space file)
C:\oraclexe\app\oracle\oradata\XE

# TABLESPACE SALES # //system 계정으로 해야함
#계정 파일 만들기
SQL> create TABLESPACE mc
  2  datafile 'C:\oraclexe\app\oracle\oradata\XE\mc.dbf'
  3  size 10M
  4  autoextend on next 1M maxsize UNLIMITED;
Tablespace created.
#데이터 관리를 위해 파일을 따로 생성해주는 거임. 
```



![image-20191223104152364](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20191223104152364.png)

### 계정 파일 삭제  _ drop

```bash
#파일 삭제
drop TABLESPACE mc INCLUDING CONTENTS AND Datafiles;
```

### 계정 생성 _ create

```bash
#계정 생성(system(관리자)권한으로 해야함)
create user test01 identified by 1234 #계정 만드는 명령
default TABLESPACE mc; #mc.dbf 파일로 들어감
-----------------------------------------------
SQL> create user test01 identified by 1234
  2  default TABLESPACE mc;
User created.
```

```bash
#** SCOTT/TIGER 계정 생성 **#
create user SCOTT identified by TIGER #계정 만드는 명령
default TABLESPACE mc; #mc.dbf 파일로 들어감
------------------------------------------------
SQL> create user SCOTT identified by TIGER
  2  default TABLESPACE mc;
User created.
```

## 계정 권한 관리 

```bash
#권한 부여
grant connect,resource,dba to test01; 
---------------------------------------------
C:\Users\student>sqlplus test01/1234

SQL*Plus: Release 11.2.0.2.0 Production on 월 12월 23 10:49:50 2019

Copyright (c) 1982, 2014, Oracle.  All rights reserved.

ERROR:
ORA-01045: user TEST01 lacks CREATE SESSION privilege; logon denied ##권한 부여가 되지않아 에러

```

### 권한 부여 _ grant

```bash
#system 에서 권한부여
#grant connect,resource,dba to test01;
----------------------------------------
SQL> grant connect,resource,dba to test01;

Grant succeeded.
```

```bash
C:\Users\student>sqlplus test01/1234

SQL*Plus: Release 11.2.0.2.0 Production on 월 12월 23 10:53:53 2019
Copyright (c) 1982, 2014, Oracle.  All rights reserved.
ERROR:
ORA-01045: user TEST01 lacks CREATE SESSION privilege; logon denied
# 권한 부여후 로그인 완료
Enter user-name: test01
Enter password:

Connected to:
Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production
```

### 권한 제거 _ revoke

```bash
#권한 제거
SQL> revoke dba from test01;
Revoke succeeded.

SQL> revoke connect from test01;
Revoke succeeded.
```

### 계정 삭제 _drop

```bash
#계정 삭제 (cascade - 강제 삭제 명령어)
drop user test01 cascade;

SQL> drop user test01 cascade;
User dropped.
```



오라클 data 파일 

C:\oraclexe\app\oracle\product\11.2.0\server

```bash
SQL> select * from tab;

no rows selected

SQL> @C:\lib\scott.sql;  #sql file에 있는 명령어를 모두 실행 시키는 명령어
SQL> select * from tab;

TNAME                                                        TABTYPE
------------------------------------------------------------ --------------
 CLUSTERID
----------
BONUS                                                        TABLE


DEPT                                                         TABLE


EMP                                                          TABLE



TNAME                                                        TABTYPE
------------------------------------------------------------ --------------
 CLUSTERID
----------
SALGRADE                                                     TABLE
```

```bash
#CMD setting
set linesize 300;
set pagesize 20;

#emp table 에 있는 정보 출력
select * from emp;  
```

```bash
glogin file

col ename for a10;
col job for a12;
col deptno for 9999;  #9999는 정수타입
col sal for 9999;
col mqr for 9999;
col comm for 9999;

SQL> select * from emp;

     EMPNO ENAME      JOB                 MGR HIREDATE   SAL  COMM DEPTNO
---------- ---------- ------------ ---------- -------- ----- ----- ------
      7369 SMITH      CLERK              7902 80/12/17   800           20
      7499 ALLEN      SALESMAN           7698 81/02/20  1600   300     30
      7521 WARD       SALESMAN           7698 81/02/22  1250   500     30
      7566 JONES      MANAGER            7839 81/04/02  2975           20
      7654 MARTIN     SALESMAN           7698 81/09/28  1250  1400     30
      7698 BLAKE      MANAGER            7839 81/05/01  2850           30
      7782 CLARK      MANAGER            7839 81/06/09  2450           10
      7839 KING       PRESIDENT               81/11/17  5000           10
      7844 TURNER     SALESMAN           7698 81/09/08  1500     0     30
      7900 JAMES      CLERK              7698 81/12/03   950           30
      7902 FORD       ANALYST            7566 81/12/03  3000           20
      7934 MILLER     CLERK              7782 82/01/23  1300           10

12 rows selected.
```

> 스키마 = 데이터베이스(Mysql)
>
> ​			= 사용자이름(Oracle)

	* PK(Primary key)기본키 - 중복되지 않은 정보 (null을 허용하지 않음(=not null))
	* FK(Foreign key)외래 키 - PK 데이터를 reference 해야함. (null을 허용 함.)

![image-20191223134625109](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20191223134625109.png)

* 정규화 과정

![image-20191223134649262](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20191223134649262.png)

* 데이터베이스 - table집합

* 데이블 - row들의 집합

* row - 특정 정보의 묶ㅇ늠을 표현하는 단일행

* 컬럼 - 테이블에서 동일한 종류

# SQL 

* Query : SELECT
* DML
* Transaction
* DDL (roll Back)
* DCL

DEPT 만들고 EMP 만들기 (삭제는 역순 EMP 삭제 후 DEPT 삭제)

conn SCOTT/TIGER 스캇 테이블로 이동

```bash
select * from emp;
select * from dept;
select * from salgrade;

desc emp;  // emp table 구조 보여줘
```

## SELECT

> 순서

```ba
select
from
where
group by
order by
```

```bash
select ename,sal,deptno
from emp;

SQL> select ename,sal,deptno  ## 사원 이름, 급여, 부서번호 컬럼 출력
  2  from emp;  ##emp 로부터

ENAME        SAL DEPTNO
---------- ----- ------
SMITH        800     20
ALLEN       1600     30
WARD        1250     30
JONES       2975     20
MARTIN      1250     30
BLAKE       2850     30
CLARK       2450     10
KING        5000     10
TURNER      1500     30
JAMES        950     30
FORD        3000     20
MILLER      1300     10

12 rows selected.


SQL> select ename,hiredate  ## 사원 이름, 입사일자 출력
  2  from emp;

ENAME      HIREDATE
---------- --------
SMITH      80/12/17
ALLEN      81/02/20
WARD       81/02/22
JONES      81/04/02
MARTIN     81/09/28
BLAKE      81/05/01
CLARK      81/06/09
KING       81/11/17
TURNER     81/09/08
JAMES      81/12/03
FORD       81/12/03
MILLER     82/01/23

12 rows selected.


SQL> select deptno,dname
  2  from dept;  ##dept로 부터(부서 이름과 번호는 dept에 있기 때문에)

DEPTNO DNAME
------ ----------------------------
    10 ACCOUNTING
    20 RESEARCH
    30 SALES
    40 OPERATIONS
    
    
SQL> select job  ## job을 검색하면 중복 값이 나옴
  2  from emp;

JOB
------------
CLERK
SALESMAN
SALESMAN
MANAGER
SALESMAN
MANAGER
MANAGER
PRESIDENT
SALESMAN
CLERK
ANALYST
CLERK

12 rows selected.

SQL> select distinct job   ##중복제거 키워드 'distinct'
  2  from emp;

JOB
------------
CLERK
SALESMAN
PRESIDENT
MANAGER
ANALYST


SQL> select ename, sal, sal*12  ##연산 가능 'sal*12' 값이 연산되어 출력
  2  from emp;

ENAME        SAL     SAL*12
---------- ----- ----------
SMITH        800       9600
ALLEN       1600      19200
WARD        1250      15000
JONES       2975      35700
MARTIN      1250      15000
BLAKE       2850      34200
CLARK       2450      29400
KING        5000      60000
TURNER      1500      18000
JAMES        950      11400
FORD        3000      36000
MILLER      1300      15600

12 rows selected.


SQL> select ename, sal, sal*12 as "연봉"  ##컬럼 얼라이언스 "연봉" 오라클에서 유일하게쓰임(오라클에서 문자열은 ' ' 이다,)
  2  from emp;

ENAME        SAL       연봉
---------- ----- ----------
SMITH        800       9600
ALLEN       1600      19200
WARD        1250      15000
JONES       2975      35700
MARTIN      1250      15000
BLAKE       2850      34200
CLARK       2450      29400
KING        5000      60000
TURNER      1500      18000
JAMES        950      11400
FORD        3000      36000
MILLER      1300      15600

12 rows selected.


SQL> select ename,job, sal, comm, sal+comm as "실급여"
  2  from emp;

ENAME      JOB            SAL  COMM     실급여
---------- ------------ ----- ----- ----------
SMITH      CLERK          800
ALLEN      SALESMAN      1600   300       1900
WARD       SALESMAN      1250   500       1750
JONES      MANAGER       2975
MARTIN     SALESMAN      1250  1400       2650
BLAKE      MANAGER       2850
CLARK      MANAGER       2450
KING       PRESIDENT     5000
TURNER     SALESMAN      1500     0       1500
JAMES      CLERK          950
FORD       ANALYST       3000
MILLER     CLERK         1300

12 rows selected.
#### 데이터 + null 연산하면 null이 출력됨 ###


## nvl 명령어 사용 ##
# nvl(abc, 5) - abc가 null 일때 null 값을 5로 변환
SQL> select ename,job, sal, comm, sal+comm, nvl(comm, -1)
  2  from emp;
## comm 이 null일떄 -1출력 해줘
ENAME      JOB            SAL  COMM   SAL+COMM NVL(COMM,-1)
---------- ------------ ----- ----- ---------- ------------
SMITH      CLERK          800                            -1
ALLEN      SALESMAN      1600   300       1900          300
WARD       SALESMAN      1250   500       1750          500
JONES      MANAGER       2975                            -1
MARTIN     SALESMAN      1250  1400       2650         1400
BLAKE      MANAGER       2850                            -1
CLARK      MANAGER       2450                            -1
KING       PRESIDENT     5000                            -1
TURNER     SALESMAN      1500     0       1500            0
JAMES      CLERK          950                            -1
FORD       ANALYST       3000                            -1
MILLER     CLERK         1300                            -1

12 rows selected.


SQL> select ename,job, sal, comm, sal+nvl(comm, 0) as "총 급여"
  2  from emp;

ENAME      JOB            SAL  COMM    총 급여
---------- ------------ ----- ----- ----------
SMITH      CLERK          800              800
ALLEN      SALESMAN      1600   300       1900
WARD       SALESMAN      1250   500       1750
JONES      MANAGER       2975             2975
MARTIN     SALESMAN      1250  1400       2650
BLAKE      MANAGER       2850             2850
CLARK      MANAGER       2450             2450
KING       PRESIDENT     5000             5000
TURNER     SALESMAN      1500     0       1500
JAMES      CLERK          950              950
FORD       ANALYST       3000             3000
MILLER     CLERK         1300             1300

12 rows selected.

## type을 맞춰줘야함 ##
SQL> select empno, ename, nvl(mgr, 'CEO')
  2  from emp;
select empno, ename, nvl(mgr, 'CEO')
                              *
ERROR at line 1:
ORA-01722: invalid number
#변환후 출력
SQL> select empno, ename, nvl(to_char(sal), 'CEO')
  2  from emp;

     EMPNO ENAME      NVL(TO_CHAR(SAL),'CEO')
---------- ---------- --------------------------------------------------------------------------------
      7369 SMITH      800
      7499 ALLEN      1600
      7521 WARD       1250
      7566 JONES      2975
      7654 MARTIN     1250
      7698 BLAKE      2850
      7782 CLARK      2450
      7839 KING       5000
      7844 TURNER     1500
      7900 JAMES      950
      7902 FORD       3000
      7934 MILLER     1300

12 rows selected.


##문자열 연결##
SQL> select ename, job
  2  from emp;

ENAME      JOB
---------- ------------
SMITH      CLERK
ALLEN      SALESMAN
WARD       SALESMAN
JONES      MANAGER
MARTIN     SALESMAN
BLAKE      MANAGER
CLARK      MANAGER
KING       PRESIDENT
TURNER     SALESMAN
JAMES      CLERK
FORD       ANALYST
MILLER     CLERK

12 rows selected.

SQL> select ename||job
  2  from emp;

ENAME||JOB
--------------------------------------
SMITHCLERK
ALLENSALESMAN
WARDSALESMAN
JONESMANAGER
MARTINSALESMAN
BLAKEMANAGER
CLARKMANAGER
KINGPRESIDENT
TURNERSALESMAN
JAMESCLERK
FORDANALYST
MILLERCLERK

12 rows selected.


## dual 은 가상 table임
SQL> select 20*20*4
  2  from dual;

   20*20*4
----------
      1600
      
 
## login 정보 출력 'user'
SQL> select user  
  2  from dual;

USER
------------------------------------------------------------
SCOTT
```

## where

* row 제한을 걸때 where를 사용

```bash
## selec 기본 출력
SQL> select ename, job, deptno
  2  from emp
  3
SQL> select ename, job, deptno
  2  from emp;

ENAME      JOB          DEPTNO
---------- ------------ ------
SMITH      CLERK            20
ALLEN      SALESMAN         30
WARD       SALESMAN         30
JONES      MANAGER          20
MARTIN     SALESMAN         30
BLAKE      MANAGER          30
CLARK      MANAGER          10
KING       PRESIDENT        10
TURNER     SALESMAN         30
JAMES      CLERK            30
FORD       ANALYST          20
MILLER     CLERK            10

12 rows selected.

## 10번에 해당하는 부서만 출력

SQL> select ename, job, deptno
  2  from emp
  3  where deptno = 10;

ENAME      JOB          DEPTNO
---------- ------------ ------
CLARK      MANAGER          10
KING       PRESIDENT        10
MILLER     CLERK            10

## UPPER/LORER 함수를 이용한 문자 변환(대문자/소문자) 

SQL> select ename, job, deptno
  2  from emp
  3  where job = 'MANAGER';

ENAME      JOB          DEPTNO
---------- ------------ ------
JONES      MANAGER          20
BLAKE      MANAGER          30
CLARK      MANAGER          10

SQL> select ename, job, deptno
  2  from emp
  3  where UPPER(job) = UPPER('MANAGEr');
#양 변을 UPPER로 변환 하여 검색
ENAME      JOB          DEPTNO
---------- ------------ ------
JONES      MANAGER          20
BLAKE      MANAGER          30
CLARK      MANAGER          10


## 부정 기호 ##
select ename, hiredate
from emp
where hiredate != '81/05/01';
# 위,아래 출력 결과는 같지만 아래'<>'문접이 mySQL에서도 사용 가능하여 '<>'로 사용한다
select ename, hiredate
from emp
where hiredate <> '81/05/01';
```

### Between and

```bash
SQL> select ename, sal
  2  from emp
  3  where sal >= 2450 and sal <=3000;

ENAME        SAL
---------- -----
JONES       2975
BLAKE       2850
CLARK       2450
FORD        3000

# Between and 연산자 사용 # (작은 값이 반드시 앞에 와야한다. 아래 예시 참조)
SQL> select ename, sal
  2  from emp
  3  where sal between 2450 and 3000;
  # where sal between 큰 수 and 작은 수;

ENAME        SAL
---------- -----
JONES       2975
BLAKE       2850
CLARK       2450
FORD        3000
```

### like 연산자

* 검색창에서는 like 연산자 주로사용

칼럼명 LIKE *patten*

patten -  **%** : 문자가 없거나 하나 이상의 어떤 값이 와도 상관없다.

​               **_** : 하나의 문자가 어떤 값이 와도 상관없다.

```bash
-- like 연산자 --

select * 
from emp
where ename = 'A';

select *
from emp
where ename like 'A%';

SQL> select *
  2  from emp
  3  where ename like 'A%';

     EMPNO ENAME      JOB                 MGR HIREDATE   SAL  COMM DEPTNO
---------- ---------- ------------ ---------- -------- ----- ----- ------
      7499 ALLEN      SALESMAN           7698 81/02/20  1600   300     30

select *
from emp
where ename like '%A';

SQL> select *
  2  from emp
  3  where ename like '%A';

no rows selected

select *
from emp
where ename like '%A%';

SQL> select *
  2  from emp
  3  where ename like '%A%';

     EMPNO ENAME      JOB                 MGR HIREDATE   SAL  COMM DEPTNO
---------- ---------- ------------ ---------- -------- ----- ----- ------
      7499 ALLEN      SALESMAN           7698 81/02/20  1600   300     30
      7521 WARD       SALESMAN           7698 81/02/22  1250   500     30
      7654 MARTIN     SALESMAN           7698 81/09/28  1250  1400     30
      7698 BLAKE      MANAGER            7839 81/05/01  2850           30
      7782 CLARK      MANAGER            7839 81/06/09  2450           10
      7900 JAMES      CLERK              7698 81/12/03   950           30

6 rows selected.

select *
from emp
where ename like '%A__';

SQL> select *
  2  from emp
  3  where ename like '%A__';

     EMPNO ENAME      JOB                 MGR HIREDATE   SAL  COMM DEPTNO
---------- ---------- ------------ ---------- -------- ----- ----- ------
      7521 WARD       SALESMAN           7698 81/02/22  1250   500     30
      7698 BLAKE      MANAGER            7839 81/05/01  2850           30
      7782 CLARK      MANAGER            7839 81/06/09  2450           10
```



### in 연산자

* where절 내에서 특정값 여러개를 선택하는 SQL 연산자
* 괄호 내의 값 중 일치하는 것이 있으면 TRUE

```bash
SELECT * FROM 테이블명
WHERE 컬럼명 IN (값1, 값2, ...);

SELECT * FROM customer  
WHERE cust_country IN ('JP', 'KR', 'US');

SELECT * FROM customer  
WHERE cust_country = 'JP'
   OR cust_country = 'KR'
   OR cust_country = 'US';
```



### where null 처리

* is

```bash
where mgr is null

SQL> select *
  2  from emp
  3  where mgr is null;

     EMPNO ENAME      JOB                 MGR HIREDATE   SAL  COMM DEPTNO
---------- ---------- ------------ ---------- -------- ----- ----- ------
      7839 KING       PRESIDENT               81/11/17  5000           10
```

* is not

```bash
select *
from emp
where mgr is not null;
```

* where 절에서는 별칭 사용 불가

```bash
SQL> select ename,sal,comm,sal+nvl(comm, 0) as"총 급여"
  2  from emp
  3  where "총 급여" >= 2000;
where "총 급여" >= 2000
         *
ERROR at line 3:
ORA-00904: "총 급여": invalid identifier

## 아래 where 처럼 표기를 해줘야 오류 안생김
select ename,sal,comm,sal+nvl(comm, 0) as"총 급여"
from emp
where sal+nvl(comm, 0) >= 2000;
```



## order by (정렬)

```bash
SQL> select ename,sal,comm,sal+nvl(comm, 0) as"총 급여"
  2  from emp
  3  order by sal;

ENAME        SAL  COMM    총 급여
---------- ----- ----- ----------
SMITH        800              800
JAMES        950              950
WARD        1250   500       1750
MARTIN      1250  1400       2650
MILLER      1300             1300
TURNER      1500     0       1500
ALLEN       1600   300       1900
CLARK       2450             2450
BLAKE       2850             2850
JONES       2975             2975
FORD        3000             3000
KING        5000             5000
```

```bash
#내림차순 정렬
Sselect ename,sal,comm,sal+nvl(comm, 0) as"총 급여"
from emp
order by sal desc;
```

```bash
#오름차순 정렬
Sselect ename,sal,comm,sal+nvl(comm, 0) as"총 급여"
from emp
order by sal asc;
```

* order by 절에서는 별칭 사용가능

```bash

SQL> select ename,sal,sal+nvl(comm, 0) as"총 급여"
  2  from emp
  3  where sal+nvl(comm, 0) >= 2000
  4  order by "총 급여";

ENAME        SAL    총 급여
---------- ----- ----------
CLARK       2450       2450
MARTIN      1250       2650
BLAKE       2850       2850
JONES       2975       2975
FORD        3000       3000
KING        5000       5000

# 위치 index 사용한 정렬
SQL> select ename,sal,sal+nvl(comm, 0) as"총 급여"
  2  from emp
  3  where sal+nvl(comm, 0) >= 2000
  4  order by 3;    #index 3 = sal+nvl(comm, 0) 

ENAME        SAL    총 급여
---------- ----- ----------
CLARK       2450       2450
MARTIN      1250       2650
BLAKE       2850       2850
JONES       2975       2975
FORD        3000       3000
KING        5000       5000
```

## 단일행 함수

### to_char()

```bash
select sysdate, substr(sysdate, 4,2) from dual;
select sysdate, to_char(sysdate,'yy') from dual;
select sysdate, to_char(sysdate,'yyyy') from dual;
select sysdate, to_char(sysdate,'day') from dual;
select sysdate, to_char(sysdate,'mm') from dual;
select sysdate, to_char(sysdate,'dd') from dual;

select ename,to_char(hiredate,'mm') as "입사 월 ", to_char(hiredate,'day') as "입사 요일"
from emp
order by to_char(hiredate,'dd') asc;
```

### to_date()

```bash
select sysdate,to_date('2019/12/24') from dual;
select sysdate,to_date('2019-12-24') from dual;
select sysdate,to_date('2019 12 24') from dual;

select sysdate,to_date('12/12/19','mm/dd/yy') from dual;
```



## Join

* 둘 이상의 테이블을 합칠떄 쓰임.

> 조건없이 쓸 수 없다.

```bash
SQL> select ename,loc
  2  from emp,dept
  3  where emp.deptno = dept.deptno;   # join 에서는 조건이 필수

ENAME      LOC
---------- --------------------------
SMITH      DALLAS
ALLEN      CHICAGO
WARD       CHICAGO
JONES      DALLAS
MARTIN     CHICAGO
BLAKE      CHICAGO
CLARK      NEW YORK
KING       NEW YORK
TURNER     CHICAGO
JAMES      CHICAGO
FORD       DALLAS
MILLER     NEW YORK

12 rows selected.
```

### Simple Join

* from절에 필요로 하는 테이블을 모두 적는다

* 일반적으로 PK와 FK간의 조건이 붙는다

* 컬럼 이름의 모호성을 피하기 위해(어느테이블에 속하는지 알 수 없음)이 있을 수 있으므로 table이름에 Alias사용(테이블 이름으로 직접 지칭 가능)

* 적절한 Join 조건을 where 절에 부여(일반적으로 테이블 개수-1 개의 조인 조건이 필요)

```bash
SQL> select ename,deptno, dname,loc
  2  from emp,dept
  3  where emp.deptno = dept.deptno;
select ename,deptno, dname,loc
             *
ERROR at line 1:
ORA-00918: column ambiguously defined
#deptno가 두개여서 선택을 하지못하는 err발생

# select 에 테이블 지정을 해주고 출력하면 정상 출력
SQL> select ename,emp.deptno, dname,loc
  2  from emp,dept
  3  where emp.deptno = dept.deptno;

ENAME      DEPTNO DNAME                        LOC
---------- ------ ---------------------------- --------------------------
SMITH          20 RESEARCH                     DALLAS
ALLEN          30 SALES                        CHICAGO
WARD           30 SALES                        CHICAGO
JONES          20 RESEARCH                     DALLAS
MARTIN         30 SALES                        CHICAGO
BLAKE          30 SALES                        CHICAGO
CLARK          10 ACCOUNTING                   NEW YORK
KING           10 ACCOUNTING                   NEW YORK
TURNER         30 SALES                        CHICAGO
JAMES          30 SALES                        CHICAGO
FORD           20 RESEARCH                     DALLAS
MILLER         10 ACCOUNTING                   NEW YORK

12 rows selected.

```

* 테이블에 별칭 지정하여 사용 예시

```bash
SQL> select ename,e.deptno, dname,loc
  2  from emp e,dept d  # table에 별칭 부여
  3  where e.deptno = d.deptno;

ENAME      DEPTNO DNAME                        LOC
---------- ------ ---------------------------- --------------------------
SMITH          20 RESEARCH                     DALLAS
ALLEN          30 SALES                        CHICAGO
WARD           30 SALES                        CHICAGO
JONES          20 RESEARCH                     DALLAS
MARTIN         30 SALES                        CHICAGO
BLAKE          30 SALES                        CHICAGO
CLARK          10 ACCOUNTING                   NEW YORK
KING           10 ACCOUNTING                   NEW YORK
TURNER         30 SALES                        CHICAGO
JAMES          30 SALES                        CHICAGO
FORD           20 RESEARCH                     DALLAS
MILLER         10 ACCOUNTING                   NEW YORK

12 rows selected.
```

* 예시 

```bash
#sal > 2000 and deptno 가 20번 부서에 근무하는 사원이름, sal,loc 출력
SQL> select ename, sal, loc
  2  from emp, dept
  3  where emp.deptno = dept.deptno and sal > 2000 and emp.deptno = '20';

ENAME        SAL LOC
---------- ----- --------------------------
JONES       2975 DALLAS
FORD        3000 DALLAS


# Ansi Join 예시 (위와 출력 동일)
select ename, sal, loc
from emp join dept
on emp.deptno = dept.deptno 
where sal > 2000 and emp.deptno = '20';
```

### Ansi Join

> from table 에 ' , ' 를 join 으로 'where' 를 on 으로 표시

​	ansi join

```bash
select ename,e.deptno, dname,loc
from emp e join dept d
on e.deptno = d.deptno;
```

​	inner join

```bash
select ename,e.deptno, dname,loc
from emp e inner join dept d
on e.deptno = d.deptno;
```

### Outer Join

```bash
##null 쪽에 '+' 마킹 ('+' 는 mySql 에서 사용 불가)
SQL> select ename, d.deptno, dname, loc
  2  from emp e, dept d
  3  where e.deptno(+) = d.deptno;

ENAME      DEPTNO DNAME                        LOC
---------- ------ ---------------------------- --------------------------
SMITH          20 RESEARCH                     DALLAS
ALLEN          30 SALES                        CHICAGO
WARD           30 SALES                        CHICAGO
JONES          20 RESEARCH                     DALLAS
MARTIN         30 SALES                        CHICAGO
BLAKE          30 SALES                        CHICAGO
CLARK          10 ACCOUNTING                   NEW YORK
KING           10 ACCOUNTING                   NEW YORK
TURNER         30 SALES                        CHICAGO
JAMES          30 SALES                        CHICAGO
FORD           20 RESEARCH                     DALLAS
MILLER         10 ACCOUNTING                   NEW YORK
               40 OPERATIONS                   BOSTON

13 rows selected.
```

### outer Ansi Join

```bash


SQL> select ename, d.deptno, dname, loc
  2  from emp e right outer join dept d
  3  on e.deptno = d.deptno
  4  order by d.deptno;

ENAME      DEPTNO DNAME                        LOC
---------- ------ ---------------------------- --------------------------
CLARK          10 ACCOUNTING                   NEW YORK
MILLER         10 ACCOUNTING                   NEW YORK
KING           10 ACCOUNTING                   NEW YORK
JONES          20 RESEARCH                     DALLAS
FORD           20 RESEARCH                     DALLAS
SMITH          20 RESEARCH                     DALLAS
ALLEN          30 SALES                        CHICAGO
TURNER         30 SALES                        CHICAGO
JAMES          30 SALES                        CHICAGO
WARD           30 SALES                        CHICAGO
BLAKE          30 SALES                        CHICAGO
MARTIN         30 SALES                        CHICAGO
               40 OPERATIONS                   BOSTON
```

### non equi join

* craclie join

```bash
SQL> select ename, sal, grade
  2  from emp,salgrade
  3  where sal between losal and hisal;

ENAME        SAL      GRADE
---------- ----- ----------
SMITH        800          1
JAMES        950          1
WARD        1250          2
MARTIN      1250          2
MILLER      1300          2
TURNER      1500          3
ALLEN       1600          3
CLARK       2450          4
BLAKE       2850          4
JONES       2975          4
FORD        3000          4
KING        5000          5
```

* ansi join

```bash
SQL> select ename, sal, grade
  2  from emp join salgrade
  3  on sal between losal and hisal;

ENAME        SAL      GRADE
---------- ----- ----------
SMITH        800          1
JAMES        950          1
WARD        1250          2
MARTIN      1250          2
MILLER      1300          2
TURNER      1500          3
ALLEN       1600          3
CLARK       2450          4
BLAKE       2850          4
JONES       2975          4
FORD        3000          4
KING        5000          5
```

### n개의 table join

```bash

--사원명, sal, 부서명, salgrade

ansi join 1
select ename, sal, dname ,grade
from emp NATURAL JOIN dept JOIN salgrade
on sal between losal and hisal;

ansi join 2
select ename, sal, dname ,grade
from emp e JOIN dept d on e.deptno = d.deptno JOIN salgrade
on sal between losal and hisal;

join
select ename, sal, dname ,grade
from emp e , dept d , salgrade
where e.deptno = d.deptno and sal between losal and hisal;



ENAME        SAL DNAME                             GRADE
---------- ----- ---------------------------- ----------
SMITH        800 RESEARCH                              1
JAMES        950 SALES                                 1
WARD        1250 SALES                                 2
MARTIN      1250 SALES                                 2
MILLER      1300 ACCOUNTING                            2
TURNER      1500 SALES                                 3
ALLEN       1600 SALES                                 3
CLARK       2450 ACCOUNTING                            4
BLAKE       2850 SALES                                 4
JONES       2975 RESEARCH                              4
FORD        3000 RESEARCH                              4
KING        5000 ACCOUNTING                            5
```

### self Join

> 하나에 table을 2개처럼 사용
>
> table 이름이 동일하기 때문에 별첨을 부여해줘야함.

```bash
SQL> select e.ename as "사원명", em.ename as "상사 이름"
  2  from emp e, emp em  #table 이름이 동일하기 때문에 별첨을 부여해줘야함
  3  where e.mgr = em.empno(+); # king 상사가 null 이기 때문에 outer 사용

사원명               상사 이름
-------------------- --------------------
FORD                 JONES
JAMES                BLAKE
TURNER               BLAKE
MARTIN               BLAKE
WARD                 BLAKE
ALLEN                BLAKE
MILLER               CLARK
CLARK                KING
BLAKE                KING
JONES                KING
SMITH                FORD
KING
```

## 집계 함수()

* AVG
* COUNT\
  * COUNT(*) - null도 count
  * COUNT(expr) - null 뺴고 count
* MAX
* MIN
* SUM

### avg()

> 집계 함수에는 select 에 collumn 을 포함 할 수 없다.
>
> avg 는 하나의 값인데 ename 의 여러 값이 있이서에러발생

```bash
SQL> select ename, avg(sal) from emp; ## avg 는 하나의 값인데 ename 의 여러 값이 있이서에러발생
select ename, avg(sal) from emp
       *
ERROR at line 1:
ORA-00937: not a single-group group function
```

```bash
SQL> select round(avg(sal))||'원'as "전체 평균 급여" from emp;  ## 정수형이 \\ 연산자로인해 Str type로 변환

전체 평균 급여
--------------------------------------------------------------------------------------
2077원
```

### group by()

> group by 함수만 select 에 column을 포함 할 수 있다.

```bash
SQL> select deptno, round(avg(sal)) from emp group by deptno ;

DEPTNO ROUND(AVG(SAL))
------ ---------------
    30            1567
    20            2258
    10            2917
```

```bash
SQL> select * from emp; #emp table

     EMPNO ENAME      JOB                 MGR HIREDATE   SAL  COMM DEPTNO
---------- ---------- ------------ ---------- -------- ----- ----- ------
      7369 SMITH      CLERK              7902 80/12/17   800           20
      7499 ALLEN      SALESMAN           7698 81/02/20  1600   300     30
      7521 WARD       SALESMAN           7698 81/02/22  1250   500     30
      7566 JONES      MANAGER            7839 81/04/02  2975           20
      7654 MARTIN     SALESMAN           7698 81/09/28  1250  1400     30
      7698 BLAKE      MANAGER            7839 81/05/01  2850           30
      7782 CLARK      MANAGER            7839 81/06/09  2450           10
      7839 KING       PRESIDENT               81/11/17  5000           10
      7844 TURNER     SALESMAN           7698 81/09/08  1500     0     30
      7900 JAMES      CLERK              7698 81/12/03   950           30
      7902 FORD       ANALYST            7566 81/12/03  3000           20
      7934 MILLER     CLERK              7782 82/01/23  1300           10
```

```bash
##group by 함수만 select 에 column을 포함 할 수 있다.##
SQL> select dept.dname,emp.deptno, avg(sal), count(*),min(sal),max(sal),count(mgr)
  2  from emp, dept
  3  where emp.deptno = dept.deptno
  4  group by dept.dname;
  
select dept.dname,emp.deptno, avg(sal), count(*),min(sal),max(sal),count(mgr)
                  *
ERROR at line 1:
ORA-00979: not a GROUP BY expression


SQL> select d.deptno,dname,avg(sal), count(*),min(sal),max(sal)
  2  from emp e, dept d
  3  where e.deptno = d.deptno
  4  group by d.deptno,dname;

DEPTNO DNAME                          AVG(SAL)   COUNT(*)   MIN(SAL)   MAX(SAL)
------ ---------------------------- ---------- ---------- ---------- ----------
    10 ACCOUNTING                   2916.66667          3       1300       5000
    20 RESEARCH                     2258.33333          3        800       3000
    30 SALES                        1566.66667          6        950       2850
```



```bash
SQL> select avg(sal), count(*),min(sal),max(sal),count(mgr)
  2  from emp;

  AVG(SAL)   COUNT(*)   MIN(SAL)   MAX(SAL) COUNT(MGR)
---------- ---------- ---------- ---------- ----------
2077.08333         12        800       5000         11


SQL> select avg(sal), count(*),min(sal),max(sal),count(mgr)
  2  from emp
  3  where deptno = 10;  #10번 부서에 집계

  AVG(SAL)   COUNT(*)   MIN(SAL)   MAX(SAL) COUNT(MGR)
---------- ---------- ---------- ---------- ----------
2916.66667          3       1300       5000          2

SQL> select avg(sal), count(*),min(sal),max(sal),count(mgr)
  2  from emp
  3  where deptno = 20;  #20번 부서에 집계

  AVG(SAL)   COUNT(*)   MIN(SAL)   MAX(SAL) COUNT(MGR)
---------- ---------- ---------- ---------- ----------
2258.33333          3        800       3000          3

SQL> select avg(sal), count(*),min(sal),max(sal),count(mgr)
  2  from emp
  3  group by deptno;  #부서번호별로 묶어서 집계 시켜줌.

  AVG(SAL)   COUNT(*)   MIN(SAL)   MAX(SAL) COUNT(MGR)
---------- ---------- ---------- ---------- ----------
1566.66667          6        950       2850          6
2258.33333          3        800       3000          3
2916.66667          3       1300       5000          2

 #group by 함수만 select 에 column을 포함 할 수 있다.
SQL> select deptno,avg(sal), count(*),min(sal),max(sal),count(mgr)
  2  from emp
  3  group by deptno;  

DEPTNO   AVG(SAL)   COUNT(*)   MIN(SAL)   MAX(SAL) COUNT(MGR)
------ ---------- ---------- ---------- ---------- ----------
    30 1566.66667          6        950       2850          6
    20 2258.33333          3        800       3000          3
    10 2916.66667          3       1300       5000          2
    
    #부서별 집계
SQL> select dept.dname,avg(sal), count(*),min(sal),max(sal),count(mgr)
  2  from emp, dept
  3  where emp.deptno = dept.deptno
  4  group by dept.dname;

DNAME                          AVG(SAL)   COUNT(*)   MIN(SAL)   MAX(SAL) COUNT(MGR)
---------------------------- ---------- ---------- ---------- ---------- ----------
ACCOUNTING                   2916.66667          3       1300       5000          2
RESEARCH                     2258.33333          3        800       3000          3
SALES                        1566.66667          6        950       2850          6
```

## having

* GROUP BY 절에 의해 생성된 결과 값 중 원하는 조건에 부합하는 자료만 보고자 할 때 사용한다.

```bash
##부서별 평균 급여 2000이상인 목록 출력##
SQL> select d.deptno,dname,round(avg(sal)) as "평균 급여"
  2  from emp e, dept d
  3  where e.deptno = d.deptno
  4  group by d.deptno,dname
  5  having round(avg(sal)) > 2000;

DEPTNO DNAME                         평균 급여
------ ---------------------------- ----------
    10 ACCOUNTING                         2917
    20 RESEARCH                           2258
```

## subQuery

```bash
SQL> select *
  2  from emp
  3  where sal > (select sal from emp where ename = 'FORD');

     EMPNO ENAME      JOB                 MGR HIREDATE   SAL  COMM DEPTNO
---------- ---------- ------------ ---------- -------- ----- ----- ------
      7839 KING       PRESIDENT               81/11/17  5000           10
      
 
SQL> select *
  2  from emp
  3  where sal >3000;

     EMPNO ENAME      JOB                 MGR HIREDATE   SAL  COMM DEPTNO
---------- ---------- ------------ ---------- -------- ----- ----- ------
      7839 KING       PRESIDENT               81/11/17  5000           10
```

### 예제

```bash
# 평균 급여보다 적게받는 사원 출력 #
SQL> select *
  2  from emp
  3  where sal < (select avg(sal) from emp);

     EMPNO ENAME      JOB                 MGR HIREDATE   SAL  COMM DEPTNO
---------- ---------- ------------ ---------- -------- ----- ----- ------
      7369 SMITH      CLERK              7902 80/12/17   800           20
      7499 ALLEN      SALESMAN           7698 81/02/20  1600   300     30
      7521 WARD       SALESMAN           7698 81/02/22  1250   500     30
      7654 MARTIN     SALESMAN           7698 81/09/28  1250  1400     30
      7844 TURNER     SALESMAN           7698 81/09/08  1500     0     30
      7900 JAMES      CLERK              7698 81/12/03   950           30
      7934 MILLER     CLERK              7782 82/01/23  1300           10

# 최소급여를 받는 사람 출력 #
SQL> select *
  2  from emp
  3  where sal = (select min(sal) from emp);

     EMPNO ENAME      JOB                 MGR HIREDATE   SAL  COMM DEPTNO
---------- ---------- ------------ ---------- -------- ----- ----- ------
      7369 SMITH      CLERK              7902 80/12/17   800           20
```

```bash

# 부서별 최고 급여 목록 #
SQL> select *
  2  from emp
  3  where sal = (select max(sal) from emp group by deptno);
where sal = (select max(sal) from emp group by deptno)
             *
ERROR at line 3:
ORA-01427: single-row subquery returns more than one row
# '=' 연산자 비교시 여러개의 목록이 비교되어 에러 발생 (in 연산자로 대처해야함)


SQL> select *
  2  from emp
  3  where (deptno,sal) in (select deptno, max(sal) from emp group by deptno)
  4  order by deptno;

     EMPNO ENAME      JOB                 MGR HIREDATE   SAL  COMM DEPTNO
---------- ---------- ------------ ---------- -------- ----- ----- ------
      7839 KING       PRESIDENT               81/11/17  5000           10
      7902 FORD       ANALYST            7566 81/12/03  3000           20
      7698 BLAKE      MANAGER            7839 81/05/01  2850           30
```

### rowNum

* 오라클에서 지원하는 **가상컬럼**으로 쿼리의 결과에 1부터 하나씩 증가하여 붙는 collum임..
* 주로 여러개의 결과를 출력하는 쿼리문을 실행 후 결과의 개수를 제한하여 가져오는데 쓰임.

```bash

SQL> select rownum, ename, job, sal
  2  from emp;

    ROWNUM ENAME      JOB            SAL
---------- ---------- ------------ -----
         1 SMITH      CLERK          800
         2 ALLEN      SALESMAN      1600
         3 WARD       SALESMAN      1250
         4 JONES      MANAGER       2975
         5 MARTIN     SALESMAN      1250
         6 BLAKE      MANAGER       2850
         7 CLARK      MANAGER       2450
         8 KING       PRESIDENT     5000
         9 TURNER     SALESMAN      1500
        10 JAMES      CLERK          950
        11 FORD       ANALYST       3000
        12 MILLER     CLERK         1300

12 rows selected.
```

* rowNum 문제점

```bash
order by와 함께 사용될 경우 발생합니다.
order by 절의 실행 순서가 where 절의 실행 후이기 때문에 order by와는 관계없이 rownum에 맞는 결과를 출력한 뒤 order by 절을 수행합니다.
따라서 순서를 바꾸기 위해 order by 절을 내부쿼리에서 실행하도록 합니다.
SQL> select rownum, ename, job, sal

  2  from emp
  3  order by sal;

    ROWNUM ENAME      JOB            SAL
---------- ---------- ------------ -----
         1 SMITH      CLERK          800
        10 JAMES      CLERK          950
         3 WARD       SALESMAN      1250
         5 MARTIN     SALESMAN      1250
        12 MILLER     CLERK         1300
         9 TURNER     SALESMAN      1500
         2 ALLEN      SALESMAN      1600
         7 CLARK      MANAGER       2450
         6 BLAKE      MANAGER       2850
         4 JONES      MANAGER       2975
        11 FORD       ANALYST       3000
         8 KING       PRESIDENT     5000

12 rows selected.


SQL> select rownum, ename, job, sal
  2  from (select * from emp order by sal);

    ROWNUM ENAME      JOB            SAL
---------- ---------- ------------ -----
         1 SMITH      CLERK          800
         2 JAMES      CLERK          950
         3 WARD       SALESMAN      1250
         4 MARTIN     SALESMAN      1250
         5 MILLER     CLERK         1300
         6 TURNER     SALESMAN      1500
         7 ALLEN      SALESMAN      1600
         8 CLARK      MANAGER       2450
         9 BLAKE      MANAGER       2850
        10 JONES      MANAGER       2975
        11 FORD       ANALYST       3000
        12 KING       PRESIDENT     5000

12 rows selected.
```

## Oracle page 처리

### web page 처리

```bash
## web에서 page 처리할때 주로 사용됨##
SQL> select *
  2  from(
  3  select rownum row#, ename, job, sal
  4  from (select * from emp order by sal desc)
  5  )
  6  where row# between 6 and 10;

      ROW ENAME      JOB            SAL
---------- ---------- ------------ -----
         6 ALLEN      SALESMAN      1600
         7 TURNER     SALESMAN      1500
         8 MILLER     CLERK         1300
         9 MARTIN     SALESMAN      1250
        10 WARD       SALESMAN      1250


## 값 출력 되지 않는 코드 ## (위 코드로 사용 가능)
SQL> select rownum, ename, job, sal
  2  from (select * from emp order by sal desc)
  3  where rownum between 6 and 10;

no rows selected
```

# DDL

> Auto commit
>
> 테이블은 실제로 데이터들이 저장되는 곳 이라고 생각하면 쉽게 이해 할 수 있으며, 
> **CREATE TABLE 명령어**를 이용해서 테이블을 생성 할 수 있다.

## 데이터 type

### VARCHAR2

- 가변길이 문자형 데이터 타입 
- 최대 길이 : 2000 바이트(반드시 길이 지정) 
- 다른 타입에 비해 제한이 적다 
- 일부만 입력시 뒷부분은 NULL 
- 입력한 값의 뒷부분에 있는 BLANK도 같이 입력 
- 전혀 값을 주지 않으면 NULL 상태 입력 
- 지정된 길이보다 길면 입력시 에러 발생 
- 컬럼 길이의 편차가 심한 경우, NULL 로 입력되는 경우가 많은 경우 VARCHAR2 사용 

### NUMBER

- 숫자형 데이타 타입, 음수, ZERO, 양수 저장 
- 전체 자리수는 38자리를 넘을 수 없다 
- 소수점이 지정되지 않았을 때 소수점이 입력되거나, 지정된 소수점자리 이상 입력되면 반올림되어 저장 
- 지정한 정수 자리수 이상 입력시 에러 발생 
- 연산이 필요한 컬럼은 NUMBER타입으로 지정한다.
- NUMBER(p,s) 로 지정시 p는 s의 자리수를 포함한 길이므로 감안하여 P의 자리수를 결정 
- NUMBER 타입은 항상 가변길이므로 충분하게 지정할 것 

### DATE

- 일자와 시간을 저장하는 날짜형 타입 
- 일자나 시간의 연산이 빈번한 경우 사용 
- 포함정보 : 세기, 년도, 월, 일, 시간, 분, 초 
- NLS_DATE_FORMAT을 이용하여 국가별 특수성을 해결 
- 특별히 시간을 지정하지 않으면 00:00:00로 입력 됨 
- 특별히 일자를 지정하지 않았다면 현재월의 1일로 지정 됨 
- SYSDATE는 현재일과 시간을 제공 



* table 생성

```bash
SQL> drop table book;

Table dropped.

SQL>
SQL> create table book(
  2  bookno number(5),
  3  title varchar2(40),
  4  author varchar2(20),
  5  pubdate date
  6  );

Table created.

## type 설정
SQL> create table book(
  2  bookno number(5), #5자리 bookno 생성
  3  title varchar2(40), 
  4  author varchar2(20),
  5  pubdate date
  6  );

Table created.
```

# DML

> commit , roll back
>
> 트렌젝션 관리

* commit 이 되면 rollback 불가능함.
* rollback - commit을 하지 않으면  rollback 으로 취소가능

### insert

```bash
insert into book(bookno, title, author, pubdate)
values(1, 'java', '홍길동', sysdate);

commit;

insert into book(bookno, title, author, pubdate)
values(2, 'sql', null, null);

rollback;

insert into book(bookno, title, author, pubdate)
values(2, 'sql', null, '2019/01/01');

insert into book(bookno, title, author, pubdate)
values(3, 'spring', '고길동', to_date('01/01/2019', 'mm/dd/yyyy'));

commit;

insert into book (bookno,title) values(4,'html5');
--collum 을 표시 안해도 insert 가능( 하지만, 추천 하지 않음)
insert into book values(1, 'java', '홍길동', sysdate);
```

### delete

```bash
# where 조건 없이 삭제하면 모든 데이터가 섹제 됨
delete from book;

# where 조건에 해당하는 데이터부분만 삭제
delete from book where bookno = 4;
```

### updata

```bash
# where 조건 없이 삭제하면 모든 데이터가 섹제 됨
update book set title='~~~~~';

# where 조건에 해당하는 데이터부분만 삭제
update book set title='~~~~~'where bookno = 3;

update book set title='hadoop', author = 'kim' where bookno = 1;

update book set title='html5' where bookno = 3;
```

# PK,FK 제약 

PK 제약조건

```bash
#deptno 가 'pk'여서 중복 등록 불가능(pk 제약 조건)
insert into dept (deptno, dname, loc) values(10,'EDU','SEOUL'); 

#deptno 가 pk이가 아니기 때문에 중복 가능
insert into dept2 (deptno, dname, loc) values(10,'EDU','SEOUL');

# null 값이 적용되도 위와같이 pk제약 조건으로 인해 등록 불가능
insert into dept (deptno, dname, loc) values(null,'EDU','SEOUL');

#deptno 가 pk이가 아니기 때문에 중복 가능
insert into dept2 (deptno, dname, loc) values(null,'EDU','SEOUL');
```

FK 제약조건

```bash
#제약 조건 때문에 등록 불가 90번 부서가 fk이기 때문에 pk에서 90번이 등록 되어있어야함.
SQL> insert into emp emp(empno, ename, sal, deptno)
  2  values(999,'홍',2100,90);
insert into emp emp(empno, ename, sal, deptno)
*
ERROR at line 1:
ORA-02291: integrity constraint (SCOTT.FK_DEPTNO) violated - parent key not found

#PK 에 50번이 있기 때문에 등록 가능.
SQL> insert into emp emp(empno, ename, sal, deptno)
  2  values(999,'홍',2100,50);

1 row created.

#제약 조건이 없기때문에 등록이 됨.(PK에 해당 부서 번호가 없기때문에 데이터 찾을때 err가 발생)
SQL> insert into emp2 emp(empno, ename, sal, deptno)
  2  values(999,'홍',2100,90);

1 row created.
```



![image-20191226144518781](image/image-20191226144518781.png)

* PK,FK 구조 설계 예시

```bash
SQL> select ename,dname, loc
  2  from emp e,dept d
  3  where e.deptno = d.deptno and e.ename = '홍';

ENAME      DNAME                        LOC
---------- ---------------------------- --------------------------
홍         EDU                          SEOUL

# emp2 에는  90번 부서가 없기 때문에 해당 내용 출력 불가
SQL> select ename,dname, loc
  2  from emp2 e,dept d
  3  where e.deptno = d.deptno and e.ename = '홍';

no rows selected
```



## 제약 조건 설정

```bash
drop table book;
## Book table 생성
create table book(
	bookno number(5) primary key,  ## PK(primary key) 설정
	title varchar2(40) unique, 
	author varchar2(20),
	price number(7,2) check(price > 0),
	pubdate date default sysdate ## pubdate 에 데이터 입력안하면 오늘날자 입력되게됨.
);
###################################################
--------------------------------------------------
## price 에서 언급 안하면 null 이다.
SQL> insert into book(bookno, title, author, pubdate)
  2  values(1, 'java', '홍길동', sysdate);

1 row created.

SQL> select * from book;

    BOOKNO TITLE        AUTHOR            PRICE PUBDATE
---------- ------------ ------------ ---------- --------
         1 java         홍길동                  19/12/26
# default sysdate ## pubdate 에 데이터 입력안하면 오늘날자 입력되게됨.

SQL> insert into book(bookno, title, author, price)
  2  values(1, 'java', '홍길동', 900);

1 row created.

SQL> select * from book;

    BOOKNO TITLE        AUTHOR            PRICE PUBDATE
---------- ------------ ------------ ---------- --------
         1 java         홍길동              900 19/12/26
         
--------------------------------------------------
######################################################

insert into book(bookno, title, author, pubdate)
values(2, 'sql', null, null);


###################################################
--------------------------------------------------
## 제약 조건떄문에 등록 되지 않음 (bookno, title는 중복허용 제약이 걸려있음)
SQL> insert into book(bookno, title, author, price)
  2  values(1, 'java', '홍길동', 900);
insert into book(bookno, title, author, price)
*
ERROR at line 1:
ORA-00001: unique constraint (SCOTT.SYS_C007006) violated
--------------------------------------------------
###################################################
```

* 제약 조건 외부 설정(추가/삭제)

```bash
# 외부에서 pk제약 조건 설정 (제약 조건 이름(book_pk)은 내가 알아보기 쉽게 설정해야한다 에러가 날때 찾아가기 쉬움
alter table book add CONSTRAINT book_pk primary key(bookno);


SQL> insert into book(bookno, title, author, price)
  2  values(1, 'sql', '홍길동', 2900);
insert into book(bookno, title, author, price)
*
ERROR at line 1:
ORA-00001: unique constraint (SCOTT.BOOK_PK) violated

-# 제약 조건 제거
alter table book drop CONSTRAINT book_pk;
```

* PK설정

```bash

```



* FK설정

# eclipse 환경설정

[환경설정 블로그](https://m.blog.naver.com/PostView.nhn?blogId=pyj721aa&logNo=221147393750&proxyReferer=https%3A%2F%2Fwww.google.com%2F)

[환경설정 블로그1](https://chrismare.tistory.com/48)

.properties

![image-20191224105511259](C:\Users\student\Desktop\khh\myGit\DB\image-20191224105511259.png)

![image-20191224101957525](C:\Users\student\Desktop\khh\myGit\DB\image-20191224101957525.png)

ojdbc6복사 - 내가 보관하는 lib파일에 붙어넣기 

![image-20191224105701152](C:\Users\student\Desktop\khh\myGit\DB\image-20191224105701152.png)

.properties file 생성



오라클에서 압축해서 제ㅐ공해주는 파일

오라클룡 jdbc driver

자바 EE로 변경 - data source explower - 



![image-20191224102509611](C:\Users\student\Desktop\khh\myGit\DB\image-20191224102509611.png)

루프백 127.0.0.1 내 자리에 서버 있어

![image-20191224112548237](C:\Users\student\Desktop\khh\myGit\DB\image-20191224112548237.png)
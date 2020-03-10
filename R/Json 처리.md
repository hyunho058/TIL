# Json 처리

## 1. Setting

### 1.1 DB settig 

   * mySQL이용해 데이터베이스 설정(Standalone)
   * bin폴더에서 CMD 실행 - ㅡmysqld(DB서버 기동) - 도스창 하나 더실행 - mysql -u root(system 계정 접속)-user 생성 - 

```
C:\Users\student>cd C:\Users\student\Desktop\mysql-5.6.47-winx64\bin

C:\Users\student\Desktop\mysql-5.6.47-winx64\bin>mysql -u root
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 1
Server version: 5.6.47 MySQL Community Server (GPL)

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.
mysql>
```

* user 계정 생성

```
mysql> create user rdata identified by "rdata";
Query OK, 0 rows affected (0.00 sec)

mysql>
```

* local host 추가

```
mysql> create user rdata@localhost identified by "rdata";
Query OK, 0 rows affected (0.00 sec)
```

* DB생성

```
mysql> create database library;
Query OK, 1 row affected (0.01 sec)
```

* DB 권한 설정 (rdata, rdata$localhost계정에 권한 설정)

```
mysql> grant all privileges on library.* to rdata;
Query OK, 0 rows affected (0.00 sec)

mysql> grant all privileges on library.* to rdata@localhost;
Query OK, 0 rows affected (0.00 sec)
```

* 권한 설정 완료

```
mysql> flush privileges;
Query OK, 0 rows affected (0.00 sec)
```

* 제공된 script 이용하여 database 구촉하기

```
_BookTableDump.sql 파일 아래 경로에 붙여넣기
C:\Users\student\Desktop\mysql-5.6.47-winx64\bin
```

![image-20200310160526310](image/image-20200310160526310.png)

* 복사 파일 설정하기(rdata 계정)

```
C:\Users\student\Desktop\mysql-5.6.47-winx64\bin>mysql -u rdata -p library < _BookTableDump.sql
Enter password: *****

C:\Users\student\Desktop\mysql-5.6.47-winx64\bin>
```





## Servlet을 이용해 Json 받아오기



## OPEN API사용

* 영화진흥 위원회(OPEN API)
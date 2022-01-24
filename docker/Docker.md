# Docker

> * 컨테이너 기반의 오픈소스 가상화 플랫폼
> * 서버에 설치되는 코드, 런타임, 시스템 도구, 시스템 라이브러리 등 설치하여 실행 환경에 상관없이 동일한 환경을 제공해준다

## Setting

> * 설치환경 - MacOs

*  Download
  * [공식홈페이지](https://www.docker.com/get-started)
  * [Docker Hub](https://hub.docker.com/editions/community/docker-ce-desktop-mac)

*  버전 확인

```bash
MacBookPro ~ % docker -v
Docker version 20.10.5, build 55c4c88
```

### MariaDB 설치

* 도커 이미지 다운(mariaDB)

```bash
docker pull mariadb:latest
```

* 컨테이너 생성
  * -d 데몬으로 실행 
  * -p 포트포워딩. (로컬 이어서 3306 3306 로 설정)
  * -e MYSQL_ROOT_PASSWORD root 패스워드 설정
  * -v 디비 데이터 저장할 폴더
  * --name 컨테이너 이름

```bash
docker container run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -v /Users/Shared/data/mariadb:/var/lib/mysql --name mariadb_local mariadb
```

## 컨테이너 구동

* 컨테이너 구동

```bash
-MacBookPro ~ % - docker ps
CONTAINER ID   IMAGE     COMMAND                  CREATED          STATUS          PORTS                    NAMES
f81de5a32c9c   mariadb   "docker-entrypoint.s…"   32 seconds ago   Up 31 seconds   0.0.0.0:3306->3306/tcp   mariadb_local
```

* 데이터 생성 확인

```bash
-MacBookPro ~ % cd /Users/Shared/data/mariadb 
-MacBookPro mariadb % ls
aria_log.00000001	ib_buffer_pool		ibdata1			multi-master.info	performance_schema
aria_log_control	ib_logfile0		ibtmp1			mysql

```

* 생성된 컨테이너 실행/정지

```bash
docker start mariadb_local => 실행
docker stop mariadb_local => 정지
```



## 컨테이너에서 MariaDB실행

* DB접속
  * mariadb가 설치된 컴테이너 실행
  * mariadb 접속 (mysql -u root -p)

```bash
docker exec -i -t mariadb bash 

mysql -uroot -p1234
```

* database 확인

```bash
MariaDB [(none)]> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
+--------------------+
3 rows in set (0.001 sec)
```

* 사용중인 계정 조회

```bash
 use mysql;						    // mysql db 사용		
 select host, user, password from user;	    // 사용중인 계정 조회
```



## Docker mariadb 컨테이너에 샘플 데이터 넣기

[GitHub_test_db](https://github.com/datacharmer/test_db) 에서 `ziq`파일 다운로드

![스크린샷 2022-01-25 오전 8 15 35](https://user-images.githubusercontent.com/58923731/150880924-1ed26a92-966a-465d-997d-881c7b76cbe0.png)

다운받은 test_db파일 복사 - `docker cp 파일경로 컨테이너이름:/`

```bash
docker cp sw/test_db-master mariadb:/
```

mariadb 접속 - `docker exec -it 컨테이너ID /bin/bash`

```bash
root@f789:/# ls
bin  boot  dev  docker-entrypoint-initdb.d  etc  home  lib  media  mnt  opt  proc  root  run  sbin  srv  sys  test_db-master  tmp  usr  var
```

test db 설치하기 위해선 `employees.sql`가 있는 위치에서 `mysql -u root -p < employees.sql`명령어 입력

```bash
root@f789:/# cd test_db-master/

root@f789:/test_db-master# ls
Changelog      employees_partitioned.sql      load_departments.dump   load_employees.dump  load_salaries3.dump  sakila            test_employees_md5.sql
README.md      employees_partitioned_5.1.sql  load_dept_emp.dump      load_salaries1.dump  load_titles.dump     show_elapsed.sql  test_employees_sha.sql
employees.sql  images                         load_dept_manager.dump  load_salaries2.dump  objects.sql          sql_test.sh       test_versions.sh

root@f789:/test_db-master# mysql -u root -p < employees.sql
```

설치후 `mysql -u root -p`명렁어로 데이터베이스 접속후 `show databases`명령어로 추가 되었는지 확인.

```bash
root@f789:/# mysql -u root -p

MariaDB [(none)]> show databases;
+--------------------+
| Database           |
+--------------------+
| employees          |
| information_schema |
| mysql              |
| performance_schema |
| sql_test           |
| sys                |
+--------------------+
6 rows in set (0.001 sec)

MariaDB [(none)]> use employees;

MariaDB [employees]> show tables;
+----------------------+
| Tables_in_employees  |
+----------------------+
| current_dept_emp     |
| departments          |
| dept_emp             |
| dept_emp_latest_date |
| dept_manager         |
| employees            |
| salaries             |
| titles               |
+----------------------+
8 rows in set (0.001 sec)
```



![스크린샷 2022-01-25 오전 8 15 35](https://user-images.githubusercontent.com/58923731/150880924-1ed26a92-966a-465d-997d-881c7b76cbe0.png)





## Reference

[https://happygrammer.github.io/docker/mariadb/](https://happygrammer.github.io/docker/mariadb/)
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

* 도커 이미지 다운

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











## Reference

[https://happygrammer.github.io/docker/mariadb/](https://happygrammer.github.io/docker/mariadb/)
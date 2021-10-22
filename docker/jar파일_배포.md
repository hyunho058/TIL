# Docker를 이용한 jar파일 배포

## Build

### jar이름 변경

 jar의 기본 이름은 `프로젝트명-0.010-SNAPSHOT`이다.

이름은 바꾸려면 `build.gradle`에서 아래처럼 설정을해주면 된다.

```groovy
bootJar{
    archivesBaseName = 'semoapi'
    archiveFileName = 'semoapi.jar'
    archiveVersion = "0.0.1"
}
```

* archivesBaseName : 프로젝트 이름
* archiveFileName : jar로 변환할 이름

### build

![스크린샷 2021-10-22 오후 11 53 51](https://user-images.githubusercontent.com/58923731/138476394-db744624-2724-4f3a-a4ef-71a931aef7c3.png) 

* Gradle - 프로젝트 - Tasks - build - (clean, build, jar)
  * clean, build, jar 순서대로 실행시켜준다.
* jar 저장 위치 : 프로젝트 - build - libs

## Dockerfile생성

```shell
FROM openjdk:11-jdk
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} semoapi.jar
WORKDIR /home/api/target
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/semoapi.jar"]
```

## 파일 전송

> FileZilla를 이용하여 local pc 에서 AWS(ec2)로 전송하였음.

ed2인스턴스에 api폴더를 만들고 하위에 target폴더를 생성

* dockerfile 위치 : `/home/api`
* jar 위치 : `/home/api/target`

## Docker파일 실행

위에서 만든 Dockerfile이 저장된 위치(home/api)에서 아래 명령어를 실행한다

```shell
$ docker build --tag semopai:0.1 .
```

위 명령어는 현재경로의 모든파일 (.) 을 semoapi 라는 이름으로 0.1 버전으로 build

`docker images`를 이용하여 이미지 생성확인

```shell
$ docker images
REPOSITORY   TAG       IMAGE ID       CREATED             SIZE
semoapi      0.1       xxxxxxxxxxxx   About an hour ago   725MB
```

`docker run -d -p 8080:8080 IMAGE ID`를 이용하여 도커 이미지 실행

```shell
$ docker run -d -p 8080:8080 xxxxxxxxxxxx
```

* -d : 백그라운드로 실행
* -p : 포트번호 지정 

`docker ps` 로 컨테이너 실행 확인

```shell
$ docker ps
CONTAINER ID   IMAGE     COMMAND                  CREATED             STATUS             PORTS                                       NAMES
xxxxxxxxxxxx   xx        "java -Djava.securit…"   About an hour ago   Up About an hour   0.0.0.0:8080->8080/tcp, :::8080->8080/tcp   vigilant_saha
```


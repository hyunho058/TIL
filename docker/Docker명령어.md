## Docker명령어

> * docker명령어는 보통 **docker + <명령어>** 형태로 구성되어있다.
> * 명령어는 항상 관리자 권한으로 실행되어야한다.
> * permission관련 오류가 발생하다면 sudo키워드를 입력한다.

* 이미지 검색

```bash
docker search ubuntu
```

* 이미지 다운로드
  * `docker pull`을 이요해서 이미지 다운
  * pull 키워드 뒤에 **:latest** 를 명시하는것은 최신 버전을 내려받는 의미이다
  * 이미지 이름 앞에 **xxx/ ** 사용자 명을 입력하면 특정 사용자가 업로드한 이미지를 pull받을 수 있다 -> ex) hyunho/ubuntu

```bash
docker pull ubuntu:latest
```

* 이미지 목록 확인

```bash
docker images
```

* 컨테이너 실행
  * `docker run`
  * run하게 되면 root계정으로 가상컨테이너 계정 터미널 환경에 접속한 것이다

```bash
docker run -it --name 컨테이너명 이미지명 /bin/bash
```

* 컨에니터에서 터미널로 나오기
  * `exit`

```bash
exit
```

* 컨테이너 목록 확인
  * -a 옵션을 사용하면 현재 정지중인 컨테이너까지 확인 가능하다., 생략시 실행중인 컨테이너 목록만을 조회한다.

```bash
docker ps -a
```

* 컨테이너 실행
  * `docker start`
  * `docekr start -a `

```bash
docker start 컨테이너명 
docker start -a 컨테이너명 // -a : 도커 컨테이너가 실행이될때 아웃풋들을 화면에 노출 시키는 역할
```

* 컨테이너 중지
  * `docker stop` :  그동안 하던 작업들을 완료하고 컨테이너를 중지
  * `docker kill` : stop과 달리 어떠한 것도 기다리지 않고 바로 컨테이너를 중지

```bash
docker stop 컨테이너명
docker kill 컨테이너명
```

* 컨테이너 재시작
  * docker restart

```bash
docker restart 컨테이너명
```

* 컨테이너 접속
  * docker attach

```bash
docker attach 컨테이너명
```

* 컨테이너 외부에서 컨테이너 명령 실행

  * `docker exec`
  * 이미 실행중인 컨테이너에 명령어를 전달

  `docker run`은 **새로 컨테이너를 만들어서 실행**

  `docker exec`은 **이미 실행중인 컨테이너에 명령어를 전달**

```bash
docker exec 컨테이너명 명령
% docker exec 1233c05773bf ls
bin
dev
etc
home
lib
media
mnt
opt
proc
root
run
sbin
srv
sys
tmp
usr
var
```

* 컨테이너 삭제
  * `docker rm`
  * `docker rm 'docker ps -a -q'` : 모든 컨테이너를 삭제

```bash
docker rm 컨테이너명
docker rm 'docker ps -a -q'
```

* 이미지 삭제
  * `docker rmi`

```bash
docker rmi 이미지명:버전
```

* 컨테이너, 이미지, 네트워크를 모두 삭제
  * `docker system prune`
  * 도커를 쓰지 않을때 모두 정리하고 싶을때 사용
  * **실행중인 컨테이너에는 영향을 주지 않음.**

```bash
docker system prune
```







[reference](http://hong.adfeel.info/docker/docker-%EC%84%A4%EC%B9%98-%EB%AA%85%EB%A0%B9%EC%96%B4-%EC%82%AC%EC%9A%A9%ED%95%B4%EB%B3%B4%EA%B8%B0/)


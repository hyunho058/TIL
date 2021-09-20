# 도커를 사용할 때의 흐름

>* 도커 Client(CLI)에 커맨드를 입력
>* 도커 서버(도커 Daemon)이 그 커맨드를 받아서 그거셍 따라 이미지를 생성, 컨테이너를 실행하는 모든 작업을 한다.

```
hyunho@gimhyeonhoui-MacBookPro ~ % docker run hello-world
```

* `docker run hello-world` -> 컴퓨터(도커 클라이언트 -> 도커 서버(이미지 Cache보관 장소)) <- 도커 허브(이미지 보관)
  * 캐시 보관 장소에 hello-world이미지가 없다면 도커 허브에서 가조온다.

## Docker와 VM

* 컨테이너
  * 하나의 HostOS(Host Operating System)위에 Docker를 설치하여 그 위에 각각의 서비스 환경들을 설치/운영하는 형태
  * 하나의 HostOS위에서 자원만 공유하기 때문에 상대적으로 매우 가벼우며 Docker가 설치된 환경이라면 이미지가 사용 가능하기 때문에 어디서든 사용가능하다는 장점
* VM(Virtual Machine)
  * Hypervisor 기법을 통해 단일 시스템에서는 여러 VM을 실행
  * 가상머신은 Hypervisor를 이용해 하나의 호스트OS에서 여러개의 OS를 사요하는 방법
  * GuestOS를 하나씩 설치하는 형태, 각각의 가상머신은 하나의 독립된 커널 공간을 가진 완전하 ㄴ컴퓨터를 생산하는 식의 환경을 구성

* 공통점 - 도커 컨테이너와 가상 머신은 기본 하드웨어에서 격리된 환경 내에 애플리에이션을 배치하는 방법
* 차이점 - 격리된 환경을 얼마나 격리시키는지의 차이(컨테이너는 하드웨어 대신 운영체제를 가상화한다)

![docker_vm](Docker흐름.assets/docker_vm.png)

어플리케이션을 실행할 때는 **컨테이너 방식에서는 호스트  OS위에 어플리케이션의 실행 패키지인 이미지를 배포**하기만 하면 되는데 **VM은 어플리케이션을 실행하기 위해서 VM을 띄우고 자원을 할당한 다음 게스트 OS를 부팅하여 어플리케이션을 실행** 해야 해서 복잡하고 무겁게 실행을 하게 된다.

## 도커 컨테이너 격리

* C Group
  * CPU, 메모리, Network Bandwidth,HD I/O등 프로세스 그룹의 시스템 리소스 사용량을 관리
  * 어떤 어플이 사용량이 너무 많다면 그 어플리케이션 같은 것을 C Group에 집어 넣어서 CPU와 메모리 사용 제한기능
* Namespace
  * 하나의 시스템에서 프로세스를 격리시킬 수 있는 가상화 기술
  * 별개의 독립된 공간을 사용하는 것처럼 격리된 환경을 제공하는 경량 프로세스 가상화 기술

`C Group`과`Namespace`는 컨테이너와 호스트에서 실행되는 다른 프로세스 사이에 벽을 만드는 리눅스 커널 기능.



## 이미지 내부 파일 시스템 구조

* 이미지로 컨테이너 만드는 순서
  1. Docker 클라이언트에 `docker run <이미지>` 입력
  2. 도커 이미지에 있는 파일 스냅샷을 컨테이너 하드 디스크에 옮겨준다.
  3. 이미지에서 가지고 있는 명령어(컨테이너가 실행될 때 사용될 명령어)를이용해서 실행시킨다.
     * 컨테이너가 실행될 명렁어가 실행되어 커널을 통해 실행파일을 작동하여 프로그램 실행





## Reference

[https://hoon93.tistory.com/41](https://hoon93.tistory.com/41)

[https://www.inflearn.com/course/%EB%94%B0%EB%9D%BC%ED%95%98%EB%A9%B0-%EB%B0%B0%EC%9A%B0%EB%8A%94-%EB%8F%84%EC%BB%A4-ci/dashboard](https://www.inflearn.com/course/%EB%94%B0%EB%9D%BC%ED%95%98%EB%A9%B0-%EB%B0%B0%EC%9A%B0%EB%8A%94-%EB%8F%84%EC%BB%A4-ci/dashboard)
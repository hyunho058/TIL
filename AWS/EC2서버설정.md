# 아마존 리눅스 서버 설정

> EC2(Amazon Linux 2)

## Java11설치

설치가능한 jdk확인

* `list java*jdk-devel`

```bash
$ yum list java*jdk-devel
Loaded plugins: extras_suggestions, langpacks, priorities, update-motd
Available Packages
java-1.7.0-openjdk-devel.x86_64                 1:1.7.0.261-2.6.22.2.amzn2.0.1                 amzn2-core
java-1.8.0-openjdk-devel.x86_64                 1:1.8.0.302.b08-0.amzn2.0.1                    amzn2-core

```

yum에는 설치가능한 JDK가 1.8까지만 존재하기 때문에 JDK11을 설치하기 위해서는 Amazon이 제공하는 OpenJDK(Amazon Coretto)를 다운받아 설치 할 수 있다.

* AWS coretto다운로드

```bash
$ sudo curl -L https://corretto.aws/downloads/latest/amazon-corretto-11-x64-linux-jdk.rpm -o jdk11.rpm
```

* jdk11 설치

```bash
$ sudo yum localinstall jdk11.rpm
```

* jdk version 선택

```bash
$ sudo /usr/sbin/alternatives --config java

1 개의 프로그램이 'java'를 제공합니다.

  선택    명령
-----------------------------------------------
*+ 1           /usr/lib/jvm/java-11-amazon-corretto/bin/java

현재 선택[+]을 유지하려면 엔터키를 누르고, 아니면 선택 번호를 입력하십시오
```

* java 버전 확인

```bash
$ java --version
openjdk 11.0.12 2021-07-20 LTS
```

* 다운받은 설치파일 제거

```bash
$ rm -rf jdk11.rpm
```

* 사용하지 않는 java.지우기

```bash
$ yum list installed | grep "java" #설치된 항목
java-11-amazon-corretto-devel.x86_64  1:11.0.12.7-1                  installed  
$ sudo yum remove java-11-amazon-corretto-devel.x86_64 #제거
```

## 타임존 변경

EC2 서버의 기본 타임은 UTC이다.(한국과 9시간 차이) 이것을 한국 시간으로 변경하기 위해서는 아래와 같이 명령어를 입력한다.

```bash
$ sudo rm /etc/localtime 
$ sudo ln -s /usr/share/zoneinfo/Asia/Seoul /etc/localtime
```

KST로 변경된 시간 확인

```bash
$ date
2021. 10. 19. (화) 18:17:04 KST
```


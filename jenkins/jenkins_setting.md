#  jenkins 설치

> 설치환경 - Docker
>
> * Docker > Jenkins > Docker

## MTU확인

> Jenkins를 설치했을 때 성공적으로 설치가 됐는데 실행이 되지 않는 오류가  발생.
> 결론적으로 Docker MTU 수치가 맞지 않아서 네트워크가 되지 않아서 생긴 오류

* ip link
  * `eth0` 의 MTU와 `docker0`의 MTU가 같은지 확인한다
  * 다르다면 둘의 MTU를 같게 설정해주어야 한다.

```bash
ip link

// 결과 //
1: lo: 
2: eth0: <> mtu 1234
3: docker0: <> mtu 1111 
```

* 변경방법
  1. VM 내부의 도커 네트워크 MTU 조정

## setting

### Jenkins 설치

> docker 확경

* jsenkins 이미지 pull 및 실행
  * **run : 이미지를 실행**
  * **-itd** 옵션: **interacitve terminal + detach(background)**
  * **--name : 이미지 이름 지정**
  * **-p: <hostport>:<docker container port>**
  * **jenkins/jenkins:lts: docker hub이미지 저장소:버전**

```bash
docker run -itd --name jenkins -p 8085:8080 jenkins/jenkins:lts
```

* Jenkins 컨테이너를 실행후 Jenkins 접속 확인 
  *  localhost:포트번호 (xxx.xxx.xx.xx:0000)

![스크린샷 2021-07-27 오후 9.57.41](jenkins_setting.assets/스크린샷 2021-07-27 오후 9.57.41.png) 

* docker logs - 젠킨스 실행 로그및 비밀번호 확인
  * `b850` :  컨테이너 (젠킨스 이름)
  * 비밀번호를 위 화면에 입력.

```bash
[root@semoapi ~]# docker logs b850

// 결과 //
*************************************************************
Jenkins initial setup is required. An admin user has been created and a password generated.
Please use the following password to proceed to installation:
xxxxxxxxxxxxxxx(비밀번호)
This may also be found at: /var/jenkins_home/secrets/initialAdminPassword
*************************************************************
```

* 비밀번호를 입력하고 나면 아래와 같이 화면이 나온다. 

![image](jenkins_setting.assets/image-7564200.png) 

* 설치 진행화면

![image (1)](jenkins_setting.assets/image (1).png) 

*  계정 생성

![image (2)](jenkins_setting.assets/image (2).png) 

![image (3)](jenkins_setting.assets/image (3).png) 





### Jenkin와 Slack 연동

* Slack 앱 추가

  * Slack - Apps - Add apps - 설치할 App인 Jenkins검색 - Jenkins CI - Configuration
  * 설치후 설정 화면에 가면,Jenkins에서 설정할 때 필요한 정보와 Jenkins에 설정 방법에 대해 설명이 나와 있으며, 설정 화면에서는 Jenkins 알람을 받을 슬랙 채널과 이름, 아이콘을 설정할 수 있다.
  * Post to Channel

  ![img](jenkins_setting.assets/img.png)   

  ![image (4)](jenkins_setting.assets/image (4).png) 

  * 젠킨스로 넘어가서
  * Jenkins관리 - 플러그인 관리 - slack notification설치

  ![image (5)](jenkins_setting.assets/image (5).png) 

  ![image (6)](jenkins_setting.assets/image (6).png) 

  * 다운로드 완료후 Jenkins관리 - 시스템 설정

  ![image (7)](jenkins_setting.assets/image (7)-7392480.png) 

  * Workspace와 Default channel / member id 입력
    * Workspace - Team Subdomain값 입력
    * Default channel / member id - jenkins알림을 받을 슬랙 채널명 입력(팀하위 도메인 값)

  ![image (9)](jenkins_setting.assets/image (9).png) 

  * Kind - Secret text 선택
  * 슬랙 설치 방법에 팀 하위 도메인과 통합 토큰 자격 증명 ID를 확인한후  Secret에 통합토큰 자격증명 ID 를 입력

  ![image (10)](jenkins_setting.assets/image (10).png) 

  * 이제 잘 설정되었는지 Test Connection 버튼을 누르면, 슬랙에 설정한 채널에 알림이 오는것을 확인할 수 있다.

  

### Jenkin와 GIthub 연동

* 실행중 컨테이너 확인

 ```bas
 docker ps
 ```

* Jenkins 컨테이너 들어간다.
  * `-itu 0` root 권한으로 들어간다.

```bash
docker exec -itu 0 b850 /bin/bash
```

* ssh key 생성

```bash
ssh-keygen -t rsa

//결과//
-----BEGIN OPENSSH PRIVATE KEY-----
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
-----END OPENSSH PRIVATE KEY-----
```

* ssh key 확인

```bash
cat /root/.ssh/id_rsa //private key 확인
cat /root/.ssh/id_rsa.pub	//public key 확인

//결과//
ssh-rsa 
AAAxxxxxxxxxxxxxxxxxxxxxx
```

* Jenkins > Global credentials 
  * 발급받은 ssk-key입력

![image (11)](jenkins_setting.assets/image (11).png)

* private key 등록
  * Key - ssh key(private key)입력

![스크린샷 2021-07-28 오후 9.59.58](jenkins_setting.assets/스크린샷 2021-07-28 오후 9.59.58.png)

* Github와 public 연결
  * Deploy keys - Add deploy key - ssh-key(public) 입력

![image (13)](jenkins_setting.assets/image (13)-7477526.png)

* Webhook설정
  * 트리거 발생 시점 설정

![image (15)](jenkins_setting.assets/image (15).png)

* Repository SSH확인

![image (16)](jenkins_setting.assets/image (16).png) 

* jenkins에 ssh값 입력
  * Ener an item name - ex)프로젝트 이름 입력
  * Freestyle project - GitHub project - project url에  복사한 ssh값 입력

![image (17)](jenkins_setting.assets/image (17).png)

![image (18)](jenkins_setting.assets/image (18).png)

* 트리거 발생 시점 선택

![image (19)](jenkins_setting.assets/image (19).png)











## [Reference](https://nirsa.tistory.com/301)




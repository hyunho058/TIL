# AWS

## EC2인스턴스 생성, 시작

Elastic Compute Cloud

EC2 는 aws에서 제공하는 성능, 용량 등으 ㄹ유동적으로 사용할 수 있는 서버(AWS에서 리눅스 서버 또는 윈도우 서버를 사용한다라고 하면  EC2를 이야기하는것)

* 리전 선택
  * AWS의 서비스가 구동될 지역

![스크린샷 2021-10-13 오후 6 57 36](https://user-images.githubusercontent.com/58923731/137112148-e1475fe4-536e-495e-8df5-e69d0f251e4d.png)

AMI(Amazon Machine Image)선택, AMI는 EC2 인스턴스르 ㄹ시자가흔데 필요한 정보를 이미지로 만들어 둔것. (인스턴스라는 가상 머신에 운영체제 등을 설치할 수 있게 이미지로 만든것)

### AMI선택

![AMI선택](AWS.assets/137871506-bcb44767-f419-4b0b-a047-680d4abe3e95.png)

아마존 리눅스2는 Centos7버전 자료들을 그대로 사용 할 수있다.

### 인스턴스 유형 선택

![인스턴스유형선택](AWS.assets/137871565-502e0f9a-a0e8-4362-9116-9a4873b84e75.png)

t2 : 요금 타입이며, micro : 사양

인스턴스 크기에따라 정해진 비율로 CPU크레딧을 계속 받게 되며, 사용하지 않을 때는 크래딧을 축적하고, 사용할 때 이 크래딧을 사용

정해진 사양보다 더 높은 트래픽이 오면 크레딧이 좀 더 적극적으로 사용하면서 트래픽을 처리하지만, **크레딧이 모두 사용되면 더이상 EC2를 사용 할수 없다.** (트래픽이 높은 서비스들은 T시리즈를 쓰지 않고 다른 시리즈를 사용하기도 한다고 함.)

### 인스턴스 세부정보 구성

![인스턴스세보정보구성](AWS.assets/137871616-af919066-e02e-4c0f-8469-2e0b6e5e2a51.png)

VCP의 서브넷 등은 AWS 서비스들의 네트워크 환경을 구성한는것

### 스토리지 구성

![스토리지구성](AWS.assets/137871653-bf9ff753-1e01-4a80-b422-93b20cccdf3f.png)

스토리지 크기의 기본값은 8GB로 되어있는데 30GB까지 프리티어로 가능하다.(30GB가 넘어가게되면 비용이 발생)

### 태그 추가

![태그추가](AWS.assets/137871686-aaac3ef1-44b1-44b2-b7c8-1067e8a354a1.png)

태그에는 웹 콘솔에서 표기될 테그인 Name태그를 등록. 태그는 해당 인스턴스를 표현하는 이름으로 사용될 수 있다. (EC2의 이름을 부여)

여러 인스턴스가 있을경우 이를 태그별로 구분하면 검색이나 그룹을 짓기 편하므로 서비스의 인스턴스를 표현할 수 있는 값으로 등록 하는게 좋다.

### 보안 그룹 추가

![보안그룹추가](AWS.assets/137871731-68f69304-b04a-437b-816d-f127dd3d84ea.png)

보안 그룹은 **방화벽**을 이야기한다. **서버로 80포트 외에는 허용하지 않는다는 역할**을 하는 방화벽이 AWS에서는 보안그룹으로 사용된다.

**SSH유형은 보안을위해 지정된 IP에서만 ssh접속이 가능하게 소스를 내IP로 바꿔준다.** (내 IP를선택하면 현재 좁속한 장소의 IP가 자동으로 지정된다. )

### 인스턴스 검토

![인스턴스검토](AWS.assets/137871799-11898ecd-aa56-46fc-9208-c101905ad4f9.png)

현재 프로젝트의 기본 포트인 8080을 추가하고 검토 및 시작 버튼을 클릭하면 컴토화면에서 보안 그룹 경고를 볼수 이께 되는데 이는 8080이 전체 오픈이 되어서 발생하는 것이다. (8080을 열어 놓는 것은 보안에 위험한 일이 아니라고 함.)

#### 인스턴스 접근하기 위한 PEM키

인스턴스에 접근하기 위해서는 pem 키가 필요하다. 

인스턴스는 지정된 pem키 와 매칭되는 공개키를 가지고 있어, 해당 pem키 외에는 접근을 허용하지 않는다.(pem 키는 EC2서버로 접솔할 때 필수 파일이니 잘 저장해두어야한다.)

인스턴스 **검토 단계에서 기존 키 페어 선택 또는 새키 페어 생성 창이 뜨게되는데 키 페어 이름을 작성하고 키 페어 다운로드**를 한다.

### 고정 IP할당

인스턴스 생성시 항상 새 IP가 할당되며, 인스턴스를 중지하고 다시 시작할때 에도 새 IP가 할당된다.

 AWS고정 IP를 Elastic IP(EMP, 탄력적 IP)라고 한다

![탄력IP](AWS.assets/137871843-137bbbf6-7ebb-4ccf-8bdc-f207b2fda611.png) 

네트워크 및 보안 - 탄력적 IP - 새 주소 할당(할당이 완료되면 탄력적 IP가 발급된다.)

탄력적.IP가 생성되면 생성된 탄력적 IP와 EC2주소를 연결해줘야한다.

![탄력IP작업](AWS.assets/137871890-4475e7d1-c2b8-4fed-be96-695405067536.png)

작업 - 주소 연결

주소 연결창이 보이면 인스턴스 항목에서 생성한 EC2 이름 선택과 IP를 선택하고 연결 버튼 클릭후 인스턴스 목록 페이지로 돌아와서 **인스턴스 세부 정보에 탄력적 IP주소가 할당**된것을 확인할 수 있다.

## EC2서버 접속

AWS 서버로 SSH접속을 하려면 맫번 다음과 같이 명령어를 입력해야한다.

```bash
ssh -i pem 키 위치 EC2의 탄력적 IP 주소
```

이러한 긴 명렁어를 입력하는 것을 간편하게 ssh접속을 할수 있도록 다음과 같이 설정할 수 있다.

pem파일을 `~/ssh/` 로 로 복사 한다 `~/ssh/`디렉토리로 pem파일을 옮겨 놓으면 ssh 실행 시 **pem 키 파일을 자동으로 읽어 접속**을 할 수있다.(별도로. Pem 키 위치를 명렁어로 지정할 필요가 없음)

`cp pem 키위치 ~/.ssh/`

```bash
~ % cp ~/sw/study/project.pem ~/.ssh/
```

`~ssh/`디렉토리로 이동해 복사 되었는지 확인

`cd ~/.ssh/`

```bash
~ % cd ~/.ssh 
```

`ls`

```bash
.ssh % ls
```

복사가 되었는지 확인후 **pem키의 권한을 변경**한다.

`chmod 600 ~/.ssh/pem키이름`

```bash
.ssh % chmod 600 ~/.ssh/project.pem 
```

권한을 변형한 뒤 pem키가 있는 `~/.ssh` 디렉토리에 **config파일을 생성**

`vim ~/.ssh/config`

```bash
.ssh % vim ~/.ssh/config
```

```bash
# project
Host 원하는 서비스명
    HostName ec2의 탄력적 IP주소
    User ec2-user
    IdentityFile ~/.ssh/pem키 이름
```

생성된 config파일은 실행 권한이 필요하므로 권한 설정을 해준다

`chmod 700 ~/.ssh/config`

설정을 완료하고 접속을 확인

`ssh config에 등록한 서비스명`

```bash
.ssh % ssh project
The authenticity of host 'IP' can't be established.
ECDSA key fingerprint is SHA256:xxxxxxxxxxxxxxxxxxxxxxx.
Are you sure you want to continue connecting (yes/no/[fingerprint])?
```

`yes`를 입력하여 EC2접속

```bash
Warning: Permanently added 'IP' (ECDSA) to the list of known hosts.

       _|_|  __|_  )
       _|    (     /   Amazon Linux 2 AMI
      _|___--___|___|

https://aws.amazon.com/amazon-linux-2/
[ec2-user@ip-000-00-00-00 ~]$ 
```

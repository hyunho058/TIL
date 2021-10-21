# RDS

## 인스턴스 생성

### 데이터베이스 생성

![스크린샷 2021-10-21 오후 2 28 07](https://user-images.githubusercontent.com/58923731/138216921-c342631b-94a3-4693-baf0-ad96acd94425.png)

### 엔진 옵션(MariaDB)

![스크린샷 2021-10-21 오후 2 33 01](https://user-images.githubusercontent.com/58923731/138217299-c31e1721-d7b5-4b74-ade6-092ba1bfa8d7.png)

### 탬플릿

프리티어를 사용하기 때문에 프리티어 선택

![스크린샷 2021-10-21 오후 2 36 55](https://user-images.githubusercontent.com/58923731/138217664-2ac5a20f-ad54-420b-8a1d-2bae0315b12f.png)

### 설정

 DB인스턴스와 마스터 사용자 정보 등록

작성된 사용자 정보로 데이터베이스에 접근하기때문에 정보를 기억해야한다. 

![스크린샷 2021-10-21 오후 2 42 41](https://user-images.githubusercontent.com/58923731/138218252-01fb0078-3e38-46f3-abd9-f674d7a503b5.png)

### 연결

퍼플릭 액세스를 `예`로변경 - 추후 보안 그룹에서 지정된 IP만 접근할수 있도록 설정

![스크린샷 2021-10-21 오후 2 47 37](https://user-images.githubusercontent.com/58923731/138218866-5683b5b2-c7a2-4226-82f6-2da900df9d7d.png)

### 추가구성 - 데이터베이스 옵션

데이터베이스 이름 설정

### 생성 완료

![스크린샷 2021-10-21 오후 2 54 25](https://user-images.githubusercontent.com/58923731/138219583-3e2fcd1c-397c-4f42-be40-249e25a6a6df.png)

## 파라미터 설정

> 타임존
>
> Character Set
>
> Max Connection

### 파라미터 그룹이동

![스크린샷 2021-10-21 오후 2 58 13](https://user-images.githubusercontent.com/58923731/138219986-0e596223-5b36-4fa0-874d-6eb14fd7d355.png) 

### 파라미터 그룹 생성

![스크린샷 2021-10-21 오후 2 59 12](https://user-images.githubusercontent.com/58923731/138220132-b5b5e614-a4b7-428b-9363-abb50dde3d9d.png)

![스크린샷 2021-10-21 오후 3 02 46](https://user-images.githubusercontent.com/58923731/138220442-49ae5230-f280-44d0-8df1-b66dfc988829.png)

파라미터 그룹 패밀리 - 생성한 인스턴스의 MariaDB와 같은 버전으로 맞춰줘야한다.(10.5.xx버전으로 생성 하였으면 10.5버전 선택)

### 파라미터 편집 화면으로 이동

![스크린샷 2021-10-21 오후 3 07 11](https://user-images.githubusercontent.com/58923731/138220969-5ddb4e76-47d3-4bd4-a8c1-34f06535b59c.png)

![스크린샷 2021-10-21 오후 3 10 29](https://user-images.githubusercontent.com/58923731/138221251-e7608019-f9be-429f-8f19-3307f42c0aec.png)

수정 버튼을 눌러 파라미터를 수정한다.

### 파라미터 수정 항목

아래 항목들을 검색하여 값을 수정해준다

* Time_zone : Asia/Seoul
* character_set_client : utf8mb4
* character_set_connection : utf8mb4
* character_set_database : utf8mb4
* character_set_filesystem : utf8mb4
* character_set_results : utf8mb4
* character_set_server : utf8mb4
* collation_connection : utf8mb4_general_ci
* collation_server : utf8mb4_general_ci
* max_connections : 150

![스크린샷 2021-10-21 오후 3 25 48](https://user-images.githubusercontent.com/58923731/138222987-e0e35e2e-ad5a-477b-9030-d52cce1dd34e.png)

![스크린샷 2021-10-21 오후 3 25 11](https://user-images.githubusercontent.com/58923731/138222977-54eb2e3f-297a-431b-a9f0-a8dc61e2a93e.png)

### 파라미터 그룹 데이터베이스 연결

![스크린샷 2021-10-21 오후 3 36 05](https://user-images.githubusercontent.com/58923731/138224318-88ffb323-9c2d-49e7-b1c3-6709d8850144.png)

데이터베이스 - 파라미터 그룹 선택 - 수정

![스크린샷 2021-10-21 오후 3 37 43](https://user-images.githubusercontent.com/58923731/138224497-56c340fe-4da1-4dee-9fe8-ffc937795d67.png)

DB 파라미터 그룹을 생성한 파라미터 그룹으로 변경.

변경을 완료하고 파라미터 그룹이 반영되지 않을 때가 있기때문에 데이터베이스 - 작업 - 재부팅 을 눌러 재부팅 진행

## PC에서 RDS접속

로컬에서 RDS로 접근하기 위해서 RDS의 보안 그룹에 접속할 PC의 IP주소를 추가가 필요.

EC2-인스턴스-보안탭(생성한 EC2 인스턴스) -보안 그룹 ID 복사-RDS-연결&버안 탭(생성한 RDS )-VPC보안 그룹 클릭

![스크린샷 2021-10-21 오후 4 03 09](https://user-images.githubusercontent.com/58923731/138227924-ba4fa3a0-8542-4887-9a15-17d96d0d58b2.png)

![스크린샷 2021-10-21 오후 4 04 28](https://user-images.githubusercontent.com/58923731/138228103-2a0373ef-77e6-42bf-b71d-881bd181d74f.png)

![스크린샷 2021-10-21 오후 3 56 54](https://user-images.githubusercontent.com/58923731/138228190-fca7dd60-696f-4fa9-96d2-adaa0adb7e63.png)

* 첫 번째 줄에는 EC2의 보안 그룹 추가.
  * EC2와 RDS 간에 접근가능.
* 두 번째 줄에는 내 PC의 IP를 등록


# Kafka
## Kafka 설정
> docker 환경에 Kafka 설정  

* docker-compose 설치
kafka를 docker에서 실행하기 위해서는 `docker-ccompose`가 설치 되어있어야 한다.
 kafka는 항상 zookeeper가 실행되어 있어야해서 docker-compose로 실행하는 것이 편리하다.

Docker-compose를 사용하기 위해 github에서 다운
```bash
git clone https://github.com/wurstmeister/kafka-docker.git
```
다운로드 후 `compose.yml`파일을 수정 해야하며,  `broker`를 하나만 올려서 테스트 할 경우 `docker-compose-single-broker.yml`을 수정해주면 되고 아닐경우 `docker-compose.yml`파일을 수정해 주면 된다.
```
version: '2'
services:
  zookeeper:
    image: wurstmeister/zookeeper
    ports:
      - "2181:2181"
  kafka:
    image: wurstmeister/kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_HOST_NAME: [IP]
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
```
`[IP] `에는 IP주소를 적어주면 된다.
위 compose 파일을 보면 `kafka`와`zookeeper` 그리고 port에 대한 설정이 있으며, 해당 내용을 저장하고 `compose.yml`파일을 실행 시켜줘야한다.
```bash
docker-compose -f docker-compose.yml up
```

위 명령어를 실행하면 kafaka와 zookeeper container가 실행된걸 볼 수 있다.
```bash
docker ps
```
<img width="497" alt="스크린샷 2022-01-12 오후 10 37 07" src="https://user-images.githubusercontent.com/58923731/149150914-9e38e433-c47a-4f9f-8c2c-62cace5370d8.png">

## Topic생성및 확인
container가 실행되어 있으면 Kafka shell에 접근해야 하며 아래 명령어로 접근이 가능하다
```bash
docker exec -it 4ed /bin/bash
```

kafka명령어를 사용하기 위해서 /opt/kafka/bin 으로 이동해야한다
위 경로로 이동하였으면 topic을 만들 명령어를 입력해주면 된다.
```bash
bash-5.1# ./kafka-topics.sh --bootstrap-server localhost:9092 --topic kafka_chatting --create
```
`kafka_chatting ` -  topic이름(해당 부분에 원하는 topic명을 입력해주면 된다)
<img width="1143" alt="스크린샷 2022-01-12 오후 10 48 33" src="https://user-images.githubusercontent.com/58923731/149152439-f7638c97-f2ce-4e55-9fa0-05e4b24efb98.png">

topic을 생성 하였으면 아래 명령어로 생성된 topic을 확인 할 수 있다.
```bash
bash-5.1# ./kafka-topics.sh --bootstrap-server localhost:9092 --list
```
<img width="488" alt="스크린샷 2022-01-12 오후 10 50 56" src="https://user-images.githubusercontent.com/58923731/149152796-39168786-b974-4345-8c10-a4e9940d3c33.png">

## TEST
* producer생성
```bash
docker exec -it 4ed kafka-console-producer.sh --topic test --broker-list localhost:9092
```
* consumer생성
```bash
docker exec -it 4ed kafka-console-consumer.sh --topic test --bootstrap-server localhost:9092
```

Producer
![스크린샷 2022-01-12 오후 10 55 25](https://user-images.githubusercontent.com/58923731/149153667-21ef2d39-f198-422e-8f4e-d2da71721427.png)

Consumer
![스크린샷 2022-01-12 오후 10 56 30](https://user-images.githubusercontent.com/58923731/149153769-8703be32-003d-4fc0-8529-394bbca249dd.png)


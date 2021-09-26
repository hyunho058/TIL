# Redis를 이용한 컨테이너 확인

* `docker run redis` Redis서버 작동

```bash
~ % docker run redis
```

* 터미널 창을 새로 열고 `docker ps`로 redis 컨테이너 아이디 확인

* `docker exec -it 컨테이너ID redis-cli` 로 Redis Client에 접속

  * `-it` 명령어를 실행 한후 계속 명령어를 적을수 있다 (`-it` 가 없다면 이렇게 그냥 redis-cli를 키기만 하고 밖으로 다시 나와버린다.)

  ```bash
  ~ % docker exec 컨테이너ID redis-cli
  ~ % 
  ```

* Redis 확인

```bash
~ % docker exec -it 92b17f28a740 redis-cli
000.0.0.0:0000> set key1 hello
OK
000.0.0.0:0000> get key1
"hello"
000.0.0.0:0000> 
```


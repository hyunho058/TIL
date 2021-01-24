# Database Connection

> * DB - MariaDB(10.4)

## Setting

	* MySql모듈 설치
	* MariaDB Connection

### MySql모듈 설치

> * pymysql, mysql 라이브러리 - mysql 연동시 
> * sqlalchemy \**라이브러리\** - mysql 포함 다른 sql 연동

```bash
$ pip install PyMySQL
```



### MariaDB Connection

1. PyMySql 모듈 Import
2. MySQL Connection
   * 호스트명, 로그인, 암호, 접속할 DB
3. DB Cursor 객체 생성 
   * Connection 객체로부터 cursor() 호출
4. SQL문 실행
   * Cursor 객체의 메소드를 사요하여 SQL을 DB서보에 전송
     * SQL을  DB서버에 전송 - execute()
     * Fetch - fetch all(), fetching(), fetch many(n)
   * Connection 객체의 commit() - CRUD
5. DB Disconnection
   * close()

```python
import pymysql

conn = pymysql.connect(
  host='host', 
  port=Port number, 
  user='User name', 
  password='User password',
  db="DataBase Name",
  charset='utf8')

try:
    with conn.cursor() as curs:
        sql = "SELECT * FROM BOOK_BEST"
        # 실행할 sql문 넣기
        curs.execute(sql) 
        # sql문 실행해서 데이터 가져오기
        rs = curs.fetchall() 
        for row in rs:
            for data in row:
                print(data, end=' ')
            print()
finally:
    conn.close()
```




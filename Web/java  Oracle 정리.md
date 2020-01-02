

### java / Oracle 정리

* 구구단 정리
* 1/2+2/3+...9/10 =?
* 1+(1+2)+(1+2+3)+(1+2+3+4)+(1+2+3+4+5)=?

```bash
Microsoft Windows [Version 10.0.18362.239]
(c) 2019 Microsoft Corporation. All rights reserved.

C:\Users\student>sqlplus "/as sysdba"

SQL*Plus: Release 11.2.0.2.0 Production on 목 1월 2 10:39:51 2020

Copyright (c) 1982, 2014, Oracle.  All rights reserved.


Connected to:
Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production

SQL> show user;
USER is "SYS"
SQL> alter user system identified by oracle;

User altered.

SQL> conn system/oracle;
Connected.
SQL> create user doublekim identified by oracle;

User created.

SQL> alter uesr doublekim account unlock;
alter uesr doublekim account unlock
      *
ERROR at line 1:
ORA-00940: invalid ALTER command


SQL> conn system
Enter password:
Connected.
SQL> alter user doublekim account unlock;

User altered.

SQL> grant connect, resource to doublekim;

Grant succeeded.

SQL> conn doublekim/oracle
Connected.

SQL> set sqlprompt doublekim>
doublekim>
```

* Jar file 저장 경로& Test 코딩

![image-20200102131324082](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200102131324082.png)

```java
package dbConn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//DB 연결 정보 반복적으로 코딩 해결, 다른 클래스에서 아래 코드 구현을 하지 않도록 설계

public class ConnectionHelper {
	
	public static void main(String[] args) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("load success");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","doublekim","oracle");
			System.out.println("SQL connection success");

////////////////err catch 방법//////////////////////////
		} catch (ClassNotFoundException e) {
			System.out.println("드라이브 로드 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL 연결 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
//		} catch (Exception e) {
//			System.out.println();
//			e.printStackTrace();
//		}
	}
}
```



### Web(HTMLS, SCC, JS, jQuery)

### Jsp & Servlet

### Spring


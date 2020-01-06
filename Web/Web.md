# Web 

> HTMLS
>
> CSS
>
> JavaScript
>
> jQuery

![image-20200106134251877](image/image-20200106134251877.png)

* Client

  요청

  html,css,JavaScript.....

  Client Side Script (해석이 client에 있는)

  * 문서구조 - HTML

    정적태그, 단항태그

  * 디자인 - CSS

  * 기능동작 - JavaScript

* Server

  응답

  Database,Framework,Java,python.....

  Server Side Script (해석이 server에 있는) - 코드 연산을 하고 client 에 응답을 해주기때문에 보안에 강하다.(client에서 코드 확인 불가)

  * jsp - tomcat
  * aspx - IIS
  * php - apache



# Eclipse Web

## Tomcat



[	Apache Tomcat](http://tomcat.apache.org/) -> Download ->Tomcat8

* Tomcat 환경설정

![image-20200106155358830](image/image-20200106155358830.png)



```bash
## Tomcat과 eclipse port가 8080이 충돌이나서 eclipse port 변경

select dbms_xdb.gethttpport()from dual;  # port 확인

exec dbms_xdb.sethttpport(9090); # 기존 port를 9090으로 변경
```

![image-20200106160419274](image/image-20200106160419274.png)



* jar 파일 Java 폴더 복사

![image-20200106164313669](image/image-20200106164313669.png)

해당 경로로 복사

   ->  C:\Program Files\Java\jre1.8.0_231\lib\ext

## Server Side Script

```bash
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Server Test 입니다.</h1>
<% Date today = new Date(); %>  #
<%= today %>                    #
</body>
</html>

```

![image-20200106165943867](image/image-20200106165943867.png)

# 유용 사이트

[w3schools](https://www.w3schools.com/)

[https://www.w3.org/](https://www.w3.org/)
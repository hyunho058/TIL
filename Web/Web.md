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



## jar 파일 Java 폴더 복사

![image-20200106164313669](image/image-20200106164313669.png)

해당 경로로 복사 붙여넣기(el-api, jsp-api, servlet-api)

  C:\app\apache-tomcat-8.5.50\lib  ->  C:\Program Files\Java\jre1.8.0_231\lib\ext

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





# HTML TAG

```bash
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>처음 만든 웹페이지</title>
</head>
<body>
여기 쓴내용이 보입니다.<br/>
즐변경 안되었네요.<br>
여러번 쓸때 한번에 한칸씩 줄바꿈 됨<br><br><br><br>
문단이 바뀐다.<p>
줄변경
</body>
</html>
```

![image-20200107100637491](image/image-20200107100637491.png)



## HTML TEMPLATE 생성/변경/적용

![image-20200107100431929](image/image-20200107100431929.png)

##  Image

### 상대경로

```bash
<!DOCTYPE html>  <!-- html5 dtd type -->
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
            "http://www.w3.org/TR/html4/strict.dtd"> -->
<html>
<head>
<meta charset="UTF-8">
<title>image tag </title>
</head>
<body>
<!-- 상대경로 -->
<!-- <img src="house.gif"> -->
<img alt="우리집" src="./house.gif" width="100" height="100" align="left">
가나다라마바사아자차카파타하가나다라마바사아자차카파타하
가나다라마바사아자차카파타하

<!-- 절대경로 -->
</br><br><br>
<img src="../images/house.gif">
</body>
</html>
```

![경로 위치](image/image-20200107105802422.png)



### 절대경로



## <a tag

## <table tag

## &nbsp ;

> tap 역할



# 유용 사이트

[w3schools](https://www.w3schools.com/)

[https://www.w3.org/](https://www.w3.org/)

[HTML5/CSS 특강](https://www.youtube.com/playlist?list=PLMfOC9Ny9UB_w3Ru8HI0g00bxz21LA0gL)

### 학습 사이트

[생활코딩](https://opentutorials.org/course/1)

[JAVA강좌 Youtube](https://www.youtube.com/playlist?list=PLieE0qnqO2kTyzAlsvxzoulHVISvO8zA9)

[Web개발 학습](https://developer.mozilla.org/ko/docs/Learn)

[프로그램 학습](https://www.javatpoint.com/android-tutorial)
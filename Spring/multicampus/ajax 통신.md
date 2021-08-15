# ajax 통신

> ajax
>
> jquery

## axjax





## jquary

-*.jsp -->DispatcherServley - controller - model- view

```jsp
<script src="/mvc/jquery.js"></script>
```

![image-20200206132151616](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200206132151616.png)

.jsp file

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<h1>이미지 보고 있는 중입니다</h1>
<h1>이미지 보고 있는 중입니다</h1>
<h1 id="me">이미지 보고 있는 중입니다</h1>
<h1>이미지 보고 있는 중입니다</h1>
<h1>이미지 보고 있는 중입니다</h1>
	<!-- <img src="http://localhost:8081/mvc/resources/images/">  아래와 같음 -->
	<img src="<%=request.getContextPath()%>/resources/images/google.png">
	
	<script src="<%=request.getContextPath()%>/resources/jquery-3.2.1.min.js"></script>
	<script>
		$("h1").css("color", "blue");  
		$("#me").css("color", "red");
	</script>
</body>
</html>
```

> <script src="<%=request.getContextPath()%>/resources/jquery-3.2.1.min.js"></script>
>
> 먼져 호출 해야함



| ajax클라이언트 | ajax 서버 |
| -------------- | --------- |
|                |           |



**@RequestBody**  

 \- HTTP 요청 몸체를 자바 객체로 전달받음

 \- HTTP 요청의 body 내용을 자바 객체로 매핑하는 역할



**@ResponseBody** 

 \- 자바 객체를 HTTP 응답 몸체로 전송함

 \- 자바 객체를 HTTP 요청의 body 내용으로 매핑하는 역할



## JSON

[MVNRepositoty](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.10.0)

자바의 객체를 JSON 현태로 자동 변경 해주는 API

```xml

```

![image-20200206164139055](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200206164139055.png)

결과 확인

![image-20200206164526615](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200206164526615.png)



동기화 방식



비동기화 통신 jquery 방식 (asynchronous javascript and xml = ajax)

요청 1 - 요청2 -요청3 - 서버 처리중 =응답 3,2,1(view)





json(javascript object notation)


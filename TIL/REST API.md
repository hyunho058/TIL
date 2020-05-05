#  REST API

> Representational State Transfer

## REST API 구성

1. 자원 (Resource) - URI
2. 행위 (Verb) - Http Method
3. 표현 (Representations) 



## 특징

1. Uniform (Uniform Interface)

   * URI 로 지정한 Resource에 대한 조작을 통일되고 한정적인 인터페이스로 수행하는 아키텍쳐(기억장치의 번지방식) 스타일.

2. Stateless (무상태성)

   * 작업을 위한 상태정보를 따로 저장하고 관리하지 않는다.
   * Session, 쿠키정보를 별도로 저장하고 관리하지 않기 때문에 API서버는 요청만을 단순히 처리하면됨.
     * 서비스의 자유도가 높다,
     * 서버에서 불필요한 정보를 관리하지 않아 구현이 단순.

3. Cacheable (캐시 가능)

   * HTTP라는 기존 웹표준을 그대로 사용하기 때문에, 웹에서 사용하는 기존 인프라를 그대로 활용 가능.
     * HTTP가 가진 캐싱 기능이 저용 가능
     * HTTP프로토콜 표준에서 사용하는 Last Modified 태그나 E.TAG를 이용하면 캐싱 구현 가능
       * Last Modified 해더 - 브라우저가 서보로 요청한 파일의 최종 수정 시간을 알려주는 해더
       * ETag 해더 - 파일 수정 여부만 판별

4. Self - Server 구조

   * Rest API 메세지만 보고도 이를 쉽게 이해할 수 있는 자체표현 구조로 되어있다.

5. .Client -Server 구조

   * 클라이언트는 사용자 인증이나 컨텍스트(Session,Login information)등을 직접 관리하는 구조로 각각의 역할이 확실히 구분된다.
     * 클라이언트와 서버에서 개발해야 할 내용이 명확해지고 서로간 의존성이 줄어듬

6. 계층형 구조

   * 다중 계층으로 구성될 수 있으며 보안, 로드 밸런싱, 암호화 계층을 추가해 구조상의 유연성을 둘수 있다.

   * PROXY(클라이언트가 자신을 통해 다른 네트워크 서버에 간접적으로 접속을 도와주는 시스템, 중계기 역할, 대리로 통신을 수행), Gateway(다른 통신망,프로토콜을 사용하는 네트워크 간의 통신을 가능하게 하는장치)이 같은 네트워크 기반의 중간 매체를 사용할 수 있게 함.

     ![image-20200418225058175](REST API.assets/image-20200418225058175.png)  

## 디자인 가이드

> 1. URI는 정보의 자원을 표현해야 한다.
> 2. 자원에 대한 행위는 HTTP Method로 표현

1. 중심 규칙

   * URI는 정보의 자원을 표현해야 한다(Resource 명은 동사보다 명사 사용)

     * REST를 제대로 적용하지 않은 URI(자원을 표현하는데 중점을 두어야함)

     ```
     GET /members/delete/1 ==> DELETE /members/1
     ```

     * 회원정보 가져오는 URI  예시

     ```
     GET /members/1
     ```

     * 회원 추가 예시

     ```
     POST /members/1   ==>GET 메서드는 리소스 생성에 맞지 않음.
     ```

     POST - POST를 통해 해당 URI를 요청하면 리소스를 생성.

     GET - GET을 통해 해당 리소스를 조회, 리소스를 조회하고 해당 document에 대한 자세한 정보를 가져온다.

     PUT - 해당 리소스 수정

     DELETE - 리소슬르 삭제

2. 자원 표현 - 리소스를 표현할 수 있으며 URI에 표현됨.

   ```
   http://restapi.example.com/sports/soccer  => sports = 컬렉선, soccer = document
   ```

   * Collection
     * 문서들의 집합
     * 객체들의 집합
     * sports 처럼 뒤에 복수를 붙여 표기해줌
   * Document
     * 단순한 문서로 이해됨
     * 하나의 객체
     * soccer 처럼 단수로 표기 해줌

3. HTTP 응답 상태코드

   * 200 - 클라이언트의 요청을 전상적으로 수행
   * 201 - 클라이언트가 어떠한리소스 생성을 요청, 해당 리소스가 성곡적으로 생성됨(POST 통한 리소스 생성 작업시)
   * 400 - 클라이언트의 요청이 부적절 할 경우 사용
   * 401 - 클라이언트가 인증되지 않은 상태에서 보호된 리소스를 요청했을떄()
   * 403 - 유저 인증상태와 관계 없이 응답하고 싶지 않은 리소스를 클라이언트가 요청했을떄
   * 405 - 클라이언트가 요청한 리소스에서는 사용 불가능한 method를 이용했을 경우
   * 301 - 클라이언트가 요청한 리소스에 대한 URI가 변경 되었을떄
   * 500 - 서버에 문제가 있을 경우 사용

# GET, POST 차이

## GET

* 클라이언트로부터의 데이터를 이름과 값이 결합된 소스 형태로 전달

* FORM(<FORM>) TAG의 Method속성의 값으로는 GET을 입력

  ```
  <FORM name="form1" action-"test.jsp" method="GET"> 
  ```

* 전송 데이터양 255

* DB에 대한 질의어 데이터와 같은 **요청 자체를 위한 정보를 전송 할떄** 사용

* 데이터가 주소 입력란에 표시되므로 **보안이 유지되지 않음**



## POST

* 클라이언트와 서버간에 인코딩하여 서버로 전송

* 해더를 통해 전송되는 방식

* FORM 태그의 Method속석의 값으로는 POST를 입력

  ```
  <FORM name="form1"action="test.jsp"method="post"
  ```

* 전송 데이터양 제한 없음

* DB에 대한 갱신 작업과 같은 **서버측에서의 정보 갱신 작업을 원할 때** 사용

* GET방식에 비해 상대적으로 처리 속도가 늦어짐

* **클라이언트 측에서 데이터를 인코딩 -> 서버측에서 디코딩**

* GET방식으로 넘길수 있는 데이터의 양은 한계가 있다, 또한 GET방식에는 예약어가 있다.

* 사용자에게 값 들을 쉽게 노출을 시키지 않으려 할때 사용

## 예제

[GET/POST](https://all-record.tistory.com/100)

* GET

```
http://localhost/test.jsp?username=ks&mail=est@hanmail.net
```

```html
<HTML>
    <HEAD>
       	<TITLE>GET 방식 예제</TITLE>
    </HEAD>
    <BODY>
        <FORM name="form1" action-"test.jsp" method="GET">
            name:<INPUT type="text" name="username">
            mail:<INPUT type="text" name="mail">
            <INPUT typE="submit" value="전송">
        </FORM>
    </BODY>
</HTML>
```

* POST

```
http://localhost/test.asp
```

```html
<HTML>
    <HEAD>
       	<TITLE>POST 방식 예제</TITLE>
    </HEAD>
    <BODY>
        <FORM name="form1" action-"test.jsp" method="POST">
            name:<INPUT type="text" name="username">
            <INPUT typE="submit" value="전송">
        </FORM>
    </BODY>
</HTML>
```

```asp
<%@ LANGUAGE=VBScript%>
<HTML>
    <HEAD>
        <TITLE>TEST</TITLE>
        <BODY>
            이름=<%=REQUEST.QUERYSTRING("username")%> : 이르=<%=Request.Form("username")%>
            메일=<%=REQUEST.QUERYSTRING("mail")%> : 메일=<%=Request.Form("mail")%>
        </BODY>
    </HEAD>
</HTML>
```


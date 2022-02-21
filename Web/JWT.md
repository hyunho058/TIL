# JWT

JWT(Json Web Token) : 인증에 필요한 정보들을 Token에 담아 암호화시켜 사용하는것

## JWT구성요소
xxxxx.xxxxx.xxxxx (각각의 구성 요소(Header, Payload, Signature)가 `.`으로 구분되어있는 형태)

**Header**
header에는 보통 토큰의 타입과, 해시 암호하 알고리즘으로구성 되며, 첫번째는 토큰의 유형(JWT), 두 번째는(`HMAC`,`SHA256`,`RSA` )해시 알고리즘을 나타낸다.

```json
{
  "typ": "JWT",
  "alg": "RS512"
}
```

**Payload**
토큰에 담을 클레임 정보를 포함하며, payload에 담는 정보의 한 조각을 `claim`이라고 부르며, key/value 한 쌍으로 이루어져있다.(토큰에는 여러개의 claim을 넣을 수 있다.)
claim의 정보는 registered, public, private 세 종류가 있다.

```json
{
  "sub": "1".
  "iss": "cri"
}
```

표준 스팩 key의 이름은 3글자로 되어있으며, JWT의 토큰에 대한 표현정의

* registered claim: 토큰 정보를 표현하기 위해 정해진 데이터로 선택적으로 작성이 가능
  1. iss: 토큰 발급자(issuer)
  2. sub: 토큰 제목(subject)
  3. aud: 토큰 대상자(audience)
  4. exp: 토큰 만료 시간(expiration), NumericDate 형식으로 되어 있어야 함 ex) 1480849147370
  5. nbf: 토큰 활성 날짜(not before), 이 날이 지나기 전의 토큰은 활성화되지 않음
  6. iat: 토큰 발급 시간(issued at), 토큰 발급 이후의 경과 시간을 알 수 있음
  7. jti: JWT 토큰 식별자(JWT ID), 중복 방지를 위해 사용하며, 일회용 토큰(Access Token) 등에 사용
* public claim: 사용자 정의 claim으로, 공개용 정보를 위해 사용(충돌 방지를 위해 URI 포맷을 사용)
  * `{ "https://jwttest.com": true }`
* private claim: 서버와 클라이언트 사이에 임의로 저장한 정보를 저장
  * `{"token_type": access}`

**Signature**
토큰을 인코딩하거나 유효성 검증을 할때 사용하는 암호화 코드이며, 서명은 header와 payload값을 각각 `BASE64`로 인코딩하고, 인코딩한 값을 비밀 키를 이용해 header에서 정의한 알고리즘으로 해싱하고, 값을 다시 `BASE64`로 인코딩하여 생성

## 예제

**Spring Boot + JWT**

* 의존성 추가

```bash
implementation 'io.jsonwebtoken:jjwt:0.9.1'
```

* JWT 토큰 만들기

```java
public static String createJwtToken(String custSeq) {
    Date now = new Date();

    return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)	//1
            .setIssuer("issuer")	//2
            .setIssuedAt(now)	//3
            .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis()))	//4
            .claim("userId", "userId")	//5
            .signWith(SignatureAlgorithm.HS256, "secret_key")	//6
            .compact();
}
```

1. 헤더의 타입을 지정, jwt를 사용하기 때문에 `Header.JWT_TYPE`를 사용
2. 토큰 발즙자(iss)를 설정
3. 발급 시간(iat)를 설정(Date 타입만 추가 가능)
4. 만료 시간(exp)를 설정
5. 비공개 claim을 설정(key-value)
6. 해싱 알고리즘과 시크릿 키를 설정

* JWT 토큰 파싱

Client가 Server요청 할때 `Authorization` header에 Bearer문자를 붙여 토큰을 보내게 되고 전달받은 토큰을 해석해서 유효한 토큰인지 확인이 가능

```basic
Authorization : Bearer eyJE03900.......
```

```java
public static Claims parseJwtToken(String token) {
		validationAuthorizationHeader(authorizationHeader); // 1
    String token = extractToken(authorizationHeader); // 2 
  
    return Jwts.parser()
            .setSigningKey("secret_key")	//3
            .parseClaimsJws(token)	//4
            .getBody();
}

private void validationAuthorizationHeader(String header) {
    if (header == null || !header.startsWith("Bearer ")) {
      throw new IllegalArgumentException();
    }
}

private String extractToken(String authorizationHeader) {
    return authorizationHeader.substring("Bearer ".length());
}
```

1. 헤더가 `Bearer`로 시작하는지 확인
2. `Bearer`을 제외한 문자열만 반한
3. Token을 생성 했을때 지정한 Secret key를 넣어주어 토큰을 해석
4. token해석
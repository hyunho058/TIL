# Custom Exception

API는 각 오류 상황에 맞는 오류 응답 스펙을 정의하고, JSON으로 데이터를 내려주어야한다. 
최근 프로젝트를 진행하면서 Error가 발생할시 Client에 정황히 어떤 에러가 발생했는지 알려주기 위해 error code, error message등을 만들어 전달하기 위한 목적으로 Custom exception을 만들고, ErrorResponse를 전달하였다.

## 예제

* 예외가 발생할 수 있는 코드

```java
public Notice deleteNotice(Long no) {
  return noticeRepository.findById(no).orElseThrow(NoticeNotfoundException::new);
}
```

위에 코드를 보면 `notice`를 조회하는 코드이며, 값이 없을시에 `NoticeNotfoundException`예외를 발생시키다.

* Exception Class

```java
public class NoticeNotfoundException extends RuntimeException {
    public NoticeNotfoundException() {}
}
```

* ExcetionHandelr

```java
@RestControllerAdvice
public class NoticeErrorHandlingAdvice {
    @ExceptionHandler(NoticeNotfoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse noticeNotFound(RuntimeException e){
        log.error("Notice Not Found", e);
        return new ErrorResponse(ErrorCode.NOTICE_NOT_FOUND);
    }
}
```

**@ExceptionHandler**
스프링에서 API예외 처리 문제를 해결하기 위해 만들어지 어노테이션이며, `ExceptionHandlerExceptionResolver`를 기본으로 제공하고, 기본으로 제공하는 `ExceptionResolver`중에 우선도가 가장 높다
`NoticeNotfoundException`이 발생하면, `noticeNotFound()`가 실행된며, ErrorResponse를 전달한다

**@ResponseStatus**
custom exception을 만들고 처리하면 정상 로직으로 동작하여 Httpt상태코드가 200으로 나가기때문에 HttpStatus.BAD_REQUEST 를 작성하여 예외상태 코드인400으로 변경

**@RestControllerAdvice**
대상으로 지정한 Controller에 `@ExceptionHandler`,`@initBinder`기능을 부여해주는 역할을 한다.
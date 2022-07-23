# Exception

> Checked Exception
>
> Unchecked Exception

![스크린샷 2021-11-12 오후 8 50 51](https://user-images.githubusercontent.com/58923731/141462969-b69d9808-c9d4-422d-8b0e-44e7fe4f7af0.png)

자바에서 에러, 예외 관련된 클래스들의 계층구조는 위와 같다, Throwable클래스를 기준으로 Error, Exception클래스로 나뉘어진다.

## Checked Exception

`RuntimeException` 의 하위 클래스가 아니면서 Exception 클래스의 하위 클래스들이다. Checked Exception의 틍직은 반드시 에러 처리를 해야하는 틍징을 가지고있다.

try catch를 해서 에러를 잡든 throws를 통해서 호출한 메서드로 예외를 던져야 한다.

활용
계좌이체 실패예외
결제시 포인트 부족 예외
로그인 ID,PW불일치 예외

## Unchecked Exception

명시적인 예외 처리를 강제하지 않는 특징이 있으며, chatch로 잡거나 throw로 호출한 메서드로 예외를 던지지 않아도 상관이 없다.

|                | Checked Exception              | Unchecked Exception                                |
| -------------- | ------------------------------ | -------------------------------------------------- |
| 처리여부       | 반드시 예외 처리 해야함        | 예외 처리 하지 않아도됨                            |
| rollback여부   | Rollback 안됨                  | Rollback 진행                                      |
| 대표 Exception | IOExcepion, <br />SQLException | NullPointException, <br />IllegalArgumentException |


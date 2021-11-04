# Singleton

> 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴(객체 인스턴스를 2개 이상 생성하지 못하다록 막아야한다)

## 기본 싱글톤 패턴

```java
public class SingletonA {

    private static SingletonA instance;

    private SingletonA(){}
    //스레드 A,B가 존재할때 getInstance() 에 접근시 A가 if 문을 통과해 instance를 생성하는중
    // B가 if 문에 들어오면 instance가 생성되지 않아 B도 조건에 통과되어 instance 를 생겅하게 된다.
    public static SingletonA getInstance(){

        if (instance == null){
            instance = new SingletonA();
        }
        return instance;
    }
}
```

멀티스레드에서 세이프하지 않음

## sychronized 키워드이용

```java
public class SingletonB {
    private static SingletonB instance;

    private SingletonB(){}

    public static synchronized SingletonB getInstance(){
        if (instance == null){
            instance = new SingletonB();
        }
        return instance;
    }
}
```

sychronized 키워드를 사용해서 멀티스레드 문제 해결

sychronized 키워드를 사용해서 매서드에 한번에 하나에 스레드만 들어오게 한다. - 여러게의 스레드가 들어올 수 없음

getInstance()를 호출할때마다 동기화 처리과정에서 성능에 좋지 않다 - 동기화 가 lock를 사용해서 lock를 가지고 있는 스레드만 접근하고 작없이 마무리되면 다음 스레드가 lock를 소유하고 접근하고를 반복한다.

## 이른 초기화(eager initialization)

```java
//
public class SingletonC {

    private static final SingletonC INSTANCE = new SingletonC();

    private SingletonC(){}

    public static SingletonC getInstance(){
        return INSTANCE;
    }
}
```

에플리케이션 로딩시 미리 생성 - 스레드 세이프한 방법이며, 미리 만들기 때문에 단점이 될수 있다(만들었는대 쓰지 않으면 리소스를 허비하게 된다.)

## Double checked locking

```java
public class SingletonD {

    private static volatile SingletonD instance;

    private SingletonD(){}

    public static SingletonD getInstance(){
        if (instance == null){
            synchronized (SingletonD.class){
                if (instance == null){
                    instance = new SingletonD();
                }
            }
        }
        return instance;
    }
}
```

JDK 1.5 이상부터 사용 가능하다

getInstance()를 호출할때마다 매번 synchronized를 실행하지 않는다는 장접이 있다.

멀티스레드가 빈번하게 일어나는 경우 if문안에 여러 스레드가 들어오게 되는대 이 경우 동시에 들어온 스레드들만 synchronized를 실해앟고 이후에 스레드들은 synchronized를 실행하지 않고 해당 매서드를 사용할 수 있다.

## static inner class

```java
public class SingletonE {
    private SingletonE(){}

    private static class SingletonHolder{
        private static final SingletonE INSTANCE = new SingletonE();
    }

    public static SingletonE getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
```

멀티스레드 환경에서도 안전하고 getInstance(0호출시 INSTANCE를 만들기 때문에 lazy로딩이 가능하다.



# 웹 어플리케이션과 싱글톤

## 싱글톤 적용X

```java
@Test
@DisplayName("스프링 없는 순수한  DI 컨테이너")
void pureContainer() {
    AppConfig appConfig = new AppConfig();
    //1. 조회: 호출할 때 마다 객체를 생성
    MemberService memberService1 = appConfig.memberService();

    //2. 조회: 호출할 때 마다 객체를 생성
    MemberService memberService2 = appConfig.memberService();

    //참조값이 다른 것을 확인
    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    Assertions.assertThat(memberService1).isNotSameAs(memberService2);
}
```

```
결과
memberService1 = hh.core.member.MemberServiceImpl@6a78afa0
memberService2 = hh.core.member.MemberServiceImpl@2f4948e4
```

## 싱글톤 패턴을 적용

* 싱글톤 패턴의 단점
  - 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다
  - 의존관계상 클라이언트가 구체 클래스에 의존한다 => **DIP를 위반**
  - 클라이언트가 구체 클래스에 의존해서 OCP원칙을 위반할 가능성이 높다.
  - 테스트가 어렵다
  - 내부 속성을 변경하거나 초기화 하기 어렵다.
  - private 생성자로 자식 클래스를 만들기 어렵다.
  - 유연성이 떨어진다
  - 안티패턴으로 불리기도 한다.

```java
@Test
@DisplayName("싱글톤 패턴을 적용한 객체 사용")
void singletonServiceTest(){
    SingletonService singletonService1 = SingletonService.getInstance();
    SingletonService singletonService2 = SingletonService.getInstance();

    System.out.println("singletonService1 = " + singletonService1);
    System.out.println("singletonService2 = " + singletonService2);

    Assertions.assertThat(singletonService1).isSameAs(singletonService2);
}
```

![스크린샷 2021-11-04 오후 5 01 18](https://user-images.githubusercontent.com/58923731/140278078-5055a111-2799-4012-b6a7-ec4fe833bc2b.png)

## 스프링 컨테이너의 싱글톤

```java

@Test
@DisplayName("스프링 컨테이너와 싱글톤")
void springContainer(){
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberService memberService1 = ac.getBean("memberService", MemberService.class);
    MemberService memberService2 = ac.getBean("memberService", MemberService.class);

    //참조값이 같은것을 확인
    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    Assertions.assertThat(memberService1).isSameAs(memberService2);
}
```

![스크린샷 2021-11-04 오후 5 03 11](https://user-images.githubusercontent.com/58923731/140278200-b0797d18-a21e-477d-8177-440c8250e274.png)

싱글톤 컨테이너 - 싱글톤 패턴의 문제점을 해결하면서, 객체 인스턴스를 싱글톤으로 관리한다

* 스프링컨테이너는 싱글톤 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
* 스프링 컨테이너는 싱글톤 컨테이너 역할을 한다. 이렇게 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라 한다
* 스프링 컨테이너의 이런 기능 덕분에 싱글턴 패턴의 모든 단점을 해결하면서 객체를 싱글톤으로 유지할 수 있다.
  * 싱글톤 패턴을 위한 지저분한 코드가 들어가지 않아도 된다.
  * DIP, OCP, 테스트, private 생성자로 부터 자유롭게 싱글톤을 사용할 수 있다.
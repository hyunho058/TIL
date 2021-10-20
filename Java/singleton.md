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
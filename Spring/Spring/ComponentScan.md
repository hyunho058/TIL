# ComponentScan

> 설정 정보 없이 자동으로 스프링 빈을 등록

* 이전까지 스프링 빈을 등록할 때 @Bean 이나 XML의 <bean> 을 통해서 설정정보에 직접 등록할 빈을 나열했다

```xml
<bean id="orderService" class="hello.core.order.OrderServiceImpl">
  <constructor-arg name="memberRepository" ref="memberRepository" />
  <constructor-arg name="discountPolicy" ref="discountPolicy" />
</bean>

<bean id="discountPolicy" class="hello.core.discount.RateDiscountPolicy" />
<bean id="memberRepository" class="hello.core.member.MemoryMemberRepository" />
```

```java
@Bean
public OrderService orderService() {
	return new OrderServiceImpl(
		memberRepository(),
		discountPolicy());
}
```

* 코드가 길어지면 하나씩 등록하기도 번거롭고 실수가 많아진다.
* Spring에서 설정정보가 없어도 스프링 빈을 등록하는 `@ComponentScan` 이라는 기능을 제공
* 의존고나계도 자동 주입하는 `@Autowired`기능도 같이 제공



## @ComponentScan

* ComponenetScan을 사용하면 `@Component`어노테이션이 붙은 클래스를 스캔해 스프링 빈으로 등록

* `@Configuration` 이 붙어 있으면 컴포넌트 스캔의 대상이 된다

  * `@Configuration` 에 `@Componenet`어노테이션이 붙어 있기 때문이다.

  ```java
  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @Component
  public @interface Configuration {
    ...
  }

* `@Autowired` 를 사용해서 생성자에 여러 의존관계도 한번에 주입받을 수 있다.

 ```java
 @Component
 public class MemberServiceImpl implements MemberService{
 
     private final MemberRepository memberRepository;//추상화에만 의존, DIP를 지키킨다.
     
     @Autowired //의존관계 자동 주입 (ac.getBean(MemberRepository.class)와 같은 동작을 한다.)
     public MemberServiceImpl(MemberRepository memberRepository) {
         this.memberRepository = memberRepository;
     }
 }
 ```

* Test

```java
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {}
```

```java
@Test
@DisplayName("basicScan")
void basicScan(){
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    MemberService memberService = ac.getBean(MemberService.class);
    Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
}
```

![스크린샷 2021-09-11 오전 11.01.21](ComponentScan.assets/스크린샷 2021-09-11 오전 11.01.21.png)



### ComponentScan 속성

* `@ComponentScan(basePackage = "hh.core")`
  * 탐색할 패키지의 시작 위치를 지정(이 패키지를 포함해서 하위 패키지를 모두 탐색
  * `basePackage = {"hh.core", "hh.test"}` 이렇게 여러 싲가 위치를 지정할 수도 있다.
* `@ComponentScan(AutoAppConfig.class)
  * 지정한 클래스의 패키지를 탐색 시작 위치로 지정
* 지정하지 않으면(default) `@ComponentScan`이 붙은 설정 정보 클래스의 패키지가 시작위치가 된다.
  * 아래 코드를 보면 `@ComponentScan` 를 붙인 클래스가 속한 패키지인 `package hh.core`가 된다 

```java
package hh.core;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
```

* 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는것을 권장

  * 최근 **스프링 부트도 이 방법을 기본으로 제공**한다.
  * 스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 `@SpringBootApplication` 를 이 프로젝트 시작 루트 위치에 두는 것이 관례이다. (그리고 이 설정안에 바로 @ComponentScan 이 들어있다!)

  ```java
  @ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
  		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
  public @interface SpringBootApplication {
    ...
  }
  ```

### Component scan의 기본 대상

* 컴포넌트 스캔은 `@Component` 뿐만 아니라 다음과 내용도 추가로 대상에 포함
  * `@Component` : 컴포넌트 스캔에서 사용
  * `@Controlller` : 스프링 MVC 컨트롤러에서 사용
  * `@Service` : 스프링 비즈니스 로직에서 사용
  * `@Repository` : 스프링 데이터 접근 계층에서 사용
  * `@Configuration` : 스프링 설정 정보에서 사용

```java
@Component
public @interface Controller {
}
@Component
public @interface Service {
}
@Component
public @interface Configuration {
}
```

* 참고 - 애노테이션에는 상속관계라는 것이 없다. 
  * 애노테이션이 특정 애노테이션을 들고 있는 것을 인식할 수 있는 것은 자바 언어가 지원하는 기능은 아니고, 스프링이 지원하는 기능이다.
* 컴포넌트 스캔의 용도 뿐만 아니라 다음 어노테이션이 있으면 스프링 부트가 기능을 수행
  * `@Controller` : 스프링 MVC 컨트롤러로 인식
  * `@Repository` : 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다. 
  * `@Configuration` : 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다.
  * `@Service` : @Service 는 특별한 처리를 하지 않는다.
    * 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나 라고 비즈니스 계층을 인식하는데 도움이 된다.

### 필터

*  `includeFilters`: 컴포넌트 스캔 대상을 추가로 지정
* `excludeFilters`: 컴포넌트 스캔에서 제외할 대상을 지정

```java
public class ComponentFilterAppConfigTest {

    @Test
    @DisplayName("filterScan")
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        assertThrows(NoSuchBeanDefinitionException.class,
                ()->ac.getBean("beanB", BeanB.class)
        );
    }


    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }
}
```

* FilterType
  * ANNOTATION: 기본값, 애노테이션을 인식해서 동작한다.
    *  ex) org.example.SomeAnnotation
  * ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다. 
    * ex) org.example.SomeClass
  * ASPECTJ: AspectJ 패턴 사용
    * ex) org.example..*Service+*
  * *REGEX: 정규 표현식*
    * *ex) org\.example\.Default.*
  * CUSTOM: TypeFilter 이라는 인터페이스를 구현해서 처리 
    * ex) org.example.MyTypeFilter

### 중복 등록과 충돌

* 컴포넌트 스캔에 의해 자동으로 스프링 빈이 등록되는데, 그 이름이 같은 경우 스프링 오류를 발생시킨다
  * `ConflictingBeanDefinitionException`예외 발생
* 수봉 빈 등록과 자동빈 등록에서 빈 이름이 중복될 경우
  * 수동 빈 등록이 우선권을 가진다.(수동 빈이 자동 빈을 오버라이딩 한다.)

```
Overriding bean definition for bean 'memoryMemberRepository' with a different definition: replacing
```

* 최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록과 빈 등옥이 충돌나면 오류가 발생하도록 기본 값을 바꾸었다.
* 수동빈 등록, 자동 빈 등록 오류시 스프링 부트 에러

```
Consider renaming one of the beans or enabling overriding by setting
spring.main.allow-bean-definition-overriding=true
```



## Component scan동작

* `@ComponentSca` 은`@Component`가 붙은 모든 클래스를 스프링빈으로 등록한다. 이때 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자로 사용
  * MemberServiceImpl 클래스 -> memberServiceImpl로 빈 객체가 등록됨
* 빈 일므을 직접 지정하고 싶으면 `@Component("memberService2")` 를 사용해서 이름을 부여한다.

## Autowired동작

* 생성자 `@Autowired`를 지정한 스프링컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다
* 타입이 같은 빈을 찾아서 주입 ( = ac.getBean(MemberRepository.class))

## @Autowired






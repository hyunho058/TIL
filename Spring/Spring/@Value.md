# @Value
> 스프링에서  Properties를 읽는 방법  

## YAML
* 가독성이 좋고 문법이 이해하기 수월
* Property설정값의 깊이에 따라 들여쓰기를 해서 구조를 훨씬 쉽게 파알할 수 있다.
* Spring boot starter에 SnakeYAML라이브러리가 기본적으로 내장되어 있어 Spring boot에서는 별도의 설정없이 사용 가능

## @Value
* YAML파일에 설정한 key 갑을 `@Value`의 프로퍼티 값으로 주면 해당 값이 필드에 할당
* 키를 정확히 입력해야 하며, 없을 경우 예외처리가 필요
* 주로 단일 필드 값을 가져오는데 사용

* application.yml

```yaml
property:
  test:
    name: property depth test

propertyTestValue: test
propertyTestList: a,b,c
```
* test code
```java
@SpringBootTest
public class ValueTest {

    @Value("${property.test.name}")
    private String propertyTestName;

    @Value("${propertyTestValue}")
    private String propertyTest;

    @Value("${noKey:default value}")
    private String defaultValue;

    @Value("${propertyTestList}")
    private String[] propertyTestArray;

    @Value("#{'${propertyTestList}'.split(',')}")
    private List<String> propertyTestList;

    @Test
    @DisplayName("VALUE_ANNOTATION_TEST")
    void VALUE_ANNOTATION_TEST(){
        assertThat(propertyTestName).isEqualTo("property depth test");
        assertThat(propertyTest).isEqualTo("test");
        assertThat(defaultValue).isEqualTo("default value");
        assertThat(propertyTestArray[0]).isEqualTo("a");
        assertThat(propertyTestList.get(0)).isEqualTo("a");
    }
}
```

* @Value(“${property.test.name}”)
	* 깊이가 있는  property.test.name의 값을 가져온다
		* 즉, *property depth test* 을 가져온다
* @Value(“${propertyTestValue}”)
	* **propertyTestValue** 라는 이름의 단일값을 가져온다
		* 즉 *test* 를 가져온다
* @Value(“${noKey:default value}”)
	* YAML 파일에 key값이 없으면 default 값이 매핑되도록 설정
		* 즉 키가 없기 때문에 *default value 를 가져온다*
* @Value(“${propertyTestList}”)
	* 여러 값을 나열할 때 배열형으로 매핑한다
		* 즉 *a,b,c*가 각각 배열에 들어간다
* @Value(“#{‘$’propertyTestList}’.’plit(‘,’)’”)
	* SpEL을 이용해서 메서드를 불러온다  `,` 을 기준으로 쪼갬
		* 즉 *a,b,c*가 각각 List에 들어간다


## 참고
Spring boot interceptor에서 `@Value`로 프로퍼티 값을 가져와 유효한 접근인지 확인을 하려고 하였는데 값이 `null`로  되어 찾아보니 Interceptor를  Spring been 으로 등록해주면 해당 현상을 해결 할수 있다고 하여, Interceptor를 Been으로 등록하여 문제를 해결.

```java
@Slf4j
public class Interceptor implements HandlerInterceptor {
    
    @Value("${API_AUTHORIZATION}")
    private String testAuthorization;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            log.info("Authorization is null");
            return false;
        }
        if (! testAuthorization.equals(authorization)) {
            log.info("Authorization fail = {}", authorization);
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
```
```java
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public Interceptor interceptor(){
        return new Interceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor())
                .addPathPatterns(“/**”);
    }
}
```
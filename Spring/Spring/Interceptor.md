# Interceptor

Interceptor는 Client에서 Server로 요청(Request)객체를  Controller에 도달하기전에 가로체 원하는 작업을 수행후 Handler로 보낼수 있도록 해주는모듈

![image](https://user-images.githubusercontent.com/58923731/145006875-62997f89-16dd-445c-a53a-c27fec5c65c6.png)

Interceptor와 filter는 Servlet 전후에서 실헹디며 AOP는 Spring의 메서드 실행 전후 Proxy형태로 실행이된다 
실행 순서는 위사진처럼 Filter > Interceptor > AOP순으로 진행

## 동작 순서

`DispatcherServlet`은 `HandlerMapping`에게 `Request`를 수행할 `Handler`를 찾도록 요청을 보내며 `HandlerExecutionChain`이 동작(`HandlerExecutionChain`은 하나 이상의 `HandlerInterceptor`를 거처 `Controller`가 실행되도록 구성되어 있다.) `HandlerExecutionChain`를 거처 `Request`에 요청이 오면 원하는 로직을 추가하여 해당 로직을 수행한 후 `Controller`로 요청객체를 전달.

## 에제 코드

```java
@Slf4j
public class Interceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = "";
        authorization = request.getHeader("Authorization");
        log.info("Authorization = {}", authorization);
        if (!authorization.equals(AUTHORIZATION)) {
            return false;	//return값이 true면 진행, false면 stop
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
```

`preHandler` - 컨트롤러에 도착하기전에 동작하는 메소드로 return값이 true면 진행, false면 stop
`postHandler` - 컨트롤러에 도착하여 view가 랜더링되기 전에 동작
`afterCompletion` - view가 정삭적으로 랜더링된 후에 마지막에 실행

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
                .addPathPatterns("/api/**");
    }
}
```

addIntercepotrs를 통해 사용할 Interceptor를 등록하고. 패턴을 등록.
`addPathPatterns` -  해당 메소드는 동작해야할 url패턴을 설정
`excludePathPatterns` - 해당 메소드는 적용한 인터셉터에서 제외할 url패턴을 설정




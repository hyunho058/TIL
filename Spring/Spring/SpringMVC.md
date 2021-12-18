# Spring MVC

## Web Server와 WAS

* Web sever는 정적 리소스(파일)을 담당허며, WAS는 애플리케이션 로직을 담당한다
  * 웹 서버도 프로그램을 실해앟는 기능을 포함하기도 하며, 엡 애플리케이션서버도 웹 서버의 기능을 제공한다.(WAS는 애플리케이션 코드를 실행하는데 더 특화)
* 웹 시스템 구성 1(WAS와 DB구성)
  * WAS는 정적 리소스, 애플리케이션 로직 모두 제공 가능
  * WAS하나로 구성하게 되면 WAS가 많은 역할을 담당해 서버 과부하 우려와, 정적 리소스 때문에 애플리케이션 로직수행이 어려울 수 있다.
* 웹 시스템 구성 2(WEB, WAS, DB로 구성)
  * 정적 리소스는 웹 서버가 처리
  * **웹 서버는 애프리케이션 로직같은 동적인 처리가 필요하면 WAS에 요청으 뤼임** **(WAS는 중요한 애플리케이션 로직 처리 전담)**
  * 효율적인 리소스 관리
    * 정적 리소스가 많이 사용되면 web서버 증설
    * 애플리캐이션 리소스가 많이들면 was증설

## 서블릿

* HTTP요청, 응답 흐럼
  * WAS는 Request, Response 객체를 새로 만들어서 서블릿 객체 호출
  * 개발자는 Request 객체에서 HTTP요청 정보를 편리하게 꺼내서 사용
  * 개발자는 Response객체에 HTTP응답 정보를 편리하게 입력
  * WAS는 Response객체에 담겨있는 내용으로 HTTP 응답 정보를 생성
* 서블릿 컨테이너
  * 통캣처럼 서블릿을 지원하는 WAS를 서블릿 컨테이너라고 함.
  * 서블릿을 생성, 호출, 관리
  * 서블릿 객체는 싱글토으로 관리
    * 고객의 요청이 올때 마다 계속 객체를 생성하는것은 비효율
    * 모든 고객 요청은 동일한 서블릿 객체 인스턴스에 접근
    * **공유 번수 사용 주의**
  * 동시 요청을 위한 멀티 쓰레드 처리 지원

## FrontController

* 프론트 컨트롤러 서블릿 하나로 클라이언트의 요청을 받음.
* 프론트 컨트롤러가 요청에 맞는 컨트롤러를 찾아서 호출
* 공통 처리 기능
* 프론트 컨트롤러를 제외한 나머지 컨트롤러는 서블릿을 사용하지 않아도 된다.

## SpirngMVC 구조

<img width="803" alt="스크린샷 2021-12-18 오후 7 07 54" src="https://user-images.githubusercontent.com/58923731/146637262-0fc319ed-30c0-4568-bfec-ce67ee678b20.png"> 

### 동적 순서

1. 핸들러 조회: 핸들러 매핑을 통해 요청URL에 매핑된 핸들러(컨트롤러)조회
2. 핸들러 어댑터 조회: 핸들러를 실행할 수 있는 핸들러 어댑터를 조회
3. 핸들러 어댑터 실행: 핸들러 어댑터를 실행
4. 핸들러 실행: 핸들러 어뱁터가 실제 핸들러를 실행
5. ModelAndVIew반환: 핸들러 어댑터는 핸들러가 반환하는 정보를 ModelAndVIew로 현환해서 반환
6. viewResolver호출: view resolver를 찾고 실행
7. view반환: view resolver는 뷰의 논리 이름을 물리 이름으로 바꾸고, 렌더링 역할을 담당하는 뷰 객체를 반환
8. 뷰 렌더링: 뷰를 통해서 뷰를 렌더링

### DispacherServlet 서블릿 등록

* `DispacherServlet`도 부모 클래스에서 `HttpServlet`을 상속 받아서 사용하고, 서블릿으로 동작한다.
  * DIspacherServlet > FrameworkServlet > HttpServletBean > HttpServlet
* 스프링 부트는`DispacherServlet`을 서릿으로 자동으로 등록하면서 모든경로에대해서 매핑
  * 더 자세한 경로가 우선순위가 높다. 그래서 기존에 등록한 서블릿도 함께 동작

### DispacherServlet요청 흐름

* 서블릿이 호출되면 `HttpServlet`이 제공하는 `service()`호출
* 스프링MVC 는`DispatcherServlet`의 부모인 `FrameworkServlet`에서 `service()`를 오버라이드 해두었다.
* `FrameworkServlet.service()`를 시작으로 여러 메서드가 호출되면서 `DispacherSErvlet.doDispatch()`가 호출

```java
protected void doDispatch(HttpServletRequest request, HttpServletResponse
        response) throws Exception {
    HttpServletRequest processedRequest = request;
    HandlerExecutionChain mappedHandler = null;
    ModelAndView mv = null;
    // 1. 핸들러 조회
    mappedHandler = getHandler(processedRequest);
    if (mappedHandler == null) {
        noHandlerFound(processedRequest, response);
        return;
    }
    //2.핸들러 어댑터 조회-핸들러를 처리할 수 있는 어댑터
    HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());
    // 3. 핸들러 어댑터 실행 -> 4. 핸들러 어댑터를 통해 핸들러 실행 -> 5. ModelAndView 반환 mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
    processDispatchResult(processedRequest, response, mappedHandler, mv,
            dispatchException);
}

private void processDispatchResult(HttpServletRequest request,
                                   HttpServletResponse response, HandlerExecutionChain mappedHandler, ModelAndView
                                           mv, Exception exception) throws Exception {
    // 뷰 렌더링 호출
    render(mv, request, response);
}

protected void render(ModelAndView mv, HttpServletRequest request,
                      HttpServletResponse response) throws Exception {
    View view;
    String viewName = mv.getViewName(); //6. 뷰 리졸버를 통해서 뷰 찾기,7.View 반환
    view = resolveViewName(viewName, mv.getModelInternal(), locale, request);
    // 8. 뷰 렌더링
    view.render(mv.getModelInternal(), request, response);
}
```


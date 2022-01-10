#  Validation
스프링 부트가  `spring-boot-starter-validation`라이브러리를 넣으면 자동으로 Bean Validator를 인지하고 스프링에 통합하며, `LocalValidatorFactoryBean`을 글러벌 Validator로 등록한다. 
이 Validator는. `@NotNull`과 같은 어노테이션을 보고 검증을 수행한며, `@Valid`, `@Validated`를 적용하여 사용하고 검증 오류가 발생하면 `FieldError`, `ObjectError`를 생성해서 `BindingResult`에 담아준다

## @Valid와 @Validated
검증시 둘다 사용 가능하지먄 `@Valid`를 사용하려면 `build.gradle`에 의존관계를 추가해줘야한다. 반면 `@Validated`는 스프링 전용 검증 어노테이션이며, `groups` 라는 기능을 포함하고 있다.


## 검증 순서
1. 필드 타입 변환시도
	1. 성공하면 다음으




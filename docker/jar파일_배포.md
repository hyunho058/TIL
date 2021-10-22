# Docker를 이용한 jar파일 배포

## Build

### jar이름 변경

 jar의 기본 이름은 `프로젝트명-0.010-SNAPSHOT`이다.

이름은 바꾸려면 `build.gradle`에서 아래처럼 설정을해주면 된다.

```groovy
bootJar{
    archivesBaseName = 'semoapi'
    archiveFileName = 'semoapi.jar'
    archiveVersion = "0.0.1"
}
```

* archivesBaseName : 프로젝트 이름
* archiveFileName : jar로 변환할 이름

### build

![스크린샷 2021-10-22 오후 11 53 51](https://user-images.githubusercontent.com/58923731/138476394-db744624-2724-4f3a-a4ef-71a931aef7c3.png) 

* Gradle - 프로젝트 - Tasks - build - (clren, build, jar)
  * clren, build, jar를 순서대로 실행시켜준다.
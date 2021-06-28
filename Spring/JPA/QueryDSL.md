# QueryDSL



## 설정

* gradle 추가

```groovy
plugins {
	  //querydsl
    id "com.ewerk.gradle.plugins.querydsl" version "1.0.10" 
}

dependencies {
    //querydsl
    implementation 'com.querydsl:querydsl-jpa'
}

//-----------querydsl-----------//
def querydslDir = "$buildDir/generated/querydsl"
querydsl {
    jpa = true
    querydslSourcesDir = querydslDir
}
sourceSets {
    main.java.srcDir querydslDir
}
configurations {
    querydsl.extendsFrom compileClasspath
}
compileQuerydsl {
    options.annotationProcessorPath = configurations.querydsl
}
//--------------------------------//
```


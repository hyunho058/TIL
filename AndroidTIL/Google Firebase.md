# Google Firebase



## Android Firebase 연동

* build.gradle(Project : appname)

```java
buildscript {
  repositories {
    // Check that you have the following line (if not, add it):
    google()  // Google's Maven repository
  }
  dependencies {
    ...
    // Add this line
    classpath 'com.google.gms:google-services:4.3.3'
  }
}

allprojects {
  ...
  repositories {
    // Check that you have the following line (if not, add it):
    google()  // Google's Maven repository
    ...
  }
}
```

* build.gradle(Module.app)

```java
apply plugin: 'com.android.application'
// Add this line
apply plugin: 'com.google.gms.google-services'

dependencies {
  // add the Firebase SDK for Google Analytics
  implementation 'com.google.firebase:firebase-analytics:17.2.2'
  // add SDKs for any other desired Firebase products
  // https://firebase.google.com/docs/android/setup#available-libraries
}
```

### Reference

[Android Studio Firebase 연동](https://lakue.tistory.com/37?category=853542)



## Firebase 호스팅



### Reference

[FIrebase](https://firebase.google.com/docs/hosting/quickstart?hl=ko)
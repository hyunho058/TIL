# Kotlin_extensions

> * ButterKnife.bind()와 findVIewById 가 불필요 하다.
> * android:id="@+id/text" 의 text를 통해 바로 접근 가능하다.

## 의존성

* gradle.build

```groovy
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'

dependencies {
    compile "org.jetbrains.kotlin:kotlin-stdlib:$rootProject.ext.kotlin_version"
}
```



## Basic Code

```kotlin
import kotlinx.android.synthetic.main.activity_image_sample.*
import kotlinx.android.synthetic.main.fragment_image_sample.*

class ImageFragment : Fragment(), ImageContract.View {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        with (activity) {
            root_container.visibility = View.GONE
        }
        recycler_image.visibility = View.GONE
    }
}
```

* `import`에는 `kotlinx.android.synthetic.main`까지는 공통으로 적용하고, 이후에는 `layout`의 이름을 사용합니다.

```kotlin
with (activity) {
  root_container.visibility = View.GONE
}

activity?.apply {
    root_container.visibility = View.GONE
}
```

* 커스텀 View나 ViewHolder에서는 아래와 같은 코드를 함께 사용 해야한다

```kotlin
with (itemView) {
  root_container.visibility = View.GONE
}

// Null 발생을 대처하기 위해서 아래와 같이 사용한다
itemView?.run {
    root_container.visibility = View.GONE
}
```





## Reference

[https://thdev.tech/kotlin/2017/03/05/AndroidStudio-Kotlin-Extensions/](https://thdev.tech/kotlin/2017/03/05/AndroidStudio-Kotlin-Extensions/)
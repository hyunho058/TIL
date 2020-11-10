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
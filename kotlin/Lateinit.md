# Lateinit

> 초기화를 하지 않으면 해당 메소드에 접근하는 것은 불가능하다 => 꼭 초기화를 해야 한다.

## lateinit 조건

> lateinit은 꼭 변수를 부르기 전에 초기화 시켜야 하는데 여러 조건을 가지고 있다

* var(mutable)에서만 사용이 가능하다.
* var 이기 떄문에 언제든 초기화를 변경할 수 있다.
* null 을 통한 초기화를 할 수 없다.
* 초기화를 하기 전에는 변수에 접근할 수 없다.
  * lateinit property subject has not been initalized
* 변수에 대한 setter/getter properties 저의가 불가능하다.
* lateinit 은 모든 변수가 가능한 건 아니고, primitive type에서는 활용이 불가능하다(Int, Double)



## lateinit 초기화 확인하기

* 실제 값을 사용할 떄  lateinit을 한번 체크해줌으로써 안전하게 접근할 수 있다.
* **::** 을 통해서만 접근이 가능한 isInitialized을 사용하여 체크할 수 있다.

```kotlin
class SampleActivity {

	private lateinit var sampleAdapter: SampleAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sample_main)

		// 부르는 시점 초기화
    sampleAdapter = SampleAdapter(ImageLoaderAdapterViewModel(this@SampleMainActivity, 3))

		if (::sampleAdapter.isInitialized) {
			sampleAdapter.addItem()
			sampleAdapter.notifyDataSetChanged()
		}
	}
}
```

* isInitialized는 Higher - Order function과 Kotlin extensions을 통해 구현되어 있다.

```kotlin
@SinceKotlin("1.2")
@InlineOnly
inline val @receiver:AccessibleLateinitPropertyLiteral KProperty0<*>.isInitialized: Boolean
    get() = throw NotImplementedError("Implementation is intrinsic")

```

## lateinit  디컴파일

* 디컴파일한 코드를 통해 lateinit이 어떤 식으로 동작하는지 확인이 가능한데, this.sampleAdapter == null 의 코드를 확인할 수 있다.

```kotlin
@NotNull
public SampleAdapter sampleAdapter;

@NotNull
public final SampleAdapter getSampleAdapter() {
	 SampleAdapter var10000 = this.sampleAdapter;
	 if(this.sampleAdapter == null) {
			Intrinsics.throwUninitializedPropertyAccessException("sampleAdapter");
	 }

	 return var10000;
}

protected void onCreate(@Nullable Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	 this.setContentView(2131361820);
	 this.sampleAdapter = new SampleAdapter(new ImageLoaderAdapterViewModel((Context)this, 3));
	 SampleAdapter var10000 = this.sampleAdapter;
	 if(this.sampleAdapter == null) {
			Intrinsics.throwUninitializedPropertyAccessException("sampleAdapter");
	 }
}
```





## Reference

[https://thdev.tech/kotlin/2018/03/25/Kotlin-lateinit-lazy/](https://thdev.tech/kotlin/2018/03/25/Kotlin-lateinit-lazy/)




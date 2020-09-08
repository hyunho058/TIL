# Fragment

> 하나의 화면을 나누어 보여주거나 각각의 부분화면 단위로 바꾸어 보여주고 싶을때 사용

* 액티비티를 분할하여 화면의 한 부분을 정의한다.
* 액티비티와 같이 레이아웃, 동작 처리, 생명주기를 가지는 독립적인 모듈이다.
* 다른 액티비티에서도 사용 할 수 있어 재사용성이 뛰어나다.
* 액티비티 내에서 실행 중에 추가, 제거가 가능하다.



## Fragment 생명주기

* 반드시 하나의 액티비티 안에 소속 되어야 한다
* 독립적인 생명 주기를 가진다

![image-20200301113812720](Fragment.assets/image-20200301113812720.png)

> 1. 프래그먼트 추가 - Activity 에 프레그먼트가 부착되고 초기화
> 2. 프래그먼트 실행 - 정해진 동작을 수행
> 3. 프래그먼트 파괴 - 역할을 다하고 종료

* onAttach() - Fragment가 Activity 에 최초로 연결될 떄호출
* onCreate() - Fragment를 초기화하는 메소드
* onCreateView() - Layout inflator 작업이 진행
* onActivityCreated() - Activity의 onCreate() UI 작업이 마무리된 후, Fragment가 Activity에 완벽히연결된 상태.
* onStrat() - 부모 Activity가 화면에 보이면 호출
* onResume() - 부모 Activity가 유저 input을 받을 준비가 됨
* onPause() - 부모 Activity가 화면에 보이지만 포커스를 잃게회면 호출
* onStop() - 부모 Activity가 더이상 화면에 보이지 않을 때 호출
* onDestroyVIew() - onCreateView()에서 호출된 View들이 Activity에서 제거되면서 호출된다. 일반적으로 View리소스를 해제하는데 사용한다.
* onDestroy() - onCreate()에 대응되는 함수, Fragment가 더 이상 유효하지 않을 때 호출, 일반적으로 Fragment 자체 리소스를 해제하는 용도로 사용된다.
* onDetach() - Fragment가 더이상 Activity에 연결되어 있지 않은 상황에서 호출, 일반적으로 부모 Activity에서 Fragment의 참조를 가지고 있다면 null로 바꿔주는 작업을 수행

## 기본 생성 Code

* Activity
  * replace(a,b)
    * parameter'a' - activity내에서 Fragment를 삽일할 Layout ID
    * parameter'b' - 삽입할 Fragment

```java
fragmentManager = getSupportFragmentManager(); //Fragment Manager선언
View.OnClickListener mclickListener = new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         fragmentTransaction = fragmentManager.beginTransaction();//FragmentTransaction Start
         switch(v.getId()){
             case R.id.btn_fragmentA:
                 fragmentA = new fragmentA();  //Fragment 생성
                 transaction.replace(R.id.frameLayout, fragmentA).commitAllowingStateLoss();         
                 break;
         }
     }
 }
```

* Fragment

```java
public class FragmentA extends Fragment {
    View view;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_a,container,false);
        context=container.getContext();
        return  view;
    }
}
```

## Fragment Transaction

* 트랜잭션이란 어떤 대상에 대해 추가,제거,변경 등의 작업들이 발생하는것을 묶어서 예기한다
* 프래그먼트 매니저는 액티비티가 사용자의 입력 이벤트에 따라 프래그먼트를 추가 및 삭제 그리고 교체 등의 작업들을 수행 할 수 있게 해준다.
* 트랜잭션의 상태를 프래그먼트 백스택(Fragment Backstack) 을 통해 저장할 수 있다.

## Fragment BackStack

* 현재 실행하려는 트랜잭션의 상태를 기억해두기 위해 만들어진 개념.

  * Back key를 통해 Fragment를 이전 상태로 되돌릴 수 있다.

  ```java
  // manager 와 transaction 초기화
  FragmentManager manager = getSupportFragmentManager();
  FragmentTransaction transaction = manager.beginTransaction();
  // 전달받은 fragment 를 replace
  transaction.replace(R.id.fragment_container, fragment);
  // 해당 transaction 을 Back Stack 에 저장
  transaction.addToBackStack(null);
  // transaction 마무리
  transaction.commit();
  
  ```

  

## 쥐의 사항

* clickable = true 로 설정해주지 않으면 Fragment Layout 뒤에 쌓여있는 화면에 click 이벤트가 눌리게된다.

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#00eeff"
    android:clickable="true">  //해당 
```



## Reference

[Fragment 참고](https://tedrepository.tistory.com/5)

[Fragment 기초](https://tedrepository.tistory.com/6)

{DataShared}(https://itpangpang.xyz/309)
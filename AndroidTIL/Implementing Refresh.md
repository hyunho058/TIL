# Implementing Refresh

* build.gradle

```java
dependencies {
    //SwipeRefreshLayout
    implementation 'com.android.support:support-v4:29.1.0'
```

* XML
  * 새로고침을 적용할 View 를 SwipeRefreshLayout로 감싼다

```xml
 <androidx.swiperefreshlayout.widget.SwipeRefreshLayout
        android:id="@+id/swipeRefresh"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toTopOf="@id/tabLayout"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent">
        <FrameLayout
            android:id="@+id/frame"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
        </FrameLayout>
</androidx.swiperefreshlayout.widget.SwipeRefreshLayout>
```

* Activity
  * 새로고침 적용할 Code를 onRefresh() 안에 작성
  * swipeRefresh.setRefreshing(false) 
    * false로 설정해야 새로고침 아이콘이 종료된다

```java
swipeRefresh = findViewById(R.id.swipeRefresh);
swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    @Override
    public void onRefresh() {
        Log.v(TAG,"onRefresh()");
        startService(serviceIntent);
        swipeRefresh.setRefreshing(false); //false 로 설정해야 새로고침 아이콘이 종료된다
    }
});
```

### Reference

[Refresh](https://guides.codepath.com/android/Implementing-Pull-to-Refresh-Guide)


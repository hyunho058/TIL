# TabLayout

* 종속성

```
dependencies {
	mplementation 'com.android.support:design:29.1.0'
```

## Touch Event

```java
tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //Tap 이 선택 되었을떄 호출
        switch (tab.getPosition()){
            case  0:
                
                break;
            case 1:
                
                break;
        }
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        //Tap 이 선택되지 않았을때 호출
        Log.v(TAG,"onTabUnselected()=="+tab);
    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        //Tap 이 다시 선택 되었을떄 호출
        Log.v(TAG,"onTabReselected()=="+tab);
    }
});
```



## select()

* 프로그래밍 상에서 tab 선택

```java
TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
TabLayout.Tab tab = tabLayout.getTabAt(someIndex);
tab.select();
```

* [참고]([https://www.it-swarm.dev/ko/android/tablayout-%ED%83%AD-%EC%84%A0%ED%83%9D/1053400774/](https://www.it-swarm.dev/ko/android/tablayout-탭-선택/1053400774/))

## Err 

```
 android.view.InflateException:
```

* 기존 Layout <android.support.design.widget.TabLayout

  ```xml
  <android.support.design.widget.TabLayout
          android:id="@+id/tabLayout"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
  ```

* 수정후 Layout <com.google.android.material.tabs.TabLayout

  ```xml
  <com.google.android.material.tabs.TabLayout
          android:id="@+id/tabLayout"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
  ```

  * [참고](https://hee96-story.tistory.com/52)

![image-20200506234428863](TabLayout.assets/image-20200506234428863.png)



## tabLayout in Fragment

```xml
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto"
       android:id="@+id/drawer_layout"
       android:layout_height="match_parent"
       android:layout_width="match_parent"
       android:fitsSystemWindows="true">

       <android.support.design.widget.CoordinatorLayout
           android:id="@+id/main_content"
           android:layout_width="match_parent"
           android:layout_height="match_parent">

           <android.support.design.widget.AppBarLayout
               android:id="@+id/appbar"
               android:layout_width="match_parent"
               android:layout_height="wrap_content"
               android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

               <android.support.v7.widget.Toolbar
                   android:id="@+id/toolbar"
                   android:layout_width="match_parent"
                   android:layout_height="?attr/actionBarSize"
                   android:background="?attr/colorPrimary"
                   app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
                   app:layout_scrollFlags="scroll|enterAlways|snap" />

               <android.support.design.widget.TabLayout
                   android:id="@+id/tabs"
                   android:layout_width="match_parent"
                   android:layout_height="wrap_content" />

           </android.support.design.widget.AppBarLayout>

           <android.support.v4.view.ViewPager
               android:id="@+id/viewpager"
               android:layout_width="match_parent"
               android:layout_height="match_parent"
               app:layout_behavior="@string/appbar_scrolling_view_behavior" />

       </android.support.design.widget.CoordinatorLayout>

       <android.support.design.widget.NavigationView
           android:id="@+id/nav_view"
           android:layout_height="match_parent"
           android:layout_width="wrap_content"
           android:layout_gravity="start"
           android:fitsSystemWindows="true"
           app:headerLayout="@layout/nav_header"
           app:menu="@menu/drawer_view"/>

</android.support.v4.widget.DrawerLayout>
```



## 참고 자료

[TabLayout Basic](https://alliwannado-start.tistory.com/4)

[tabLayout In Fragment](https://stackoverrun.com/ko/q/10214701)

[tab설정](https://itnext.io/android-tablayout-and-tabitem-268ac06ba966)


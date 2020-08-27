# TabLayout



* XML

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  android:layout_width="match_parent"
  android:layout_height="match_parent">

  <rocateer.finaltor.utils.SwipeViewPager
    android:id="@+id/main_view_pager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_above="@id/main_tab_layout" />

  <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="1dp"
    android:layout_above="@id/main_tab_layout"
    android:background="@color/color_f6f6f6" />

  <com.google.android.material.tabs.TabLayout
    android:id="@+id/main_tab_layout"
    android:layout_width="match_parent"
    android:layout_height="60dp"
    android:layout_alignParentBottom="true"
    android:layout_gravity="center"
    android:background="@color/color_fbfbfb"
    app:tabGravity="fill"
    app:tabIconTint="@color/color_00000000"
    app:tabIconTintMode="src_atop"
    app:tabIndicatorHeight="0dp"
    app:tabMode="fixed"
    app:tabTextAppearance="@style/TabLayoutStyle"
    app:tabTextColor="@color/color_999999">

    <com.google.android.material.tabs.TabItem
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:icon="@drawable/menu_1_off"
      android:text="홈" />

    <com.google.android.material.tabs.TabItem
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:icon="@drawable/menu_2_off"
      android:text="지도"
      app:tabIconTint="@color/color_00000000" />

    <com.google.android.material.tabs.TabItem
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:icon="@drawable/menu_3_off"
      android:text="게시판" />

    <com.google.android.material.tabs.TabItem
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:icon="@drawable/menu_4_off"
      android:text="마이페이지" />

  </com.google.android.material.tabs.TabLayout>

```

* MainActivity

```java
public class MainActivity extends RocateerActivity {
  //--------------------------------------------------------------------------------------------
  // MARK : GET START INTENT
  //--------------------------------------------------------------------------------------------
  public static Intent getStartIntent(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    return intent;
  }

  //--------------------------------------------------------------------------------------------
  // MARK : Bind Area
  //--------------------------------------------------------------------------------------------
  @BindView(R.id.main_view_pager)
  SwipeViewPager mMainViewPager;
  @BindView(R.id.main_tab_layout)
  TabLayout mMainTabLayout;
  //--------------------------------------------------------------------------------------------
  // MARK : Local variables
  //--------------------------------------------------------------------------------------------

  //--------------------------------------------------------------------------------------------
  // MARK : Override
  //--------------------------------------------------------------------------------------------
  @Override
  protected int inflateLayout() {
    return R.layout.activity_main;
  }

  @Override
  protected void initLayout() {
    mMainTabLayout.getTabAt(0).setIcon(R.drawable.menu_1_on);
    mMainTabLayout.getTabAt(1).setIcon(R.drawable.menu_2_off);
    mMainTabLayout.getTabAt(2).setIcon(R.drawable.menu_3_off);
    mMainTabLayout.getTabAt(3).setIcon(R.drawable.menu_4_off);

    MainTabPagerAdapter pagerAdapter = new MainTabPagerAdapter(getSupportFragmentManager());
    mMainViewPager.setAdapter(pagerAdapter);
    mMainViewPager.setOffscreenPageLimit(5);
    mMainViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mMainTabLayout));
    mMainViewPager.setPagingEnabled(false);

    mMainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition() == 0) {
          tab.setIcon(R.drawable.menu_1_on);
        } else if (tab.getPosition() == 1) {
          tab.setIcon(R.drawable.menu_2_on);
        } else if (tab.getPosition() == 2) {
          tab.setIcon(R.drawable.menu_3_on);
        } else if (tab.getPosition() == 3) {
          tab.setIcon(R.drawable.menu_4_on);
        }
        mMainViewPager.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {
        if (tab.getPosition() == 0) {
          tab.setIcon(R.drawable.menu_1_off);
        } else if (tab.getPosition() == 1) {
          tab.setIcon(R.drawable.menu_2_off);
        } else if (tab.getPosition() == 2) {
          tab.setIcon(R.drawable.menu_3_off);
        } else if (tab.getPosition() == 3) {
          tab.setIcon(R.drawable.menu_4_off);
        }
      }
      @Override
      public void onTabReselected(TabLayout.Tab tab) {
      }
    });
    mMainTabLayout.getTabAt(0).select();
    mMainViewPager.setCurrentItem(0);
  }
  @Override
  protected void initRequest() {

  }
  //--------------------------------------------------------------------------------------------
  // MARK : Local functions
  //--------------------------------------------------------------------------------------------

  //--------------------------------------------------------------------------------------------
  // MARK : Bind Actions
  //--------------------------------------------------------------------------------------------
}
```

* TabPagerAdapter

```java
public class MainTabPagerAdapter extends FragmentStatePagerAdapter {

  public MainTabPagerAdapter(FragmentManager fm) {
    super(fm);
  }
  private HomeFragment mHomeFragment;
  private MapFragment mMapFragment;
  private NoticeFragment mNoticeFragment;
  private MyPageFragment mMyPageFragment;

  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        mHomeFragment = new HomeFragment();
        return mHomeFragment;
      case 1:
        mMapFragment = new MapFragment();
        return mMapFragment;
      case 2:
        mNoticeFragment = new NoticeFragment();
        return mNoticeFragment;
      case 3:
        mMyPageFragment = new MyPageFragment();
        return mMyPageFragment;
      default:
        mHomeFragment = new HomeFragment();
        return mHomeFragment;
    }
  }
  @Override
  public int getCount() {
    return 4;
  }
}
```


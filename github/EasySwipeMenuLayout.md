# EasySwipeMenuLayout

* main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  android:orientation="vertical">

  <com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout
    android:id="@+id/swipe_menu_layout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:contentView="@+id/content"
    app:rightMenuView="@+id/right_menu">
    <RelativeLayout
      android:id="@+id/content"
      android:layout_width="match_parent"
      android:layout_height="wrap_content">
      <LinearLayout
        android:id="@+id/content_linear_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:layout_alignParentLeft="true">
        <androidx.appcompat.widget.AppCompatTextView
          android:id="@+id/alarm_title_text_view"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:text="서비스 이용 방법 안내입니다."
          android:textSize="16dp"
          android:textColor="@color/color_222222"
          android:fontFamily="@font/nanumsquareroundb"
          android:layout_marginLeft="20dp"
          android:layout_marginTop="17dp"
          android:layout_marginBottom="8dp"
          android:layout_marginRight="20dp" />
        <androidx.appcompat.widget.AppCompatTextView
          android:id="@+id/alarm_date_text_view"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:text="2020.09.01 19:00"
          android:textSize="12dp"
          android:textColor="@color/color_999999"
          android:fontFamily="@font/nanumsquareroundb"
          android:layout_marginLeft="20dp"
          android:layout_marginBottom="17dp" />
      </LinearLayout>
      <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:background="@color/color_dddddd"
        android:layout_below="@+id/content_linear_layout"/>
    </RelativeLayout>

    <RelativeLayout
      android:id="@+id/right_menu"
      android:layout_width="65dp"
      android:layout_height="wrap_content">
      <androidx.appcompat.widget.AppCompatImageView
        android:id="@+id/alarm_delete_image_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/delete_i_tresh" />
    </RelativeLayout>

  </com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout>

</LinearLayout>
```


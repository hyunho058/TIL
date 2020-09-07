# RoundRectView

> * 모서리를 둥근 직사각형을 만들때 사용



* 둥근 모서리 직사각형

```xml
<com.github.florent37.shapeofview.shapes.RoundRectView
        android:id="@+id/login_round_rect_view"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:layout_marginLeft="30dp"
        android:layout_marginTop="50dp"
        android:layout_marginRight="30dp"
        app:shape_roundRect_bottomLeftRadius="6dp"
        app:shape_roundRect_bottomRightRadius="6dp"
        app:shape_roundRect_topLeftRadius="6dp"
        app:shape_roundRect_topRightRadius="6dp">
        <RelativeLayout
          android:layout_width="match_parent"
          android:layout_height="match_parent"
          android:background="@color/colorPrimary">
          <androidx.appcompat.widget.AppCompatTextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:fontFamily="@font/nanumsquareroundb"
            android:text="로그인"
            android:textColor="@color/color_ffffff"
            android:textSize="16dp" />
    </RelativeLayout>
</com.github.florent37.shapeofview.shapes.RoundRectView>
```

![image-20200907225816673](RoundRectView.assets/image-20200907225816673.png)



* 동근 이미지

``` xml
		<RelativeLayout
          android:id="@+id/title_image_relative_layout"
          android:layout_width="54dp"
          android:layout_height="54dp"
          android:layout_centerVertical="true">
          <com.github.florent37.shapeofview.shapes.RoundRectView
            android:layout_width="54dp"
            android:layout_height="54dp"
            app:shape_roundRect_topRightRadius="60dp"
            app:shape_roundRect_bottomRightRadius="60dp"
            app:shape_roundRect_topLeftRadius="60dp"
            app:shape_roundRect_bottomLeftRadius="60dp">
            <androidx.appcompat.widget.AppCompatImageView
              android:id="@+id/title_image_image_view"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:src="@drawable/delete_i_tresh"/>
          </com.github.florent37.shapeofview.shapes.RoundRectView>
          <androidx.appcompat.widget.AppCompatImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@drawable/btn_i_picture"
            android:layout_alignParentRight="true"
            android:layout_alignParentBottom="true"/>
        </RelativeLayout>
```

![image-20200907230127594](RoundRectView.assets/image-20200907230127594.png)


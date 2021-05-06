# SwitchCompat

## Theme  색상 변경

* styles.xml

  ```xml
  <resources>
    <style name="Color1SwitchStyle">
      <item name="colorControlActivated">@color/colorAccent</item>
    </style>
  </resources>
  ```

* main_activity.xml

  * android:theme="@style/Color1SwitchStyle

  ```xml
  <androidx.appcompat.widget.SwitchCompat
                android:id="@+id/alarm_switch_image_view"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentRight="true"
                android:layout_centerVertical="true"
                android:theme="@style/Color1SwitchStyle"/>
  ```

  


# TextView 화면 크기에따라 크기 변경

* Code

```java
private int standardSize_X, standardSize_Y;
  private float density;

// 기기의 디스플레이 사이즈(해상도)를 반환해주는 코드.
public Point getScreenSize(Activity activity) {
  Display display = activity.getWindowManager().getDefaultDisplay();
  Point size = new Point();
  display.getSize(size);

  return  size;
}
//기기별 해상도를 기기별 density로 나누어 모든 기기에 무관한 기준사이즈를 얻는다.
public void getStandardSize() {
  Point ScreenSize = getScreenSize(mActivity);
  density  = getResources().getDisplayMetrics().density;

  standardSize_X = (int) (ScreenSize.x / density);
  standardSize_Y = (int) (ScreenSize.y / density);
}


textView.setTextSize((float) (standardSize_X / 3));
textView.setTextSize((float) (standardSize_Y / 5));
```



## Reference

[https://featherwing.tistory.com/22](https://featherwing.tistory.com/22)
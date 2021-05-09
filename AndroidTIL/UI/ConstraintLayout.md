#  ConstraintLayout

> * Linear Layout을 써야만 했던 뷰 비율 조절도 간단히 가능
> * 뷰 계층 간단히 할 수 있어 유지보수와 성능에 좋다.
> * 



## 가로/세로 비율

* app:layout_constraintDimensionRatio="0.75:1" => 가로:세로 = 0.75:1

```xml
<androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="120dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="10dp"
        android:layout_marginEnd="10dp"
        android:layout_marginTop="15dp">
        <androidx.appcompat.widget.AppCompatImageView
            android:layout_width="match_parent"
            android:layout_height="0dp"
            app:layout_constraintDimensionRatio="0.75:1"
            app:layout_constraintTop_toTopOf="parent">
 </com.github.florent37.shapeofview.shapes.RoundRectView>
```



## Percent size

* 부모 뷰의 크기에 비례하여 뷰의 크기를 결정
  * padding 제외

* android:layout_width="match_constraint"
* app:layout_constraintWidth_default="percent"
* app:layout_constraintWidth_percent="0.4"



## 뷰 크기(최소/최대)

* 최소/최대 크기 지정
* app:layout_constraintWidth[min|max]="size"
* 
  wrap_Content일 땐 android:[min|max]width 적용
* match_constraint일 땐 app:layout_constraintWidth[min|max] 적용



## 위치 지정 (bias)

* 가로 또는 세로 축 방향을 가진 가사의 뷰
* 부모 뷰의 특정 위치를 기준으로 할때 사용
* app:layout_constraintGuide_begin : 시작지점으로 부터의 거리
* app:layout_constraintGuide_end : 끝 지점으로 부터의 거리
* app:layout_constraintGuide_percent: 시작점으로 부터의 % 거리
* app:layout_constraintVertical_bias  : 세로 방향에 비율을 정한다
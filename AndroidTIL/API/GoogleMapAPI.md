# Google Map API

> * 설정
> * 기본 예제 코드 작성
> * Custom Cluster (Renderer)
>   * 클러스터 색상
>   * 클러스터 최소 크기 설정\

* 이;미지

![image-20210118230612481](GoogleMapAPI.assets/KakaoTalk_20210118_230421621.gif) 

## Seetting

* AndroidManifest.xml

```xml
<application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
         <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="API키" /> 
        <activity android:name=".MainActivity">
```

* build.gradle

```
implementation 'com.google.android.gms:play-services-maps:17.0.0'               //google map
implementation 'com.google.android.gms:play-services-location:17.1.0'           //google map
implementation 'com.google.maps.android:android-maps-utils:0.5'                 //google Map Cluster
```



## Example

* activity_google_map.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity" >
    <fragment
		android:id="@+id/google_map"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MapsActivity"
        android:name="com.google.android.gms.maps.SupportMapFragment" />
</LinearLayout>
```

* GoogleMapActivity.java

  * Fragment에서 mapFragment 를 정의할때는 아래 코드처럼 해준다

    >* SupportMapFragment을 통해 레이아웃에 만든 fragment의 ID를 참조하고 구글맵을 호출한다.
    >* SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager()
    >  * java.lang.NullPointerException: Attempt to invoke virtual method 'void com.google.android.gms.maps.SupportMapFragment.getMapAsync(com.google.android.gms.maps.OnMapReadyCallback)' on a null object reference
    >
    >*  위 코드처럼 에러 발생하여 getFragmentManager() -> this.getChildFragmentManager() 로 변경

  * ```java
    SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
            .findFragmentById(R.id.google_map);
        //getMapAsync must be called on the main thread.
        mapFragment.getMapAsync(this);
    
        mActivity.registerReceiver(mMovePositionReceiver, new IntentFilter(Constants.MOVE_MAP_POSITION));
        mActivity.registerReceiver(mGetFilterReceiver, new IntentFilter(Constants.FILTER_RESULT));
    
        mChangeMapRelativeLayout.setSelected(false);
        mChangeMapTextView.setSelected(false);
    ```

```java
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mGoogleMap = googleMap;
        //지도타입 - 일반
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //회전
        mGoogleMap.getUiSettings().setRotateGesturesEnabled(false);
        //기울기
        mGoogleMap.getUiSettings().setTiltGesturesEnabled(false);

        LatLng SEOUL = new LatLng(37.56, 126.97);
        //최초 노출 위치
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 14));
        
        //클러스터 설정 method
        setUpCluster();
        
    }
    
    
   /**
   * 클러스터 설정
   *
   * @author khh
   * @since 1/18/21
   **/
  private void setUpCluster() {
    // Position the map.

    // Initialize the manager with the context and the map.
    // (Activity extends context, so we can pass 'this' in the constructor.)
    clusterManager = new ClusterManager<MyItem>(mActivity, mGoogleMap);
    
    // Point the map's listeners at the listeners implemented by the cluster
    // manager.
    mGoogleMap.setOnCameraIdleListener(clusterManager);
    mGoogleMap.setOnMarkerClickListener(clusterManager);
      
    //Cluster Item Touched Event
    clusterManager.setOnClusterItemClickListener(
        new ClusterManager.OnClusterItemClickListener<MyItem>() {
      @Override
      public boolean onClusterItemClick(MyItem myItem) {
        Timber.i("onClusterItemClick == %s", myItem.getPosition());
        return true;
      }
    });
      
    //Cluster Touched Event
    clusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
      @Override
      public boolean onClusterClick(Cluster<MyItem> cluster) {
        Timber.i("onClusterClick == %s", cluster.getPosition());
        for (MyItem myItem : cluster.getItems()) {
          Timber.i("================" + myItem.getProductIdx());
          mProductIdxList.add(myItem.getProductIdx());
        }
        return true;
      }
    }); 
    addItems();
    //커스텀 클러스트 등록
    MyClusterRenderer renderer = new MyClusterRenderer(mActivity, mGoogleMap, clusterManager);
    clusterManager.setRenderer(renderer);

  }
  /**
   * 클러스터 TEST Marker
   */
  private void addItems() {
    // Set some lat/lng coordinates to start with.
    double lat = 37.56;
    double lng = 126.97;

    // Add ten cluster items in close proximity, for purposes of this example.
    for (int i = 0; i < 10; i++) {
      double offset = i / 1000D;
      lat = lat + offset;
      lng = lng + offset;
      MyItem offsetItem = new MyItem(lat, lng, "Title " + i, "Snippet " + i,"2");
      clusterManager.addItem(offsetItem);
    }
    lat = 37.50;
    lng = 126.90;
    for (int i = 0; i < 10; i++) {
      double offset = i / 10000d;
      lat = lat + offset;
      lng = lng + offset;
      MyItem offsetItem = new MyItem(lat, lng, "Title " + i, "Snippet " + i,"2");
      clusterManager.addItem(offsetItem);
    }
  }
    
    
}
```



* Cluster Item 
  * 클러스터 아이템  객체

```java
public class MyItem implements ClusterItem {
  private final LatLng position;
  private final String title;
  private final String snippet;
  private String mProductIdx;

  public MyItem(double lat, double lng, String title, String snippet, String productIdx) {
    position = new LatLng(lat, lng);
    this.title = title;
    this.snippet = snippet;
    mProductIdx = productIdx;
  }

  @Override
  public LatLng getPosition() {
    return position;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public String getSnippet() {
    return snippet;
  }

  public String getProductIdx(){
    return mProductIdx;
  }
}
```



* Renderer 설정
  * getClusterText() - 클러스터에 표시되는 text 설정
  * shouldRenderAsCluster() - 클러스터 최소 표시 설정
    * 구글 지도에 마커를 표시하지 않고 클러스터를 표시하기 위해 최소 크기를 '0' 보다 크게 조건을 주었다.
  * getColor() - 클러스터의 Background 색상 설정

```java
public class MyClusterRenderer extends DefaultClusterRenderer<MyItem> {

  private final Context mContext;
  private int mClusterSize;

  public MyClusterRenderer(Context context, GoogleMap map,
                           ClusterManager<MyItem> clusterManager) {
    super(context, map, clusterManager);
    mContext = context;
  }

  /**
   * Cluster Count
   *
   * @author khh
   * @since 1/18/21
  **/
  @Override
  protected int getBucket(Cluster<MyItem> cluster) {
    Timber.i("getBucket=="+cluster.getSize());
    mClusterSize = cluster.getSize();
    getClusterText(mClusterSize);
    return super.getBucket(cluster);
  }

  /**
   * 클러스터 Text 표시
   *
   * @author khh
   * @since 1/18/21
  **/
  @Override
  protected String getClusterText(int bucket) {
    //return bucket < BUCKETS[0] ? String.valueOf(bucket) : bucket + "+";
    Timber.i("getClusterText=="+bucket);
//    return super.getClusterText(bucket);
    return String.valueOf(mClusterSize);
  }

  @Override
  public void onClustersChanged(Set<? extends Cluster<MyItem>> clusters) {
    Timber.i("onClustersChanged=="+clusters.size());
    super.onClustersChanged(clusters);
  }

  @Override
  protected void onBeforeClusterRendered(Cluster<MyItem> cluster, MarkerOptions markerOptions) {
    Timber.i("onBeforeClusterRendered==");
    super.onBeforeClusterRendered(cluster, markerOptions);
  }

  @Override
  protected void onClusterRendered(Cluster<MyItem> cluster, Marker marker) {
    super.onClusterRendered(cluster, marker);
  }

  /**
   * Cluster min size setting
   *
   * @author khh
   * @since 1/18/21
  **/
  @Override
  protected boolean shouldRenderAsCluster(Cluster<MyItem> cluster) {
//    return super.shouldRenderAsCluster(cluster);
    return cluster.getSize() > 0;
  }

  /**
   * 클러스터 아이템에 마커를 설정하려먼 해당 매서드에서 작업하면 된다.
   * @author khh
   * @since 2021-01-18 오후 10:44
   **/
  //  @Override
  //  protected void onBeforeClusterItemRendered(MyItem item, MarkerOptions markerOptions) {
  //    final BitmapDescriptor markerDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
  //    markerOptions.icon(markerDescriptor).snippet("");
  //  }

  /**
   * Cluster Circle Background Color
   *
   * @author khh
   * @since 1/18/21
   **/
  @Override
  protected int getColor(int clusterSize) {
    return Color.parseColor("#19A259");
  }
}
```


# BookSearch

* RecyclerView

  * Library 추가 (build.gradle)

  ```java
  dependencies {
          implementation 'com.android.support:recyclerview-v7:28.0.0'
      }
  ```

  * LinearLayoutManager

    ```java
    LinearLayoutManager(Context context, int orientation, boolean reverseLayout)
    ```

    * context - `Context`: Current context, will be used to access resources.
    * orientation - `int`: Layout orientation. Should be `HORIZONTAL` or `VERTICAL`.
    * reverseLayout - `boolean`: When set to true, layouts from end to start.

  * Adapter

  ```java
  package com.example.booksearchrecyclerviewkakaoapi;
  
  import android.content.Context;
  import android.util.Log;
  import android.view.LayoutInflater;
  import android.view.View;
  import android.view.ViewGroup;
  import android.widget.TextView;
  
  import androidx.annotation.NonNull;
  import androidx.recyclerview.widget.LinearLayoutManager;
  import androidx.recyclerview.widget.RecyclerView;
  
  import java.util.ArrayList;
  
  public class VerticalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
      String TAG = "VerticalAdapter";
      View view;
      ArrayList<AdapterVO> adapterList = new ArrayList<AdapterVO>();
      Context context;
      HorizontalAdapter horizontalAdapter;
  
      public VerticalAdapter(ArrayList<AdapterVO> adapterList, Context context) {
          this.adapterList = adapterList;
          this.context = context;
      }
  
      //Layout을 만들어서 Holder에 저장 (View Holder 를 생성하고 View를 붙여줌
      @NonNull
      @Override
      public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          Log.v(TAG,"onCreateViewHolder__viewType=="+viewType);
          if(viewType ==ViewType.ItemBookTitle){
              view=inflater.inflate(R.layout.title_item,parent,false);
              return new BookTitleItem(view);
          }else {
              view=inflater.inflate(R.layout.recycler_horizontal,parent,false);
              return new HorizontalItem(view);
          }
      }
      //넘겨받은 데이터를 화면에 출력하는 역할
      //제활용되는 View가 호출하여 실행되는 Method
      //View Holder 를 전달하고 Adapter는 position 의 데이터를 결합
      @Override
      public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
          Log.v(TAG,"onBindViewHolder=="+holder+"/position=="+position);
          if (holder instanceof BookTitleItem){
              ((BookTitleItem)holder).tvBookTitle.setText(adapterList.get(position).getBookTitle());
          }else if(holder instanceof HorizontalItem){
              ((HorizontalItem)holder).recycler_horizontal.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
              horizontalAdapter = new HorizontalAdapter(context,adapterList.get(position).getBookVO());
              ((HorizontalItem)holder).recycler_horizontal.setAdapter(horizontalAdapter);
          }
      }
  
      @Override
      public int getItemViewType(int position) {
          Log.v(TAG,"getItemViewType_position"+position);
          return adapterList.get(position).getViewType();
      }
  
      @Override
      public int getItemCount() {
          Log.v(TAG,"getItemCount_ddapterList.size()"+adapterList.size());
          return adapterList.size();
      }
  
      public class BookTitleItem extends RecyclerView.ViewHolder{
          TextView tvBookTitle;
  
          public BookTitleItem(View itemView) {
              super(itemView);
              tvBookTitle=itemView.findViewById(R.id.tvBookTitle);
          }
      }
  
      public class HorizontalItem extends RecyclerView.ViewHolder{
          RecyclerView recycler_horizontal;
  
          public HorizontalItem(View itemView) {
              super(itemView);
              this.recycler_horizontal=(RecyclerView)itemView.findViewById(R.id.recyclerViewHorizontal);
          }
      }
  
  }
  
  ```

  * onCreateViewHolder()

    ```java
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Log.v(TAG,"onCreateViewHolder__viewType=="+viewType);
            if(viewType ==ViewType.ItemBookTitle){
                view=inflater.inflate(R.layout.title_item,parent,false);
                return new BookTitleItem(view);
            }else {
                view=inflater.inflate(R.layout.recycler_horizontal,parent,false);
                return new HorizontalItem(view);
            }
        }
    ```

    * LayoutInflater

      *  XML에 미리 정의해둔 틀을 실제 메모리에 올려주는 역할(XML에 정의된 Resource를View객체로 반환)

      ![image-20200403132204549](C:\Users\hyunh\AppData\Roaming\Typora\typora-user-images\image-20200403132204549.png)

    * asdfafs

    * ㅁㄴㅇㄹㄴㅇㄹ

    * sadfsdaf

  

  

  

  

* Fragment

* Kakao API

  * JSON
  * Jackson Library

* Thread

  * Handler
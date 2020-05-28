# Glide

## Library 추가

* build.gradle

```java
implementation 'com.github.bumptech.glide:glide:3.7.0'
```

* 기본 Glide사용 code
  * with() - context
  * load() - url
  * into() - ImageView ID

```java
Glide.with(context).load(getUrl).into(ivBookImage);
```

* 함수
  * override()
    *  -지정한 이미지의 크기만큼만 불러옴(이미지 로딩 속도를 최적화)
  * placeholder()
    * 이미지를 로딩하는동안 처음에 보여줄 placeHolder 이미지를 지정
  * error()
    * 이미지로딩에 실패할 경우 실패 이미지 지정
  * thumbnail()
    * 지정한 %비율 만큼 미리 이미지를 가져와서 보여줌
    * 0.1f => 이미지 크기중 10%만 먼져 가져와서 흐릿하게 보여줌
  * asGif()
    * 정적인 이미지 뿐만 아니라 GIF로딩
  * centerCrop()
  * fitCenter()



## Thread 이용한 url 호출

* Glide 를 사용하기전 url호출 code 이다.

```java
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try{
                URL url = new URL(getUrl);
                Log.v(TAG,"url==="+url);
                InputStream inputStream = url.openStream();
                Log.v(TAG,"InputStream=========="+inputStream);
                bitmap = BitmapFactory.decodeStream(inputStream); //InputStream 으로부터 Bitmap를 만든다
                handler.post(new Runnable() {
                    @Override
                    public void run() {  // 화면에 그려줄 작업
                        Log.v(TAG,"handler.post_run()----------------"+bitmap);
                        ivBookImage.setImageBitmap(bitmap);
                    }
                });
            } catch(Exception e){
                Log.v(TAG,"thread_run()_Exception ="+e.toString());
            }
        }
    });
```


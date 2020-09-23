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
    *  지정한 이미지의 크기만큼만 불러옴(이미지 로딩 속도를 최적화)
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
* Basic Code

```java
RequestOptions requestOptions = new RequestOptions();
requestOptions.centerCrop();
Glide.with(mActivity)
    .load("https://picsum.photos/200/300")
    .apply(requestOptions)
    .into(mTitleImageView);
```

* 이미지 로딩 모니터링 클래스

```java
 private static class LoadingListener implements RequestListener<Drawable> {
 
        @Override
        public boolean onLoadFailed(@Nullable final GlideException e, final Object model, final Target<Drawable> target,
                final boolean isFirstResource) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, String.format(Locale.ROOT,
                        "GLIDE onException(%s, %s, %s, %s)", e, model, target, isFirstResource), e);
            }
 
            return false;
        }
 
        @Override
        public boolean onResourceReady(final Drawable resource, final Object model, final Target<Drawable> target,
                final DataSource dataSource, final boolean isFirstResource) {
            if (BuildConfig.DEBUG) {
                Log.i(TAG, String.format(Locale.ROOT,
                        "GLIDE onResourceReady(%s, %s, %s, %s, %s)", resource, model,
                        target, dataSource, isFirstResource));
            }
 
            return false;
        }
    }
```

* 사용 주의 사항
  * imageView의 넓이, 높이를 wrap_contet로 주지 말고 특정 크기로 줘야한다.
  * 모양을 커스텀할때 imageView attribute에 scaleType을 지정하면 안된다.
  * MultiTransformation사용시 centerCrop등의 리사이즈 옵션을 먼저 지정해야한다.



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



##Reference

[Class RequestOptions](https://bumptech.github.io/glide/javadocs/400/com/bumptech/glide/request/RequestOptions.html)


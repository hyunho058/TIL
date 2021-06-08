# Room

> Room은 SQLite에 대한 추상화 레이어를 제공하여 원할한 데이터베이스 엑세스를 지원하는 동시에SQLite를 완벽히 할용 한다.

* Room구성요소
    * Database
    	* database holder를 포함하며, 앱에 영구 저장되는 데이터와 기본 연결을 위한 주 액세스 지점이다. RoomDatabase를 extend 하는 추상 클래스여야 하며, 테이블과 버전을 정의하는 곳이다.
    * DAO
    	* 데이터베이스에 접근해서 실질적으로 insert, delete 등을 수행하는 메소드를 포함한다.
    * Entity
    	* 데이터베이스의 테이블을 나타낸다.

![](https://images.velog.io/images/hyunho058/post/bbca0312-6fc8-48e1-853a-2cfeddb780a9/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202021-06-08%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%2011.13.22.png)


## Dependency추가
* `build.gradle`

```bash
dependencies {
    def room_version = "2.2.5" 

    //Room//
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version" // For Kotlin use kapt instead of annotationProcessor
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation "androidx.room:room-ktx:$room_version"
    // optional - RxJava support for Room
    implementation "androidx.room:room-rxjava2:$room_version"
    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation "androidx.room:room-guava:$room_version"
    // Test helpers
    testImplementation "androidx.room:room-testing:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
}
```


## Room 사용
### Entity
* 각각의 Entitiy는 고유 식별자인 PK(기본키)가 반드시 필요하다.
    * autoGenerate = true 를 사용하여 자동으로 생성
```java
@Entity(tableName = "USER")
data class UserEntity(@ColumnInfo(name = "user_id") var userId: String,
                      @ColumnInfo(name = "user_pw") var userPw: String,
                      @ColumnInfo(name = "user_name") var userName: String
){
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}
```
### DAO
* DB에 접근해 질의를 수행할 DAO를 만든다.
* @Query()를 이용해 쿼리문을 작성할수 있다.
```java
@Dao
interface UserDao {
    //죄회
    @Query("SELECT * FROM user")
    fun getUser(): List<UserEntity>
    //입력
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: UserEntity)
    //삭제
    @Query("DELETE from user")
    fun delete()
}
```
### Database
```java
@Database(version = 1,
    entities = [UserEntity::class]
)
abstract class UserDB : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UserDB? = null

        fun getInstance(context: Context): UserDB? {
            if (INSTANCE == null) {
                synchronized(UserDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDB::class.java, "user.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
```
### Activity에서 Room에 접근
* Room 구축후 MainActivity에서 객체를 생성. 
* 메인 쓰레드에서 Room DB에 접근하려고 하면 에러가 발생하기 때문에 Thread혹은 AsyncTask를 이용해야한다.
    * Cannot access database on the main thread since it may potentially lock the UI for a long period of time.
    
* 아래 코드는 DB에 값을 저장하는 예시이다.
```java
class MainActivity : AppCompatActivity() {
    private var userDb: UserDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userDb = UserDB.getInstance(this)
        //USER DB에 저장
        val runnable = Runnable {
                    val newUser = UserEntity(
                        "USER0_ID",
                        "USER_PW",
                        "USER_NAME"
                    )
                    userDb?.userDao()?.insert(newUser)
                }
                val thread = Thread(runnable)
                thread.start()
    }
}
```



[안드로이드Room7가지 유용한 팁](https://medium.com/harrythegreat/%EB%B2%88%EC%97%AD-%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-room-7%EA%B0%80%EC%A7%80-%EC%9C%A0%EC%9A%A9%ED%95%9C-%ED%8C%81-18252a941e27)
[https://developer.android.com/training/data-storage/room/defining-data](https://developer.android.com/training/data-storage/room/defining-data)
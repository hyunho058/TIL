# 스트림(Stream)

> * 자바8부터 추가된 컬렉션의 저장 요소를 하나씩 참조해서 람다식으로 처리할 수있도록 해주는 반복자
> * Iterator와 비슷한 역할을 하지만 람다식으로 요소 처리 코드를 제공하여 코드가 좀 더 간결하게 할 수 있다는 점과 반복자를 사용하므로 병렬처리가 쉽다는 점에서 차이가 있다.

* Iterator와 Stream 비교

  * Iterator

  ```java
  ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3));
  Iterator<Integer> iter = list.iterator();
  while(iter.hasNext()) {
      int num = iter.next();
      System.out.println("값 : "+num);
  }
  ```

  * Stream
    * stream()메소드로 스트림 객체를 얻은 후 foreach()에서 list에 있는 요소들을 하나씩 출력
    * stream.forEach()메소드는 Consumer함수적 인터페이스 타입의 매개값을 가지므로 컬렉션의 요소를 소비할 코드를 람다식으로 만들 수 있다.

  ```java
  ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3));
  Stream<Integer> stream = list.stream();
  stream.forEach(num -> System.out.println("값 : "+num));
  ```

* Stream사용법

  * 배열에서의 스트림 사용

  ```java
  //String 배열
  String[] strArray = { "홍길동", "이순신", "임꺽정"};
  Stream<String> strStream = Arrays.stream(strArray);
  strStream.forEach(a -> System.out.print(a + ","));
  System.out.println();
  		
  //int 배열
  int[] intArray = { 1, 2, 3, 4, 5 };
  IntStream intStream = Arrays.stream(intArray);
  intStream.forEach(a -> System.out.print(a + ","));
  System.out.println();
  ```

  * 클래스에서 스트림 사용

  ```java
  class Student {
      private String name;
      private int score;
  	
      public Student(String name, int score) {
          this.name = name;
          this.score = score;
      }
  
      public String getName() { return name; }
      public int getScore() { return score; }
  }
  
  
  public class FromCollectionExample {
      public static void main(String[] args) {
          List<Student> studentList = Arrays.asList(
              new Student("홍길동", 10),
              new Student("이순신", 20),
              new Student("임꺽정", 30)
          );
  		
          Stream<Student> stream = studentList.stream();
          stream.forEach(s -> System.out.println("이름 : "+ s.getName()));
      }
  }
  ```

  
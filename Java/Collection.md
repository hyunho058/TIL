# Collection

* Java collection에는 `List`, `Map`, `Set` 인터페이스를 기준으로 여러 구현체가 존재한다. 이에 더해 `stack`과 `Queue`인터페이스도 존재한
* 배열과 다르게 객체를 보관하기 위한 공간을 미리 정하지 않아도 되므로, 상황에 따라 객체의 수를 동적으로 정할 수 있다. 
  * 프로그램의 공간적인 효율성을 높여줌

## List

* `List`인터페이스를 직접 `@Override`를 통해 사용자가 정의하여 사용할 수도 있으며, 대표적인 구현체로는 `ArrayList`가 존재한다. 

## Map

* 대표적인 구현체로 `HashMap`이 존재
* Key- value의 구조로 이루어져 있으며, key를 기준으로 중복된 값을 저장하지 않으며, 순서를 보장하지 않는다.
  * key에 대해서 순서를 보장하기 위해서는 `LinkedHashMap`을 사용

## Set

* 대표적인 구현체로 `HashSet`이 존재
* `value`에 대해서 중복된 값을 저장하지 않는다
* Set자료구조는 Map의 key-value구조에서 key대신에 value가 들어가 value를 key로 하는 자료구조일 뿐이다
* 순서를 보장하지 않으며, 순서를 보장해주기 위해서는 `LinkedHashSet`을 사용

## Slack과 Queue

* `Stack` 객체는 직접 new 키워드로 사용할 수 있으며, `Queue`인터페이스는 JDK1.5부터 `LinkedList`에 new 키워드를 적용하여 사용할 수있다.
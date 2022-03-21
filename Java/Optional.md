# Optional

메서드에서 작업 중 특별한 상황에서 값을 제대로 리턴할 수 없는 경우 선택하는 방법으로 Optional을 return하며, 명시적으로 빈 값일 수도 있다는걸 알려주고, 이 경우에 대한 처리를 강제함.

**주의사항**

* 리턴값으로만 사용하는 것을 권장 ( 메소드 매개변수 타입, 맴의 키타입, 인스턴스 필드 타입으로 쓰지 않는다.)
* Optional을 리턴하는 메소드에서 null을 리턴하지 않는다.
* Primmitive타입용 Optional을 따로있다.
* Collection, Map, Stream Array, Optional은 Optional로 감싸지 않는다.

## Optioanl 기능

**Optioanl생성**
Optional.of(value) : value 변수 값이 null인 경우 NullPointException이 발생(반드시 값이 있어야 하는 경우 사용) 
Optional.onNullable(value) :value값이 null인 경우 Optional.empty()가 리턴된다. 
Optional.empty() : 빈 객체를 생성

**값이 있는지 없는지 확인**
inPresent() : 람다식을 인자로 받아, 값이 존재할 때 그 값을 람다시을 적용해준다.
isEmpty() => JAVA11 이상부터 사용가능

**Optional 값 가져오기**
get()

**Optional에 값이 있는경우 해당 값을 가지고 추가작업**
ifPresent()

**Optional에 값이 있는경우 값을 가져오고 없는경우 xxx리턴**
onElseGet()

**Optional에 값이 있는경우 값을 가져오고 없는 error을 던짐**
onElseThrow()

**Optional 값 중간처리**
Optional.filter()
Optional.map()
Optional.flatMap() => Optional 안에 들어있는 인스턴스가 Optioanl인 경우에 사용하면 편리함.

## 사용 예제

```java
dealRepository.findById(request.getId())
        .filter(deal -> deal.getId().equals(request.getId()))
        .map(deal -> deal.updateDeal(
                        request,
                        getLocation(
                                request.getAddress1(),
                                request.getAddress2())
                )
        )
        .orElseThrow(DealNotFoundException::new);
```

```java
locationRepository.findByAddress1AndAddress2(
        address1,
        address2
).orElseGet(
        () -> locationRepository.save(
                Location.builder()
                        .address1(address1)
                        .address2(address2)
                        .build())
);
```


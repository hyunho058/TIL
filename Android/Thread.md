# Thread

## Multiprocessing

* Task(일,작업)를 실행하는 core(Processor)가 2개 이상인 경우

## Multi Tasking

* 하나의 core가 시분할(time slicing)기법을 이용해서 여러개의 Task를 동시에 수행되는 것처럼 보이게 하는 것.

## Multi Threading

* 하나의 Task를 여러개의 sub Task로 분할해서 동시간대에 실행되는 것처럼 만드는 것.
* 다수의 작업을 각 작업마다 core가 붙어 동작을 하는것(=분할 작업)
  * Multi Threading를 하지 않으면 순차적으로 데이터를 처리해야하기 때문에 성능과 시간이 비효율적이다.
* Thread - 독립적인 실행 흐름.


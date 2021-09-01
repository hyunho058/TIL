# Garbage Collection

![스크린샷 2021-09-01 오후 6.32.08](GarbageCollection.assets/스크린샷 2021-09-01 오후 6.32.08.png)

## Minor GC

* 새로 생성된 대부분의 객체(Instance)는 Eden영역에 위치한다
* Eden영역에 GC가 한 번 발생한 후 살아남은 객체는 Survivor영역 중 하나로 이동된다
* 이 과정을 반복하다가 계속해서 살아남아 있는 객체는 일정시간 참조되고 있다는 뜻이므로 Old영역으로 이동시킨다

## Major GC

* Old영역에 있는 모든 객체들을 검사하여 참조되지 않은 객체들을 한꺼번에 삭제한다
* 시간이 오래 걸리고 실행 중 프로세스가 정지된다 이석을 `stor the word`라고 하는데 Major GC가 발생하면 GC를 실행하는 스레드를 제외한 나머지 스레드는 모두 작업을 멈춘다
# Queue

> * 컴퓨터의 기본적인 자료 구조의 한가지
> * 먼저 집어 넣은 데이터가 먼저나오는 FIFO(First In First Out)구조로 저장하는 형식
> * 데이터가 입력된 시간 순서대로 처리해야 할 필요가 있는 상황에 사용
> * 사용 사례
>   * 너비 우선 탐색(BFS)
>     * 처리해야할 노드의 리스트를 저장하는 용도로 Queue를 사용
>     * 노드를 하나 처리할 때마다 해당 노드와 인접한 노드들을 큐에 다시 저장.
>     * 노드를 접근한 순서대로 처리할 수 있다
>   * 캐시(Cache)구현
>   * 우선숭위가 같은 잡업 예약(인쇄 대기열)
>   * 프로세스 관리

* Queue 구조

![image-20200817220922695](Queue.assets/image-20200817220922695.png)

* Java 라이브러리 Queue사용 법
  * Implementing Class
    * 제네릭 형태로 사용할 때
      * LinkedList
        * 끝에 요소를 추가하기에 용이하다.
        * List interface구현
      * PrinorityQueue
        * 우선순위 큐
        * PIPO(Priority In Priority Out)
        * 정렬된 순서에 의해 반복
      * PriorityBlockingQueue
        * PriorityQueue의 동기화된 버전
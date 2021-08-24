# CPU스케줄링

> CPU가 하나의 프로세스 작어비 끝나면 다음 프로세스 작업을 수행해야한다. 이때 어떤 프로세스를 다음에 처리할 지 선택하는 알고리즘을 CPU Scheduling알고리즘이라고 한다. 

## Preemptive vs Non-Preemptive

* Preemptive(선점)
  * 프로세스가 CPU를 점유하고 있는동안 I/O나 인터럽트가 발생하지 않았음에도 다른 프로세스가 해당 CPU를 강제로 점유할 수 있다.
  * 프로세스가 정상적으로 수행중인 동안 다른 프로세스가 CPU를 강제로 점유하여 실행할 수 있다.
* Non-Preemptive
  * 한 프로세스가 CPU를 점유했다면 I/O나 인터럽트 발생 또는 프로세스가 종료될 때까지 다른 프로세스가 CPU를 점유하지 못하는 것이다.

## 선점형 스케줄링

* SRT(Shortest Remaining Time) 스케줄링

  * 짧은 시간 순서대로 프로세스를 수행
  * 현재 CPU에서 실행 중인프로세스의 남은 CPU 버스트 시간보다 더 짧은 CPU버스트 시간을 가지는 프로세스가 도착하며 CPU가 선점된다.

* Round Robin 스케줄링

  * 시분할 시스템의 성질을 활용한 방법
  * 일정 시간을 정하여 하나의 프로세스가 이 사간동안 수행하고 다시 대기 상태로 돌아간다
  * 다음 프로세스 역시 같은 시간동안 수행한 후, 대기한다. 이러한 작업을 모든 프로세스가 돌아가면서 진행하며, 마지막 프로세스가 끝나면 다시 처음프로세스로 돌아와서 작업을 반복한다.
  * 일정 시간을 Time Quantum(Time Slice)라고 부른다.
  * 한 프로세스가 종료되기 전에 time quantum이 끝나면 다른 프로세스에게 CPU를 넘겨주기 때문에 선점형 스케줄링의 대표적인 예시다.

* Multi-level Queue 스케줄링
  * 프로세스를 그룹으로 나누어, 각 그룹에 따라 Ready Queue(준비 큐)를 여러 개 두며, 각 큐마다 다른 규칙을 지정할 수도 있다.(ex. 우선순위, CPU 시간 등)
  * 즉, 준비 큐를 여러 개로 분할해 관리하는 스케줄링 방법.
  * 프로세스들이 CPU를 기다리기 위해 한 줄로 서는 게 아니라 여러 줄로 선다.

  ![img](CPU스케줄링.assets/53879673-5e979880-4052-11e9-9f9b-e8bfec7c9be6.png)

* Multi-level feedback Queue 스케줄링
  * 기본 개념은 Multi-level Queue와 동일하나, 프로세스가 하나의 큐에서 다른 큐로 이동 가능하다는 점이 다르다.
  * 위 그림에서 모든 프로세스는 가장 위의 큐에서 CPU의 점유를 대기한다. 이 상태로 진행하다가 이 큐에서 기다리는 시간이 너무 오래 걸린다면 **아래의 큐로 프로세스를 옮긴다.** 이와 같은 방식으로 대기 시간을 조정할 수 있다.
  * 만약, 우선순위 순으로 큐를 사용하는 상황에서 우선순위가 낮은 아래의 큐에 있는 프로세스에서 starvation 상태가 발생하면 이를 우선순위가 높은 위의 큐로 옮길 수도 있다.
  * 대부분의 상용 운영체제는 여러 개의 큐를 사용하고 각 큐마다 다른 스케줄링 방식을 사용한다. 프로세스의 성격에 맞는 스케줄링 방식을 사용하여 최대한 효율을 높일 수 있는 방법을 선택한다.

  ![img](CPU스케줄링.assets/53879675-5f302f00-4052-11e9-86a2-c02ee03bac64.png)

## 비선점형 스케줄링

* FCFS(First Come First Server)
  * 준비 큐에 먼저 도착한 프로세스가 먼저 CPU를 점유하는 방식
  * CPU를 할당받으면 CPU 버스트가 완료될 때까지 CPU를 변환하지 않으며, 할당되었던 CPU가 변환될 때만 스케줄링이 이루어진다.





## Reference

[https://github.com/WooVictory/Ready-For-Tech-Interview/blob/master/Operating%20System/CPU%20%EC%8A%A4%EC%BC%80%EC%A4%84%EB%A7%81.md](https://github.com/WooVictory/Ready-For-Tech-Interview/blob/master/Operating%20System/CPU%20%EC%8A%A4%EC%BC%80%EC%A4%84%EB%A7%81.md)


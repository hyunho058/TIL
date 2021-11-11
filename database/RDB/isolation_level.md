# 트랜잭션 격리 수준

> Isolation Level

트랜잭션 격리수준이란 동시에 여러 트랜잭션이 처리될 때, 트랜잭션끼리 얼마나 서로 고립되어 있는지를 나타내는것.
(특정 트랜잭션이 다른 트랜잭션에 변경한 데이터를 볼 수 있도록 허용할지 말지를 결정하는것)

아래와같이 4가지의 격리수준으로 나뉜다. (아래로 내려갈수록 트랜잭션간 고립 정도가 높아지며, 성능이 떨어진다)

* Read uncommitted
* Read Committed
* Repeatable Read
* Serializable

일반적인 온라인 서비스에서는 ``Read Committed` 또는 `Repeatable Read`를 사용한다

## Read uncommitted

어떤 트랜잭션의 변경내용이 commit이나 rollback과 상관없이 다른 트랜잭션에서 보여진다.

## Read Committed

트랜잭션의 변경 니용이 commit되어야만 다른 트랜잭션에서 조회할 수 있다.
Oracle에서 기본으로 사용하고 있고, 가장 많이 사용되는 격리수준.

## Repeatable Read

트랜잭션이 시작되기 전에 커밋된 내용에 대해서만 조회할 수 있는 격리수준.
MySQL에서 기본으로 사용하고 있으며, 해당 격리수준에서는 Non-Repetable Read 부정합이 발생하지 않는다.

자신의 트랜잭션 번호보다 낮은 트랜잭션 번호에서 변경된(커밋) 것만 보게 되는것

## Serializable

가장 단순하고 업격한 격리수준.
순수한.select작업은 아무런 잠금을 걸지않고 동작하는데, 격리수준이 serializable일 경우 읽기 작업에도 공유 잠금을 설정하게 되고, 동시에 다른 트랜잭션에서 이 기록을 변경하지 못하게 된다. (=> 동시처리 능력이 다른 격리수준보다 떨어지고, 성능저하가 발생)
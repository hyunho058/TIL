# Grafana_spring boot_지표

## HTTP Request Count

 HTTP 요청의 순간적인 비율을 초 단위로 보여줍니다. 이는 애플리케이션의 트래픽 변화를 파악

```bash
irate(
  http_server_requests_seconds_count{
    instance="$instance", 
    application="$application", 
    uri!~".*(prometheus|health).*",
    namespace="$Namespace"
  }
  [$__rate_interval]
)
```

 특정 시간 간격 동안의 요청 수의 증가율을 나타내는 데 사용되는 함수와 레이블 선택자들로 구성됩니다.

- `irate()`: 이 Prometheus 함수는 지정된 시간 범위 내에서 각 시계열 데이터(HTTP 요청 수)의 순간적인 변화율을 계산합니다. 
  `irate`는 주로 빠르게 변화하는 메트릭에 사용되며, 짧은 시간 간격의 데이터 포인트 사이의 변화율을 계산합니다.
- `http_server_requests_seconds_count`:  메트릭은 Spring Boot 애플리케이션에서 수집된 HTTP 요청의 총 개수를 나타냅니다. 이 메트릭은 **시간에 따른 요청 수를 측정하여, 얼마나 많은 요청이 처리되었는지를 파악**하는 데 사용됩니다.
- `{}`: 중괄호 안에 있는 레이블 선택자는 메트릭을 필터링하는 데 사용됩니다.
  - `instance="$instance"`: Grafana 변수를 사용하여 특정 인스턴스에 대한 메트릭을 필터링합니다.
  - `application="$application"`: 특정 애플리케이션에 대한 메트릭을 필터링합니다.
  - `uri!~".*(prometheus|health).*"`: 정규 표현식을 사용하여 URI 경로가 "prometheus" 또는 "health"를 포함하는 요청을 제외합니다. 즉, Prometheus 자체 메트릭이나 헬스 체크 엔드포인트로의 요청은 측정에서 제외됩니다.
  - `namespace="$Namespace"`: 특정 쿠버네티스 네임스페이스에 대한 메트릭을 필터링합니다.
- `[$__rate_interval]`: Grafana에서 제공하는 내장 변수로, 메트릭을 계산할 때 사용할 시간 범위를 동적으로 설정합니다. Grafana는 패널이나 대시보드의 시간 범위 설정에 따라 이 값을 자동으로 조정합니다.

요약하자면, 이 쿼리는 선택된 인스턴스, 애플리케이션, 네임스페이스에 대하여 프로메테우스나 헬스 체크 엔드포인트로의



## HTTP Response Time

Spring Boot 애플리케이션의 HTTP 요청이 얼마나 빨리 처리되는지를 보여주는 지표입니다. 이 값은 응답 시간이 낮을수록 더 나은 성능을 의미합니다.

```bash
irate(
  http_server_requests_seconds_sum{
    instance="$instance", 
    application="$application", 
    exception="none", 
    uri!~".*(prometheus|health).*", 
    namespace="$Namespace"
  }
  [$__rate_interval]
) / 
irate(
  http_server_requests_seconds_count{
    instance="$instance", 
    application="$application", 
    exception="none",
    uri!~".*(prometheus|health).*", 
    namespace="$Namespace"
  }
  [$__rate_interval]
)
```


위 쿼리는 HTTP 요청에 대한 평균 응답 시간을 계산 합니다. 두 개의 `irate` 함수를 사용하여 초 단위로 된 총 응답 시간의 합계와 요청의 수를 각각 계산한 뒤, 이 두 값을 나누어 평균 응답 시간을 구합니다.(이렇게 계산된 평균 응답 시간은 일정 시간 간격에 대한 응답 시간의 평균을 나타냄)

- `irate(http_server_requests_seconds_sum{...}[$__rate_interval])`: `http_server_requests_seconds_sum` 메트릭은 각 HTTP 요청에 대한 처리 시간을 초 단위로 누적한 값입니다. `irate` 함수는 이 값의 변화율을 계산합니다. 즉, 주어진 `$__rate_interval` 시간 동안의 요청 처리 시간의 변화량입니다.
- `irate(http_server_requests_seconds_count{...}[$__rate_interval])`: `http_server_requests_seconds_count` 메트릭은 수행된 HTTP 요청의 수를 나타냅니다. 여기서도 `irate` 함수를 사용하여 요청 수의 변화율을 계산합니다.
- `{instance="$instance", application="$application", exception="none", uri!~".*(prometheus|health).*", namespace="$Namespace"}`: 이 레이블 선택자는 쿼리가 특정 인스턴스, 애플리케이션, 네임스페이스에 해당하고 예외가 발생하지 않은 요청(`exception="none"`)만을 필터링하도록 지정합니다. 또한, `uri` 레이블이 "prometheus" 또는 "health"를 포함하는 요청은 제외합니다.
- `[$__rate_interval]`: Grafana가 자동으로 설정하는 변수로, 쿼리를 수행하는 데 사용할 적절한 시간 간격을 나타냅니다.



## Connection Usage Time

**커넥션 하나가 평균적으로 얼마나 오래 사용되었는지를 초 단위로 보여줍니다**. (데이터베이스와의 커넥션 처리 효율성을 모니터링하는 데 유용한 지표)

``` bash
hikaricp_connections_usage_seconds_sum{
  instance="$instance", 
  application="$application", 
  pool="$hikaricp"
} / 
hikaricp_connections_usage_seconds_count{
  instance="$instance", 
  application="$application", 
  pool="$hikaricp"
}
```

Spring Boot 애플리케이션에서 HikariCP 커넥션 풀을 사용하는 데 걸리는 시간의 평균을 계산합니다. 
(HikariCP는 JDBC 커넥션 풀 라이브러리로, 데이터베이스와의 커넥션 관리를 담당.)

- `hikaricp_connections_usage_seconds_sum`: 이 메트릭은 HikariCP 커넥션 풀에서 커넥션을 사용한 총 시간의 합계를 초 단위로 나타냅니다.
- `hikaricp_connections_usage_seconds_count`: 이 메트릭은 HikariCP 커넥션 풀에서 커넥션을 사용한 횟수를 계수합니다.

쿼리에서 두 메트릭을 나누는 것은 각 커넥션 사용 사례에 대한 평균 사용 시간을 계산하는 것입니다:

- 분자(`hikaricp_connections_usage_seconds_sum`)는 사용된 총 시간을 나타냅니다.
- 분모(`hikaricp_connections_usage_seconds_count`)는 사용된 횟수를 나타냅니다.

 쿼리는 특정 인스턴스(`$instance`), 애플리케이션(`$application`), 그리고 커넥션 풀 이름(`$hikaricp`)에 대해 필터링됩니다. 이러한 레이블 선택자는 Grafana 대시보드에서 설정한 변수를 사용하여, 특정 환경 또는 커넥션 풀에 대한 데이터를 동적으로 검색할 수 있게 합니다.
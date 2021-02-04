# Elasticsearch

## Setting

* 설정 파일 위치
  * [elasticsearch디렉토리]\config\elasticsearch.yml
* 설정 항목 정보
  * cluster.name - 여러 노드를 묶기 위한 클러스트명
  * node.name - 인덱스 경로 (default - /paht/to/data)
  * path.logs - 노드와 클러스터에서 생성되는 로그 저장 경로 (default -  /path/to/logs)
  * path.repo - 인덱스를 백업하기 위한 스냅숏 경로
  * network.host - 특정 IP만 접근 허용하기 위한 설정
    * 모든 IP허용할 경우 0.0.0.0 으로 설정한다.
  * http.port - Elasticsearch API 호출을 위한 포트 번호 (default - 9200)
  * transport.tcp.port - 엘라스틱서치 클라이언트가 접근할 수 있는 TCP 포트 (default - 9300)
  * discovery.zen.minimum_master_nodes - 마스터 노드의 선출 기준이 되는 노드수 지정
  * node.master - 마스터 노드로써 동장 여부 (true/false)
  * node.data - 데이터 노드로써 동작 여부 (true/false)
# Fetch_Join

>* JPQL의 성능 튜닝을 위해 제공되는 조인
>* 연관된 엔티티 or 컬렉션을 SQL한번에 함께 조회하는 기능

* example

  * 회원과 팀이 N : 1 양방향 연관관계 매핑으로 동작한다.
  * 연관관계의 주인인 회원 엔티티에 @ManyToOne(fetch = FetchType.LAZY)을 통해 **지연로딩 전략**을 선택

  ```java
  @Entity
  @Getter
  public class Member {
      @Id @GeneratedValue
      private Long id;
      private String username;
      private int age;
      
      // 양방향 연관 필드
      // 연관관계 주인
      @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name = "TEAM_ID")
      private Team team;
  }
  
  @Entity
  @Getter
  public class Team {
  
      @Id @GeneratedValue
      private Long id;
  
      private String name;
  
      // 양방향 연관 필드
      @OneToMany(mappedBy = "team")
      private List<Member> memList = new ArrayList<Member>();
  
  }
  ```

  ```java
  // JPQL
  select m from Memeber m join fetch m.team
  
  // 실제 데이터베이스에 전송되는 SQL
  SELECT M.*, T.* FROM MEMBER M INNER JOIN TEAM T ON M.TEAM_ID = T.ID
  ```

  * JPQL에서 fetch join을 사용하면 실제 SQL에서는 두 테이블을 조인한 테이블을 조회할 수 있다. DB로 저송되는 실제 SQL을 보면 FetchType.EAGER을 사용한 즉시로딩 으로 조회한 것처럼 보인다
    * 엔티티에서 지연로딩 으로 세팅을 해도 JPQL에서 Fetch join으로 날리는 것이 우선 순이 이기 때문.


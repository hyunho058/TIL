# MySQL LIKE 검색

> like 검색시 %위치에 따른 성능 확인

* 검색 데이터 포함 하는 형태 `%검색어%`

![image](https://user-images.githubusercontent.com/58923731/180610208-9302374c-965c-4b3b-b0ba-b41cf5bb80bd.png)

* 검색 데이터로 끝나는 형태 `%검색어`

![image](https://user-images.githubusercontent.com/58923731/180610277-3ca97446-b7c7-44f3-ba10-498fefd3f2e7.png)

* 검색 데이터로 시작하는 형태 `검색어%`

![image](https://user-images.githubusercontent.com/58923731/180610306-b8ab7312-fe85-4e35-8277-73ad179f4468.png)





## FullText

> Full text를 이용한 단어 검색

```sql
-- DROP INDEX
DROP INDEX ft_index ON titles;
-- CREATE INDEX
ALTER TABLE titles ADD FULLTEXT INDEX idx_ft_title_and_body(title) WITH PARSER NGRAM;
-- SHOW INDEX
show index from titles;
```


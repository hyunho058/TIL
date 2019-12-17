# Github 특강1217

## github  web

### 실습 자료

* [김현호](https://hyunho058.github.io/#)

* [web page 템플릿](https://startbootstrap.com/)

* [wep icons](https://fontawesome.com/icons?d=gallery)

  

## 마크다운 기반 블로그

정적 템플릿 생성기 : md ->html/scc로 바꿔준다.



### Jekyll

* 오래사용
* 레퍼런스가 암ㅎ다
* 카카오 기술블로그 기반

### Gatsby

* 최신 프레임워크
* github octoverse 기준으로 2019년 가장많이 성장한 오픈소스 TOP10
* react, graphqul 이 반영되어 있따.

## 원격 저장소 활용하기

준비사항

* 멀티캠퍼스 컴퓨터에 git 저장소(database)

```b
$ git init
$ touch a. txt
$ git add .
$ git commit -m 'init'
```

* 집 컴퓨터에 git 저장소(database)

```ba
$git clone {url}
```

* github원격 저장소

### 시나리오

> 작업을 완료한 이후에는 항상 push,
>
> 작업을 시작하기 전에는 항상 pull......

![image-20191217135721513](image/image-20191217135721513.png)

1. 멀캠 도착

* 집에 복사한 파일이 있을경우

```ba
$ git pull origin master
```

2. 멀캠 작업

```ba
# 임의의 파일 수정/생성 등
$ git add .
$ git commit -m '메세지'
```

3. 멀캠 퇴근

```ba
$ git push origin master
```

4. 집 도착

* 집에 최초 처음 clone 폴더 만들기

```bas
$ git clone https://github.com/hyunho058/database.git
```

* clone파일이 있으면


```ba
$ git pull origin master
```

5. 집 작업

```ba
# 임의의 파일 수정/생성 등
$ git add .
$ git commit -m '메세지'
```

6. 집 나가기

```bas
$ git push origin master
```



###  멀캠이서 작업한거 집에서 내려받기

```bash
student@M16026 MINGW64 ~/Desktop/home
$ git clone https://github.com/hyunho058/database.git
Cloning into 'database'...
remote: Enumerating objects: 3, done.
remote: Counting objects: 100% (3/3), done.
remote: Compressing objects: 100% (2/2), done.
remote: Total 3 (delta 0), reused 3 (delta 0), pack-reused 0
Unpacking objects: 100% (3/3), done.

student@M16026 MINGW64 ~/Desktop/home
$ cd database/

student@M16026 MINGW64 ~/Desktop/home/database (master)
$ ls
'데이터베이스 설치법.txt'

//

student@M16026 MINGW64 ~/Desktop/home
$ git clone https://github.com/hyunho058/database.git
Cloning into 'database'...
remote: Enumerating objects: 3, done.
remote: Counting objects: 100% (3/3), done.
remote: Compressing objects: 100% (2/2), done.
remote: Total 3 (delta 0), reused 3 (delta 0), pack-reused 0
Unpacking objects: 100% (3/3), done.

student@M16026 MINGW64 ~/Desktop/home
$ cd database/

student@M16026 MINGW64 ~/Desktop/home/database (master)
$ ls
'데이터베이스 설치법.txt'

student@M16026 MINGW64 ~/Desktop/home/database (master)
$ git status
On branch master
Your branch is up to date with 'origin/master'.

Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        modified:   "\353\215\260\354\235\264\355\204\260\353\262\240\354\235\264\354\212\244 \354\204\244\354\271\230\353\262\225.txt"

no changes added to commit (use "git add" and/or "git commit -a")

student@M16026 MINGW64 ~/Desktop/home/database (master)
$ git add .

student@M16026 MINGW64 ~/Desktop/home/database (master)
$ git commit -m '집 - 복습'
[master dfa0725] 집 - 복습
 1 file changed, 1 insertion(+)

student@M16026 MINGW64 ~/Desktop/home/database (master)
$ git push
Enumerating objects: 5, done.
Counting objects: 100% (5/5), done.
Delta compression using up to 8 threads
Compressing objects: 100% (3/3), done.
Writing objects: 100% (3/3), 324 bytes | 324.00 KiB/s, done.
Total 3 (delta 0), reused 0 (delta 0)
To https://github.com/hyunho058/database.git
   adb73d3..dfa0725  master -> master


```

### 집에서 작업한 내용 멀캠에 내려받기

```bash

student@M16026 MINGW64 ~/Desktop/dataBase (master)
$ git pull origin master
remote: Enumerating objects: 5, done.
remote: Counting objects: 100% (5/5), done.
remote: Compressing objects: 100% (3/3), done.
remote: Total 3 (delta 0), reused 3 (delta 0), pack-reused 0
Unpacking objects: 100% (3/3), done.
From https://github.com/hyunho058/database
 * branch            master     -> FETCH_HEAD
   adb73d3..dfa0725  master     -> origin/master
Updating adb73d3..dfa0725
Fast-forward
 ...240\354\235\264\354\212\244 \354\204\244\354\271\230\353\262\225.txt" | 1 +
 1 file changed, 1 insertion(+)
```

```


```

### 에러 메시지

만약에 , 원격 저장소의 이력과 로컬 저장소의 이력이 다른 경우에는 아래의 메시지가 발생

```badsh
student@M16026 MINGW64 ~/Desktop/cloneTest (master)
$ git push origin master
To https://github.com/hyunho058/cloneTest.git
 ! [rejected]        master -> master (fetch first)
error: failed to push some refs to 'https://github.com/hyunho058/cloneTest.git'
#원격저장소의 작업(work - commit)내용과 로컬 내용이 다르다.
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
# 원격 저장소 변경(change)사항을 통합하고 다시push해라.
# git pull origin master....
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
```

이 메시지를 보게된다면, 로컬에서 git log, 원격 저장소(github)의 커밋 이력들을 확인하고 다른 부분을 체크하자!!

```bash
$ git pull origin master
```

통합후,

```bash
$ git push origin master
```

* pull로 데이터를 받아오고 새로운 창이 뜨면 :wq로 나가고 push로 다시 파일을 github에 올린다.

## branch

### 상황 1. fast-foward

> fast-foward는 feature 브랜치 생성된 이후 master 브랜치에 변경 사항이 없는 상황

1. feature/test branch 생성 및 이동

   ```bash
   $ git checkout -b feature/test
   
   ```

   

2. 작업 완료 후 commit

   ```bash
   $ touch test.txt
   $ git add test.txt
   $ git commit -m 'Complete test'
   student@M16026 MINGW64 ~/Desktop/naver (feature/test)
   $ git commit -m 'Complate test'
   [feature/test bda82d8] Complate test
    1 file changed, 0 insertions(+), 0 deletions(-)
    create mode 100644 test.txt
   
   ```

3. master 이동

   ```bash
   (feature/test) $ git checkout master
   Switched to branch 'master'
   (master) $
   student@M16026 MINGW64 ~/Desktop/naver (feature/test)
   $ git checkout master
   Switched to branch 'master'
   ```

   

4. master에 병합

   ```bash
   (master) $git merge feature/test
   student@M16026 MINGW64 ~/Desktop/naver (master)
   $ git merge feature/test
   Updating b972fd1..bda82d8
   Fast-forward
    test.txt | 0
    1 file changed, 0 insertions(+), 0 deletions(-)
    create mode 100644 test.txt
   
   ```

   

5. 결과 -> fast-foward (단순히 HEAD를 이동)

6. branch 삭제

------

### 상황 2. merge commit

> 서로 다른 이력(commit)을 병합(merge)하는 과정에서 다른 파일이 수정되어 있는 상황
>
> git이 auto merging을 진행하고, commit이 발생된다.

1. feature/signout branch 생성 및 이동

   ```bash
   # $ git branch feature/signout #생성
   # $ git checkout feature/signout #이동
   # $ git checkout -b feature/signout #생성및 이동
   ```

2. 작업 완료 후 commit

   ```bash
   $ touch signout.html
   $ git add .
   $ git commit -m 'Complete signout'
   ```

3. master 이동

   ```bash
   student@M16026 MINGW64 ~/Desktop/naver (master)
   $ git log --oneline
   bda82d8 (HEAD -> master) Complate test
   b972fd1 Complete index page
   2c3586e Add README
   
   ```

   

4. *master에 추가 commit 이 발생시키기!!*

   - **다른 파일을 수정 혹은 생성하세요!**

     ```bash
     $ stich hotfix.txt
     $ git add .
     $ git commit -m 'Hotfix on master'
     ```

     ```bash
     $ git log --noeline
     student@M16026 MINGW64 ~/Desktop/naver (master)
     $ git log --oneline
     bff9ab0 (HEAD -> master) Hotfix on master
     bda82d8 Complate test
     b972fd1 Complete index page
     2c3586e Add README
     
     ```

     

5. master에 병합

   ```bash
   (master) $ git merge feature/signout
   ```

6. 결과 -> 자동으로 *merge commit 발생*

   - vim 편집기 화면이 나타납니다.
   - 자동으로 작성된 커밋 메시지를 확인하고, `esc`를 누른 후 `:wq`를 입력하여 저장 및 종료를 합니다.
     - `w` : write
     - `q` : quit
   - 커밋이 확인 해봅시다.

7. 그래프 확인하기

   ```bash
   $ git log --oneline --graph
   student@M16026 MINGW64 ~/Desktop/naver (master)
   $ git log --oneline --graph
   *   8721802 (HEAD -> master) Merge branch 'feature/signout'
   |\
   | * 75112c3 (feature/signout) Complete signout
   * | bff9ab0 Hotfix on master
   |/
   * bda82d8 Complate test
   * b972fd1 Complete index page
   * 2c3586e Add README
   ```

8. branch 삭제

   ```bash
   (master)$ git branch -d {branch name}
   & git branch -d feature/signout
   ```

   

------

### 상황 3. merge commit 충돌

> 서로 다른 이력(commit)을 병합(merge)하는 과정에서 동일 파일이 수정되어 있는 상황
>
> git이 auto merging을 하지 못하고, 해당 파일의 위치에 라벨링을 해준다.
>
> 원하는 형태의 코드로 직접 수정을 하고 merge commit을 발생 시켜야 한다.

1. feature/board branch 생성 및 이동

   ```bash
   $ git checkout -b feature/board
   ```

2. 작업 완료 후 commit

   ```bash
   $ touch board.html
   # README.md 수정
   $ git add .
   $ git commit -m 'Complete board & Update README'
   $ git log --oneline
   
   student@M16026 MINGW64 ~/Desktop/naver (feature/board)
   $ git commit -m 'Complete board & Update README'
   [feature/board 5139cc6] Complete board & Update README
    2 files changed, 2 insertions(+)
    create mode 100644 board.html
   
   $ git log --oneline
   5139cc6 (HEAD -> feature/board) Complete board & Update README
   8721802 (master) Merge branch 'feature/signout'
   bff9ab0 Hotfix on master
   75112c3 Complete signout
   bda82d8 Complate test
   b972fd1 Complete index page
   2c3586e Add README
   ```

3. master 이동

   ```bash
   $ git checkout master
   ```

4. *master에 추가 commit 이 발생시키기!!*

   - **동일 파일을 수정 혹은 생성하세요!**

     ```bash
     # README.md 수정
     (master)$ git add .
     $ git commit -m 'Update README on master'
     ```

     ```bash
     $ git log --oneline
     14eca5e (HEAD -> master) Update README on master
     8721802 Merge branch 'feature/signout'
     bff9ab0 Hotfix on master
     75112c3 Complete signout
     bda82d8 Complate test
     b972fd1 Complete index page
     2c3586e Add README
     ```

5. master에 병합

   ```bash
   $ git merge feature/board
   Auto-merging README.md
   CONFLICT (content): Merge conflict in README.md
   Automatic merge failed; fix conflicts and then commit the result. 
   
   $ git status
   On branch master
   You have unmerged paths.
     (fix conflicts and run "git commit")
     (use "git merge --abort" to abort the merge)
   
   Changes to be committed:
           new file:   board.html
   
   Unmerged paths:
     (use "git add <file>..." to mark resolution)
           both modified:   README.md
   
   ```

6. 결과 -> *merge conflict발생*

7. 충돌 확인 및 해결

   ```
   <<<<<<< HEAD
   master에서 추가 함.
   
   =======
   >>>>>>> feature/board
   ```

   * HEAD(현재상황),아래에 feature/ooard

8. merge commit 진행

   ```
   $ git add .
   $ git commit
   ```

   - vim 편집기 화면이 나타납니다.
   - 자동으로 작성된 커밋 메시지를 확인하고, `esc`를 누른 후 `:wq`를 입력하여 저장 및 종료를 합니다.
     - `w` : write
     - `q` : quit
   - 커밋이 확인 해봅시다.

9. 그래프 확인하기

   ```bash
   $ git log --oneline --graph
   *   3661dc3 (HEAD -> master) Merge branch 'feature/board'
   |\
   | * 5139cc6 (feature/board) Complete board & Update README
   * | 14eca5e Update README on master
   |/
   *   8721802 Merge branch 'feature/signout'
   |\
   | * 75112c3 Complete signout
   * | bff9ab0 Hotfix on master
   |/
   * bda82d8 Complate test
   * b972fd1 Complete index page
   * 2c3586e Add README
   ```

10. branch 삭제

```b
$ git branch -d feature/board
Deleted branch feature/board (was 5139cc6).
```


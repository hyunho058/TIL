# Gtihub 특강1218

## Git status&undoing

### 1. commit

```bash
#  WD o, staging area X
$ git commit
# commit 할 것이 없지만, ->stagingarea가 비어있음.
# untracked file 이 있다. ->gitcommit 이려에 담기지 않은 파일은 있음.
$ git commit
On branch master
Initial commit
Untracked files:
        a.txt
nothing added to commit but untracked files present

# WD x, staging area X
#어떠한 변경 사항도 없음.
student@M16026 MINGW64 ~/Desktop/git/1218 (master)
$ git commit
On branch master
Initial commit
nothing to commit

```

### 2. status

1. 새로 파일 생성 한 경우

```bash
$ git status
On branch master
No commits yet
# commit 이력에 담긴 적 없는 파일들
Untracked files:
# commit 될 목록(staging area)에 추가하려면, git add <file>..."
  (use "git add <file>..." to include in what will be committed)
        a.txt
nothing added to commit but untracked files present (use "git add" to track)

```

2. add 한 이후

```bash
$ git add a.txt
$ git status
On branch master
# 커밋될 변경사항들(changes)
No commits yet

Changes to be committed:
  (use "git rm --cached <file>..." to unstage)
        new file:   a.txt
```

### commit 메세지 작성하기

> 부제: vim 활용 기초

```bash
$ git commit
# Please enter the commit message for your changes. Lines starting
# with '#' will be ignored, and an empty message aborts the commit.
#
# On branch master
#
# Initial commit
#
# Changes to be committed:
#       new file:   a.txt
#
```

* 편집(입력)모드 : i

  * 문서 편집가

* 명령 모드 :esc

  * dd :해당 출 삭제

  * :wq :저장 및 종료

    * w: write
    * q: quit

    :q! :강제종료

    * q: quit
    * !: 강제종료

    [vim](https://vim-adventures.com/)

```bash
$ git commit -m 'commit message'
```

	* 커밋 메세지는 항상 해당 작업 이력을 나타낼 수있도록 작성한다.
	* 일관적인 포멧으로 작성하려고 노력한다.

### log

> 커밋은 해시 값(hash value)에 의해 구분된다.
>
>  SHA-1 해시 알고리즘을 사용하여 표현한다.

```bash
$ git log
commit 071e2e9abad901e21e48d4fa7ce27af15e13f4ff (HEAD -> master)
Author: hyunho058 <hyunho058@naver.com>
Date:   Wed Dec 18 10:15:05 2019 +0900
    Add b.txt
commit 1be74bcf59ddf8030e44cb6a92da13d7795a0e18
Author: hyunho058 <hyunho058@naver.com>
Date:   Wed Dec 18 09:43:22 2019 +0900
    Add a.txxt
    *a.txt 내용추가
    *asdfa
```

```bash
$ git log --oneline
$ git log -1 #최신기준 
commit 071e2e9abad901e21e48d4fa7ce27af15e13f4ff (HEAD -> master)
Author: hyunho058 <hyunho058@naver.com>
Date:   Wed Dec 18 10:15:05 2019 +0900
    Add b.txt
$ git log --oneline --graph
$ git log -1 --oneline
071e2e9 (HEAD -> master) Add b.txt

```

### commit undoing

1. 커밋 메시지 수정

```bash
$ git commit --amend
[master cac7cff] b.txt
 Date: Wed Dec 18 10:15:05 2019 +0900
 1 file changed, 0 insertions(+), 0 deletions(-)
 create mode 100644 b.txt

```

```bash
# 커밋 어멘드 전
$ git log --oneline
071e2e9 (HEAD -> master) Add b.txt
1be74bc Add a.txxt *a.txt 내용추가 *asdfa
# 커밋 어멘드 후
$ git log --oneline
cac7cff (HEAD -> master) b.txt
1be74bc Add a.txxt *a.txt 내용추가 *asdfa

```

커밋 메시지 수정하는 경우 해시 값이 변경 되므로, 다른 이력으로 관리가 된다.

따라서, 공게된 저장소(원격저장소)에 이미 push된 경우 절대 수정해서는 안된다.

2. 특정 파일 추가하기
   * c. txt 파일을 같이 커밋을 하려고 했는데, add 를 하지 않고 커밋 해버렸다.

```bash
$ git add c.txt
$ git commit --amend

student@M16026 MINGW64 ~/Desktop/git/1218 (master)
$ touch c.txt

student@M16026 MINGW64 ~/Desktop/git/1218 (master)
$ git status
On branch master
Untracked files:
  (use "git add <file>..." to include in what will be committed)
        c.txt
nothing added to commit but untracked files present (use "git add" to track)

student@M16026 MINGW64 ~/Desktop/git/1218 (master)
$ ls
a.txt  b.txt  c.txt

student@M16026 MINGW64 ~/Desktop/git/1218 (master)
$ git add c.txt

student@M16026 MINGW64 ~/Desktop/git/1218 (master)
$ git commit --amend
[master ebaff97] c.txt,b.txt
 Date: Wed Dec 18 10:15:05 2019 +0900
 2 files changed, 0 insertions(+), 0 deletions(-)
 create mode 100644 b.txt
 create mode 100644 c.txt

student@M16026 MINGW64 ~/Desktop/git/1218 (master)
$ git status
On branch master
nothing to commit, working tree clean

student@M16026 MINGW64 ~/Desktop/git/1218 (master)
$ git log --oneline -1
ebaff97 (HEAD -> master) c.txt,b.txt

```

### Staging area

​	1. 커밋 이력이 있는 파일 수정하는경우

```bash
$ git status
On branch master
#변경 사항인데(WD O), staging area X
Changes not staged for commit:
#git add로 staging area로 보낼 수 있다.
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        modified:   a.txt

no changes added to commit (use "git add" and/or "git commit -a")

$ git add a.txt
$ git status
On branch master
Changes to be committed:
# unstage 하기 위해서 (staging area 에서 제외하기 위해서)
  (use "git restore --staged <file>..." to unstage)
        modified:   a.txt
$ git restore --staged a.txt
$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        modified:   a.txt

no changes added to commit (use "git add" and/or "git commit -a")
```

### add 취소하기

```bash
$ git restore --staged<file>
```

* 구버전의 git 에서는 아래의 명령어를 사용 해야 한다.

```bash
$ git reset HEAD <file>
```

### Working Directory(WD)변화 삭제하기

> git 에서는 모든 commit 시점으로 되돌릴 수 는 있따.
>
> 다만, WD 삭제하는 것은 되돌릴 수가 없다.

```bash
$ git restore <file>
$ git restore .
```

* 구버전 git 에서는 아래의 명령어를 사용해야 한다.

```bash
$ git checkout --<file>
```



github student pack

jetbrains student

notion student

## Stash

> 변경사항을 임시로 저장 해놓는 공간.
>
> 마지막 커밋 시점으로 되돌려준다.

1. feature branch 에서 a.txt 변경후 커밋
2. master branch 에서 a.txt 수정 (add 나 commit 없이)
3. merge

```bash
$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        modified:   a.txt

no changes added to commit (use "git add" and/or "git commit -a")

student@M16026 MINGW64 ~/Desktop/git/1218 (master)
$ git merge freature
error: Your local changes to the following files would be overwritten by merge:
        a.txt
Please commit your changes or stash them before you merge.
Aborting
Updating 4161dfa..12dc618

```

### 명령어

stash 저장

```bash
$ git stash
Saved working directory and index state WIP on master: 4161dfa Edit a.txt
```

stash 목록

```bash
$ git stash list
stash@{0}: WIP on master: 4161dfa Edit a.txt
```

stash 불러오기

```bash
$ git stash pop # 불러오기 + 목록에서 삭제
# git stash apply # 불러오기
# git stash drop # 목록에서 삭제
```

### 해결

```bash
$ git stash #임시 공간 저장
$ git merge feature # 병합
$ git stash pop #임시 공간에서 불러오기
#충골 발생, 해결 후 작업 이어가기
```

> merge 전에 변경이력이 있으면 commit 또는 stash 해야하는데 작업이 미완성일떄 잠시 작업전 상황에서 다른 작업을 해야하면 stash를 하는게 좋다.

## Reset VS Revert

### Reset

> 공개된 저장소(원격저장소)에 push된 이력은 절대 reset하지 않는다.

```bash
$ git reset {해시코드}
```

* rlqhs(--mixed) : 이후 변경 사항을 Wd에 유지시켜줌.

* --hard : 이후 변경 사항이 모두 삭제됨.

  주의

* --soft : 지금 작업하고 있는 내용 WD및 병경사항을 WD에 유지시켜줌.

### Revert

> 해당 커밋으로 되돌렸다는 이력(revert commit)을 남긴다.

```bash
$ git revert {해시코드}
```

* vim ->커밋 메시지 작성

  

[Git Book](https://git-scm.com/book/ko)

[skgit](https://github.com/metatron-app/metatron-discovery/blob/master/README.md)


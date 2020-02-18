# Linux 명령어

* cd (change directory)
* ls
* wget - 다운로드
* yum 다운로드+설치

```
(/etc/yum.repos.d/*.repo 파일)  = /앞에 c드라이브 안적어줘도 됨.
```

* gedit

windoiws = 폴더

linux = 디렉토리(폴더)

* pwd - 현제 작업중인 디렉토리 명

```
[root@localhost network-scripts]# pwd
/etc/sysconfig/network-scripts
```



## 버전 확인

* cat /etc/redhat-release

```
[root@localhost network-scripts]# cat /etc/redhat-release 
CentOS Linux release 7.0.1406 (Core)
```

## /...

* /root -->root 계정 관련 저장 디렉토리

* /hone/사용자계정/해당 계정 관련 저장 디렉토리

* /etc/xxx:설정파일 젖아 디렉토리

* /usr/...: 모든 계정 사용 가능한 디렉토리

## system 종료

* shutdown -P now - 지금 시스템 종료

* halt -p - 지금 시스템 종료

* init 0 - 지금 시스템 종료

* poweroff - 지금 시스템 종료

* shutdown -P +10 - 10분 뒤 시스템 종료

## system 재시작

* shutdown -r now

* **reboot** - 설정파일 변경 사항 반영(가장 많이 사용)

* init 6 ==> (0~6)**런레벨** 값을 넣을 수 있다.

## 런레벨

> 기본 값이 5로 설정 되어 있어 재푸팅하면 Graphic 화면이 가장 먼져 나온다.

## 자동완성과 히스토리

* history -지금까지 썻던 명령어 표시
* history -c - 썻던 명령어 삭제

## 파일정보 확인

* ls 파일명 - 파일 정보 확인

* cat 파일명 - 파일 내용 출력(입력X)

* gedit 파일명 - 파일 내용 출력(입력O)

* vi 파일명 = gedit

## 도움말 사용

> man (명령어)  ==> man (manual)
>
> 리눅스에 체계화된 도움말
>
> window + space 는 한/영 바꾸는 key

man ls

* ls -l

```
[root@localhost ~]# pwd
/root
[root@localhost ~]# ls
anaconda-ks.cfg       공개      문서      비디오  서식
initial-setup-ks.cfg  다운로드  바탕화면  사진    음악
[root@localhost ~]# ls -l
합계 8
-rw-------. 1 root root 1365  2월 18  2020 anaconda-ks.cfg  앞에 d가 없으면 파일명이다(d가 있으면 디렉토리)
-rw-r--r--. 1 root root 1582  2월 18 11:11 initial-setup-ks.cfg
drwxr-xr-x. 2 root root    6  2월 18 11:12 공개
drwxr-xr-x. 2 root root    6  2월 18 11:12 다운로드
drwxr-xr-x. 2 root root    6  2월 18 11:12 문서
drwxr-xr-x. 2 root root    6  2월 18 11:12 바탕화면
drwxr-xr-x. 2 root root    6  2월 18 11:12 비디오
drwxr-xr-x. 2 root root    6  2월 18 11:12 사진
drwxr-xr-x. 2 root root    6  2월 18 11:12 서식
drwxr-xr-x. 2 root root    6  2월 18 11:12 음악
```

## 기본 명령어

* ls

  * -a 현제 디렉토리의 목록
  * -l 현재 디렉토리의 목록을 자세히 보여줌

* cd

  * ```
    [root@localhost ~]# cd /etc/sysconfig
    [root@localhost sysconfig]# pwd
    /etc/sysconfig
    [root@localhost sysconfig]# cd ..
    [root@localhost etc]# pwd
    /etc
    [root@localhost etc]# cd ..
    [root@localhost /]# pwd
    /
    [root@localhost /]# cd etc/sysconfig
    [root@localhost sysconfig]# pwd
    /etc/sysconfig
    [root@localhost sysconfig]# cd /root
    [root@localhost ~]# pwd
    /root
    [root@localhost ~]# cd etc/sysconfig
    bash: cd: etc/sysconfig: 그런 파일이나 디렉터리가 없습니다    cd /root/ect ... 라고 생각해서 파일을 찾지 못하는거다.
    ```

* pwd

* rm - 파일+디렉토리(하위디렉토리)삭제.

  * rm -fr/ 하드디스크를 다 제거하는 명령어 (사용해서는 안된다.)
  * rmdir - 디렉토리 삭제(비어있는 디렉토리만 삭제)
  * mkdir - 디렉토리 삭제 (비어있는 디렉토리만 삭제)

  ```
  [root@localhost ~]# mkdir testdir
  [root@localhost ~]# ls
  anaconda-ks.cfg       sample.txt  공개      문서      비디오  서식
  initial-setup-ks.cfg  testdir     다운로드  바탕화면  사진    음악
  [root@localhost ~]# touch testdir/test.txt
  [root@localhost ~]# gedit testdir/test.txt
  Fontconfig warning: Directory/file mtime in the future. New fonts may not be detected.
  [root@localhost ~]# ls testdir/test.txt
  testdir/test.txt
  [root@localhost ~]# gedit testdir/test.txt
  [root@localhost ~]# rmdir testdir
  rmdir: failed to remove `testdir': 디렉터리가 비어있지 않음
  [root@localhost ~]# rm testdir
  rm: cannot remove `testdir': 디렉터리입니다
  [root@localhost ~]# rm -r testdir
  rm: descend into directory `testdir'? y
  rm: remove 일반 파일 `testdir/test.txt'? y
  rm: remove 일반 파일 `testdir/test.txt~'? y
  rm: remove 디렉토리 `testdir'? y
  [root@localhost ~]# ls
  anaconda-ks.cfg       sample.txt  다운로드  바탕화면  사진  음악
  initial-setup-ks.cfg  공개        문서      비디오    서식
  [root@localhost ~]# 
  ```

* cat

> 1. cat 파일명1 파일명2 > 파일명3(내용 삭제) ==> 파일명3에 내용을 지우고 파일1,2,를 3에 저장
>
> 2. cat 파일명1 파일명2 >> 파일명3(내용 삭제X) ==> 파일명3에 내용을 지우지 않고 파일1,2,를 3에 저장

1. 

```
[root@localhost ~]# cat anaconda-ks.cfg initial-setup-ks.cfg > all.cfg
```

2. 

```
[root@localhost ~]# cat anaconda-ks.cfg initial-setup-ks.cfg >> all.cfg
```



* cp

  ```
  [root@localhost ~]# cp sample.txt /root/다운로드
  [root@localhost ~]# ls sample.txt
  sample.txt
  [root@localhost ~]# ls
  anaconda-ks.cfg       sample.txt  다운로드  바탕화면  사진  음악
  initial-setup-ks.cfg  공개        문서      비디오    서식
  [root@localhost ~]# cd /root/다운로드
  [root@localhost 다운로드]# ls sample.txt
  sample.txt
  [root@localhost 다운로드]# 
  ```

* touch

  * 크기가 0인 파일을 만들때 사용.

  ```
  [root@localhost ~]# touch sample.txt
  [root@localhost ~]# ls -l sample.txt
  -rw-r--r-- 1 root root 0  2월 18 14:44 sample.txt
  ```

* mv

  ```
  [root@localhost 다운로드]# mv sample.txt /root/문서
  [root@localhost 다운로드]# pwd
  /root/다운로드
  [root@localhost 다운로드]# ls
  [root@localhost 다운로드]# ls /root/문서
  sample.txt
  ```

* head 

  > 위에서부터 10줄만 보여줌

  ```
  [root@localhost ~]# head 10 all.cfg 
  head: cannot open `10' for reading: 그런 파일이나 디렉터리가 없습니다
  ==> all.cfg <==
  #version=RHEL7
  # System authorization information
  auth --enableshadow --passalgo=sha512
  
  # Use CDROM installation media
  cdrom
  # Run the Setup Agent on first boot
  firstboot --enable
  ignoredisk --only-use=sda
  # Keyboard layouts
  
  ```

* tail 

  > 뒤에서 10줄 보여줘

  ```
  [root@localhost ~]# tail 10 all.cfg 
  tail: cannot open `10' for reading: 그런 파일이나 디렉터리가 없습니다
  ==> all.cfg <==
  @print-client
  @ruby-runtime
  @virtualization-client
  @virtualization-hypervisor
  @virtualization-tools
  @web-server
  @x11
  
  %end
  ```

  

* more

* file\

  > 파일에 대한 정보 출력

  ```
  [root@localhost ~]# file all.cfg 
  all.cfg: ASCII text
  ```



* clear

> 화면 reset



## 사용자 관리 파일속성

> 사용권한 3가지 (파일마다 다르게 적용 가능)
>
> 1. r - read
> 2. w - write
> 3. x - execute

```
-rw-r--r--  1 root root 5894  2월 18 15:25 all.cfg
(root rPwjd)-rw-  (root계정과 같은 그룹에 속한 사용자가있으면)r--  (다른 사용자 그룹도)r--

```

* r - read
* w - write
* x - execute

### 파일 허가권

* chmod 000(rwx)
  * 0
  * 1 - x
  * 2 - w
  * 4 - r
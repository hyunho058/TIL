# Tomcat 설치





## 파이어폭스 설치

> 최신 버전으로 적용

```
[root@localhost ~]# pwd
/root
[root@localhost ~]# cd 다운로드
[root@localhost 다운로드]# ls
apache-tomcat-9.0.30.tar.gz  jdk-8u241-linux-x64.tar.gz
firefox-72.0.2.tar.bz2       oracle-xe-11.2.0-1.0.x86_64.rpm.zip
hadoop-1.2.1.tar.gz
[root@localhost 다운로드]# tar cvf firefox-72.0.2.tar.bz2 
				.
			압축풀기
				.

[root@localhost 다운로드]# ls -l
합계 636400
-rwxrw-rw- 1 root root  11026056  2월 18 08:36 apache-tomcat-9.0.30.tar.gz
drwxr-xr-x 8 root root      4096  2월 20 15:09 firefox
-rwxrw-rw- 1 root root  66348863  2월 18 08:38 firefox-72.0.2.tar.bz2
-rwxrw-rw- 1 root root  63851630  2월 18 08:38 hadoop-1.2.1.tar.gz
-rwxrw-rw- 1 root root 194545143  2월 18 08:38 jdk-8u241-linux-x64.tar.gz
-rwxrw-rw- 1 root root 315891481  2월 18 08:38 oracle-xe-11.2.0-1.0.x86_64.rpm.zip

```

![image-20200220151524475](image/image-20200220151524475.png)

root 안에 파일이있으면 다른 사용자가 사용 불가능 ==>

```
[root@localhost 다운로드]# cd /root/다운로드
[root@localhost 다운로드]# ls -l
합계 636400
-rwxrw-rw- 1 root root  11026056  2월 18 08:36 apache-tomcat-9.0.30.tar.gz
drwxr-xr-x 8 root root      4096  2월 20 15:09 firefox
-rwxrw-rw- 1 root root  66348863  2월 18 08:38 firefox-72.0.2.tar.bz2
-rwxrw-rw- 1 root root  63851630  2월 18 08:38 hadoop-1.2.1.tar.gz
-rwxrw-rw- 1 root root 194545143  2월 18 08:38 jdk-8u241-linux-x64.tar.gz
-rwxrw-rw- 1 root root 315891481  2월 18 08:38 oracle-xe-11.2.0-1.0.x86_64.rpm.zip
[root@localhost 다운로드]# mv firefox /user/local
mv: cannot move `firefox' to `/user/local': 그런 파일이나 디렉터리가 없습니다
[root@localhost 다운로드]# mv firefox /usr/local
[root@localhost 다운로드]# ls -l /usr/local
합계 8
drwxr-xr-x. 2 root  root     6  6월 10  2014 bin
drwxr-xr-x. 2 root  root     6  6월 10  2014 etc
drwxr-xr-x  8 root  root  4096  2월 20 15:09 firefox
drwxr-xr-x. 2 root  root     6  6월 10  2014 games
drwxr-xr-x. 2 root  root     6  6월 10  2014 include
drwxr-xr-x  7 10143 10143 4096 12월 11 19:39 jdk1.8
drwxr-xr-x. 2 root  root     6  6월 10  2014 lib
drwxr-xr-x. 2 root  root     6  6월 10  2014 lib64
drwxr-xr-x. 2 root  root     6  6월 10  2014 libexec
drwxr-xr-x. 2 root  root     6  6월 10  2014 sbin
drwxr-xr-x. 5 root  root    46  2월 18 19:53 share
drwxr-xr-x. 2 root  root     6  6월 10  2014 src
[root@localhost 다운로드]# ln -s /usr/local/firefox/firefox /usr/local/bin

[root@localhost 다운로드]# cd /usr/local/bin
[root@localhost bin]# ls -l
합계 0
lrwxrwxrwx 1 root root 26  2월 20 15:15 firefox -> /usr/local/firefox/firefox
[root@localhost bin]# firefox -version
Mozilla Firefox 72.0.2
[root@localhost bin]# firefox

(firefox:19336): GLib-GObject-WARNING **: g_object_get_valist: object class `GtkSettings' has no property named `gtk-decoration-layout'

(/usr/local/firefox/firefox-bin:19569): GLib-GObject-WARNING **: g_object_get_valist: object class `GtkSettings' has no property named `gtk-decoration-layout'

(/usr/local/firefox/firefox-bin:19584): GLib-GObject-WARNING **: g_object_get_valist: object class `GtkSettings' has no property named `gtk-decoration-layout'

(/usr/local/firefox/firefox-bin:19659): GLib-GObject-WARNING **: g_object_get_valist: object class `GtkSettings' has no property named `gtk-decoration-layout'

(/usr/local/firefox/firefox-bin:19765): GLib-GObject-WARNING **: g_object_get_valist: object class `GtkSettings' has no property named `gtk-decoration-layout'
```

<img src="image/image-20200220152121949.png" alt="image-20200220152121949" style="zoom:67%;" />

<img src="image/image-20200220152550955.png" alt="image-20200220152550955" style="zoom: 67%;" />



## firefox 충돌 에러 발생시

1. /root/다운로드/압축해제(파이어폭스)
2. ./root/다운로드/firefox/firefox



## Tomcat 설치

```
[root@localhost ~]# mv /root/다운로드/tomcat9 /usr/local
[root@localhost ~]# ls -l /usr/local/
합계 12
drwxr-xr-x.  2 root  root    20  2월 20 15:15 bin
drwxr-xr-x.  2 root  root     6  6월 10  2014 etc
drwxr-xr-x  10 root  root  4096  2월 20 15:21 firefox
drwxr-xr-x.  2 root  root     6  6월 10  2014 games
drwxr-xr-x.  2 root  root     6  6월 10  2014 include
drwxr-xr-x   7 10143 10143 4096 12월 11 19:39 jdk1.8
drwxr-xr-x.  2 root  root     6  6월 10  2014 lib
drwxr-xr-x.  2 root  root     6  6월 10  2014 lib64
drwxr-xr-x.  2 root  root     6  6월 10  2014 libexec
drwxr-xr-x.  2 root  root     6  6월 10  2014 sbin
drwxr-xr-x.  5 root  root    46  2월 18 19:53 share
drwxr-xr-x.  2 root  root     6  6월 10  2014 src
drwxr-xr-x   9 root  root  4096  2월 20 15:49 tomcat9
 ////////////////Tomcat 시작///////////////
[root@localhost ~]# /usr/local/tomcat9/bin/startup.sh 
Using CATALINA_BASE:   /usr/local/tomcat9
Using CATALINA_HOME:   /usr/local/tomcat9
Using CATALINA_TMPDIR: /usr/local/tomcat9/temp
Using JRE_HOME:        /usr/local/jdk1.8
Using CLASSPATH:       /usr/local/tomcat9/bin/bootstrap.jar:/usr/local/tomcat9/bin/tomcat-juli.jar
Tomcat started.      ====> 이 부분 확인

```

![image-20200220155552912](image/image-20200220155552912.png)

http://localhost:8080 검색 => 아래 화면 출력

![image-20200220155805414](image/image-20200220155805414.png)


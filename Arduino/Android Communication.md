# Android Communication

* 서비스를 만들어서 Thread 이용해 Network socket연결
* Java Network Server(Server Socket)
* 아두이노와 Serial 통신

## Android

* Thread를 이용해 Network Socket Connection

  * Stream을 이용해 Data전달

* Thread가 사용할 SharedObject 만든다

  * SharedObject 를 이용해 POP, PUT 수행
  * 동기화 처리를 위한 monitor 객체 생성
  * 버튼이 눌렸을때 Monitor의 Lock이 풀려 데이터 전달 
    * wait()
    * notify()

  ```java
  public void put(String line){
      synchronized (monitor){
          list.addLast(line); // LinkedList 끝에 데이터를 넣는다
          Log.v(TAG,"SharedObject_put()=="+line);
          monitor.notify(); //가지고 있는 모니터를 놔주고 wait()이 풀리면서 코드 진행
      }
  }
  public String pop(){
      String result = "";
      synchronized (monitor){
          if (list.isEmpty()){
              //list안에 문자열이 없으니까 일시 대기 (데이터가 들어올떄까지 대기)
              //Monitor 를 이용한 wait()을 이용해 일시정지(Locking)
              try {
                  //모니터를 잡고있다고 wait()이 걸리면 모니터를 놓고 모니터가 notify() 를 호출 할떄까지 기다린다
                  monitor.wait(); 
                  result = list.removeFirst(); // 큐구조이기 때문에 앞에 데이터를 빼간다
              }catch (InterruptedException e){
                  Log.v(TAG,"InterruptedException=="+e);
              }
          }else {
              result = list.removeFirst();
              Log.v(TAG,"SharedObject_put()=="+list);
          }
      }
      return result;
  }
  ```

## Java Server

### Android Communication

```java
Thread thread = new Thread(new Runnable() {
    @Override
    public void run() {
        try {
            server = new ServerSocket(1234);
            printMsg("[서버소켓 기동]");
            socket = server.accept();
            printMsg("[클라이언트 접속");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pr = new PrintWriter(socket.getOutputStream());
            String msg = "";
            while (true) {
                if((msg = br.readLine())!=null) {
                    if(msg.equals("ON")) {
                        printMsg("LED ON");
                        //Arduino 와의 Stream을 통해 Data Communication
                        bw.write(msg,0,msg.length()); //0 번째부터 길이만큼 보낸다
                        bw.flush();
                    }
                    if(msg.equals("OFF")) {
                        printMsg("LED OFF");
                        //Arduino 와의 Stream을 통해 Data Communication
                        bw.write(msg,0,msg.length()); //0 번째부터 길이만큼 보낸다
                        bw.flush();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
});
thread.start();
```

### Arduino Communication

* BufferedWriter 를 이용해 데이터 전달

```java
CommPortIdentifier portIdentifier;
try {
    portIdentifier = CommPortIdentifier.getPortIdentifier("COM7");
    if (portIdentifier.isCurrentlyOwned()) {
        System.out.println("포트 사용중");
    } else {
        CommPort commPort = portIdentifier.open("PORT_OPEN", 2000);
        if (commPort instanceof SerialPort) {
            SerialPort serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                                           SerialPort.PARITY_NONE);
            InputStream in = serialPort.getInputStream();
            OutputStream out = serialPort.getOutputStream();
            //문자열 형테로 한줄로 데이터 전송 (아두이노에게)
            bw = new BufferedWriter(new OutputStreamWriter(out));

        } else {
            System.out.println("Serial Port만 이용 가능");
        }
    }
} catch (Exception e) {
    System.out.println(e);
}
```

## Arduino

```c
void setup() {
  // put your setup code here, to run once:
  
  // serial port이용한 통신
  Serial.begin(9600);
  pinMode(13,OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  if(Serial.available()){
    String msg = Serial.readStringUntil('\n'); // '\n' 앞까지의 문자열 을 받는다. 
    if(msg =="ON"){
      digitalWrite(13, HIGH);
      Serial.println("ON");
      delay(10);
    }else if(msg =="OFF"){
      digitalWrite(13, LOW);
      Serial.println("OFF");
      delay(10);
    }
  }
}
```






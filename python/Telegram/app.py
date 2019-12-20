from flask import Flask,render_template,request
from decouple import config
import requests
import random

app = Flask(__name__)

token = config('TELEGRAM_BOT_TOKEN') #token 값 넣기
chat_id = config('CHAT_ID')

app_url = f"https://api.telegram.org/bot{token}"

# root directory (http://127.0.0.1:5000/)
@app.route('/')
def hello():
    return "안녕하세요~"

@app.route('/write')
def write():
    return render_template("write.html") #HTML 피일 리턴 

@app.route('/send')
def send():
    message = request.args.get("message")
    message_url = app_url + f"/sendMessage?chat_id={chat_id}&text={message}"

    #message 받아서 telegram 메세지 보내는 요청
    requests.get(message_url)
    return "메시지 전송 완료 했어요!"

# webHook 
@app.route(f"/{token}", methods = ['POST']) #내가 가지고있는 token 값으로 길을 열어주겠다.
def telegram():
    response = request.get_json()
    # 1.사용자의 아이디, 텍스트
    chat_id =response["message"]["chat"]["id"]
    text = response["message"]["text"] #사용자의 메세지가 담겨있는곳

    # 2.앵무새, 텔레그램 메시지 보내기 요청
    # message_url = app_url + f"/sendMessage?chat_id={chat_id}&text={text}"
    # requests.get(message_url)

    # 3.if 만약 '/로또' 입력되면 txt -> 6개 숫자 추천
    if text == "/로또":
        text = sorted(random.sample(range(1, 46),6))
        # message_url = app_url + f"/sendMessage?chat_id={chat_id}&text={text}"
        # requests.get(message_url)
    # 4. 메뉴 추천
    elif text == "/메뉴":
        menuList = ["짜장", "짬뽕", "사천", "볶음밥", "짬뽕밥"]
        text = random.choice(menuList)

    message_url = app_url + f"/sendMessage?chat_id={chat_id}&text={text}"
    requests.get(message_url)

    # return body, status_code
    return '', 200 # '' 는 body 부이고 200(status_code)은 web에서 응답이 성공했을시 발생하는 code (반드시 작성을 해야함)





#디버그 모드
if __name__ == "__main__":
    app.run(debug=True)
from flask import Flask, render_template, request
import datetime
import random

# 지금부터 flask 서버의 이름이 app
app = Flask(__name__)

#url 을 관리해주는 역할 > @app.route("/")
@app.route("/")
def hello():
    return "안녕!!"

@app.route("/dday")
def dday():
    today = datetime.datetime.now()
    final = datetime.datetime(2020, 6, 9)
    result = final - today
    print(result)
    return f"{result.days}일 남았습니다."

## is it christmas
@app.route("/christmas")
def day():
    today = datetime.datetime.now()
    chris = datetime.datetime(2019, 12, 25)
    dday = chris - today
    
    if today == chris:
        result = "<h1>Christmas!<h1>"
    elif today != chris:
        result = f"<h1>아니요{dday.days} 일 남았어요<h1>"
    return result
@app.route("/movies")
def movies():
    movies = ["겨울왕국2", "클라우스", "어바웃타임", "나홀로집에"]
    return render_template("movie.html", movies = movies, text = "영화 목록")

@app.route("/greeting/<string:name>")
def greeting(name):
    return f"안녕하세요! {name} 님"

@app.route("/cube/<int:num>") #int 형으로 받아올때는 <int:   >해줘야 한다.
def cube(num):
    result = num**3
    return str(result)
##식사 메뉴 추천
#1. random
#2. Dynamic route : @app.route("/lunch/1 2 3 4")
#  LIst : ["자장명", "짬뽕", .....]
#  <int:num> 숫자 만큼 뽑기

@app.route("/lunch/<int:num>")
def lunch(num):
    menuList = ["짜장", "짬뽕", "사천", "볶음밥", "짬뽕밥"]
    randMenu = random.sample(menuList, num)

    return render_template("movie.html", movies = randMenu, text = "점심 메뉴") 

@app.route("/vonvon")
def vonvon():
    return render_template("vonvon.html")

@app.route("/godmademe")
def godmademe():
    name = request.args.get("name")
    print(name)

    first_list = ["못생김", "잘생김", "그냥 생김"]
    second_list = ["애교", "소심", "착함"]
    third_list = ["식욕","승부욕"]
    first = random.choice(first_list)
    second = random.choice(second_list)
    third = random.choice(third_list)

    return render_template("godmademe.html", name = name ,first=first, second=second, third=third   )

#디버그 모드 실행 문장 > 실행은 python app.py 로실행 해야 한다.
if __name__== "__main__":
    app.run(debug=True)
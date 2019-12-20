# 내부 라이부러리
import random
import json
# 외부 라이브러리(다운로드 필요)
import requests
from bs4 import BeautifulSoup as bs # bs4로부터 BeautifulSoup 를 bs라는 이름으로 가져오겠다.

numbers = random.sample(range(1,46), 6)
print(sorted(numbers))

url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=836"

response = requests.get(url)
print(response.text)

print("------------------------")

lotto = json.loads(response.text)
print(lotto)
print(lotto["drwtNo6"])  #파일이 비어있으면 에러
print(lotto.get("drwtNo6")) #파일이 비어있으면 none이출력 (에러 발생이없다.)

winner = []

for i in range(1, 7):
    winner.append(lotto.get(f"drwtNo{i}")) #fString 사용

print(winner)

#python 함수
def pickLotto():
    picked = sorted(random.sample(range(1, 46),6))
    matched = len(set(winner) & set(picked))

    if matched == 6:
        print("1등")
    elif matched == 5:
        print("5등")
    elif matched == 4:
        print("4등")
    elif matched == 3:
        print("5등")
    

pickLotto()
import requests
from bs4 import BeautifulSoup as bs

url = "https://finance.naver.com/marketindex/"

response = requests.get(url).text
soup = bs(response, 'html.parser')

usd = soup.select_one("#exchangeList > li.on > a.head.usd > div > span.value")

print(f"현재 원/달러 환율은{usd.text} 입니다. ")

##파일 저장
with open('test.txt', 'w', encoding='utf-8') as f: # 'as'는 이름을 지정해주는 역할이다
    f.write(f"현재 원/달러 환율은{usd.text} 입니다.")
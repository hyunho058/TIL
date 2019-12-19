# 크롤링
# requests 는 web에 소스를 가져오고 bs4는 내용을 탐색해주는 역할이다

import requests
from bs4 import BeautifulSoup as bs

url = "https://finance.naver.com/sise/"

response = requests.get(url).text
soup = bs(response, 'html.parser')

kospi = soup.select_one("#KOSPI_now")

print(kospi)
print(kospi.text)
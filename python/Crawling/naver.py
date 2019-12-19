'''
1.requests > naver.com
2.response > bs4
3.webbrowser
'''

import requests
from bs4 import BeautifulSoup as bs
import webbrowser

url = "https://www.naver.com/"
response = requests.get(url).text

# 'html.parser' > 받아올 형식 지정
doc = bs(response, 'html.parser')

# naver 실시간 검색어 클래스 불러오기

result = doc.select_one('.ah_k')  # '.' 은 클레스를 가져오겠다. '#' 은 아이디를 가져오겠따
print(result)

result1 = doc.select('.ah_k')
print(result1[1])

search_url = "https://search.naver.com/search.naver?query="
for i in range(5):
    webbrowser.open(search_url + result1[i].text)
    #print(search_url + result1[i].text)

#webbrowser.open(url)
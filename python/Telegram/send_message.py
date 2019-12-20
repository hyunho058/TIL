import requests
from decouple import config

token = config('TELEGRAM_BOT_TOKEN') #token 값 넣기
app_url = f"https://api.telegram.org/bot{token}"  #gitbash 파일로 종보 숨김
chat_id = config('CHAT_ID')


#####. env 에서 koken , id value 를 받아 필요 없는 sorce 부분
# update_url = app_url + "/getUpdates"

# response = requests.get(update_url).json() #update_url 를 json 으로 파싱
# print(response)

# print("---------------")

# chat_id = response["result"][0]["message"]["chat"]["id"]
# print(chat_id)

text = "점심 먹고와"
message_url = app_url + f"/sendMessage?chat_id={chat_id}&text={text}"

requests.get(message_url) #Telegram 으로 파일을 날려 출력 을 발생시킴
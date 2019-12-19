'''
String whwkrgkrl

1. 글자 합체
string + string

2. 글자 삽입(수술)

3. 글자 자르기
'''

# 1. 글자 합체

a = "happy" +" "+ "hacking"

print(a)

# 2. 글자 삽입

name = "hyun"
age = 18

text = "안녕하세요. 제 이름은{}입니다. 나이는 {}살" .format(name, age)
print(text)

f_text = f"안녕하세요. 제 이름은{name}입니다. 나이는 {age}살" 
print(f_text)

# 3. 글자 자르기
# string > "어떠한 글자들"[start : end]
text_nema = text[:15]
print(text_nema)

text_age = text[16:]
print(text_age)

text_split = text.split()
print(text_split)
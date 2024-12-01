# Naver-Webtoon
'네이버'에서 제공하는 웹툰 서비스를 재현 및 재구성하여 제작한 프로젝트입니다.</br>
</br>

## 개요
* **제작 목적** :  실제 서비스되는 제품 구현을 통해 사용자가 필요로 하는 기능 인지
* **역할** : 개인 프로젝트
* **제작 기간** : 2024.02.19 ~ 2024.05.29
</br>

## ERD
![image](https://github.com/joohee56/Naver-Webtoon/assets/83942393/d5233bea-8f6c-424b-a945-075d13b0028c)</br>
</br>

## 기능
| 웹툰 관람 |
|:----:|
|<img src="https://github.com/user-attachments/assets/9beb331d-d6f4-40fd-98d7-3e0235cbfbbe" width="600px">|
</br>
</br>
</br>

| 정식연재 / 도전만화 |
|:----:|
|<img src="https://github.com/user-attachments/assets/bdefff55-e96d-4559-a98c-0eb0fe564969" width="600px">|
</br>
</br>
</br>

|신규 작품 등록|
|:----:|
|<img src="https://github.com/joohee56/Naver-Webtoon/assets/83942393/3356b047-a35e-4150-bff6-c841cb5f9a18" width="600px">|
- `도전만화` 카테고리에 업로드
</br>
</br>
</br>

|신규 회차 등록 (1)|
|:----:|
|<img src="https://github.com/joohee56/Naver-Webtoon/assets/83942393/f9fbf9eb-e13e-4906-8cb8-681a8a41b04a" width="600px">|
- `회차 번호` : 작품당 자동으로 증가하는 번호 부여 (EntityListener, @Prepost 활용)
</br>
</br>
</br>

|신규 회차 등록 (2)|
|:----:|
|<img src="https://github.com/joohee56/Naver-Webtoon/assets/83942393/e30475df-0e20-4ce0-a847-048c32a0b63f" width="600px">|
- `미리보기` : 업로드한 이미지 확인
- `전체 미리보기` : 분할된 웹툰 이미지를 canvas를 사용해 하나의 원고로 생성
</br>
</br>
</br>

|웹툰 랭킹|
|:----:|
|<img src="https://github.com/joohee56/Naver-Webtoon/assets/83942393/f3ce4394-0170-40e8-861d-6f05ea3c51b0" width="600px">|
- `랭킹 업데이트` : Websocket과 @Scheduled 사용하여 별도의 새로고침 없이 정해진 시간에 랭킹 업데이트
</br>
</br>
</br>

|검색|
|:----:|
|<img src="https://github.com/joohee56/Naver-Webtoon/assets/83942393/b0702321-95cf-4c2c-90b4-8374f47d2bac" width="600px">|
- `작가 이름` 또는 `작품 이름`으로 검색
</br>
</br>
</br>

|프로필 이미지 변경|
|:----:|
|<img src="https://github.com/joohee56/Naver-Webtoon/assets/83942393/8e1fb899-fef3-4241-a6c0-aea9952e8533" width="600px">|
- `프로필 이미지` 또는 `사용자 정보` 변경
</br>
</br>
</br>

## 기술 스택
### Frontend
* Vue 
* Vuex 
* Vue-router 
* axios 
* sockjs
* stomp

### Backend
* Java 
* SpringBoot 
* JPA (Hibernate)

### DB
* MySQL 

# Naver-Webtoon
네이버 웹툰<img src="https://github.com/joohee56/Naver-Webtoon/assets/83942393/1e8027e1-2b5e-4673-9577-4bcb98c89622" width="30px"></br>
</br>

## 목차
* [소개](#소개)
* [사용자 주요 유스케이스](#사용자-주요-유스케이스)
* [기능](#기능)
* [기술 스택](#기술-스택)
* [ERD](#erd)
</br>

## 개요
* **제작 목적** :  실제 서비스되는 제품 구현을 통해 사용자가 필요로하는 기능 인지 및 기술 활용
* **역할** : 기획, 풀스택, 문서화
* **제작 기간** : `2024.02.19 ~ 2024.05.29`
</br>

## 사용자 주요 유스케이스
### 1. 일반 유저의 웹툰 관람
<img src="https://github.com/joohee56/Naver-Webtoon/assets/83942393/8bcb518e-7e38-4ec9-889a-992a53403381" width="80%"></br>
</br>

### 2. 작가의 웹툰 등록
<img src="https://github.com/joohee56/Naver-Webtoon/assets/83942393/f1ad6e8c-fa62-46d8-b139-1fb9d3e97670" width="85%">
</br>

## ERD
![image](https://github.com/joohee56/Naver-Webtoon/assets/83942393/d5233bea-8f6c-424b-a945-075d13b0028c)</br>
</br>

## 기능
⚠️프로젝트에 사용된 웹툰들은 학습용도로 일부만을 캡쳐하여 사용하였습니다. </br>
</br>

|신규 작품 등록|
|:----:|
|![신규 작품 등록](https://github.com/joohee56/Naver-Webtoon/assets/83942393/3356b047-a35e-4150-bff6-c841cb5f9a18)|
- 등록된 웹툰은 `도전만화`에 노출
</br>
</br>

|신규 회차 등록 (1)|
|:----:|
|![신규 회차 등록 -1 ](https://github.com/joohee56/Naver-Webtoon/assets/83942393/f9fbf9eb-e13e-4906-8cb8-681a8a41b04a)|
- `회차 번호` : 등록할 작품 선택 시 회차 번호 자동 부여
- `회차명`, `대표 이미지`, `원고로 사용될 이미지` 업로드
</br>
</br>

|신규 회차 등록 (2)|
|:----:|
|![신규 회차 등록 2 - 보완](https://github.com/joohee56/Naver-Webtoon/assets/83942393/e30475df-0e20-4ce0-a847-048c32a0b63f)|
- `미리보기` : 업로드한 이미지 확인
- `이미지 이동 버튼` : 이미지를 선택한 후 4가지 이동 버튼(`맨위`, `위`, `아래`, `맨아래`)을 통해 이미지의 순서 변경
- `전체 미리보기` : 이미지들을 하나로 이어붙인 최종 원고 확인
- `회차 업로드` : 확인 버튼을 통해 최종 업로드
</br>
</br>

|페이징|
|:----:|
|![페이징](https://github.com/joohee56/Naver-Webtoon/assets/83942393/6b0ceb70-2607-47fc-9103-c80865435bb5)|
</br>
</br>

|메뉴바 트랜지션|
|:----:|
|![메뉴바 트랜지션](https://github.com/joohee56/Naver-Webtoon/assets/83942393/fe07cd45-0fe7-4d54-ab4e-c45dc0abe009)|
</br>
</br>

|웹툰 관람|
|:----:|
|![웹툰 관람](https://github.com/joohee56/Naver-Webtoon/assets/83942393/3e52eb30-a563-47f3-a5cb-3230fb9a5d3f)|
- `목차 미리보기`
- `좋아요`
</br>
</br>

|웹툰 랭킹|
|:----:|
|![웹툰 랭킹](https://github.com/joohee56/Naver-Webtoon/assets/83942393/f3ce4394-0170-40e8-861d-6f05ea3c51b0)|
- `순위 기준` : 가장 최근에 등록한 총 10개의 회차의 누적된 좋아요 수를 합산하여 랭킹 순위 선정
- `랭킹 업데이트` : sock.js와 @Scheduled 사용하여 별도의 새로고침 없이 정해진 시간에 랭킹 업데이트
</br>
</br>

|웹툰 랭킹 표시| 업로드 표시 |
|:----:|:-----:
|![웹툰 랭킹 표시](https://github.com/joohee56/Naver-Webtoon/assets/83942393/a18c9cb5-1919-4932-b269-2b4036780819)| ![스크린샷 2024-07-02 033546](https://github.com/joohee56/Naver-Webtoon/assets/83942393/33e429b7-cb66-45a2-878c-826506848be4) |
- 랭킹 상승 또는 하강한 작품에 각각 `빨간색 ▲`, `파란색 ▼` 표시
- 회차가 업로드될 경우 `UP` 글자 표시
</br>
</br>

|사용자 맟줌 웹툰 필터|
|:----:|
|![장르별 웹툰](https://github.com/joohee56/Naver-Webtoon/assets/83942393/fdc0e595-6449-4c9e-87d9-55a6f806cc72)|
- 사용자가 선호하는 장르의 웹툰이 `인기순` 또는 `업데이트` 순으로 한 눈에 보이도록 추가 기획하여 `웹툰 필터` 구현
</br>
</br>

|요일별 웹툰|
|:----:|
|![요일별 웹툰](https://github.com/joohee56/Naver-Webtoon/assets/83942393/82652d05-3a40-4cb3-9331-7263478b7317)|
</br>
</br>

|장르별 웹툰(도전만화)|
|:----:|
|![장르별 (도전만화)](https://github.com/joohee56/Naver-Webtoon/assets/83942393/a03f38fa-e089-4d34-b574-d995bc759691)|
</br>
</br>

|댓글 등록|
|:----:|
|![댓글 등록](https://github.com/joohee56/Naver-Webtoon/assets/83942393/835b32b4-92a5-4fd9-bd23-f5dfa4d9aa76)|
- `로그인` 후 댓글 등록 가능
- `BEST 댓글` : 가장 많은 '좋아요'와 가장 적은 '싫어요'를 받은 상위 4개의 댓글
</br>
</br>

|댓글 삭제|
|:----:|
|![댓글 삭제](https://github.com/joohee56/Naver-Webtoon/assets/83942393/2a93571c-d7d5-43d8-9036-e6344d30918e)|
- 본인이 작성한 댓글만 수정할 수 있도록 `삭제 메뉴 버튼` 활성화
</br>
</br>

|댓글 좋아요, 싫어요|
|:----:|
|![댓글 좋아요, 싫어요](https://github.com/joohee56/Naver-Webtoon/assets/83942393/dead8de1-ed27-4377-b069-5ef5a778b1de)|
</br>
</br>

|대댓글 등록|대댓글 삭제|
|:----:|:----:|
|![대댓글 등록](https://github.com/joohee56/Naver-Webtoon/assets/83942393/025f84df-1530-42ff-8f74-2184754128bd)|![대댓글 삭제](https://github.com/joohee56/Naver-Webtoon/assets/83942393/0e0bc80f-4a4c-4818-acdd-859e7501c6b7)|
</br>
</br>

|검색|
|:----:|
|![작품 이름으로 검색](https://github.com/joohee56/Naver-Webtoon/assets/83942393/b0702321-95cf-4c2c-90b4-8374f47d2bac)|
- `작가 이름` 또는 `작품 이름`으로 검색
</br>
</br>

|프로필 이미지 변경|
|:----:|
|![프로필 이미지 변경](https://github.com/joohee56/Naver-Webtoon/assets/83942393/8e1fb899-fef3-4241-a6c0-aea9952e8533)|
- `프로필 이미지` 또는 `사용자 정보` 변경
</br>
</br>

|자동 로그아웃|
|:----:|
|![자동 로그아웃](https://github.com/joohee56/Naver-Webtoon/assets/83942393/2910c044-3278-48f3-830a-d7cbcd08c016)|
- 일정 시간동안 미사용시 `세션이 만료`되어 자동 `로그아웃`
</br>
</br>

|로그인|회원가입|
|:----:|:----:|
|![로그인](https://github.com/joohee56/Naver-Webtoon/assets/83942393/0a4757d4-8ea2-4012-a6dc-21b5ba7bf1e0)|![회원가입](https://github.com/joohee56/Naver-Webtoon/assets/83942393/80a3aa77-103a-4466-be24-e16762c86f81)|
</br>
</br>

## 기술 스택
### Frontend
* HTML5, CSS3, ES6
* Vue `2.6.14`
* Vuex `3.6.2`
* Vue-router `3.5.1`
* axios `1.6.7`
* eslint `7.32.0`, prettier `2.4.1`
* sockjs `1.6.1`

### Backend
* Java `17`
* SpringBoot `3.2.3`
* Hibernate
* Gradle

### DB
* MySQL `8.0.36`

### IDE
* IntelliJ
* VSCode

### Tool
* Github
* Figma
* erdcloud
</br>



<template lang="ko">
	<div>
		<div class="title-container">
			<p class="title">요일별 전체 웹툰</p>
			<div class="filter">
				<button class="active">인기순</button> • 
				<button>업데이트순</button> •
				<button>조회순</button>
			</div>
		</div>
		<div class="genre">
			<form>
				<label><input type="checkbox">전체</label>
				<label><input type="checkbox">로맨스</label>
				<label><input type="checkbox">판타지</label>
				<label><input type="checkbox">액션</label>
				<label><input type="checkbox">스포츠</label>
				<label><input type="checkbox">개그</label>
				<label><input type="checkbox">스릴러</label>
				<label><input type="checkbox">무협/사극</label>
				<label><input type="checkbox">감성</label>
				<label><input type="checkbox">소설원작</label>
			</form>
		</div>
		<div class="webtoon-container">
			<ul class="today"> 
				<div class="dayOfWeek"> 월요웹툰 </div>
				<li>
					<img src="@/assets/image/webtoon-cover-sample-2.png">
					<div>
						<span class="up">UP</span>
						<span class="webtoon-name">바른 연애 길잡이</span>
					</div>
				</li> 
				<li>
					<img src="@/assets/image/webtoon-cover-sample-2.png">
					<span class="webtoon-name">바른 연애 길잡이</span>
				</li> 
				<li>
					<img src="@/assets/image/webtoon-cover-sample-2.png">
					<span>바른 연애 길잡이</span>
				</li> 
				<li>
					<img src="@/assets/image/webtoon-cover-sample-2.png">
					<span>바른 연애 길잡이</span>
				</li> 
			</ul>
			<ul> 
				<div class="dayOfWeek"> 화요웹툰 </div>
				<li>
					<img src="@/assets/image/webtoon-cover-sample-2.png">
					<span>바른 연애 길잡이</span>
				</li> 
			</ul>
			<ul> 
				<div class="dayOfWeek"> 수요웹툰 </div>
				<li>
					<img src="@/assets/image/webtoon-cover-sample-2.png">
					<span>바른 연애 길잡이</span>
				</li> 
			</ul>
			<ul> 
				<div class="dayOfWeek"> 목요웹툰 </div>
				<li>
					<img src="@/assets/image/webtoon-cover-sample-2.png">
					<span>바른 연애 길잡이</span>
				</li> 
			</ul>
			<ul> 
				<div class="dayOfWeek"> 금요웹툰 </div>
				<li>
					<img src="@/assets/image/webtoon-cover-sample-2.png">
					<span>바른 연애 길잡이</span>
				</li> 
			</ul>
			<ul> 
				<div class="dayOfWeek"> 토요웹툰 </div>
				<li>
					<img src="@/assets/image/webtoon-cover-sample-2.png">
					<span>바른 연애 길잡이</span>
				</li> 
			</ul>
			<ul> 
				<div class="dayOfWeek"> 일요웹툰 </div>
				<li>
					<img src="@/assets/image/webtoon-cover-sample-2.png">
					<span>바른 연애 길잡이</span>
				</li> 
			</ul>
		</div>
	</div>
</template>

<script>
export default {};
</script>

<style scoped>
.title-container {
  display: flex;
}
.title {
  margin: 0;
  font-family: AppleSDGothicNeoEB;
  font-size: 1.3rem;
}
button {
  background: none;
  border: none;
  padding: 0;
  font-size: 13;
  font-family: AppleSDGothicNeoEB;
}
.filter {
  margin-left: 1rem;
}
.active {
  color: #00dc64;
}
.today {
  background-color: #b2eece;
}
.today > div {
  color: white;
  background-color: #00dc64;
}
.genre {
  float: right;
  margin-bottom: 15px;
  font-family: AppleSDGothicNeoM;
}

.webtoon-container {
  border: #d9d9d9 solid 0.5px;
  display: flex;
  width: fit-content;
}
ul {
  list-style: none;
  padding: 0;
  margin: 0;
  font-family: AppleSDGothicNeoM;
}
.webtoon-container .up {
  color: #ff4d56;
  border-radius: 5px;
  border: #ff4d56 solid 0.5px;
  font-family: AppleSDGothicNeoEB;
  text-align: center;
  padding: 1px 4px;
  margin-right: 5px;
  font-size: 15px;
}
.webtoon-container .dayOfWeek {
  font-family: AppleSDGothicNeoEB;
  font-size: 1rem;
  text-align: center;
  padding: 15px 0px;
}

ul > li {
  padding: 10px 22px;
}
</style>

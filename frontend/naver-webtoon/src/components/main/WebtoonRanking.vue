<template lang="ko">
	<div class="container">
		<div class="box-container">
			<div style="display: flex;">
				<p class="title">실시간 인기 웹툰</p>
				<div class="filter">
					<button class="active">전체</button> • 
					<button>여성</button> •
					<button>남성</button>
				</div>
			</div>
			<ul class="webtoon">
				<li>
					<div class="ranking">
						<div class="rank-num">1</div>
						<div>
							<img src="@/assets/image/polygon.png">
						</div>
					</div>
					<div>
						<img src="@/assets/image/webtoon-cover-sample.png">
					</div>
					<div class="info">
						<div class="like">
							<i class="fa-solid fa-heart"></i>
							<div>1025만</div>
						</div>
						<div class="title">세레나</div>
						<div class="genre">로맨스</div>
					</div>
				</li>
				<li>
					<div class="ranking">
						<div class="rank-num">1</div>
						<div>
							<img src="@/assets/image/polygon.png">
						</div>
					</div>
					<div>
						<img src="@/assets/image/webtoon-cover-sample.png">
					</div>
					<div class="info">
						<div class="like">
							<i class="fa-solid fa-heart"></i>
							<div>1025만</div>
						</div>
						<div class="title">세레나</div>
						<div class="genre">로맨스</div>
					</div>
				</li>
				<li>
					<div class="ranking">
						<div class="rank-num">1</div>
						<div>
							<img src="@/assets/image/polygon.png">
						</div>
					</div>
					<div>
						<img src="@/assets/image/webtoon-cover-sample.png">
					</div>
					<div class="info">
						<div class="like">
							<i class="fa-solid fa-heart"></i>
							<div>1025만</div>
						</div>
						<div class="title">세레나</div>
						<div class="genre">로맨스</div>
					</div>
				</li>
				<li>
					<div class="ranking">
						<div class="rank-num">1</div>
						<div>
							<img src="@/assets/image/polygon.png">
						</div>
					</div>
					<div>
						<img src="@/assets/image/webtoon-cover-sample.png">
					</div>
					<div class="info">
						<div class="like">
							<i class="fa-solid fa-heart"></i>
							<div>1025만</div>
						</div>
						<div class="title">세레나</div>
						<div class="genre">로맨스</div>
					</div>
				</li>
				<li>
					<div class="ranking">
						<div class="rank-num">1</div>
						<div>
							<img src="@/assets/image/polygon.png">
						</div>
					</div>
					<div>
						<img src="@/assets/image/webtoon-cover-sample.png">
					</div>
					<div class="info">
						<div class="like">
							<i class="fa-solid fa-heart"></i>
							<div>1025만</div>
						</div>
						<div class="title">세레나</div>
						<div class="genre">로맨스</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</template>

<script>
export default {};
</script>

<style scoped>
.container {
  padding: 2rem 0rem;
}
.box-container {
  background-color: #fbfbfb;
  border: #e8e8e8 solid 0.5px;
  border-radius: 13px;
  height: 10rem;
  padding: 1rem;
}
.title {
  margin: 0;
  font-family: AppleSDGothicNeoEB;
}
.filter {
  margin-left: 1rem;
}
button {
  background: none;
  border: none;
  padding: 0;
  font-size: 13;
  font-family: AppleSDGothicNeoEB;
}
.active {
  color: #00dc64;
}
ul {
  list-style: none;
  padding: 0;
}
.webtoon {
  display: flex;
}
.webtoon li {
  display: flex;
  margin-right: 1.5rem;
}
.webtoon div {
  margin-left: 8px;
}
.ranking {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 1fr auto;
  justify-items: center;
}
.rank-num {
  font-size: 3rem;
  font-weight: 700;
  margin-top: auto;
}
.info {
  font-family: AppleSDGothicNeoEB;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 1fr auto;
}
.like {
  color: #ff4d56;
  display: flex;
  margin: auto 0 0 0;
}
.like div {
  margin: 0 5px auto;
  font-size: 0.8rem;
}
.fa-heart {
  font-size: 15px;
}
.webtoon .title {
  font-size: 1rem;
}
.genre {
  font-size: 0.8rem;
  color: #616161;
}
</style>

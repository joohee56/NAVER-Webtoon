<template lang="ko">
	<div class="ranking-container">

    <div class="subject-wrap">
      <div class="title">실시간 인기 웹툰</div>
      <div class="filter">
        <button class="active">전체</button> ·
        <button>여성</button> ·
        <button>남성</button>
      </div>
      <div class="update-time">{{updatedAt}} 기준</div>
    </div>

    <div class="box-container">
      <div v-if="rankings.length === 0">
        <div class="no-ranking-description-text">조회할 랭킹이 없습니다.</div>
      </div>
			<ul class="webtoon-list" v-if="rankings.length !== 0">
				<li class="webtoon-item-wrap" v-for="(ranking, index) in rankings">
					<div class="ranking-num-wrap">
            <div class="ranking-status-image-wrap">
              <img class="ranking-status-image" :src="require(`@/assets/image/ranking-status-${ranking.rankingStatus}.png`)">
            </div>
            <div class="rank-num">{{index+1}}</div>
          </div>
					<div class="cover-image">
						<router-link :to="{name:'roundList', params: {webtoonId: ranking.webtoonId}}">
							<img :src="require(`@/assets/image/${ranking.thumbnail}`)">
						</router-link>
					</div>
					<div class="info-wrap">
						<div class="like-cnt">
							<i class="fa-solid fa-heart"></i>
							<div>&nbsp;{{ranking.totalLikeCount}}</div>
						</div>
						<div class="title">{{ranking.webtoonName}}</div>
						<div class="genre">
              <span v-for="genre in ranking.genres">{{genre}} </span>
            </div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import { getWebtoonRanking } from "@/api/webtoon";

export default {
  data() {
    return {
      rankings: [], //webtoonId, webtoonName, thumbnail, totalLikeCount, rankingStatus, genres, ranking
      updatedAt: "",
    };
  },
  created() {
    this.fetchRanking();
    //해당 뷰가 생성되면 소켓 연결을 시도
    this.connect();
  },
  beforeDestroy() {
    this.disconnect();
  },
  methods: {
    connect() {
      const serverURL = "http://localhost:8081/websocket";
      const socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소 : ${serverURL}`);
      //웹 소켓 연결
      this.stompClient.connect(
        {},
        (frame) => {
          //소켓 연결 성공
          this.connected = true;
          console.log("소켓 연결 성공", frame);

          //서버 구독
          this.stompClient.subscribe("/send", (res) => {
            const responseData = JSON.parse(res.body);
            this.rankings = responseData.rankings;
            this.updatedAt = responseData.updatedAt;
          });
        },
        (error) => {
          //소켓 연결 실패
          console.log("소켓 연결 실패", error);
          this.connected = false;
        }
      );
    },
    disconnect() {
      this.stompClient.disconnect();
      this.connected = false;
    },
    async fetchRanking() {
      try {
        const response = await getWebtoonRanking();
        console.log(response.data);
        this.rankings = response.data.rankings;
        this.updatedAt = response.data.updatedAt;
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style scoped>
.ranking-container {
  padding: 50px 0 60px;
}
.subject-wrap {
  margin-bottom: 15px;
  display: flex;
}
.subject-wrap > div {
  margin-right: 10px;
}
.title {
  font-size: 1.2rem;
  margin: 0;
  font-family: AppleSDGothicNeoEB;
}
.update-time {
  font-size: 14px;
  font-family: AppleSDGothicNeoEB;
  color: #999;
  line-height: 30px;
  /* align-items: flex-end;
  display: flex; */
}
button {
  background: none;
  border: none;
  padding: 0;
  font-size: 13px;
  font-family: AppleSDGothicNeoEB;
}
.active {
  color: #00dc64;
}

/* 웹툰 리스트 */
.box-container {
  border: #e8e8e8 solid 0.5px;
  border-radius: 6px;
  padding: 12px 10px;
  display: flex;
  justify-content: center;
  box-shadow: 5px 1px 8px 0 rgba(0, 0, 0, 0.06);
  background-color: #f6f8fa;
}
ul {
  list-style: none;
  padding: 0;
  margin: 0;
}
a {
  cursor: pointer;
}
.no-ranking-description-text {
  text-align: center;
  font-family: AppleSDGothicNeoM;
  font-size: 18px;
  padding: 15px 15px;
}
.webtoon-list {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  justify-items: center;
  column-gap: 10px;
  /* display: flex; */
}
.webtoon-list .webtoon-item-wrap {
  display: flex;
}

/* 랭킹 숫자 */
.ranking-num-wrap {
  margin-top: auto;
  text-align: center;
  margin-right: 15px;
}
.rank-num {
  font-size: 55px;
  font-weight: 700;
  line-height: 45px;
  font-style: italic;
}
.ranking-status-image-wrap {
  display: flex;
}
.ranking-status-image {
  width: 17px;
  padding: 10px 0;
  margin-left: auto;
}

/* 커버이미지 */
.cover-image {
  margin-right: 8px;
}
.cover-image img {
  width: 115px;
  aspect-ratio: 480 / 623;
  border-radius: 3px;
  height: 100%;
}

/* 웹툰 정보 */
.info-wrap {
  font-family: AppleSDGothicNeoEB;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 1fr auto;
  margin-bottom: 5px;
}
.like-cnt {
  color: #ff4d56;
  display: flex;
  margin: auto 0 0 0;
  font-size: 0.8rem;
}
.like-cnt i {
  font-size: 12px;
  margin: auto 0;
}
.info-wrap .title {
  font-size: 16px;
}
.info-wrap .genre {
  font-size: 13px;
  color: #7c7c7c;
}
.genre span:not(:last-child)::after {
  content: "/";
}
.overflow-hidden {
  white-space: nowrap; /* 줄 바꿈 방지 */
  overflow: hidden; /* 넘치는 부분 숨김 */
  text-overflow: ellipsis; /* 넘치는 부분에 ... 추가 */
}
</style>

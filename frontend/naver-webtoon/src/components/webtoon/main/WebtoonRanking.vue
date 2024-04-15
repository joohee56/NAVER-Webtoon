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
      <div v-if="rankings.length !== 0" class="box-container-inner">
        <button class="ranking-arrow-btn" style="cursor: pointer;" @click="clickLeftRankingBtn" v-if="!isLeftBtnDisabled"><i class="fa-solid fa-chevron-left"></i></button>
        <ul class="webtoon-list">
          <li class="webtoon-item-wrap" v-for="(ranking, index) in rankings">
            <div class="ranking-num-wrap">
              <div class="rank-num">{{ranking.rankingNum}}</div>
              <div class="ranking-status-image-wrap">
                <img class="ranking-status-image" :src="require(`@/assets/image/ranking-status-${ranking.rankingStatus}.png`)">
              </div>
            </div>
            <div class="cover-image">
              <router-link :to="{name:this.webtoonType.toLowerCase()+'RoundList', params: {webtoonId: ranking.webtoonId}}">
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
        <button class="ranking-arrow-btn" style="cursor: pointer;" @click="clickRightRankingBtn" v-if="!isRightBtnDisabled"><i class="fa-solid fa-chevron-right"></i></button>
      </div>
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
      rankings: [], //webtoonId, webtoonName, thumbnail, totalLikeCount, rankingStatus, genres, rankingNum
      updatedAt: "",
      rankingStartIndex: "",
      rankingLimit: 5,
      isRightBtnDisabled: false,
      isLeftBtnDisabled: false,
    };
  },
  props: ["webtoonType"],
  watch: {
    rankingStartIndex() {
      this.fetchRanking();
      this.setRankigBtnDisabled();
    },
  },
  created() {
    //해당 뷰가 생성되면 소켓 연결을 시도
    this.connect();
    this.rankingStartIndex = 0;
  },
  destroyed() {
    this.disconnect();
  },
  methods: {
    connect() {
      const webtoonTypeConst = this.webtoonType.toLowerCase();
      const serverURL = "http://localhost:8081/websocket";
      const socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소 : ${serverURL}`);
      //웹 소켓 연결
      this.stompClient.connect(
        {},
        () => {
          //소켓 연결 성공
          this.connected = true;
          console.log("소켓 연결 성공");

          //서버 구독
          this.stompClient.subscribe("/send/" + webtoonTypeConst, (res) => {
            const responseData = JSON.parse(res.body);
            this.rankings = responseData.rankings;
            this.updatedAt = responseData.updatedAt;
            this.rankingStartIndex = 0;
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
      console.log("disconnect() 호출");
      this.stompClient.disconnect();
      this.connected = false;
    },
    async fetchRanking() {
      try {
        const response = await getWebtoonRanking(
          this.rankingStartIndex,
          this.rankingLimit,
          this.webtoonType
        );
        console.log(response.data);
        this.rankings = response.data.rankings;
        this.updatedAt = response.data.updatedAt;
      } catch (error) {
        console.log(error);
      }
    },
    clickRightRankingBtn() {
      this.rankingStartIndex += this.rankingLimit;
    },
    clickLeftRankingBtn() {
      this.rankingStartIndex -= this.rankingLimit;
    },
    setRankigBtnDisabled() {
      if (this.rankingStartIndex + this.rankingLimit >= 10) {
        this.isRightBtnDisabled = true;
      } else {
        this.isRightBtnDisabled = false;
      }

      if (this.rankingStartIndex === 0) {
        this.isLeftBtnDisabled = true;
      } else {
        this.isLeftBtnDisabled = false;
      }
    },
  },
};
</script>

<style scoped>
ul {
  list-style: none;
  padding: 0;
  margin: 0;
}
a {
  cursor: pointer;
}
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
  padding: 20px 20px;
  display: flex;
  /* justify-content: center; */
  box-shadow: 5px 1px 8px 0 rgba(0, 0, 0, 0.08);
  /* background-color: #f6f8fa; */
}
.box-container-inner {
  display: flex;
  width: 100%;
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
  justify-items: left;
  column-gap: 10px;
  padding: 0 10px;
  width: 100%;
  /* display: flex; */
}
.webtoon-list .webtoon-item-wrap {
  display: flex;
}
/* 화살표 버튼 */
.ranking-arrow-btn {
  font-size: 20px;
  font-weight: 800;
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
  line-height: 60px;
  font-style: italic;
}
.ranking-status-image-wrap {
  display: flex;
}
.ranking-status-image {
  width: 17px;
  /* margin-left: auto; */
  /* text-align: center; */
  margin: 0 auto;
}

/* 커버이미지 */
.cover-image {
  margin-right: 8px;
  box-shadow: 5px 1px 8px 0 rgba(0, 0, 0, 0.08);
}
.cover-image img {
  width: 100px;
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

<template lang="ko">
	<div class="ranking-container">

    <!-- 타이틀 -->
    <div class="subject-wrap">
      <div class="title">가장 핫한 웹툰만 모아봤어요!</div>
      <div class="update-time" v-if="updatedAt !== ''">{{updatedAt}} 기준</div>
    </div>

    <!-- 웹툰 랭킹 -->
    <div class="box-container">
      <!-- 랭킹이 없는 경우 -->
      <div v-if="rankings.length === 0">
        <div class="no-ranking-description-text">조회할 랭킹이 없습니다.</div>
      </div>

      <!-- 랭킹이 있는 경우 -->
      <div v-if="rankings.length !== 0" class="box-container-inner">
        <button class="ranking-arrow-btn" @click="clickLeftRankingBtn" v-if="!isLeftBtnDisabled"><i class="fa-solid fa-chevron-left"></i></button>
        
        <ul class="webtoon-list">
          <li class="webtoon-item-wrap" v-for="(ranking, index) in rankings">
            <!-- 썸네일, 랭킹 숫자 -->
            <div class="thumbnail-ranking-wrap">
              <div class="cover-image">
                <router-link :to="{name:ranking.webtoonType.toLowerCase()+'RoundList', params: {webtoonId: ranking.webtoonId}}">
                  <img :src="require(`@/assets/image/${ranking.thumbnail}`)">
                </router-link>
              </div>
              <div class="ranking-num-status-wrap">
                <div class="ranking-status">
                  <i class="fa-solid fa-play up" v-if="ranking.rankingStatus==='up'"></i> 
                  <i class="fa-solid fa-play down" v-if="ranking.rankingStatus==='down'"></i>
                  <i class="fa-solid fa-minus" v-if="ranking.rankingStatus==='unchanging'"></i>
                </div>
                <div class="ranking-num">{{ranking.rankingNum}}</div>
              </div>
            </div>
            
            <!-- 웹툰 정보 -->
            <div class="info-wrap">
              <div class="title overflow-hidden">
                <router-link :to="{name:ranking.webtoonType.toLowerCase()+'RoundList', params: {webtoonId: ranking.webtoonId}}">
                  {{ranking.webtoonName}}
                </router-link>
              </div>
              <div class="genre">
                <span v-for="genre in ranking.genres">{{genre}}</span>
              </div>
              <div class="like-cnt">
                <i class="fa-solid fa-heart"></i>
                <div>&nbsp;{{ranking.totalLikeCount}}</div>
              </div>
            </div>
          </li>
        </ul>
        
        <button class="ranking-arrow-btn" @click="clickRightRankingBtn" v-if="!isRightBtnDisabled"><i class="fa-solid fa-chevron-right"></i></button>
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
      rankings: [], //webtoonId, webtoonName, thumbnail, webtoonType, totalLikeCount, rankingStatus, genres, rankingNum
      updatedAt: "",
      rankingStartIndex: "",
      rankingLimit: 7,
      totalRankingCount: 14,
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
      const response = await getWebtoonRanking(
        this.rankingStartIndex,
        this.rankingLimit,
        this.webtoonType
      );
      console.log(response.data);
      if (response.status === 200) {
        this.rankings = response.data.rankings;
        this.updatedAt = response.data.updatedAt;
      }
    },
    clickRightRankingBtn() {
      this.rankingStartIndex += this.rankingLimit;
    },
    clickLeftRankingBtn() {
      this.rankingStartIndex -= this.rankingLimit;
    },
    setRankigBtnDisabled() {
      if (
        this.rankingStartIndex + this.rankingLimit >=
        this.totalRankingCount
      ) {
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
a:hover {
  text-decoration: underline;
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

/* 웹툰 리스트 */
.webtoon-list {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  justify-items: left;
  column-gap: 10px;
  padding: 0 10px;
  width: 100%;
}

.thumbnail-ranking-wrap {
  position: relative;
}

/* 커버이미지 */
.cover-image img {
  width: 150px;
  aspect-ratio: 480 / 623;
  border-radius: 4px;
  border: 1px solid #dbdbdb;
  height: 100%;
}

/* 랭킹 숫자 */
.ranking-num-status-wrap {
  position: absolute;
  top: 155px;
}

.ranking-num {
  font-size: 60px;
  font-family: "Montserrat", sans-serif;
  font-weight: 800;
  text-shadow: -1px 0px white, 0px 1px white, 1px 0px white, 0px -1px white;
  display: inline-block;
  width: 50px;
  text-align: center;
  line-height: 55px;
}

/* 상승,하강,변동없음 아이콘 */
.ranking-status {
  width: 50px;
  text-align: center;
}
.ranking-status i {
  text-shadow: -1px 0px white, 0px 1px white, 1px 0px white, 0px -1px white;
}
.ranking-status .fa-minus {
  color: #c6c6c6;
  font-weight: 800;
}
.ranking-status .up {
  color: #ff4d56;
  font-size: 15px;
  transform: rotate(-90deg);
}
.ranking-status .down {
  color: #7fc6fe;
  font-size: 15px;
  transform: rotate(90deg);
}

/* 웹툰 정보 */
.info-wrap {
  margin-top: 35px;
  font-family: AppleSDGothicNeoEB;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 1fr auto;
}
.title-raking-status-wrap {
  display: flex;
}
.info-wrap .title {
  font-size: 16px;
  margin-right: 5px;
}

/* 장르 */
.info-wrap .genre {
  font-size: 13px;
  color: #7c7c7c;
}
.genre span:not(:last-child)::after {
  content: "/";
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
.overflow-hidden {
  white-space: nowrap; /* 줄 바꿈 방지 */
  overflow: hidden; /* 넘치는 부분 숨김 */
  text-overflow: ellipsis; /* 넘치는 부분에 ... 추가 */
}

/* 화살표 버튼 */
.ranking-arrow-btn {
  font-size: 20px;
  cursor: pointer;
  box-shadow: 0px 5px 5px 1px rgba(0, 0, 0, 0.068);
  width: 50px;
  height: 50px;
  border-radius: 50%;
  margin-top: 80px;
}
</style>

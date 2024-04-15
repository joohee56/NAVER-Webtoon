<template lang="ko">
	<div class="container">

		<!-- 상단 메뉴바 -->
		<div id="menubar" class="menu-bar">
			<router-link :to="{name: webtoonType+'RoundList', params: {webtoonId : `${this.$route.params.webtoonId}`}}"><i class="fa-solid fa-angle-left"></i></router-link>
			<router-link :to="{name: webtoonType+'RoundList', params: {webtoonId : `${this.$route.params.webtoonId}`}}">{{roundDetail.webtoonName}}</router-link>
			<span class="separator"></span>
			<span>{{roundDetail.roundNumber}}화 {{roundDetail.roundTitle}}</span>
			<span @click="moveBeforeRound" class="before-round">
				<i class="fa-solid fa-caret-left"></i>
				<span> 이전화</span>
      </span>
			<span class="separator"></span>
			<router-link :to="{name: webtoonType+'RoundList', params: {webtoonId : `${this.$route.params.webtoonId}`}}">
				<i class="fa-solid fa-list-ul"></i>
				<span> 목록</span>
			</router-link>
			<span class="separator"></span>
			<span @click="moveNextRound" class="next-round">
				<span>다음화 </span>
				<i class="fa-solid fa-caret-right"></i>
			</span>
		</div>

		<!-- 원고 -->
		<div id="manuscript" class="manusript-wrap">
			<img v-if="roundDetail.mergeManuscript !== ''" class="manuscript" :src="require(`@/assets/image/${roundDetail.mergeManuscript}`)">
		</div>

    <!-- 좋아요 -->
    <div class="reaction-btn-wrap">
      <div>
        <div class="icon circle-check"><i class="fa-solid fa-circle-check"></i></div>
        <div class="title">관심웹툰</div>
        <div class="count">13,941</div>
      </div>
      <div @click="clickRoundLike">
        <div class="icon" style="color: #ff5151;" v-if="this.roundDetail.isUserLike">
          <i class="fa-solid fa-heart"></i>
        </div>
        <div class="icon" v-else >
          <i class="fa-regular fa-heart"></i>
        </div>
        <div class="title">좋아요</div>
        <div class="count">{{this.roundDetail.totalLikeCnt}}</div>
      </div>
    </div>

    <!-- 목록 -->
    <div class="round-list">
      <button @click="lessRange" :disabled="isPreviewLeftBtnDisabled"><i class="fa-solid fa-angle-left"></i></button>
        <div class="preview-round-wrap">
          <div v-for="round in previewRounds" class="preview-round-item">
            <router-link :to="{name: 'roundDetail', params: {weboonId: `${roundDetail.weboonId}`, roundId: `${round.roundId}`}}" :class={active:isNowRound(round.roundNumber)}>
              <img class="round-thumbnail" :src="require(`@/assets/image/${round.thumbnail}`)">
              <p class="round-title overflow-hidden">{{round.roundNumber}}화 {{round.title}}</p>
            </router-link>
          </div>
        </div>
      <button class="right-btn" @click="rightRange" :disabled="isPreviewRightBtnDisabled"><i class="fa-solid fa-angle-right"></i></button>
    </div>

    <!-- 작가의 말 -->
    <div class="author-note-wrap">
      <div class="author-profile">	
        <img class="author-profile-image" :src="require(`@/assets/image/${roundDetail.authorProfileImage}`)">
        <span>{{roundDetail.authorName}}</span>
        <span style="color: #999">· 글/그림</span>
      </div>
      <div class="author-note">
        {{roundDetail.authorNote}}
      </div>
    </div>

	</div>
</template>

<script>
import { getRoundDetail, getRounds, postRoundLike } from "@/api/round";

export default {
  data() {
    return {
      roundDetail: {
        webtoonId: "",
        webtoonName: "",
        roundNumber: "",
        roundTitle: "",
        mergeManuscript: "",
        authorProfileImage: "default-thumbnail.png",
        authorName: "",
        authorNote: "",
        totalLikeCnt: "",
        isUserLike: "",
      },
      rounds: [],
      previewRounds: [],
      previewRangeStart: "",
      isPreviewLeftBtnDisabled: "false",
      isPreviewRightBtnDisabled: "false",
    };
  },
  props: ["webtoonType"],
  watch: {
    "$route.params.roundId": function () {
      location.reload();
      window.scrollTo({
        top: 0,
        left: 0,
      });
    },
    previewRangeStart() {
      this.previewRounds = [];

      const previewRangeEnd = Math.min(
        this.rounds.length - 1,
        this.previewRangeStart + 6
      );
      for (let i = this.previewRangeStart; i <= previewRangeEnd; i++) {
        this.previewRounds.push(this.rounds[i]);
      }

      this.setPreviewBtnDisable();
    },
  },
  async mounted() {
    await this.fetchRoundDetail();
    await this.fetchRounds();
  },
  methods: {
    setPreviewBtnDisable() {
      if (this.previewRangeStart === 0) {
        this.isPreviewLeftBtnDisabled = true;
      } else {
        this.isPreviewLeftBtnDisabled = false;
      }

      if (this.previewRangeStart + 7 >= this.rounds.length) {
        this.isPreviewRightBtnDisabled = true;
      } else {
        this.isPreviewRightBtnDisabled = false;
      }
    },
    async fetchRoundDetail() {
      try {
        const response = await getRoundDetail(this.$route.params.roundId);
        console.log(response.data);
        this.roundDetail = response.data;
      } catch (error) {
        console.log(error);
      }
    },
    async fetchRounds() {
      try {
        const response = await getRounds(this.$route.params.webtoonId);
        this.rounds = response.data;
        this.setPreviewRange();
      } catch (error) {
        console.log(error);
      }
    },
    setPreviewRange() {
      const now = this.roundDetail.roundNumber - 1;
      let start = Math.max(0, now - 3);
      this.previewRangeStart = start;
    },
    isNowRound(roundNumber) {
      return this.roundDetail.roundNumber == roundNumber;
    },
    lessRange() {
      this.previewRangeStart = Math.max(0, this.previewRangeStart - 7);
    },
    rightRange() {
      this.previewRangeStart = Math.min(
        this.rounds.length - 1,
        this.previewRangeStart + 7
      );
    },
    async clickRoundLike() {
      try {
        const response = await postRoundLike(this.$route.params.roundId);
        this.roundDetail.totalLikeCnt = response.data.totalLikeCnt;
        this.roundDetail.isUserLike = response.data.isUserLike;
      } catch (error) {
        console.log(error);
      }
    },
    moveBeforeRound() {
      if (this.roundDetail.roundNumber === 1) {
        alert("첫화입니다.");
      } else {
        this.$router.push({
          name: "roundDetail",
          params: {
            webtoonId: this.roundDetail.webtoonId,
            roundId: this.$route.params.roundId - 1,
          },
        });
      }
    },
    moveNextRound() {
      if (this.roundDetail.roundNumber === this.rounds.length) {
        alert("마지막화입니다.");
      } else {
        this.$router.push({
          name: "roundDetail",
          params: {
            webtoonId: this.roundDetail.webtoonId,
            roundId: parseInt(this.$route.params.roundId, 10) + 1,
          },
        });
      }
    },
    // menubarScoll() {
    //   // 메뉴바 트랜지션
    //   const menubar = this.document.getElementById("menubar");
    //   const manuscript = this.document.getElementById("manuscript");

    //   window.addEventListener("scroll", transition);
    //   console.log(this.window.scrollY);
    //   const transition = function () {
    //     if (manuscript !== null) {
    //       if (this.window.scrollY >= manuscript.offsetTop) {
    //         menubar.style.transition = "opacity 0.5s ease";
    //         menubar.style.opacity = 0;
    //       } else {
    //         menubar.style.transition = "opacity 0.5s ease";
    //         menubar.style.opacity = 1;
    //       }
    //     } else {
    //       window.removeEventListener("scroll", transition);
    //     }
    //   };
    // },
  },
};
</script>

<style scoped>
.container > * {
  margin: 0 auto;
}
/* 상단 메뉴바 */
.menu-bar {
  background-color: #383b45;
  height: 70px;
  display: flex;
  align-items: center;
  font-size: 18px;
  position: sticky;
  top: 0;
  width: 100%;
  z-index: 1000;
}
.menu-bar :first-child {
  margin-left: 20px;
}
.menu-bar :last-child {
  margin-right: 20px;
}
.menu-bar * {
  color: white;
}
.menu-bar > * {
  margin-right: 15px;
}
.menu-bar *:not(i) {
  font-family: AppleSDGothicNeoB;
}
.separator {
  content: "";
  top: 3px;
  left: 12px;
  width: 1px;
  height: 13px;
  border-radius: 0.5px;
  background-color: #e3e2e2;
}
.before-round {
  margin-left: auto;
  cursor: pointer;
}
.next-round {
  cursor: pointer;
}

/* 원고 */
.manusript-wrap {
  width: 100%;
  justify-content: center;
  display: flex;
}
.manuscript {
  width: 690px;
}

/* 좋아요 */
.reaction-btn-wrap {
  display: table;
  border-collapse: collapse;
  margin-top: 150px;
  margin-bottom: 150px;
  text-align: center;
}
.reaction-btn-wrap > div {
  background-color: white;
  display: table-cell;
  border: 1px solid #efefef;
  padding: 22px 65px;
  cursor: pointer;
}
.reaction-btn-wrap .icon {
  font-size: 25px;
}
.reaction-btn-wrap .circle-check i {
  color: #00dc64;
}
.reaction-btn-wrap .title {
  font-family: AppleSDGothicNeoM;
  font-size: 18px;
  margin-top: 8px;
}
.reaction-btn-wrap .count {
  color: #999;
}
/* 목록 */
.round-list {
  border: 1px solid #efefef;
  height: 200px;
  margin: 30px 0 60px;
  display: flex;
  width: 100%;
}
.preview-round-wrap {
  display: flex;
  align-items: center;
  grid-gap: 35px;
}
.preview-round-item {
  width: 147px;
}
.round-thumbnail {
  width: 100%;
  border: 1px solid #efefef;
  border-radius: 4px;
  aspect-ratio: 540 / 340;
}
.round-title {
  margin: 0;
  margin-top: 5px;
  font-family: AppleSDGothicNeoSB;
}
.round-list button {
  background: white;
  border: none;
  font-size: 30px;
  padding: 0 20px;
  cursor: pointer;
}
.round-list .right-btn {
  margin-left: auto;
}
.active .round-thumbnail {
  border: 1px solid #00dc64;
}
.active .round-title {
  color: #00dc64;
}

.overflow-hidden {
  white-space: nowrap; /* 줄 바꿈 방지 */
  overflow: hidden; /* 넘치는 부분 숨김 */
  text-overflow: ellipsis; /* 넘치는 부분에 ... 추가 */
}

/* 작가의 말 */
.author-note-wrap {
  margin-top: 30px;
}
.author-profile {
  display: flex;
  direction: column;
  align-items: center;
  font-family: AppleSDGothicNeoSB;
  font-size: 18px;
}
.author-profile > * {
  margin-right: 5px;
}
.author-profile-image {
  width: 40px;
}
.author-note {
  padding: 18px 5px;
  font-family: AppleSDGothicNeoSB;
  font-size: 17px;
  border-bottom: 1px solid #efefef;
}
</style>

<template lang="ko">
	<div class="container">

		<!-- 상단 -->
		<div class="header">
			<div>총 {{paging.totalRoundCount}}화</div>
			<div class="sort-btn">
				<span :class={select:selectDesc} @click="sortToDesc(true)">최신화부터</span> | <span :class={select:!selectDesc} @click="sortToDesc(false)">1화부터</span>
			</div>
		</div>

		<!-- 회차 -->
		<div class="round-wrap">
			<div class="round-list-item" v-for="round in rounds">
				<router-link :to="{name: webtoonType+'RoundDetail', params: {webtoonId: `${$route.params.webtoonId}`, roundId: round.roundId}}">
					<img class="thumbnail" :src="require(`@/assets/image/${round.thumbnail}`)">
					<div class="detail-wrap">
						<div class="webtoon-title">{{round.roundNumber}}화 {{round.title}}</div>
						<div class="etc">
							<div><i class="fa-solid fa-heart"></i> {{round.totalLikeCount}}</div>
							<div> {{round.createdAt}}</div>
						</div>
					</div>
				</router-link>
			</div>
		</div>

		<!-- 페이지 -->
    <div class="paging-btn-wrap">
      <button :disabled="paging.isBeforePageBtnDisabled" @click="clickBeforePage"><i class="fa-solid fa-angle-left before-page-btn"></i></button>
      <div class="paging-btn">
        <button v-for="(page, index) in paging.pages" @click="changePage(page, index)" class="page-btn" :class="{active: paging.nowPageIndex===index}">{{page}}</button>
      </div>
      <button :disabled="paging.isNextPageBtnDisabled" @click="clickNextPage"><i class="fa-solid fa-angle-right next-page-btn"></i></button>
    </div>

	</div>

</template>

<script>
import { getRoundsWithPaging } from "@/api/round";

export default {
  data() {
    return {
      rounds: [], //roundId, roundNumber, thumbnail, title, createdAt, totalLikeCount
      totalRoundCount: "",
      selectDesc: true,
      paging: {
        roundLimit: 10,
        totalPageCount: "",
        pages: [],
        nowPageIndex: 0,
        startPage: "",
        pageLimit: 5,
        isBeforePageBtnDisabled: false,
        isNextPageBtnDisabled: false,
      },
    };
  },
  props: ["webtoonType"],
  watch: {
    "paging.startPage"() {
      this.paging.pages = [];
      const endPage = Math.min(
        this.paging.totalPageCount + 1,
        this.paging.startPage + this.paging.pageLimit
      );
      for (let i = this.paging.startPage; i < endPage; i++) {
        this.paging.pages.push(i);
      }
      this.paging.nowPageIndex = 0;

      this.setPageBtnDisabled();
    },
  },
  async mounted() {
    await this.fetchRounds(0);
    this.paging.startPage = 1;
  },
  methods: {
    async fetchRounds(offset) {
      try {
        const response = await getRoundsWithPaging(
          this.$route.params.webtoonId,
          offset,
          this.paging.roundLimit,
          this.selectDesc
        );
        console.log(response.data);
        this.rounds = response.data.rounds;
        this.paging.totalPageCount = response.data.pageCount;
        this.paging.totalRoundCount = response.data.totalRoundCount;
      } catch (error) {
        console.log(error);
      }
    },
    sortToDesc(isDesc) {
      if (this.selectDesc != isDesc) {
        this.selectDesc = !this.selectDesc;
        this.fetchRounds(0);
      }
    },
    setPageBtnDisabled() {
      if (this.paging.startPage === 1) {
        this.paging.isBeforePageBtnDisabled = true;
      } else {
        this.paging.isBeforePageBtnDisabled = false;
      }

      if (
        this.paging.startPage + this.paging.pageLimit >
        this.paging.totalPageCount
      ) {
        this.paging.isNextPageBtnDisabled = true;
      } else {
        this.paging.isNextPageBtnDisabled = false;
      }
    },
    clickBeforePage() {
      this.paging.startPage = Math.max(
        1,
        this.paging.startPage - this.paging.pageLimit
      );
      this.changePage(this.paging.startPage, 0);
    },
    clickNextPage() {
      this.paging.startPage = this.paging.startPage + this.paging.pageLimit;
      this.changePage(this.paging.startPage, 0);
    },
    async changePage(n, index) {
      await this.fetchRounds((n - 1) * this.paging.roundLimit);
      this.paging.nowPageIndex = index;
    },
  },
};
</script>

<style scoped>
.container {
  font-family: AppleSDGothicNeoM;
  color: #666;
  height: 1200px;
}
.header {
  display: flex;
  font-size: 18px;
}
.sort-btn {
  margin-left: auto;
}
.sort-btn span {
  cursor: pointer;
}
.select {
  color: black;
  font-family: AppleSDGothicNeoSB;
}

/* 회차 리스트 */
.round-wrap {
  margin-top: 10px;
}
.round-wrap .round-list-item:first-child {
  border-top: 1px solid #efefef;
}
.round-list-item {
  height: 90px;
  border-bottom: 1px solid #efefef;
}
.round-list-item a {
  display: flex;
  height: 100%;
  padding: 10px 0;
  box-sizing: border-box;
}

/* 썸네일 이미지 */
.thumbnail {
  width: 120px;
  height: 100%;
  border-radius: 3px;
}

/* 상세 정보 */
.detail-wrap {
  margin-left: 15px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.webtoon-title {
  font-size: 18px;
  color: black;
  font-family: AppleSDGothicNeoSB;
}
.etc {
  display: flex;
  font-size: 15px;
  color: #999;
  margin-top: 4px;
}
.etc *:not(:last-child) {
  margin-right: 8px;
}
.etc i {
  font-size: 12px;
}

/* 페이징 */
.paging-btn-wrap {
  display: flex;
  margin-top: 30px;
  justify-content: center;
}
.paging-btn-wrap button {
  background-color: white;
  border: none;
  font-size: 17px;
  font-family: AppleSDGothicNeoB;
  cursor: pointer;
}
.paging-btn-wrap button {
  margin-right: 10px;
}
.paging-btn .active {
  color: #00dc64;
}
</style>

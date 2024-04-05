<template lang="ko">
	<div class="container">

		<!-- 상단 -->
		<div class="header">
			<div>총 {{totalRoundCount}}화</div>
			<div class="sort-btn">
				<span :class={select:selectDesc} @click="sortToDesc(true)">최신화부터</span> | <span :class={select:!selectDesc} @click="sortToDesc(false)">1화부터</span>
			</div>
		</div>

		<!-- 회차 -->
		<div class="round-wrap">
			<div class="round-list-item" v-for="round in rounds">
				<router-link :to="{name: 'roundDetail', params: {webtoonId: `${$route.params.webtoonId}`, roundId : `${round.roundId}`}}">
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
      <button :disabled="isBeforePageBtnDisabled" @click="clickBeforePage"><i class="fa-solid fa-angle-left before-page-btn"></i></button>
			<button v-for="(page, index) in pages" @click="changePage(page.n, index)" class="page-btn" :class="{active:page.isNowPage}">{{page.n}}</button>
      <button :disabled="isNextPageBtnDisabled" @click="clickNextPage"><i class="fa-solid fa-angle-right next-page-btn"></i></button>
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
      roundLimit: 10,
      selectDesc: true,
      pages: [],
      nowPageIndex: 0,
      startPage: "",
      totalPageCount: "",
      pageLimit: 5,
      isBeforePageBtnDisabled: false,
      isNextPageBtnDisabled: false,
    };
  },
  watch: {
    startPage() {
      this.pages = [];
      const end = Math.min(
        this.totalPageCount + 1,
        this.startPage + this.pageLimit
      );
      for (let i = this.startPage; i < end; i++) {
        this.pages.push({ n: i, isNowPage: false });
      }
      this.nowPageIndex = 0;
      this.pages[0].isNowPage = true;

      this.setPageBtnDisabled();
    },

    nowPageIndex(val, oldVal) {
      this.pages[oldVal].isNowPage = false;
      this.pages[val].isNowPage = true;
    },
  },
  async mounted() {
    await this.fetchRounds(0, true);
    this.startPage = 1;
  },
  methods: {
    async fetchRounds(offset, isDesc) {
      try {
        console.log("ROUND LIST");
        const response = await getRoundsWithPaging(
          this.$route.params.webtoonId,
          offset,
          this.roundLimit,
          isDesc
        );
        console.log(response.data);
        this.rounds = response.data.rounds;
        this.totalPageCount = response.data.pageCount;
        this.totalRoundCount = response.data.totalRoundCount;
      } catch (error) {
        console.log(error);
      }
    },
    sortToDesc(isDesc) {
      if (this.selectDesc != isDesc) {
        this.fetchRounds(0, isDesc);
        this.selectDesc = !this.selectDesc;
      }
    },
    changePage(n, index) {
      this.fetchRounds((n - 1) * 10, this.selectDesc);
      this.nowPageIndex = index;
    },
    clickBeforePage() {
      this.startPage = Math.max(1, this.startPage - this.pageLimit);
      this.changePage(this.startPage, 0);
    },
    clickNextPage() {
      this.startPage = Math.min(
        this.totalPageCount + 1,
        this.startPage + this.pageLimit
      );
      this.changePage(this.startPage, 0);
    },
    setPageBtnDisabled() {
      if (this.startPage === 1) {
        this.isBeforePageBtnDisabled = true;
      } else {
        this.isBeforePageBtnDisabled = false;
      }

      if (this.startPage + this.pageLimit > this.totalPageCount) {
        this.isNextPageBtnDisabled = true;
      } else {
        this.isNextPageBtnDisabled = false;
      }
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
  height: 100px;
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
  text-align: center;
  margin-top: 30px;
}
.paging-btn-wrap > * {
  margin-right: 20px;
}
.paging-btn-wrap button {
  background-color: white;
  border: none;
  font-size: 17px;
  font-family: AppleSDGothicNeoB;
  cursor: pointer;
}
.paging-btn-wrap .active {
  color: #00dc64;
}
.before-page-btn,
.next-page-btn {
  cursor: pointer;
}
</style>

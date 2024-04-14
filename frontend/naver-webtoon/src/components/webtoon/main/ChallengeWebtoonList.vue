<template lang="ko">
	<div class="container">

    <!-- 타이틀, 정렬 -->
		<div class="subject-container">
			<p class="title">장르별 전체 웹툰</p>
			<div class="filter">
				<input type="radio" id="popularity" v-model="selectedSorting" value="POPULARITY" hidden/>
        <label for="popularity">인기순</label> · 
				<input type="radio" id="update" v-model="selectedSorting" value="UPDATE" hidden/>
        <label for="update">업데이트순</label>
			</div>
		</div>

    <!-- 웹툰 리스트 -->
		<div class="webtoon-container">
      <ThumbnailComp v-for="(webtoon, key, index) in webtoons" :key=key :headerTitle=genreTitle[index] :webtoons=webtoon></ThumbnailComp>
		</div>

    <!-- 페이지 -->
    <div class="paging-btn-wrap">
      <button :disabled="beforePageBtnDisabled" @click="clickBeforePage"><i class="fa-solid fa-angle-left before-page-btn"></i></button>
			<button v-for="(page, index) in pages" @click="changePage(page.n, index)" class="page-btn" :class="{active:page.isNowPage}">{{page.n}}</button>
      <button :disabled="nextPageBtnDisabled" @click="clickNextPage"><i class="fa-solid fa-angle-right next-page-btn"></i></button>
    </div>

	</div>
</template>

<script>
import { getChallengeWebtoonAll } from "@/api/webtoon";
import ThumbnailComp from "./ThumbnailComp.vue";

export default {
  data() {
    return {
      webtoons: {
        ROMANCE: [],
        FANTASY: [],
        DAILY_LIFE: [],
        COMEDY: [],
        DRAMA: [],
        MARTIAL_ARTS_AND_HISTORICAL: [],
        THRILLER: [],
        SPORTS: [],
      },
      genreTitle: [
        "로맨스",
        "판타지",
        "일상",
        "개그",
        "드라마",
        "무협/사극",
        "스릴러",
        "스포츠",
      ],
      pages: [],
      nowPageIndex: 0,
      totalPageCount: 0,
      startPageNum: "",
      pageLimit: 5,
      webtoonLimit: 5,
      offset: 0,
      selectedSorting: "POPULARITY",
      beforePageBtnDisabled: false,
      nextPageBtnDisabled: false,
    };
  },
  components: {
    ThumbnailComp,
  },
  async mounted() {
    await this.fetchChallengeWebtoons();
    this.startPageNum = 1;
  },
  watch: {
    selectedSorting() {
      this.fetchChallengeWebtoons();
    },
    startPageNum() {
      this.pages = [];
      const end = Math.min(
        this.totalPageCount + 1,
        this.startPageNum + this.pageLimit
      );
      for (let i = this.startPageNum; i < end; i++) {
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
    offset() {
      this.fetchChallengeWebtoons();
    },
  },
  methods: {
    async fetchChallengeWebtoons() {
      try {
        const response = await getChallengeWebtoonAll(
          this.offset,
          this.pageLimit,
          this.selectedSorting
        );
        console.log(response);
        this.setWebtoons(response.data.webtoonMap);
        this.totalPageCount = response.data.totalPageCount;
      } catch (error) {
        console.log(error);
      }
    },
    setWebtoons(webtoonMap) {
      this.webtoons.ROMANCE = webtoonMap.ROMANCE;
      this.webtoons.FANTASY = webtoonMap.FANTASY;
      this.webtoons.DRAMA = webtoonMap.DRAMA;
      this.webtoons.DAILY_LIFE = webtoonMap.DAILY_LIFE;
      this.webtoons.COMEDY = webtoonMap.COMEDY;
      this.webtoons.MARTIAL_ARTS_AND_HISTORICAL =
        webtoonMap.MARTIAL_ARTS_AND_HISTORICAL;
      this.webtoons.THRILLER = webtoonMap.THRILLER;
      this.webtoons.SPORTS = webtoonMap.SPORTS;
    },
    clickBeforePage() {
      this.startPageNum = Math.max(1, this.startPageNum - this.pageLimit);
      this.changePage(this.startPageNum, 0);
    },
    clickNextPage() {
      this.startPageNum = Math.min(
        this.totalPageCount + 1,
        this.startPageNum + this.pageLimit
      );
      this.changePage(this.startPageNum, 0);
    },
    changePage(n, index) {
      this.offset = (n - 1) * this.webtoonLimit;
      this.nowPageIndex = index;
    },
    setPageBtnDisabled() {
      if (this.startPageNum === 1) {
        this.beforePageBtnDisabled = true;
      } else {
        this.beforePageBtnDisabled = false;
      }

      if (this.startPageNum + this.pageLimit > this.totalPageCount) {
        this.nextPageBtnDisabled = true;
      } else {
        this.nextPageBtnDisabled = false;
      }
    },
  },
};
</script>

<style scoped>
.subject-container {
  display: flex;
}
.title {
  margin: 0;
  font-family: AppleSDGothicNeoB;
  font-size: 1.4rem;
}
.filter {
  margin-left: 1rem;
  line-height: 30px;
}
.filter label {
  font-size: 13px;
  font-family: AppleSDGothicNeoEB;
  cursor: pointer;
}
.filter input:checked + label {
  color: #00dc64;
}

/* webtoons */
.webtoon-container {
  border: #d9d9d9 solid 0.5px;
  width: 100%;
  display: grid;
  grid-template-columns: repeat(8, 140px);
  margin-top: 20px;
  column-gap: 30px;
  box-sizing: border-box;
  padding: 0 10px;
  justify-content: center;
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

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

    <!-- 페이징 -->
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
      paging: {
        webtoonLimit: 5,
        totalPageCount: "",
        pages: [],
        nowPageIndex: 0,
        startPage: "",
        pageLimit: 5,
        isBeforePageBtnDisabled: false,
        isNextPageBtnDisabled: false,
      },
      selectedSorting: "POPULARITY",
    };
  },
  components: {
    ThumbnailComp,
  },
  async mounted() {
    await this.fetchChallengeWebtoons(0);
    this.paging.startPage = 1;
  },
  watch: {
    selectedSorting() {
      this.fetchChallengeWebtoons(0);
    },
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
  methods: {
    async fetchChallengeWebtoons(offset) {
      try {
        const response = await getChallengeWebtoonAll(
          offset,
          this.paging.pageLimit,
          this.selectedSorting
        );
        console.log(response);
        this.setWebtoons(response.data.webtoonMap);
        this.paging.totalPageCount = response.data.totalPageCount;
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
      await this.fetchChallengeWebtoons((n - 1) * this.paging.webtoonLimit);
      this.paging.nowPageIndex = index;
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

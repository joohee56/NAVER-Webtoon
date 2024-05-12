<template lang="ko">
	<div class="container">

    <!-- 타이틀 -->
		<div class="subject-wrap">
			<div class="title">전체 {{subjectTitle}} 웹툰</div>
			<div class="filter-wrap">
        <input type="radio" id="popularity" v-model="selectedSorting" value="POPULARITY" hidden/>
        <label for="popularity">인기순</label> · 
				<input type="radio" id="update" v-model="selectedSorting" value="UPDATE" hidden/>
        <label for="update">업데이트순</label>
			</div>
		</div>

		<WebtoonContainer :webtoons=this.webtoons webtoonType="challenge"></WebtoonContainer>

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
import { getChallengeWebtoonByGenre } from "@/api/webtoon";
import WebtoonContainer from "@/components/webtoon/dayOfWeek/WebtoonContainer.vue";

export default {
  data() {
    return {
      genre: this.$route.params.genre,
      selectedSorting: "POPULARITY",
      webtoons: [],
      paging: {
        webtoonLimit: 28,
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
  components: {
    WebtoonContainer,
  },
  watch: {
    "$route.params.genre"() {
      location.reload();
      window.scrollTo({
        top: 0,
      });
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
    selectedSorting() {
      this.fetchWebtoons(0);
    },
  },
  async mounted() {
    await this.fetchWebtoons(0);
    this.paging.startPage = 1;
  },
  methods: {
    async fetchWebtoons(offset) {
      try {
        const response = await getChallengeWebtoonByGenre(
          this.genre,
          this.selectedSorting,
          offset,
          this.paging.webtoonLimit
        );
        console.log(response);
        if (response.status === 200) {
          this.webtoons = response.data.webtoons;
          this.paging.totalPageCount = response.data.totalPageCount;
        }
      } catch (error) {
        console.log(error);
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
      await this.fetchWebtoons((n - 1) * this.paging.webtoonLimit);
      this.paging.nowPageIndex = index;
    },
  },
  computed: {
    subjectTitle() {
      switch (this.genre) {
        case "ROMANCE":
          return "로맨스";
        case "FANTASY":
          return "판타지";
        case "ACTION":
          return "액션";
        case "DAILY_LIFE":
          return "일상";
        case "COMEDY":
          return "개그";
        case "MARTIAL_ARTS_AND_HISTORICAL":
          return "무협/사극";
        case "THRILLER":
          return "스릴러";
        case "SPORTS":
          return "스포츠";
        case "DRAMA":
          return "드라마";
        case "EMOTION":
          return "감성";
      }
      return "오류";
    },
  },
};
</script>

<style scoped>
.container {
  margin: 0 auto;
  font-family: "Inter", sans-serif;
  font-optical-sizing: auto;
  font-weight: 400;
  font-style: normal;
  font-variation-settings: "slnt" 0;
  width: 85rem;
  margin-top: 50px;
}

/* 타이틀 */
.subject-wrap {
  display: flex;
  margin-bottom: 20px;
}
.title {
  margin: 0;
  font-family: AppleSDGothicNeoB;
  font-size: 23px;
}
.filter-wrap {
  margin-left: 20px;
  margin-top: 5px;
}
.filter-wrap label {
  font-size: 13px;
  font-family: AppleSDGothicNeoEB;
  cursor: pointer;
}
.filter-wrap input:checked + label {
  color: #00dc64;
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

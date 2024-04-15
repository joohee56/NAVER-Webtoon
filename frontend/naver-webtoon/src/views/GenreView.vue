<template lang="ko">
	<div class="container">

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
    };
  },
  watch: {
    "$route.params.genre"() {
      location.reload();
      window.scrollTo({
        top: 0,
        left: 0,
      });
    },
    selectedSorting() {
      this.fetchWebtoons();
    },
  },
  mounted() {
    this.fetchWebtoons();
  },
  methods: {
    async fetchWebtoons() {
      try {
        const response = await getChallengeWebtoonByGenre(
          this.genre,
          this.selectedSorting
        );
        console.log(response);
        this.webtoons = response.data;
      } catch (error) {
        console.log(error);
      }
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
  components: {
    WebtoonContainer,
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
</style>

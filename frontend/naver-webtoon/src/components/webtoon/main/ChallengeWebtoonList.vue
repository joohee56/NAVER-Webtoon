<template lang="ko">
	<div class="container">
		<div class="subject-container">
			<p class="title">장르별 전체 웹툰</p>
			<div class="filter">
				<input type="radio" id="popularity" v-model="selectedSorting" value="POPULARITY" hidden/>
        <label for="popularity">인기순</label> · 
				<input type="radio" id="update" v-model="selectedSorting" value="UPDATE" hidden/>
        <label for="update">업데이트순</label>
			</div>
		</div>

		<div class="webtoon-container">
      <ThumbnailComp v-for="(webtoon, key, index) in webtoons" :key=key :headerTitle=genreTitle[index] :webtoons=webtoon></ThumbnailComp>
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
      totalPageCount: 0,
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
      selectedSorting: "POPULARITY",
    };
  },
  components: {
    ThumbnailComp,
  },
  mounted() {
    this.fetchChallengeWebtoons();
  },
  watch: {
    selectedSorting() {
      this.fetchChallengeWebtoons();
    },
  },
  methods: {
    async fetchChallengeWebtoons() {
      try {
        const offset = 0;
        const limit = 5;
        const response = await getChallengeWebtoonAll(
          offset,
          limit,
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
</style>

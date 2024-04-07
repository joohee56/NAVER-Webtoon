<template lang="ko">
	<div class="container">
		<div class="subject-container">
			<p class="title">요일별 전체 웹툰</p>
			<div class="filter">
				<button class="active">인기순</button> · 
				<button>업데이트순</button> ·
				<button>조회순</button>
			</div>
		</div>

		<div class="genre-wrap">
      <div v-for="genre in genres">
        <input type="checkbox" :id="genre.value" :value="genre.value" @click="clickGenre(genre.value)" v-model="selectedGenres">
        <label class="genre-checkbox-label" :for="genre.value">{{genre.title}}</label>
      </div>
		</div>

		<div class="webtoon-container">
			<ThumbnailComp v-bind:dayOfWeekHeader="dayOfWeekHeaders[0]" :webtoons="webtoons.monday" :dayOfWeek="1"></ThumbnailComp>
			<ThumbnailComp v-bind:dayOfWeekHeader="dayOfWeekHeaders[1]" :webtoons="webtoons.tuesday" :dayOfWeek="2"></ThumbnailComp>
			<ThumbnailComp v-bind:dayOfWeekHeader="dayOfWeekHeaders[2]" :webtoons="webtoons.wednesday" :dayOfWeek="3"></ThumbnailComp>
			<ThumbnailComp v-bind:dayOfWeekHeader="dayOfWeekHeaders[3]" :webtoons="webtoons.thursday" :dayOfWeek="4"></ThumbnailComp>
			<ThumbnailComp v-bind:dayOfWeekHeader="dayOfWeekHeaders[4]" :webtoons="webtoons.friday" :dayOfWeek="5"></ThumbnailComp>
			<ThumbnailComp v-bind:dayOfWeekHeader="dayOfWeekHeaders[5]" :webtoons="webtoons.saturday" :dayOfWeek="6"></ThumbnailComp>
			<ThumbnailComp v-bind:dayOfWeekHeader="dayOfWeekHeaders[6]" :webtoons="webtoons.sunday" :dayOfWeek="0"></ThumbnailComp>
		</div>
	</div>
</template>

<script>
import { getOfficialWebtoonAll } from "@/api/webtoon";
import ThumbnailComp from "./ThumbnailComp.vue";

export default {
  data() {
    return {
      webtoons: {
        monday: [],
        tuesday: [],
        wednesday: [],
        thursday: [],
        friday: [],
        saturday: [],
        sunday: [],
      },
      dayOfWeekHeaders: [
        "월요웹툰",
        "화요웹툰",
        "수요웹툰",
        "목요웹툰",
        "금요웹툰",
        "토요웹툰",
        "일요웹툰",
      ],
      genres: [
        { title: "전체", value: "all" },
        { title: "로맨스", value: "romance" },
        { title: "일상", value: "daily_life" },
        { title: "판타지", value: "fantasy" },
        { title: "액션", value: "action" },
        { title: "스포츠", value: "sports" },
        { title: "개그", value: "comedy" },
        { title: "스릴러", value: "thriller" },
        { title: "무협/사극", value: "martial_arts_and_historical" },
        { title: "감성", value: "emotion" },
        { title: "완결", value: "complete" },
      ],
      selectedGenres: [],
    };
  },
  components: {
    ThumbnailComp,
  },
  mounted() {
    this.fetchOfficialWebtoons();
  },
  methods: {
    async fetchOfficialWebtoons() {
      try {
        const response = await getOfficialWebtoonAll();
        for (const webtoon of response.data) {
          switch (webtoon.dayOfWeek) {
            case "MONDAY":
              this.webtoons.monday.push(webtoon);
              break;
            case "TUESDAY":
              this.webtoons.tuesday.push(webtoon);
              break;
            case "WEDNESDAY":
              this.webtoons.wednesday.push(webtoon);
              break;
            case "THURSDAY":
              this.webtoons.thursday.push(webtoon);
              break;
            case "FRIDAY":
              this.webtoons.friday.push(webtoon);
              break;
            case "SATURDAY":
              this.webtoons.saturday.push(webtoon);
              break;
            case "SUNDAY":
              this.webtoons.sunday.push(webtoon);
              break;
          }
        }
        console.log(this.webtoons);
      } catch (error) {
        console.log(error);
      }
    },
    clickGenre(value) {
      if (value === "all") {
        this.selectedGenres = [];
        this.selectedGenres.push(value);
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
}
.filter button {
  background: none;
  border: none;
  padding: 0;
  font-size: 13;
  font-family: AppleSDGothicNeoEB;
}
.active {
  color: #00dc64;
}

/* genre checkbox */
.genre-wrap {
  float: right;
  margin-bottom: 20px;
  font-family: AppleSDGothicNeoM;
  display: flex;
}
.genre-wrap > div {
  margin-right: 10px;
}
input[type="checkbox"] {
  display: none;
}
.genre-checkbox-label::before {
  content: "";
  display: inline-block;
  width: 13px;
  border-radius: 3px;
  aspect-ratio: 1/1;
  background-color: #fff;
  border: 1px solid #999;
  margin-right: 5px;
  vertical-align: middle;
}
/* 체크된 상태일 때의 가상 요소 스타일 정의 */
input[type="checkbox"]:checked + .genre-checkbox-label::before {
  background-color: #00dc64;
  border: 1px solid #00dc64;
  border-radius: 4px;
  content: "\2713";
  color: white;
  font-size: 16px;
  text-align: center;
  width: 13px;
  height: 13px;
  font-weight: 600;
  line-height: 14px;
}

/* webtoons */
.webtoon-container {
  border: #d9d9d9 solid 0.5px;
  display: flex;
  width: 100%;
}
</style>

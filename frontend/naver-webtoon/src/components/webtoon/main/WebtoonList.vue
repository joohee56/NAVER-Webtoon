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

		<div class="genre">
			<form>
				<label><input type="checkbox">전체</label>
				<label><input type="checkbox">로맨스</label>
				<label><input type="checkbox">판타지</label>
				<label><input type="checkbox">액션</label>
				<label><input type="checkbox">스포츠</label>
				<label><input type="checkbox">개그</label>
				<label><input type="checkbox">스릴러</label>
				<label><input type="checkbox">무협/사극</label>
				<label><input type="checkbox">감성</label>
				<label><input type="checkbox">소설원작</label>
			</form>
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
  },
};
</script>

<style scoped>
.subject-container {
  display: flex;
}
.title {
  margin: 0;
  font-family: AppleSDGothicNeoSB;
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
.genre {
  float: right;
  margin-bottom: 20px;
  font-family: AppleSDGothicNeoM;
}

/* webtoons */
.webtoon-container {
  border: #d9d9d9 solid 0.5px;
  display: flex;
  width: 100%;
}
</style>

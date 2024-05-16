<template lang="ko">
	<div class="container">

    <!-- 타이틀 -->
		<div class="subject-container">
			<p class="title">요일별 전체 웹툰</p>
			<div class="filter">
				<input type="radio" id="popularity" v-model="selectedSorting" value="POPULARITY" hidden/>
        <label for="popularity">인기순</label> · 
				<input type="radio" id="update" v-model="selectedSorting" value="UPDATE" hidden/>
        <label for="update">업데이트순</label>
			</div>
		</div>

    <!-- 장르 필터 -->
		<div class="genre-wrap">
      <div v-for="genre in genres">
        <input type="checkbox" :id="genre.value" :value="genre.value" @click="clickGenre(genre.value)" v-model="selectedGenres">
        <label class="genre-checkbox-label" :for="genre.value">{{genre.title}}</label>
      </div>
		</div>

    <!-- 웹툰 리스트 -->
		<div class="webtoon-container">
			<ThumbnailComp :headerTitle="dayOfWeekHeaders[0]" :webtoons="webtoons.monday" :dayOfWeek="1"></ThumbnailComp>
			<ThumbnailComp :headerTitle="dayOfWeekHeaders[1]" :webtoons="webtoons.tuesday" :dayOfWeek="2"></ThumbnailComp>
			<ThumbnailComp :headerTitle="dayOfWeekHeaders[2]" :webtoons="webtoons.wednesday" :dayOfWeek="3"></ThumbnailComp>
			<ThumbnailComp :headerTitle="dayOfWeekHeaders[3]" :webtoons="webtoons.thursday" :dayOfWeek="4"></ThumbnailComp>
			<ThumbnailComp :headerTitle="dayOfWeekHeaders[4]" :webtoons="webtoons.friday" :dayOfWeek="5"></ThumbnailComp>
			<ThumbnailComp :headerTitle="dayOfWeekHeaders[5]" :webtoons="webtoons.saturday" :dayOfWeek="6"></ThumbnailComp>
			<ThumbnailComp :headerTitle="dayOfWeekHeaders[6]" :webtoons="webtoons.sunday" :dayOfWeek="0"></ThumbnailComp>
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
        //webtoonId, webtoonName, posterStoreFileName, dayOfWeek, roundUpdateCount, totalLikeCount, updatedAt
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
        { title: "전체", value: "ALL" },
        { title: "로맨스", value: "ROMANCE" },
        { title: "판타지", value: "FANTASY" },
        { title: "액션", value: "ACTION" },
        { title: "일상", value: "DAILY_LIFE" },
        { title: "스릴러", value: "THRILLER" },
        { title: "개그", value: "COMEDY" },
        { title: "무협/사극", value: "MARTIAL_ARTS_AND_HISTORICAL" },
        { title: "드라마", value: "DRAMA" },
        { title: "감성", value: "EMOTION" },
        { title: "스포츠", value: "SPORTS" },
      ],
      selectedGenres: ["ALL"],
      selectedSorting: "POPULARITY",
    };
  },
  components: {
    ThumbnailComp,
  },
  mounted() {
    this.fetchOfficialWebtoons();
    this.fetchSelectedGenres();
  },
  watch: {
    selectedGenres() {
      if (this.selectedGenres.length === 0) {
        this.selectedGenres = [];
        this.selectedGenres.push("ALL");
      }
      localStorage.setItem("genres", JSON.stringify(this.selectedGenres));
      this.fetchOfficialWebtoons();
    },
    selectedSorting() {
      this.fetchOfficialWebtoons();
    },
  },
  methods: {
    async fetchOfficialWebtoons() {
      try {
        const response = await getOfficialWebtoonAll(
          this.selectedGenres,
          this.selectedSorting
        );

        if (response.status === 200) {
          this.webtoons = {
            monday: [],
            tuesday: [],
            wednesday: [],
            thursday: [],
            friday: [],
            saturday: [],
            sunday: [],
          };

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
        }
      } catch (error) {
        console.log(error);
      }
    },
    fetchSelectedGenres() {
      if (localStorage.getItem("genres") !== null) {
        this.selectedGenres = JSON.parse(localStorage.getItem("genres"));
      }
    },
    clickGenre(value) {
      if (value === "ALL") {
        this.selectedGenres = [];
        this.selectedGenres.push(value);
      } else {
        if (this.selectedGenres[0] === "ALL") {
          this.selectedGenres.splice(0, 1); //"ALL" 제거
          this.selectedGenres.push(value);
        }
      }
    },
  },
};
</script>

<style scoped>
.subject-container {
  display: flex;
  margin-bottom: 15px;
}
.title {
  margin: 0;
  font-family: AppleSDGothicNeoB;
  font-size: 24px;
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

/* genre checkbox */
.genre-wrap {
  float: right;
  margin-bottom: 20px;
  font-family: AppleSDGothicNeoM;
  display: flex;
}
.genre-wrap > div {
  margin-right: 14px;
}
input[type="checkbox"] {
  display: none;
}
.genre-checkbox-label {
  display: inline-block;
  vertical-align: middle;
}
.genre-checkbox-label::before {
  content: "";
  display: inline-block;
  width: 13px;
  height: 13px;
  border-radius: 3px;
  background-color: #fff;
  border: 1px solid #bbbbbb;
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
  width: 14px;
  height: 14px;
  font-weight: 600;
  line-height: 14px;
}

/* webtoons */
.webtoon-container {
  border: #d9d9d9 solid 0.5px;
  /* display: flex; */
  width: 100%;
  display: grid;
  /* grid-template-columns: repeat(auto-fit, minmax(150px, 1fr)); */
  grid-template-columns: repeat(7, 1fr);
}
</style>

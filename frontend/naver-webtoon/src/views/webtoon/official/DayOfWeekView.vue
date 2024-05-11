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

		<WebtoonContainer :webtoons=this.webtoons webtoonType="official"></WebtoonContainer>
	</div>
</template>

<script>
import { getOfficialWebtoonAllByDayOfWeek } from "@/api/webtoon";
import WebtoonContainer from "@/components/webtoon/dayOfWeek/WebtoonContainer.vue";

export default {
  data() {
    return {
      dayOfWeek: this.$route.params.dayOfWeek,
      selectedSorting: "POPULARITY",
      webtoons: [],
    };
  },
  computed: {
    subjectTitle() {
      switch (this.dayOfWeek) {
        case "MONDAY":
          return "월요";
        case "TUESDAY":
          return "화요";
        case "WEDNESDAY":
          return "수요";
        case "THURSDAY":
          return "목요";
        case "FRIDAY":
          return "금요";
        case "SATURDAY":
          return "토요";
        case "SUNDAY":
          return "일요";
      }
      return "오류";
    },
  },
  components: {
    WebtoonContainer,
  },
  watch: {
    "$route.params.dayOfWeek"() {
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
        const response = await getOfficialWebtoonAllByDayOfWeek(
          this.dayOfWeek,
          this.selectedSorting
        );
        console.log(response);
        this.webtoons = response.data;
      } catch (error) {
        console.log(error);
      }
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
</style>

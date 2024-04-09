<template lang="ko">
	<div class="container">
		<WebtoonRanking></WebtoonRanking>
		<div class="subject-wrap">
			<div class="title">전체 {{subjectTitle}} 웹툰</div>
			<div class="filter-wrap">
				<button class="active">인기순</button> · 
				<button>업데이트순</button> ·
				<button>조회순</button>
			</div>
		</div>
		<WebtoonContainer :dayOfWeek=this.dayOfWeek></WebtoonContainer>
	</div>
</template>

<script>
import WebtoonRanking from "@/components/webtoon/main/WebtoonRanking";
import WebtoonContainer from "@/components/webtoon/dayOfWeek/WebtoonContainer.vue";

export default {
  data() {
    return {
      dayOfWeek: this.$route.params.dayOfWeek,
    };
  },
  watch: {
    "$route.params.dayOfWeek"() {
      location.reload();
      window.scrollTo({
        top: 0,
        left: 0,
      });
    },
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
    WebtoonRanking,
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
  margin-left: auto;
}
.filter-wrap button {
  background: none;
  border: none;
  padding: 0;
  font-size: 13;
  font-family: AppleSDGothicNeoEB;
}
.active {
  color: #00dc64;
}
</style>

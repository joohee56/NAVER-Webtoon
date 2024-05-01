<template lang="ko">
	<div class="webtoon-wrap" v-if="webtoon !== undefined">
		<div>
			<router-link :to="{name: webtoonType+'RoundList', params: {webtoonId: webtoon.webtoonId}}"><img class="thumbnail" :src="require(`@/assets/image/${webtoon.thumbnail}`)" /></router-link>
		</div>
    <div class="info-wrap">
      <div class="webtoon-name">
        <router-link :to="{name: webtoonType+'RoundList', params: {webtoonId: webtoon.webtoonId}}">
          <span :class="{sameKeyword:webtoon.webtoonName === keyword}">{{webtoon.webtoonName}}</span>
        </router-link>
      </div>
      <div class="info-detail">
        <span class="author-name" :class="{sameKeyword:webtoon.authorName === keyword}">{{webtoon.authorName}}</span><span> · 글/그림</span>
        <span> | {{webtoon.dayOfWeek | dayOfWeekTitle}}웹툰</span>
        <span> · 총 {{webtoon.totalRoundCount}}화</span>
        <span> | 최종 업데이트 {{webtoon.recentUpdatedAt}}</span>
      </div>
      <div class="summary">
        {{webtoon.summary}}
      </div>
    </div>
	</div>
</template>

<script>
export default {
  props: ["webtoon", "webtoonType"],
  data() {
    return {
      keyword: this.$route.params.keyword,
    };
  },
  watch: {
    "$route.params.keyword"() {
      location.reload();
    },
  },
  // data() {
  //   return {
  //     keyword: this.$route.params.keyword,
  //     webtoon: {
  //       webtoonId: "",
  //       webtoonName: "",
  //       thumbnail: "default-webtoon-cover.png",
  //       totalRoundCount: "",
  //       summary: "",
  //       authorName: "",
  //       dayOfWeek: "",
  //       recentUpdatedAt: "",
  //     },
  //   };
  // },
  // created() {
  //   this.webtoon = { ...this.propWebtoon };
  // },
  filters: {
    dayOfWeekTitle(value) {
      switch (value) {
        case "MONDAY": {
          return "월요";
        }
        case "TUESDAY": {
          return "화요";
        }
        case "WEDNESDAY": {
          return "수요";
        }
        case "THURSDAY": {
          return "목요";
        }
        case "FRIDAY": {
          return "금요";
        }
        case "SATURDAY": {
          return "토요";
        }
        case "SUNDAY": {
          return "일요";
        }
      }
    },
  },
};
</script>

<style scoped>
.webtoon-wrap {
  display: flex;
  margin-bottom: 15px;
}
a:hover {
  text-decoration: underline;
  cursor: pointer;
}
.info-wrap {
  margin-left: 15px;
}
.thumbnail {
  width: 135px;
  aspect-ratio: 480 / 623;
  border-radius: 5px;
  border: 1px solid #e0e0e0;
}
.webtoon-name {
  font-family: AppleSDGothicNeoB;
  font-size: 20px;
}
.info-detail {
  color: #999;
  font-family: AppleSDGothicNeoSB;
}
.author-name {
  color: black !important;
}
.sameKeyword {
  color: #00dc64 !important;
}
.summary {
  margin-top: 10px;
  font-family: AppleSDGothicNeoM;
  line-height: 21px;
}
</style>

<template lang="ko">
	<div>
    <!-- 웹툰 -->
		<div v-if="totalOfficialCount > 0">
			<div class="title-wrap">
        <span class="category">웹툰</span>
        <span class="count"> 총 {{totalOfficialCount}}</span>
        <router-link :to="{name: 'webtoonWhole', params: {webtoonType: 'OFFICIAL', keyword: keyword}}" v-if="totalOfficialCount > limit" class="more-link">웹툰 더보기 <i class="fa-solid fa-chevron-right"></i></router-link>
      </div>
			<div class="result-wrap">
				<WebtoonComp v-for="(official, index) in officials" :webtoon=official webtoonType="official" :key="index"></WebtoonComp>
			</div>
		</div>

    <!-- 도전만화 -->
    <div v-if="totalChallengeCount > 0">
			<div class="title-wrap">
        <span class="category">도전만화</span> 
        <span class="count"> 총 {{totalChallengeCount}}</span> 
        <router-link :to="{name: 'webtoonWhole', params: {webtoonType: 'CHALLENGE', keyword: keyword}}" v-if="totalChallengeCount > limit" class="more-link">도전만화 더보기 <i class="fa-solid fa-chevron-right"></i></router-link>
      </div>
			<div class="result-wrap">
				<WebtoonComp v-for="(cahllenge, index) in challenges" :webtoon=cahllenge webtoonType="challenge" :key="index"></WebtoonComp>
			</div>
		</div>
	</div>
</template>

<script>
import WebtoonComp from "@/components/webtoon/search/WebtoonComp.vue";
import { getSearch } from "@/api/webtoon";

export default {
  data() {
    return {
      keyword: this.$route.params.keyword,
      offset: 0,
      limit: 5,
      challenges: [],
      officials: [],
      totalChallengeCount: "",
      totalOfficialCount: "",
    };
  },
  components: {
    WebtoonComp,
  },
  created() {
    this.fetchSearchResult();
  },
  methods: {
    async fetchSearchResult() {
      const response = await getSearch(this.keyword, this.offset, this.limit);
      this.challenges = response.data.challenges;
      this.officials = response.data.officials;
      this.totalChallengeCount = response.data.totalChallengeCount;
      this.totalOfficialCount = response.data.totalOfficialCount;
      console.log(response);
    },
  },
};
</script>

<style scoped>
.title-wrap {
  padding: 30px 0 10px;
  border-bottom: 1px solid #efefef;
}
.title-wrap .category {
  font-family: AppleSDGothicNeoSB;
  font-size: 23px;
}
.title-wrap .count {
  font-family: AppleSDGothicNeoSB;
  color: #666;
}
.title-wrap .more-link {
  float: right;
  font-family: AppleSDGothicNeoSB;
  color: #666;
  font-size: 17px;
}

.result-wrap {
  padding: 20px 0;
}
</style>

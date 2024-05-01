<template lang="ko">
	<div>
		<div>
			<div>웹툰 <span>총 {{totalOfficialCount}}</span> <router-link to="#">웹툰 더보기</router-link></div>
			<div>
				<WebtoonComp v-for="(official, index) in officials" :webtoon=official webtoonType="official" :key="index"></WebtoonComp>
			</div>
		</div>
		<WebtoonComp></WebtoonComp>
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

<style scoped></style>

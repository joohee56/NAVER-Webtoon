<template lang="ko">
	<div>
		{{keyword}}
	</div>
</template>

<script>
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
  created() {
    this.fetchSearchResult();
  },
  methods: {
    async fetchSearchResult() {
      const response = await getSearch(this.keyword, this.offset, this.limit);
      this.challenges = response.data.challenges;
      this.officials = response.data.challenges;
      this.totalChallengeCount = response.data.totalChallengeCount;
      this.totalOfficialCount = response.data.totalOfficialCount;
      console.log(response);
    },
  },
};
</script>

<style scoped></style>

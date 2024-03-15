<template lang="ko">
	<div class="container">
		<WebtoonRanking></WebtoonRanking>
		<WebtoonList></WebtoonList>
	</div>
</template>

<script>
import WebtoonRanking from "@/components/webtoon/main/WebtoonRanking";
import WebtoonList from "@/components/webtoon/main/WebtoonList.vue";

export default {
  components: {
    WebtoonRanking,
    WebtoonList,
  },
};
</script>

<style scoped>
.container {
  padding: 0 1rem;
  margin: 0 auto;
  font-family: "Inter", sans-serif;
  font-optical-sizing: auto;
  font-weight: 400;
  font-style: normal;
  font-variation-settings: "slnt" 0;
  width: 80rem;
}
</style>

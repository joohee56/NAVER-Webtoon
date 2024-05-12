<template lang="ko">
	<div class="container">
    <WebtoonInfo></WebtoonInfo>
    <RoundList webtoonType="challenge"></RoundList>
	</div>
</template>

<script>
import WebtoonInfo from "@/components/webtoon/roundList/WebtoonInfo.vue";
import RoundList from "@/components/webtoon/roundList/RoundList.vue";

export default {
  components: {
    WebtoonInfo,
    RoundList,
  },
};
</script>

<style scoped></style>

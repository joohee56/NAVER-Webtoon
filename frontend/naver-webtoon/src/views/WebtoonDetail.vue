<template lang="ko">
	<div class="container">
    <WebtoonInfo></WebtoonInfo>
    <RoundList></RoundList>
	</div>
</template>

<script>
import WebtoonInfo from "@/components/webtoon/detail/WebtoonInfo.vue";
import RoundList from "@/components/webtoon/detail/RoundList.vue";

export default {
  components: {
    WebtoonInfo,
    RoundList,
  },
  created() {},
};
</script>

<style scoped>
.container {
  width: 85rem;
  margin: 0 auto;
}
</style>

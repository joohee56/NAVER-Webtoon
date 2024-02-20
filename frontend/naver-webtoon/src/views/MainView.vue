<template lang="ko">
  <div>
    <WebtoonRanking></WebtoonRanking>
    <WebtoonList></WebtoonList>
  </div>
</template>

<script>
import WebtoonList from "@/components/main/WebtoonList.vue";
import WebtoonRanking from "@/components/main/WebtoonRanking.vue";

export default {
  components: {
    WebtoonList,
    WebtoonRanking,
  },
};
</script>

<style></style>

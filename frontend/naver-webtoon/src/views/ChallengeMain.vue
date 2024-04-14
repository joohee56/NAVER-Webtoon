<template lang="ko">
  <div class="container">
    <WebtoonRanking webtoonType="CHALLENGE"></WebtoonRanking>
    <ChallengeWebtoonList></ChallengeWebtoonList>
  </div>
</template>

<script>
import WebtoonRanking from "@/components/webtoon/main/WebtoonRanking";
import ChallengeWebtoonList from "@/components/webtoon/main/ChallengeWebtoonList";

export default {
  components: {
    WebtoonRanking,
    ChallengeWebtoonList,
  },
};
</script>

<style scoped></style>

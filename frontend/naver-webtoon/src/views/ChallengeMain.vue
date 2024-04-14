<template lang="ko">
  <div class="container">
    <WebtoonRanking webtoonType="CHALLENGE"></WebtoonRanking>
    <ChallengeWebtoonList></ChallengeWebtoonList>
  </div>
</template>

<script>
import WebtoonRanking from "@/components/webtoon/main/WebtoonRanking";
import ChallengeWebtoonList from "@/components/webtoon/main/ChallengeWebtoonList";
import { mapMutations } from "vuex";

export default {
  components: {
    WebtoonRanking,
    ChallengeWebtoonList,
  },
  created() {
    this.setGenreActive();
  },
  methods: {
    ...mapMutations("navStore", ["SET_GENRE_IS_ACTIVE"]),
    setGenreActive() {
      this.SET_GENRE_IS_ACTIVE(0);
    },
  },
};
</script>

<style scoped></style>

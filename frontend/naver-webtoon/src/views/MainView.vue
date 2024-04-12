<template lang="ko">
	<div class="container">
		<WebtoonRanking></WebtoonRanking>
		<WebtoonList></WebtoonList>
	</div>
</template>

<script>
import WebtoonRanking from "@/components/webtoon/main/WebtoonRanking";
import WebtoonList from "@/components/webtoon/main/WebtoonList.vue";
import { mapMutations } from "vuex";

export default {
  components: {
    WebtoonRanking,
    WebtoonList,
  },
  created() {
    this.setDayOfWeekActive();
  },
  methods: {
    ...mapMutations("dayOfWeekStore", ["SET_IS_ACTIVE"]),
    setDayOfWeekActive() {
      this.SET_IS_ACTIVE(0);
    },
  },
};
</script>

<style scoped>
.container {
  margin: 0 auto;
  font-family: "Inter", sans-serif;
  font-optical-sizing: auto;
  font-weight: 400;
  font-style: normal;
  font-variation-settings: "slnt" 0;
  width: 85rem;
}
</style>

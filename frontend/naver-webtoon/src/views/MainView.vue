<template lang="ko">
	<div class="container">
		<WebtoonRanking webtoonType="OFFICIAL"></WebtoonRanking>
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
    ...mapMutations("navStore", ["SET_DAY_OF_WEEK_IS_ACTIVE"]),
    setDayOfWeekActive() {
      this.SET_DAY_OF_WEEK_IS_ACTIVE(0);
    },
  },
};
</script>

<style scoped>
.container {
  margin: 0 auto;
  width: 85rem;
}
</style>

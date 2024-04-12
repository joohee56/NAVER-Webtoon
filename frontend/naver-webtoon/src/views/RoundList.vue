<template lang="ko">
	<div class="container">
    <WebtoonInfo></WebtoonInfo>
    <RoundList></RoundList>
	</div>
</template>

<script>
import WebtoonInfo from "@/components/webtoon/roundList/WebtoonInfo.vue";
import RoundList from "@/components/webtoon/roundList/RoundList.vue";
import { mapMutations } from "vuex";

export default {
  components: {
    WebtoonInfo,
    RoundList,
  },
  created() {
    this.setDayOfWeekActive();
  },
  methods: {
    ...mapMutations("dayOfWeekStore", ["SET_IS_ACTIVE"]),
    setDayOfWeekActive() {
      this.SET_IS_ACTIVE(this.$route.params.dayOfWeek);
    },
  },
};
</script>

<style scoped>
.container {
  width: 85rem;
  margin: 0 auto;
}
</style>

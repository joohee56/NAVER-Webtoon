<template lang="ko">
	<div class="container">
		<div class="line">
      <DayOfWeekNav></DayOfWeekNav>
		</div>
		<router-view></router-view>
	</div>
</template>

<script>
import DayOfWeekNav from "@/components/webtoon/header/DayOfWeekNav.vue";

export default {
  components: {
    DayOfWeekNav,
  },
};
</script>

<style scoped>
.line {
  width: 100%;
  border-bottom: 0.5px solid #dadada;
}
</style>

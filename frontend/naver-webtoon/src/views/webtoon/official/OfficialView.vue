<template lang="ko">
	<div class="container">
		<div class="line">
			<div class="day-of-week-nav">
				<DayOfWeekNav></DayOfWeekNav>
			</div>
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
  display: flex;
  flex-direction: column;
  align-items: center;
}
.day-of-week-nav {
  width: 85rem;
}
</style>

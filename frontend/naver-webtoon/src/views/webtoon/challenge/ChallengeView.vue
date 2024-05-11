<template lang="ko">
  <div class="container">
    <div class="line">
			<div class="genre-nav">
				<GenreNav></GenreNav>
			</div>
		</div>
    <div class="router-view">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import GenreNav from "@/components/webtoon/header/GenreNav.vue";

export default {
  components: {
    GenreNav,
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
.genre-nav {
  width: 85rem;
}
.router-view {
  width: 85rem;
  margin: 0 auto;
}
</style>

<template lang="ko">
  <div class="container">
    <div class="line">
      <GenreNav></GenreNav>
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
}
.router-view {
  width: 85rem;
  margin: 0 auto;
}
</style>

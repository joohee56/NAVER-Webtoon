<template lang="ko">
  <div class="container">
    <HeaderComp></HeaderComp>
    <router-view></router-view>
    <FooterComp></FooterComp>
  </div>
</template>

<script>
import HeaderComp from "@/components/webtoon/HeaderComp.vue";
import FooterComp from "@/components/webtoon/FooterComp.vue";

export default {
  components: {
    HeaderComp,
    FooterComp,
  },
};
</script>

<style scoped>
.container {
  width: 85rem;
  margin: 0 auto;
}
</style>

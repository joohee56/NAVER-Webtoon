<template lang="ko">
  <div class="container">
    <HeaderComp></HeaderComp>
    <router-view></router-view>
    <div class="footer">
      <FooterComp></FooterComp>
    </div>
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
footer {
  width: 85rem;
  margin: 0 auto;
  margin-top: 40px;
}
</style>

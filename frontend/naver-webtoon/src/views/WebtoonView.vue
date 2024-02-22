<template lang="ko">
  <div class="container">
    <HeaderComp></HeaderComp>
    <router-view name="subView"></router-view>
    <FooterComp></FooterComp>
  </div>
</template>

<script>
import HeaderComp from "@/components/HeaderComp.vue";
import FooterComp from "@/components/FooterComp.vue";

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

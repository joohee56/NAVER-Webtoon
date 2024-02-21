<template lang="ko">
  <div>
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

<style></style>

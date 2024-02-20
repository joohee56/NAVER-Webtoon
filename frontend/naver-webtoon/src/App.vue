<template>
  <div id="app">
    <HeaderComp></HeaderComp>
    <router-view></router-view>
    <FooterComp></FooterComp>
  </div>
</template>

<script>
import HeaderComp from "@/components/HeaderComp.vue";
import FooterComp from "@/components/FooterComp.vue";

export default {
  name: "App",
  components: {
    HeaderComp,
    FooterComp,
  },
};
</script>

<style>
body {
  padding: 0;
  margin: 0;
  font-family: "Inter", sans-serif;
  font-optical-sizing: auto;
  font-weight: 400;
  font-style: normal;
  font-variation-settings: "slnt" 0;
}
a {
  text-decoration: none;
  color: black;
}
@font-face {
  font-family: AppleSDGothicNeoEB;
  font-weight: 400;
  src: url("@/font/AppleSDGothicNeoEB00.woff2") format("woff2");
}
</style>

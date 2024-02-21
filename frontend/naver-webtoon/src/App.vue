<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
body {
  padding: 0 1rem;
  margin: 0 auto;
  font-family: "Inter", sans-serif;
  font-optical-sizing: auto;
  font-weight: 400;
  font-style: normal;
  font-variation-settings: "slnt" 0;
  width: 80rem;
}
a {
  color: black;
  text-decoration: none;
}
@font-face {
  font-family: AppleSDGothicNeoM;
  font-weight: 400;
  src: url("@/font/AppleSDGothicNeoM00.woff2") format("woff2");
}
@font-face {
  font-family: AppleSDGothicNeoB;
  font-weight: 400;
  src: url("@/font/AppleSDGothicNeoB00.woff2") format("woff2");
}
@font-face {
  font-family: AppleSDGothicNeoEB;
  font-weight: 400;
  src: url("@/font/AppleSDGothicNeoEB00.woff2") format("woff2");
}

@font-face {
  font-family: AppleSDGothicNeoH;
  font-weight: 400;
  src: url("@/font/AppleSDGothicNeoH00.woff2") format("woff2");
}
</style>

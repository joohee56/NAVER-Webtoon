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
  margin: 0;
  padding: 0;
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

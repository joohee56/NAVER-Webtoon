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
  height: 100%;
  width: 100%;
}
a {
  color: black;
  text-decoration: none;
}

@font-face {
  font-family: AppleSDGothicNeoL;
  src: url("@/font/AppleSDGothicNeoL00.woff2") format("woff2");
}
@font-face {
  font-family: AppleSDGothicNeoR;
  src: url("@/font/AppleSDGothicNeoR00.woff2") format("woff2");
}
@font-face {
  font-family: AppleSDGothicNeoM;
  src: url("@/font/AppleSDGothicNeoM00.woff2") format("woff2");
}
@font-face {
  font-family: AppleSDGothicNeoSB;
  src: url("@/font/AppleSDGothicNeoSB00.woff2") format("woff2");
}
@font-face {
  font-family: AppleSDGothicNeoB;
  src: url("@/font/AppleSDGothicNeoB00.woff2") format("woff2");
}
@font-face {
  font-family: AppleSDGothicNeoEB;
  src: url("@/font/AppleSDGothicNeoEB00.woff2") format("woff2");
}
</style>

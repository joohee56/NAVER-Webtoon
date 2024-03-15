<template lang="ko">
  <div class="container">
    <nav>
      <router-link :to="{name: 'main'}">웹툰</router-link>
      <router-link :to="{name: 'challenge'}">도전만화</router-link>
      <router-link to="/mypage/favorite">마이페이지</router-link>
    </nav>
    <router-link :to="{name: 'creatorDashboard'}" class="creator-btn">CREATOR'S</router-link>
  </div>  
</template>

<script>
export default {
  computed: {
    isActive() {
      return location.href === this.$route.path;
    },
  },
};
</script>

<style scoped>
.container {
  display: flex;
  border-bottom: 0.5px solid #dadada;
  border-top: 0.5px solid #dadada;
  align-items: center;
  overflow: hidden;
}

nav {
  margin: 0;
  font-family: AppleSDGothicNeoSB;
  font-size: 20px;
}

nav a {
  padding: 1.5rem 2rem;
  box-sizing: border-box;
  display: inline-block;
}

.router-link-active {
  background-color: #00dc64;
  color: white;
}

.creator-btn {
  background-color: #00dc64;
  color: white;
  padding: 0.8rem 3rem;
  margin-left: auto;
  font-family: AppleSDGothicNeoB;
  border-radius: 6px;
}
</style>

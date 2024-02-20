<template lang="ko">
  <nav class="category">
    <router-link to="/" class="active">웹툰</router-link>
    <router-link to="/challenge">도전만화</router-link>
    <router-link to="/mypage/favorite">마이페이지</router-link>
  </nav>
</template>

<script>
export default {};
</script>

<style>
.category {
  display: flex;
  margin: 0;
}
.category a {
  padding: 2rem 2rem;
  border-radius: 8px;
}
.category .active {
  background-color: #00dc64;
  color: white;
}
</style>

<template>
  <div class="toolbar-wrap">
    <div class="naver-logo">
      <a href="https://www.naver.com">NAVER</a>
    </div>
    <div class="webtoon-logo">
      <a href="/">웹툰</a>
    </div>
    <div class="search-wrap">
      <form>
        <input
          type="search"
          class="search"
          placeholder="제목/작가로 검색할 수 있습니다."
          size="35"
        />
        <button class="search-btn">
          <img src="@/assets/image/search.png" alt="검색" onclick="" />
        </button>
      </form>
    </div>
    <div class="user-info" v-if="loginUser.userName != ''">
      <img src="@/assets/image/profile-image.png" class="profile-image" />
      <p class="user-name">{{ loginUser.userName }}</p>
    </div>
    <router-link
      :to="{ name: 'login', params: { redirectUrl: this.$route.name } }"
      class="login-btn"
      v-else
      >로그인</router-link
    >
  </div>
</template>

<script scoped>
import { mapState } from "vuex";

export default {
  data() {
    return {
      isLogin: true,
    };
  },
  computed: {
    ...mapState("memberStore", ["loginUser"]),
  },
};
</script>

<style scoped>
.toolbar-wrap {
  display: flex;
  margin: 1rem 0;
}
.naver-logo {
  font-family: "Archivo Black", sans-serif;
  font-weight: 400;
  font-style: normal;
  font-size: 1rem;
  margin: 17px 10px;
}
.webtoon-logo {
  font-family: "Noto Sans KR", sans-serif;
  font-optical-sizing: auto;
  font-weight: 800;
  font-style: normal;
  font-size: 1.3rem;
  margin: 10px 0;
}
.search-wrap {
  margin: 0 0 0 auto;
}
form {
  color: #03c75a;
  display: flex;
  border: 1px solid currentColor;
  border-radius: 30px;
  padding: 0;
}
.search {
  border: none;
  background: transparent;
  font-size: 15px;
  color: inherit;
  border: 1px solid transparent;
  border-radius: inherit;
  margin-left: 20px;
}
.search::placeholder {
  color: #d2d2d2;
}
.search-btn {
  width: 30px;
  border: 1px solid transparent;
  border-radius: inherit;
  background: transparent;
  cursor: pointer;
  opacity: 0.7;
  margin-right: 20px;
}
.user-info {
  display: flex;
}
.user-name {
  font-family: AppleSDGothicNeoEB;
  margin-left: 0.5rem;
}
.profile-image {
  width: 3rem;
  height: 3rem;
  margin-left: 2rem;
}
.login-btn {
  background-color: #03c75a;
  border-radius: 6px;
  color: white;
  border: none;
  margin-left: 2rem;
  padding: 8px 24px;
  height: fit-content;
}
</style>

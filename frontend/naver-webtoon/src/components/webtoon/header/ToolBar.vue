<template>
  <div class="toolbar-wrap">
    <!-- 로고 -->
    <div class="naver-logo">
      <a href="/">NAVER</a>
    </div>
    <div class="webtoon-logo">
      <a href="/">웹툰</a>
    </div>

    <!-- 검색창 -->
    <div class="search-wrap">
      <input
        type="text"
        class="search"
        placeholder="제목/작가로 검색할 수 있습니다."
        v-model="searchContent"
        @keyup.enter="search"
      />
      <span
        class="btn-delete"
        v-show="searchContent.length > 0"
        @click="searchContent = ''"
        ><i class="fa-solid fa-circle-xmark"></i
      ></span>
      <button class="search-btn">
        <img src="@/assets/image/search.png" alt="검색" @click="search" />
      </button>
    </div>

    <!-- 사용자 정보 -->
    <div class="user-info" v-if="loginUser.userName != ''">
      <router-link :to="{ name: 'userProfile' }">
        <img
          :src="require(`@/assets/image/${loginUser.profileImage}`)"
          class="profile-image"
        />
      </router-link>
      <router-link :to="{ name: 'userProfile' }">
        <p class="user-name">{{ loginUser.userName }}</p>
      </router-link>
    </div>
    <router-link
      :to="{ name: 'login', params: { redirectUrl: this.$route.path } }"
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
      searchContent: "",
    };
  },
  computed: {
    ...mapState("memberStore", ["loginUser"]),
  },
  methods: {
    search() {
      if (this.searchContent === "") {
        alert("검색어를 입력해 주십시오");
        return;
      }
      this.$router.push({
        name: "wholeResult",
        params: {
          keyword: this.searchContent,
        },
      });
    },
  },
};
</script>

<style scoped>
.toolbar-wrap {
  display: flex;
  margin: 10px 0;
}
.naver-logo {
  font-family: "Archivo Black", sans-serif;
  font-weight: 400;
  font-style: normal;
  font-size: 20px;
  margin: 17px 10px;
}
.webtoon-logo {
  font-family: "Noto Sans KR", sans-serif;
  font-optical-sizing: auto;
  font-weight: 800;
  font-style: normal;
  font-size: 25px;
  margin: 10px 0;
}

/* 검색창 */
.search-wrap {
  display: flex;
  border: 1px solid #03c75a;
  border-radius: 30px;
  height: 40px;
  margin: auto 0;
  margin-left: auto;
  width: 380px;
}
.search {
  border: none;
  font-size: 16px;
  margin-left: 20px;
  outline: none;
  width: 100%;
}
.search::placeholder {
  color: #d2d2d2;
  font-family: AppleSDGothicNeoM;
}
.search-btn {
  width: 30px;
  border: 1px solid transparent;
  border-radius: inherit;
  background: transparent;
  cursor: pointer;
  margin-left: auto;
  margin-right: 20px;
}
.btn-delete {
  width: 35px;
  height: 35px;
  z-index: 5;
  background-color: #fff;
  line-height: 40px;
  cursor: pointer;
}
.btn-delete i {
  font-size: large;
  color: #9ba1a3;
}
/* 로그인 정보 */
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
  border-radius: 50%;
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

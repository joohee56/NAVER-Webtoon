<template lang="ko">
  <div id="container">
		<div class="content-wrap">
			<div class="logo">
				<router-link :to="{name: `creatorDashboard`}" class="n">N</router-link>
				<router-link :to="{name: `creatorDashboard`}">WEBTOON CREATOR'S</router-link>
			</div>
			<router-link :to="{name: `main`}" class="webtoon-link">| 웹툰</router-link>
			<div class="create-btn">
				<router-link :to="{name: `createNewRound`}"><i class="fa-solid fa-file-circle-plus"></i> 신규 회차 등록</router-link>
        <router-link :to="{name: `createNewOfficialWebtoon`}" v-if="loginUser.loginId=='joohee56'"><i class="fa-regular fa-pen-to-square"></i> 신규 작품 등록</router-link>
				<router-link :to="{name: `createNewWebtoon`}" v-else><i class="fa-regular fa-pen-to-square"></i> 신규 작품 등록</router-link>
			</div>
			<div>
				<router-link :to="{ name: 'userProfile' }">
					<img :src="require(`@/assets/image/${loginUser.profileImage}`)" class="profile-image"/>
				</router-link>
				<router-link :to="{ name: 'userProfile' }">
					<p class="user-name">{{ loginUser.userName }}</p>
				</router-link>
			</div>
		</div>
	</div>
</template>

<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState("memberStore", ["loginUser"]),
  },
};
</script>

<style scoped>
#container {
  background-color: white;
  border-bottom: 0.5px solid #ebebeb;
  height: 4rem;
  position: sticky;
  top: 0;
  z-index: 1000;
}

.content-wrap {
  display: flex;
  width: 1150px;
  margin: 0 auto;
  padding: 0 10px;
  align-items: center;
  height: 100%;
}

.content-wrap div {
  display: flex;
  align-items: center;
}

/* logo */
.logo {
  font-family: "Archivo Black", sans-serif;
  font-weight: 400;
  font-style: normal;
  font-size: 1.5rem;
  display: inline-block;
  margin-right: 10px;
}
.logo .n {
  color: white;
  background: black;
  font-size: 1rem;
  padding: 2px 6px;
  margin: auto 0;
  margin-right: 10px;
}
.webtoon-link {
  color: #b2b2b2;
  font-weight: 500;
}

/* button */
.create-btn {
  margin-left: auto;
}
.create-btn a {
  color: white;
  font-family: AppleSDGothicNeoM;
  background-color: #00dc64;
  border-radius: 4px;
  padding: 13px 20px;
  font-size: 14px;
  align-items: center;
  line-height: 14px;
  border: 1px solid rgba(0, 0, 0, 0.06);
}
.create-btn a:not(:last-child) {
  margin-right: 10px;
}
.create-btn {
  margin-right: 20px;
}

/* userinfo */
.profile-image {
  width: 2rem;
  height: 2rem;
  object-fit: cover;
  border-radius: 50%;
  margin-right: 10px;
}
.user-name {
  font-family: AppleSDGothicNeoEB;
}
</style>

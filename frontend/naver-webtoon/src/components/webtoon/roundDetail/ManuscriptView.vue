<template lang="ko">
	<div class="container">
		<!-- 상단 메뉴바 -->
		<div id="menubar" class="menu-bar">
			<router-link :to="{name: 'roundList', params: {webtoonId : `${roundDetail.webtoonId}`}}"><i class="fa-solid fa-angle-left"></i></router-link>
			<router-link :to="{name: 'roundList', params: {webtoonId : `${roundDetail.webtoonId}`}}">{{roundDetail.webtoonName}}</router-link>
			<span class="separator"></span>
			<span>{{roundDetail.roundNumber}}화. {{roundDetail.roundTitle}}</span>
			<router-link to="" class="before-round">
				<i class="fa-solid fa-caret-left"></i>
				<span> 이전화</span>
			</router-link>
			<span class="separator"></span>
			<router-link :to="{name: 'roundList', params: {webtoonId : `${roundDetail.webtoonId}`}}">
				<i class="fa-solid fa-list-ul"></i>
				<span> 목록</span>
			</router-link>
			<span class="separator"></span>
			<router-link to="">
				<i class="fa-solid fa-caret-right"></i>
				<span> 다음화</span>
			</router-link>
		</div>

		<!-- 원고 -->
		<img id="manuscript" class="manuscript" :src="require(`@/assets/image/${roundDetail.mergeManuscript}`)">
	</div>
</template>

<script>
import { getRoundDetail } from "@/api/round";
export default {
  data() {
    return {
      roundDetail: {},
    };
  },
  mounted() {
    this.fetchRoundDetail();
  },
  methods: {
    async fetchRoundDetail() {
      try {
        const response = await getRoundDetail(this.$route.params.roundId);
        console.log(response.data);
        this.roundDetail = response.data;
      } catch (error) {
        console.log(error);
      }
    },
  },
};

// 메뉴바 트랜지션
window.addEventListener("scroll", function () {
  const menubar = this.document.getElementById("menubar");
  const manuscript = this.document.getElementById("manuscript");

  if (this.window.scrollY >= manuscript.offsetTop) {
    menubar.style.transition = "opacity 0.5s ease";
    menubar.style.opacity = 0;
  } else {
    menubar.style.transition = "opacity 0.5s ease";
    menubar.style.opacity = 1;
  }
  console.log(window.scrollY);
});
</script>

<style scoped>
.container > * {
  margin: 0 auto;
}
/* 상단 메뉴바 */
.menu-bar {
  background-color: #383b45;
  height: 70px;
  display: flex;
  align-items: center;
  padding: 0 30px;
  font-size: 18px;
  position: sticky;
  top: 0;
  width: 100%;
  z-index: 1000;
}
.menu-bar * {
  color: white;
}
.menu-bar > * {
  margin-right: 15px;
}
.menu-bar *:not(i) {
  font-family: AppleSDGothicNeoB;
}
.separator {
  content: "";
  top: 3px;
  left: 12px;
  width: 1px;
  height: 13px;
  border-radius: 0.5px;
  background-color: #e3e2e2;
}
.before-round {
  margin-left: auto;
}

/* 원고 */
.manuscript {
  display: block;
  width: 690px;
}
</style>

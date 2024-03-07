<template lang="ko">
	<div class="container">
		<div class="container-inner">
			<div class="naver-logo">
				<a href="https://www.naver.com">NAVER</a>
			</div>
			<div class="title">
				환영합니다!
			</div>
			<div class="description">
				<br><span>{{userName}}</span>님, 회원가입을 축하합니다.</br>
				네이버의 새로운 아이디는 <span class="loginId">{{loginId}}</span>입니다.</p>
			</div>
			<div class="start-btn-wrap">
				<router-link :to="{name: 'login', params: {redirectUrl: 'main'}}" class="start-btn">시작하기</router-link>
			</div>
		</div>
	</div>
</template>

<script>
export default {
  data() {
    return {
      loginId: "",
      userName: "",
    };
  },
  created() {
    this.loginId = this.$route.params.loginId;
    this.userName = this.$route.params.name;
  },
};
</script>

<style scoped>
.container {
  height: 100vh;
  /* align-items: center; */
  /* display: flex; */
  padding-top: 70px;
}

.container-inner {
  width: 400px;
  margin: 0 auto;
  padding: 50px;
}

.naver-logo,
.title,
.description,
.start-btn-wrap {
  text-align: center;
}

.naver-logo {
  margin-bottom: 150px;
}

.naver-logo a {
  font-family: "Archivo Black", sans-serif;
  font-weight: 400;
  font-style: normal;
  color: #03c75a;
  font-size: 3rem;
}

.title {
  font-size: 3.5rem;
  font-weight: 100;
  margin-bottom: 20px;
}

.description {
  font-weight: 600;
  margin-bottom: 150px;
}

.loginId {
  color: #03c75a;
}

.start-btn {
  background-color: #03c75a;
  color: white;
  border-radius: 6px;
  padding: 15px;
  width: inherit;
  display: block;
  font-weight: 600;
}
</style>

<template lang="ko">
	<nav class="dayOfWeek">
		<router-link to="/" class="active">요일전체</router-link>
		<router-link to="/mon">월</router-link>
		<router-link to="/tue">화</router-link>
		<router-link to="/wed">수</router-link>
		<router-link to="/thu">목</router-link>
		<router-link to="/fri">금</router-link>
		<router-link to="/sat">토</router-link>
		<router-link to="/sun">일</router-link>
		<router-link to="/finish">완결</router-link>
	</nav>
</template>

<script>
export default {};
</script>

<style>
.dayOfWeek {
  margin: 0;
  display: flex;
}
.dayOfWeek a {
  display: block;
  margin: 2rem 1rem 0;
  padding-bottom: 0.5rem;
  font-family: AppleSDGothicNeoM;
  font-size: 18px;
}
.dayOfWeek .active {
  font-family: AppleSDGothicNeoEB;
  color: #00dc64;
  border-bottom: #00dc64 solid;
}
</style>

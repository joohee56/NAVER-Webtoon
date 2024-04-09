<template lang="ko">
	<nav>
		<router-link :to="{name: 'main'}">요일전체</router-link>
		<router-link :to="{name:'dayOfWeek', params: {dayOfWeek: 'mon'}}">월</router-link>
		<router-link :to="{name:'dayOfWeek', params: {dayOfWeek: 'tue'}}">화</router-link>
		<router-link :to="{name:'dayOfWeek', params: {dayOfWeek: 'wed'}}">수</router-link>
		<router-link :to="{name:'dayOfWeek', params: {dayOfWeek: 'thu'}}">목</router-link>
		<router-link :to="{name:'dayOfWeek', params: {dayOfWeek: 'fri'}}">금</router-link>
		<router-link :to="{name:'dayOfWeek', params: {dayOfWeek: 'sat'}}">토</router-link>
		<router-link :to="{name:'dayOfWeek', params: {dayOfWeek: 'sun'}}">일</router-link>
		<router-link :to="{name:'dayOfWeek', params: {dayOfWeek: 'finish'}}">완결</router-link>
	</nav>
</template>

<script>
export default {};
</script>

<style scoped>
nav {
  margin: 0;
  display: flex;
}
nav a {
  display: block;
  margin: 20px 30px 0 0;
  padding-bottom: 0.5rem;
  font-family: AppleSDGothicNeoSB;
  font-size: 18px;
}
.router-link-active {
  font-family: AppleSDGothicNeoB;
  color: #00dc64;
  border-bottom: #00dc64 solid 1.5px;
}
</style>

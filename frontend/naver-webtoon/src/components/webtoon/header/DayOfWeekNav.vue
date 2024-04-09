<template lang="ko">
	<nav>
		<router-link :to="{name: 'main'}">요일전체</router-link>
		<router-link :to="{name:'dayOfWeek', params: {dayOfWeek: 'MONDAY'}}">월</router-link>
		<router-link :to="{name:'dayOfWeek', params: {dayOfWeek: 'TUESDAY'}}">화</router-link>
		<router-link :to="{name:'dayOfWeek', params: {dayOfWeek: 'WEDNESDAY'}}">수</router-link>
		<router-link :to="{name:'dayOfWeek', params: {dayOfWeek: 'THURSDAY'}}">목</router-link>
		<router-link :to="{name:'dayOfWeek', params: {dayOfWeek: 'FRIDAY'}}">금</router-link>
		<router-link :to="{name:'dayOfWeek', params: {dayOfWeek: 'SATURDAY'}}">토</router-link>
		<router-link :to="{name:'dayOfWeek', params: {dayOfWeek: 'SUNDAY'}}">일</router-link>
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

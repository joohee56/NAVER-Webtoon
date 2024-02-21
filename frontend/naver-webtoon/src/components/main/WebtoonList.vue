<template lang="ko">
	<div>
		<div>
			<p class="title">요일별 전체 웹툰</p>
		</div>
		<div class="filter">
			<input type="checkbox">전체</input>
			<input type="checkbox">로맨스</input>
			<input type="checkbox">판타지</input>
			<input type="checkbox">액션</input>
			<input type="checkbox">스포츠</input>
			<input type="checkbox">개그</input>
			<input type="checkbox">스릴러</input>
			<input type="checkbox">무협/사극</input>
			<input type="checkbox">감성</input>
			<input type="checkbox">소설원작</input>
		</div>
		<div>
		</div>
	</div>
</template>

<script>
export default {};
</script>

<style scoped>
.title {
  margin: 0;
  font-family: AppleSDGothicNeoEB;
}
.filter {
  float: right;
}
.filter input {
  margin-left: 1rem;
}
</style>

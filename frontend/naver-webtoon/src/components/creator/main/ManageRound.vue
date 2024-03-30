<template lang="ko">
	<div class="round-manage-container">
		<div class="subject">회차 관리</div>
		<div class="content-wrap">

			<!-- 웹툰 정보 -->
			<div>
				<img>
				<div>
					<div>테스트2</div>
					<div>테스트입니당</div>
				</div>
				<div>
					<router-link>작품 정보 수정</router-link>
					<router-link>신규 회차 등록</router-link>
				</div>
			</div>

			<!-- 회차 리스트 -->
			<div>
				<div class="round-list-wrap">
					<div>회차 번호</div>
					<div>대표 이미지</div>
					<div>회차명</div>
					<div>공개일시</div>
					<div>수정일시</div>
					<div>조회수</div>
					<div>좋아요수</div>
					<div>댓글수</div>
					<div>관리</div>
				</div>
				<div class="round-list-wrap">
					<div v-for="">
						<div>1</div>
						<div><img></div>
						<div>새회차</div>
						<div>2021.09.20 16:37:14</div>
						<div>2021.09.20 16:40:17</div>
						<div>15</div>
						<div>0</div>
						<div>0</div>
						<div><router-link>수정</router-link></div>
					</div>	
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
  mounted() {},
};
</script>

<style scoped>
.round-manage-container {
  padding: 30px 30px;
}
.subject {
  font-family: AppleSDGothicNeoSB;
  font-size: 1.3rem;
}
.content-wrap {
  padding: 10px 0px;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 15px 15px;
}
</style>

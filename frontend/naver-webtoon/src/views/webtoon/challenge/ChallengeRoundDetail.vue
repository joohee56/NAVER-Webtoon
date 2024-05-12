<template lang="ko">
	<div>
		<ManuscriptView webtoonType="challenge"></ManuscriptView>
		<CommentView></CommentView>
	</div>
</template>

<script>
import ManuscriptView from "@/components/webtoon/roundDetail/ManuscriptView.vue";
import CommentView from "@/components/webtoon/roundDetail/CommentView.vue";

export default {
  components: {
    ManuscriptView,
    CommentView,
  },
};
</script>

<style scoped></style>

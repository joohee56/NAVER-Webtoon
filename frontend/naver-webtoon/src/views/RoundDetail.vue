<template lang="ko">
	<div class="container">
		<ManuscriptView></ManuscriptView>
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
  created() {},
};
</script>

<style scoped>
.container {
  width: 85rem;
  margin: 0 auto;
}
</style>

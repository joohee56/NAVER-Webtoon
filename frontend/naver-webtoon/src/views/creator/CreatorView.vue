<template lang="ko">
	<div>
		<ToolBar></ToolBar>
		<div class="main-content">
			<NavBar></NavBar>
			<router-view class="content"></router-view>
		</div>
	</div>
</template>

<script>
import ToolBar from "@/components/creator/header/ToolBar";
import NavBar from "@/components/creator/header/NavBar.vue";

export default {
  components: {
    ToolBar,
    NavBar,
  },
};
</script>

<style scoped>
.main-content {
  display: flex;
  height: 100%;
}
.content {
  background-color: #f6f6f6;
}
</style>

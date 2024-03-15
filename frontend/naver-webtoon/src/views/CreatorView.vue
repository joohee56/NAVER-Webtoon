<template lang="ko">
	<div class="container">
		<ToolBar></ToolBar>
		<div class="main-content">
			<NavBar></NavBar>
			<router-view></router-view>
		</div>
	</div>
</template>

<script>
import ToolBar from "@/components/creator/ToolBar";
import NavBar from "@/components/creator/NavBar.vue";

export default {
  components: {
    ToolBar,
    NavBar,
  },
};
</script>

<style scoped>
.container {
  background-color: #f6f6f6;
}

.main-content {
  display: flex;
}
</style>

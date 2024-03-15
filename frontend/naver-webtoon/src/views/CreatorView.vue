<template lang="ko">
	<div>
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
.main-content {
  width: 1595px;
  background-color: #f9fbfc;
}
</style>

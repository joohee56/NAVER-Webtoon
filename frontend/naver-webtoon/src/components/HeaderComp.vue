<template lang="ko">
  <header>
    <ToolBar></ToolBar>
    <CategoryNav></CategoryNav>
    <DayOfWeekNav></DayOfWeekNav>
  </header>
</template>

<script>
import CategoryNav from "./header/CategoryNav.vue";
import DayOfWeekNav from "./header/DayOfWeekNav.vue";
import ToolBar from "./header/ToolBar.vue";

export default {
  components: {
    CategoryNav,
    DayOfWeekNav,
    ToolBar,
  },
};
</script>

<style scoped>
header {
  border-bottom: #d9d9d9 solid 0.5px;
}
</style>

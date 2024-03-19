<template lang="ko">
  <header>
    <div class="tool-bar">
      <ToolBar></ToolBar>
    </div>
    <div class="line">
      <div class="category-nav">
        <CategoryNav></CategoryNav>
      </div>
    </div>
    <div class="day-of-week-nav">
      <DayOfWeekNav></DayOfWeekNav>
    </div>
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
.line {
  width: 100%;
  border-bottom: 0.5px solid #dadada;
  border-top: 0.5px solid #dadada;
}
header,
.line {
  border-bottom: #d9d9d9 solid 0.5px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.tool-bar,
.category-nav,
.day-of-week-nav {
  width: 85rem;
}
</style>

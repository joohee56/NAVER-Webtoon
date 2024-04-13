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
  </header>
</template>

<script>
import CategoryNav from "./header/CategoryNav.vue";
import ToolBar from "./header/ToolBar.vue";

export default {
  components: {
    CategoryNav,
    ToolBar,
  },
};
</script>

<style scoped>
header,
.line {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.tool-bar,
.category-nav {
  width: 85rem;
}
.line {
  width: 100%;
  border-bottom: 0.5px solid #dadada;
  border-top: 0.5px solid #dadada;
}
</style>

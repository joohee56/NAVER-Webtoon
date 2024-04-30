<template lang="ko">
  <div class="container">
    <button class="category-nav" v-for="(category, index) in categorys" :class="{active:category.isActive}" @click="handleRouterClick(category, index)">{{category.title}}</button>
    <router-link :to="{name: 'creatorDashboard'}" class="creator-btn">CREATOR'S</router-link>
  </div>  
</template>

<script>
import { mapMutations, mapState } from "vuex";

export default {
  computed: {
    ...mapState("navStore", ["categorys"]),
  },
  methods: {
    ...mapMutations("navStore", ["SET_CATEGORY_IS_ACTIVE"]),
    handleRouterClick(category, index) {
      this.SET_CATEGORY_IS_ACTIVE(index);
      this.$router.push({ name: category.routerName });
    },
  },
};
</script>

<style scoped>
.container {
  display: flex;
  align-items: center;
  overflow: hidden;
}

.category-nav {
  padding: 20px 20px;
  box-sizing: border-box;
  display: inline-block;
  border: none;
  background-color: white;
  font-size: 18px;
  font-family: AppleSDGothicNeoB;
  cursor: pointer;
}

.active {
  background-color: #00dc64;
  color: white;
}

.creator-btn {
  background-color: #00dc64;
  color: white;
  padding: 0.8rem 3rem;
  margin-left: auto;
  font-family: AppleSDGothicNeoB;
  border-radius: 6px;
}
</style>

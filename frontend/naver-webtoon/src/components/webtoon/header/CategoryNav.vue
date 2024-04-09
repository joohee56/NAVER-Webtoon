<template lang="ko">
  <div class="container">
    <nav>
      <button v-for="(categoryHeader, index) in categoryHeaders" @click="handleLinkClick(index)" :class="{active:isActive[index]}">{{categoryHeader}}</button>
    </nav>
    <router-link :to="{name: 'creatorDashboard'}" class="creator-btn">CREATOR'S</router-link>
  </div>  
</template>

<script>
export default {
  data() {
    return {
      isActive: [true, false, false],
      categoryHeaders: ["웹툰", "도전만화"],
      routerNames: ["main", "challenge"],
    };
  },

  methods: {
    handleLinkClick(index) {
      this.isActive = [false, false, false];
      this.isActive[index] = true;
      this.$router.push({ name: this.routerNames[index] });
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

/* nav {
  margin: 0;
  font-family: AppleSDGothicNeoSB;
  font-size: 20px;
}

nav a {
  padding: 20px 20px;
  box-sizing: border-box;
  display: inline-block;
} */

nav button {
  padding: 20px 20px;
  box-sizing: border-box;
  display: inline-block;
  border: none;
  background-color: white;
  font-size: 19px;
  font-family: AppleSDGothicNeoSB;
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

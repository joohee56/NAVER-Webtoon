<template lang="ko">
	<nav>
		<button v-for="(dayOfWeek, index) in dayOfWeeks"  @click="handleLinkClick(index)" :class="{active:selectedIndex_dayOfWeek===index}">{{dayOfWeek.title}}</button>
	</nav>
</template>

<script>
import { mapMutations, mapState } from "vuex";

export default {
  computed: {
    ...mapState("navStore", ["dayOfWeeks", "selectedIndex_dayOfWeek"]),
  },
  methods: {
    ...mapMutations("navStore", ["SET_DAY_OF_WEEK_ACTIVE"]),

    handleLinkClick(index) {
      this.SET_DAY_OF_WEEK_ACTIVE(index);

      if (index == 0) {
        this.$router.push({ name: "main" }).catch(() => {});
      } else {
        this.$router
          .push({
            name: "dayOfWeek",
            params: { dayOfWeek: this.dayOfWeeks[index].value },
          })
          .catch(() => {});
      }
    },
  },
};
</script>

<style scoped>
nav {
  width: 85rem;
  display: flex;
  margin: 0 auto;
  margin-top: 20px;
}

nav button {
  margin-right: 20px;
  padding-bottom: 0.5rem;
  font-family: AppleSDGothicNeoSB;
  font-size: 18px;
  border: none;
  background-color: white;
  cursor: pointer;
}

.active {
  font-family: AppleSDGothicNeoB;
  color: #00dc64;
  border-bottom: #00dc64 solid 3px;
}
</style>

<template lang="ko">
	<nav>
		<button v-for="dayOfWeek in dayOfWeeks"  @click="handleLinkClick(dayOfWeek.value)" :class="{active:selected_dayOfWeek===dayOfWeek.value}">{{dayOfWeek.title}}</button>
	</nav>
</template>

<script>
import { mapMutations, mapState } from "vuex";

export default {
  computed: {
    ...mapState("navStore", ["dayOfWeeks", "selected_dayOfWeek"]),
  },
  methods: {
    ...mapMutations("navStore", ["SET_DAY_OF_WEEK_ACTIVE"]),
    handleLinkClick(dayOfWeek) {
      this.SET_DAY_OF_WEEK_ACTIVE(dayOfWeek);

      if (dayOfWeek === "ALL") {
        this.$router.push({ name: "main" }).catch(() => {});
      } else {
        this.$router
          .push({
            name: "dayOfWeek",
            params: { dayOfWeek: dayOfWeek },
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

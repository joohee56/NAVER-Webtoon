<template lang="ko">
	<nav>
		<button v-for="(dayOfWeek, index) in dayOfWeeks"  @click="handleLinkClick(index)" :class="{active:dayOfWeek.isActive}">{{dayOfWeek.title}}</button>
	</nav>
</template>

<script>
import { mapMutations, mapState } from "vuex";

export default {
  computed: {
    ...mapState("navStore", ["dayOfWeeks"]),
  },
  methods: {
    ...mapMutations("navStore", ["SET_IS_ACTIVE"]),

    handleLinkClick(index) {
      this.SET_IS_ACTIVE(index);

      if (index == 0) {
        this.$router.push({ name: "main" });
      } else {
        this.$router.push({
          name: "dayOfWeek",
          params: { dayOfWeek: this.dayOfWeeks[index].value },
        });
      }
    },
  },
};
</script>

<style scoped>
nav button {
  margin: 20px 30px 0 0;
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
  border-bottom: #00dc64 solid 1.5px;
}
</style>

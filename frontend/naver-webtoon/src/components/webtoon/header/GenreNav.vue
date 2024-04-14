<template lang="ko">
  <nav>
		<button v-for="(genre, index) in genres"  @click="handleLinkClick(index)" :class="{active:genre.isActive}">{{genre.title}}</button>
	</nav>
</template>

<script>
import { mapState, mapMutations } from "vuex";

export default {
  computed: {
    ...mapState("navStore", ["genres"]),
  },
  methods: {
    ...mapMutations("navStore", ["SET_GENRE_IS_ACTIVE"]),

    handleLinkClick(index) {
      this.SET_GENRE_IS_ACTIVE(index);

      if (index == 0) {
        this.$router.push({ name: "challengeMain" });
      } else {
        this.$router.push({
          name: "genreView",
          params: {
            genre: this.genres[index].value,
          },
        });
      }
    },
  },
};
</script>

<style scoped>
nav button {
  margin: 20px 20px 0 0;
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

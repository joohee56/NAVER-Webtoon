<template lang="ko">
  <nav>
		<button v-for="genre of genres"  @click="handleLinkClick(genre.value)" :class="{active:selected_genre === genre.value}">{{genre.title}}</button>
	</nav>
</template>

<script>
import { mapState, mapMutations } from "vuex";

export default {
  computed: {
    ...mapState("navStore", ["genres", "selected_genre"]),
  },
  methods: {
    ...mapMutations("navStore", ["SET_GENRE_ACTIVE"]),

    handleLinkClick(genre) {
      this.SET_GENRE_ACTIVE(genre);

      if (genre == "ALL") {
        this.$router.push({ name: "challengeMain" }).catch(() => {});
      } else {
        this.$router
          .push({
            name: "genreView",
            params: {
              genre: genre,
            },
          })
          .catch(() => {});
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
  border-bottom: #00dc64 solid 3px;
}
</style>

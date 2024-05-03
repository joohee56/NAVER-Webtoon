<template lang="ko">
	<div class="container">
		<WebtoonRanking webtoonType="OFFICIAL"></WebtoonRanking>
		<OfficialWebtoonList></OfficialWebtoonList>
	</div>
</template>

<script>
import WebtoonRanking from "@/components/webtoon/main/WebtoonRanking";
import OfficialWebtoonList from "@/components/webtoon/main/OfficialWebtoonList.vue";
import { mapMutations } from "vuex";

export default {
  components: {
    WebtoonRanking,
    OfficialWebtoonList,
  },
  created() {
    this.navActive();
  },
  methods: {
    ...mapMutations("navStore", [
      "SET_CATEGORY_ACTIVE",
      "SET_DAY_OF_WEEK_ACTIVE",
    ]),
    navActive() {
      this.SET_CATEGORY_ACTIVE(0);
      this.SET_DAY_OF_WEEK_ACTIVE(0);
    },
  },
};
</script>

<style scoped>
.container {
  margin: 0 auto;
  width: 85rem;
}
</style>

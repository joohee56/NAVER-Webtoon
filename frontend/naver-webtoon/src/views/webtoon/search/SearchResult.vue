<template lang="ko">
	<div class="container">
		<div class="title">
			<span class="correct">'{{keyword}}'</span>에 대한 검색결과 입니다.
		</div>

		<!-- nav -->
		<nav>
			<button v-for="(menu, index) in menues" :class="{active: selectedIndex_search===index}" @click="handleMenuClick(menu, index)" :disabled="menu.count===0">{{menu.title}} ({{menu.count}})</button>
		</nav>

		<router-view></router-view>
	</div>
</template>

<script>
import { getSearchCount } from "@/api/webtoon";
import { mapMutations, mapState } from "vuex";

export default {
  data() {
    return {
      keyword: this.$route.params.keyword,
      menues: [
        { title: "전체", count: 0, routerName: "wholeResult" },
        {
          title: "웹툰",
          count: 0,
          routerName: "webtoonWhole",
          webtoonType: "OFFICIAL",
        },
        {
          title: "도전만화",
          count: 0,
          routerName: "webtoonWhole",
          webtoonType: "CHALLENGE",
        },
      ],
      totalChallengeCount: "",
      totalOfficialCount: "",
    };
  },
  computed: {
    ...mapState("navStore", ["selectedIndex_category", "selectedIndex_search"]),
  },
  watch: {
    "$route.params.keyword"() {
      location.reload();
    },
  },
  mounted() {
    this.SET_CATEGORY_ACTIVE(2);
    this.SET_SEARCH_ACTIVE(0);
    this.fetchSearchCount();
  },
  methods: {
    ...mapMutations("navStore", ["SET_CATEGORY_ACTIVE", "SET_SEARCH_ACTIVE"]),
    handleMenuClick(menu, index) {
      this.SET_SEARCH_ACTIVE(index);
      if (index === 0) {
        this.$router
          .push({
            name: menu.routerName,
            params: {
              keyword: this.keyword,
            },
          })
          .catch(() => {});
      } else {
        this.$router
          .push({
            name: menu.routerName,
            params: {
              webtoonType: menu.webtoonType,
              keyword: this.keyword,
            },
          })
          .catch(() => {});
      }
    },
    async fetchSearchCount() {
      const response = await getSearchCount(this.keyword);
      this.totalChallengeCount = response.data.totalChallengeCount;
      this.totalOfficialCount = response.data.totalOfficialCount;
      this.setMenuCount();
    },
    setMenuCount() {
      this.menues[0].count = this.totalChallengeCount + this.totalOfficialCount;
      this.menues[1].count = this.totalOfficialCount;
      this.menues[2].count = this.totalChallengeCount;
    },
  },
};
</script>

<style scoped>
.container {
  width: 85rem;
  margin: 0 auto;
}
/* title */
.title {
  font-family: AppleSDGothicNeoB;
  font-size: 22px;
  height: 95px;
  align-items: center;
  display: flex;
}
.correct {
  color: #00dc64;
}
/* nav */
nav {
  border-bottom: 1px solid #efefef;
}
nav button {
  margin-right: 25px;
  padding-bottom: 10px;
  font-family: AppleSDGothicNeoB;
  font-size: 17px;
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

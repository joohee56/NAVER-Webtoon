<template lang="ko">
	<div class="container">
		<WebtoonComp v-for="(webtoon, index) in webtoons" :webtoon=webtoon webtoonType="official" :key="index"></WebtoonComp>

		<!-- 페이징 -->
    <div class="paging-btn-wrap">
      <button :disabled="paging.isBeforePageBtnDisabled" @click="clickBeforePage"><i class="fa-solid fa-angle-left before-page-btn"></i></button>
      <div class="paging-btn">
        <button v-for="(page, index) in paging.pages" @click="changePage(page, index)" class="page-btn" :class="{active: paging.nowPageIndex===index}">{{page}}</button>
      </div>
      <button :disabled="paging.isNextPageBtnDisabled" @click="clickNextPage"><i class="fa-solid fa-angle-right next-page-btn"></i></button>
    </div>

	</div>
</template>

<script>
import WebtoonComp from "@/components/webtoon/search/WebtoonComp.vue";
import { getSearchWebtoon } from "@/api/webtoon";
import { mapMutations } from "vuex";

export default {
  data() {
    return {
      keyword: this.$route.params.keyword,
      webtoonType: this.$route.params.webtoonType,
      paging: {
        webtoonLimit: 10,
        totalPageCount: "",
        pages: [],
        nowPageIndex: 0,
        startPage: "",
        pageLimit: 5,
        isBeforePageBtnDisabled: false,
        isNextPageBtnDisabled: false,
      },
      webtoons: "",
    };
  },
  watch: {
    "$route.params.webtoonType"() {
      this.reload();
    },
    "paging.startPage"() {
      this.paging.pages = [];
      const endPage = Math.min(
        this.paging.totalPageCount + 1,
        this.paging.startPage + this.paging.pageLimit
      );
      for (let i = this.paging.startPage; i < endPage; i++) {
        this.paging.pages.push(i);
      }
      this.paging.nowPageIndex = 0;

      this.setPageBtnDisabled();
    },
  },
  components: {
    WebtoonComp,
  },
  async created() {
    await this.fetchSearchResult(0);
    if (this.webtoonType === "OFFICIAL") {
      this.SET_SEARCH_ACTIVE(1);
    } else {
      this.SET_SEARCH_ACTIVE(2);
    }
    this.paging.startPage = 1;
  },
  methods: {
    ...mapMutations("navStore", ["SET_SEARCH_ACTIVE"]),
    async fetchSearchResult(offset) {
      const response = await getSearchWebtoon(
        this.keyword,
        this.webtoonType,
        offset,
        this.paging.webtoonLimit
      );
      console.log(response);

      if (response.status === 200) {
        this.webtoons = response.data;
        this.setTotalPageCount();
      }
    },
    reload() {
      this.webtoonType = this.$route.params.webtoonType;
      this.fetchSearchResult(0);
      this.paging.startPage = 1;
    },
    setTotalPageCount() {
      this.paging.totalPageCount =
        this.webtoons.length == 0
          ? 1
          : Math.ceil(this.webtoons.length / this.paging.webtoonLimit);
    },
    setPageBtnDisabled() {
      if (this.paging.startPage === 1) {
        this.paging.isBeforePageBtnDisabled = true;
      } else {
        this.paging.isBeforePageBtnDisabled = false;
      }

      if (
        this.paging.startPage + this.paging.pageLimit >
        this.paging.totalPageCount
      ) {
        this.paging.isNextPageBtnDisabled = true;
      } else {
        this.paging.isNextPageBtnDisabled = false;
      }
    },
    clickBeforePage() {
      this.paging.startPage = Math.max(
        1,
        this.paging.startPage - this.paging.pageLimit
      );
      this.changePage(this.paging.startPage, 0);
    },
    clickNextPage() {
      this.paging.startPage = this.paging.startPage + this.paging.pageLimit;
      this.changePage(this.paging.startPage, 0);
    },
    async changePage(n, index) {
      await this.fetchSearchResult((n - 1) * this.paging.webtoonLimit);
      this.paging.nowPageIndex = index;
    },
  },
};
</script>

<style scoped>
.container {
  margin-top: 20px;
}
/* 페이징 */
.paging-btn-wrap {
  display: flex;
  margin-top: 30px;
  justify-content: center;
}
.paging-btn-wrap button {
  background-color: white;
  border: none;
  font-size: 17px;
  font-family: AppleSDGothicNeoB;
  cursor: pointer;
}
.paging-btn-wrap button {
  margin-right: 10px;
}
.paging-btn .active {
  color: #00dc64;
}
</style>

<template lang="ko">
	<div class="round-manage-container">
		<div class="subject">회차 관리</div>
		<div class="content-wrap">

			<!-- 웹툰 정보 -->
			<div class="webtoon-info-wrap">
				<img class="webtoon-thumbnail" :src="require(`@/assets/image/${this.webtoon.webtoonThumbnail}`)">
				<div class="webtoon-desription">
					<div class="title">{{this.webtoon.webtoonName}}</div>
					<div v-html="webtoon.summary"></div>
				</div>
				<div class="btn-wrap">
					<router-link to="#" class="gray-btn"><i class="fa-solid fa-pencil"></i>&nbsp;작품 정보 수정</router-link>
					<router-link :to="{name: 'createNewRound'}" class="green-btn"><i class="fa-solid fa-file-circle-plus"></i>&nbsp;신규 회차 등록</router-link>
				</div>
			</div>

			<!-- 회차 리스트 -->
			<div class="round-list-container">
				<div class="round-list-wrap column-name">
					<div>회차 번호</div>
					<div>대표 이미지</div>
					<div>회차명</div>
					<div>공개일시</div>
					<div>수정일시</div>
					<div>조회수</div>
					<div>좋아요수</div>
					<div>댓글수</div>
					<div>관리</div>
				</div>
				<div>
					<div v-for="round in rounds" class="round-list-wrap round">
						<div>{{round.roundNumber}}</div>
						<div><router-link :to="{name: webtoonType+'RoundDetail', params: {webtoonId: webtoon.webtoonId, roundId: round.roundId}}"><img class="thumbnail" :src="require(`@/assets/image/${round.roundThumbnail}`)"></router-link></div>
						<div><router-link :to="{name: webtoonType+'RoundDetail', params: {webtoonId: webtoon.webtoonId, roundId: round.roundId}}">{{round.roundTitle}}</router-link></div>
						<div>{{round.createdAt}}</div>
						<div>{{round.updatedAt}}</div>
						<div>0</div>
						<div>{{round.totalLikeCount}}</div>
						<div>{{round.totalCommentCount}}</div>
						<div><router-link to="#" class="update-btn">수정</router-link></div>
					</div>	
				</div>
			</div>

      <!-- 페이징 -->
      <div class="paging-btn-wrap">
        <button :disabled="paging.isBeforePageBtnDisabled" @click="clickBeforePage"><i class="fa-solid fa-angle-left before-page-btn"></i></button>
        <div class="paging-btn">
          <button v-for="(page, index) in paging.pages" @click="changePage(page, index)" class="page-btn" :class="{active: paging.nowPageIndex===index}">{{page}}</button>
        </div>
        <button :disabled="paging.isNextPageBtnDisabled" @click="clickNextPage"><i class="fa-solid fa-angle-right next-page-btn"></i></button>
      </div>

		</div>
	</div>
</template>

<script>
import { getManageRound } from "@/api/round";

export default {
  data() {
    return {
      webtoon: {
        webtoonId: this.$route.params.webtoonId,
        webtoonName: "",
        webtoonThumbnail: "default-webtoon-cover.png",
        summary: "",
      },
      paging: {
        roundLimit: 10,
        totalPageCount: "",
        pages: [],
        nowPageIndex: 0,
        startPage: "",
        pageLimit: 5,
        isBeforePageBtnDisabled: false,
        isNextPageBtnDisabled: false,
      },
      webtoonType: this.$route.params.webtoonType,
      rounds: [], //roundId, roundNumber, roundThumbnail, roundTitle, createdAt, updatedAt, totalLikeCount, totalCommentCount
    };
  },
  watch: {
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
  async mounted() {
    await this.fetchManageRound(0);
    this.paging.startPage = 1;
  },
  methods: {
    async fetchManageRound(offset) {
      try {
        const response = await getManageRound(
          this.webtoon.webtoonId,
          offset,
          this.paging.roundLimit
        );
        console.log(response.data);
        if (response.status === 200) {
          this.webtoon.webtoonName = response.data.webtoonName;
          this.webtoon.webtoonThumbnail = response.data.webtoonThumbnail;
          this.webtoon.summary = response.data.summary.replaceAll(
            "\n",
            "<br />"
          );
          this.rounds = response.data.rounds;
          this.paging.totalPageCount = response.data.totalPageCount;
        }
      } catch (error) {
        console.log(error);
      }
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
      await this.fetchManageRound((n - 1) * this.paging.roundLimit);
      this.paging.nowPageIndex = index;
    },
  },
};
</script>

<style scoped>
.round-manage-container {
  padding: 30px 30px;
  width: 970px;
}
.subject {
  font-family: AppleSDGothicNeoSB;
  font-size: 1.3rem;
  margin-bottom: 15px;
}
.content-wrap {
  background-color: white;
  border: 0.5px solid #ebebeb;
  border-radius: 5px;
  padding: 25px;
}

/* 웹툰 정보 */
.webtoon-info-wrap {
  display: flex;
}
.webtoon-thumbnail {
  width: 140px;
  border-radius: 5px;
  aspect-ratio: 480 / 623;
}
.webtoon-desription {
  padding: 5px 15px;
  font-family: AppleSDGothicNeoM;
  line-height: 25px;
}
.webtoon-desription .title {
  font-family: AppleSDGothicNeoB;
  font-size: 19px;
  margin-bottom: 5px;
}
.btn-wrap {
  width: 150px;
  margin-left: auto;
}
.btn-wrap a {
  display: block;
  font-family: AppleSDGothicNeoSB;
  border-radius: 4px;
  padding: 13px 15px 10px;
  font-size: 14px;
  border: 1px solid rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  text-align: center;
}
.btn-wrap .gray-btn {
  background-color: #f6f6f6;
}
.btn-wrap .green-btn {
  color: white;
  background-color: #00dc64;
}

/* 회차 리스트 */
.round-list-container {
  margin-top: 30px;
  text-align: center;
  font-family: AppleSDGothicNeoM;
}
.round-list-wrap {
  display: grid;
  grid-template-columns: 1fr 1.5fr 3fr 1.5fr 1.5fr 1fr 1fr 1fr 1fr;
  font-size: 15px;
}
.round-list-wrap:not(:last-child) {
  margin-bottom: 10px;
}
.round-list-wrap > div {
  margin: auto 0;
}
.round-list-wrap.column-name {
  font-family: AppleSDGothicNeoSB;
  font-size: 12px;
}
.round-list-wrap.round:not(:last-child) {
  border-bottom: 1px solid #efefef;
}
.round-list-wrap .thumbnail {
  width: 100px;
  aspect-ratio: 202 / 120;
  border-radius: 4px;
}
.round-list-wrap a:hover {
  text-decoration: underline;
}
.round-list-wrap .update-btn {
  background-color: #00dc64;
  color: white;
  padding: 5px 7px 3px;
  border-radius: 5px;
  font-size: 12px;
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

<template lang="ko">
	<div class="container">

		<!-- 상단 -->
		<div class="header">
			<div>총 {{totalRoundCount}}화</div>
			<div class="sort-btn">
				<span :class={select:selectDesc} @click="sortToDesc(true)">최신화부터</span> | <span :class={select:!selectDesc} @click="sortToDesc(false)">1화부터</span>
			</div>
		</div>

		<!-- 회차 -->
		<div class="round-wrap">
			<div class="round-list-item" v-for="round in rounds">
				<router-link to="#">
					<img class="thumbnail" :src="require(`@/assets/image/${round.thumbnail}`)">
					<div class="detail-wrap">
						<div class="webtoon-title">{{round.roundNumber}}화. {{round.title}}</div>
						<div class="etc">
							<div><i class="fa-solid fa-heart"></i>&nbsp; 100만</div>
							<div> {{round.createdAt}}</div>
						</div>
					</div>
				</router-link>
			</div>
		</div>

		<!-- 페이지 -->
		<div class="paginate-wrap">
			<button>
			</button>
		</div>

	</div>

</template>

<script>
import { getRoundsWithPaging } from "@/api/Round";

export default {
  data() {
    return {
      rounds: [],
      pageCount: "",
      totalRoundCount: "",
      limit: 15,
      selectDesc: true,
    };
  },
  mounted() {
    this.fetchRounds(0, true);
  },
  methods: {
    async fetchRounds(offset, isDesc) {
      try {
        console.log("ROUND LIST");
        const response = await getRoundsWithPaging(
          this.$route.params.webtoonId,
          offset,
          this.limit,
          isDesc
        );
        console.log(response.data);
        this.rounds = response.data.rounds;
        this.pageCount = response.data.pageCount;
        this.totalRoundCount = response.data.totalRoundCount;
      } catch (error) {
        console.log(error);
      }
    },
    sortToDesc(isDesc) {
      if (this.selectDesc != isDesc) {
        this.fetchRounds(0, isDesc);
        this.selectDesc = !this.selectDesc;
      }
    },
  },
};
</script>

<style scoped>
.container {
  font-family: AppleSDGothicNeoM;
  color: #666;
}
.header {
  display: flex;
  font-size: 18px;
}
.sort-btn {
  margin-left: auto;
}
.select {
  color: black;
  font-family: AppleSDGothicNeoSB;
}

/* 회차 리스트 */
.round-wrap {
  margin-top: 10px;
}
.round-wrap .round-list-item:first-child {
  border-top: 1px solid #efefef;
}
.round-list-item {
  height: 100px;
  border-bottom: 1px solid #efefef;
}
.round-list-item a {
  display: flex;
  height: 100%;
  padding: 10px 0;
  box-sizing: border-box;
}

/* 썸네일 이미지 */
.thumbnail {
  width: 120px;
  height: 100%;
  border-radius: 3px;
}

/* 상세 정보 */
.detail-wrap {
  margin-left: 15px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.webtoon-title {
  font-size: 18px;
  color: black;
  font-family: AppleSDGothicNeoSB;
}
.etc {
  display: flex;
  font-size: 15px;
  color: #999;
  margin-top: 4px;
}
.etc *:not(:last-child) {
  margin-right: 8px;
}
.etc i {
  color: rgb(255, 71, 71);
  font-size: 13px;
}

/* 페이지 */
</style>

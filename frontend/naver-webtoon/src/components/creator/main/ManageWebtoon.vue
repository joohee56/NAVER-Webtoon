<template lang="ko">
	<div class="manage-webtoon-container">
		<div class="subject">작품 관리</div>
		<div class="content-wrap">

      <div v-for="(webtoon, index) in webtoons" class="webtoon-list">
        <div class="thumbnail-wrap" @mouseover="showLink(index)" @mouseleave="hideLink">
					<img :src="require(`@/assets/image/${webtoon.posterStoreName}`)" class="cover-image">
          <div class="cover-image-wrap" v-if='showLinkWebtoonIndex===index'></div>
          <router-link :to="{name: webtoon.serialType.toLowerCase()+'RoundList', params: {webtoonId: webtoon.webtoonId}}" class="link-to-webtoon-detail" v-if='showLinkWebtoonIndex===index'>
            웹툰에서 보기 ↗
          </router-link>
				</div>
				<div class="webtoon-info">
					<div>
						<span class="title">{{webtoon.webtoonName}}</span>
            <router-link :to="{name: 'editWebtoon', params: {webtoonId: webtoon.webtoonId}}"  class="edit-webtoon-btn">작품 정보 수정 ></router-link>
					</div>
					<div class="description">
						<div class="domain">{{webtoon.serialType | serialType}} | </div>
						<div class="date">최신 회차 공개일 {{webtoon.createdAt}}</div>
					</div>
					<div id="statistics">
            <!-- 조회수 -->
						<div class="item">
							<i class="fa-regular fa-eye"></i>
							<div>0</div>
						</div>
            <!-- 관심 독자수 -->
						<div class="item">
							<i class="fa-solid fa-user-group"></i>
							<div>0</div>
						</div>
            <!-- 총 댓글 갯수 -->
						<div class="item">
							<i class="fa-regular fa-comment-dots"></i>
							<div>{{webtoon.totalCommentCount}}</div>
						</div>
					</div>
					<div class="btn">
						<a link="#">작품 통계</a>
						<router-link :to="{name: 'manageRound', params: {webtoonId: webtoon.webtoonId, webtoonType: webtoon.serialType.toLowerCase() }}">회차 관리</router-link>
					</div>
				</div>
      </div>

      <!-- 새작품 등록 버튼 -->
			<router-link :to="{name: 'createNewWebtoon'}">
				<div class="create-new-webtoon-btn">
					<div>
						<div class="icon-wrap">
							<i class="fa-regular fa-pen-to-square"></i>
						</div>
						<p class="title">새 작품 등록</p>
						<p>새로운 작품을 등록해보세요</p>
					</div>
				</div>
			</router-link>
		</div>
	</div>
</template>

<script>
import { getWebtoonAllByMember } from "@/api/webtoon";

export default {
  data() {
    return {
      webtoons: [], //webtoonId, webtoonName, serialType, posterStoreName, totalCommentCount, createdAt
      showLinkWebtoonIndex: "",
    };
  },
  mounted() {
    this.fetchWebtoon();
  },
  methods: {
    async fetchWebtoon() {
      try {
        const response = await getWebtoonAllByMember();
        console.log(response);
        if (response.status === 200) {
          this.webtoons = response.data;
        }
      } catch (error) {
        console.log(error);
      }
    },
    showLink(index) {
      this.showLinkWebtoonIndex = index;
    },
    hideLink() {
      this.showLinkWebtoonIndex = "";
    },
  },
  filters: {
    serialType(value) {
      return value === "OFFICIAL" ? "정식연재" : "도전만화";
    },
  },
};
</script>

<style scoped>
.manage-webtoon-container {
  padding: 30px 30px;
}
.subject {
  font-family: AppleSDGothicNeoSB;
  font-size: 1.3rem;
}

/* 웹툰 리스트 */
.content-wrap {
  padding: 10px 0px;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 15px 15px;
}

.webtoon-list {
  display: flex;
  background-color: white;
  border: 0.5px solid #ebebeb;
  border-radius: 5px;
  padding: 19px;
  max-width: 480px;
  max-height: 214px;
}

.thumbnail-wrap {
  position: relative;
}
.cover-image-wrap {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 10;
  background: linear-gradient(180deg, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.4));
  border-radius: 4px;
}
.thumbnail-wrap .link-to-webtoon-detail {
  z-index: 99;
  position: absolute;
  cursor: pointer;
  right: 15px;
  bottom: 20px;
  left: 15px;
  color: white;
  font-size: 15px;
  border: 1px solid white;
  background: rgba(0, 0, 0, 0.601);
  font-family: AppleSDGothicNeoR;
  text-align: center;
  padding: 6px 3px 3px;
}
.isBlind {
  display: none;
}
.cover-image {
  width: 135px;
  height: 100%;
  border-radius: 5px;
  border: 0.5px solid #ebebeb;
  object-fit: cover;
}

.webtoon-info {
  padding-left: 15px;
  width: 100%;
  height: 100%;
  position: relative;
}
.webtoon-info div {
  display: flex;
}
.webtoon-list .title {
  font-family: AppleSDGothicNeoB;
  display: inline-block;
}
.webtoon-list .edit-webtoon-btn {
  margin-left: auto;
  display: inline-block;
  color: #00dc64;
  font-family: AppleSDGothicNeoB;
  font-size: 13px;
}
.webtoon-list .description {
  color: #7f7f7f;
  display: flex;
  font-family: AppleSDGothicNeoR;
  font-size: 13px;
  margin-top: 5px;
  text-align: center;
}
.description .date {
  margin-left: 5px;
}
#statistics {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 4px 12px;
  margin-top: 26px;
}
#statistics .item {
  position: relative;
  align-items: center;
  column-gap: 6px;
  font-size: 15px;
  line-height: 22px;
}
#statistics i {
  color: #7f7f7f;
}
.webtoon-info .btn {
  display: grid;
  grid-template-columns: 1fr 1fr;
  column-gap: 12px;
  padding-top: 10px;
}

.webtoon-info .btn a {
  background-color: #f6f6f6;
  padding: 14px 0;
  border: 1px solid #ebebeb;
  text-align: center;
  font-family: AppleSDGothicNeoM;
  font-size: 14px;
  line-height: 14px;
  border-radius: 5px;
}
/* 새 작품 등록 */
.create-new-webtoon-btn {
  background-color: #f1f2f3;
  width: 480px;
  height: 214px;
  border: 0.5px solid #ebebeb;
  border-radius: 5px;
  align-items: center;
  font-family: AppleSDGothicNeoR;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
}
.create-new-webtoon-btn p {
  padding: 0;
  margin: 0;
}
.create-new-webtoon-btn .title {
  font-family: AppleSDGothicNeoB;
  margin-top: 13px;
  margin-bottom: 5px;
}
.icon-wrap {
  background-color: #00dc64;
  font-family: AppleSDGothicNeoL;
  display: inline-block;
  color: white;
  border-radius: 50%;
  padding: 14px 14px 11px 14px;
}
</style>

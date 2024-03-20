<template lang="ko">
	<div class="webtoon-info-container">
		<div class="webtoon-info-wrap">
			<div class="webtoon-thumbnail">
				<img :src="require(`@/assets/image/${webtoonInfo.posterStoreFileName}`)">
			</div>
			<div class="webtoon-info">
				<div class="webtoon-name">{{webtoonInfo.webtoonName}}</div>
				<div class="info-line">
					<img :src="require(`@/assets/image/${webtoonInfo.profileStoreFileName}`)" class="profile-image">
					<span class="author-name">{{webtoonInfo.memberName}}</span>
					<span>· 글/그림 | 토요웹툰 · {{webtoonInfo.ageLimit}}세 이용가</span>
				</div>
				<div class="summary">
					<div>"{{webtoonInfo.oneLineSummary}}"</div>
					<div v-html="webtoonInfo.summary"></div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { getOfficialWebtoonDetail } from "@/api/webtoon";

export default {
  data() {
    return {
      webtoonInfo: {
        posterStoreFileName: "",
        webtoonName: "",
        profileStoreFileName: "",
        memberName: "",
        dayOfWeek: "",
        ageLimit: "",
        oneLineSummary: "",
        summary: "",
        tags: [],
      },
    };
  },
  mounted() {
    this.fetchWebtoonInfo();
  },
  methods: {
    async fetchWebtoonInfo() {
      try {
        const response = await getOfficialWebtoonDetail(
          this.$route.params.webtoonId
        );
        console.log(response.data);
        this.webtoonInfo = response.data;
        this.webtoonInfo.summary = this.webtoonInfo.summary.replaceAll(
          "\n",
          "<br />"
        );
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style scoped>
.webtoon-info-container {
  padding: 30px 0;
}
.webtoon-info-wrap {
  display: flex;
}
.webtoon-thumbnail img {
  width: 230px;
  border-radius: 5px;
  border: #efefef solid 1px;
}
.webtoon-info {
  margin-left: 20px;
  font-family: AppleSDGothicNeoM;
}
.webtoon-info > *:not(:first-child) {
  margin-top: 10px;
}
.webtoon-name {
  font-family: AppleSDGothicNeoB;
  font-size: 25px;
}
.info-line * {
  color: #999;
  margin-right: 5px;
  font-size: 18px;
  vertical-align: middle;
}
.info-line .author-name {
  color: black;
}
.profile-image {
  width: 35px;
}
.summary {
  font-size: 18px;
}
</style>

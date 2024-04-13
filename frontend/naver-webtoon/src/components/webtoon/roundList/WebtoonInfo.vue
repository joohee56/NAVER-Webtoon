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
					<span class="author-name">{{webtoonInfo.memberName}} · 글/그림</span>
					<span v-if="webtoonInfo.dayOfWeek"> | {{webtoonInfo.dayOfWeek | dayOfWeekTitle}}웹툰 </span> <span>· {{webtoonInfo.ageLimit}}세 이용가</span>
				</div>
				<div class="summary">
					<div class="overflow-hidden">{{webtoonInfo.oneLineSummary}}</div>
					<div v-html="webtoonInfo.summary"></div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { getOfficialWebtoonDetail } from "@/api/webtoon";
import { mapMutations } from "vuex";

export default {
  data() {
    return {
      webtoonInfo: {
        posterStoreFileName: "default-webtoon-cover.png",
        webtoonName: "",
        profileStoreFileName: "default-profile-image.png",
        memberName: "",
        dayOfWeek: "",
        ageLimit: "",
        oneLineSummary: "",
        summary: "",
        tags: [],
      },
    };
  },
  filters: {
    dayOfWeekTitle(value) {
      switch (value) {
        case "MONDAY": {
          return "월요";
        }
        case "TUESDAY": {
          return "화요";
        }
        case "WEDNESDAY": {
          return "수요";
        }
        case "THURSDAY": {
          return "목요";
        }
        case "FRIDAY": {
          return "금요";
        }
        case "SATURDAY": {
          return "토요";
        }
        case "SUNDAY": {
          return "일요";
        }
      }
    },
  },
  async mounted() {
    await this.fetchWebtoonInfo();
    if (this.webtoonInfo.dayOfWeek) {
      this.setDayOfWeekActive();
    }
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
    ...mapMutations("navStore", ["SET_DAY_OF_WEEK_IS_ACTIVE"]),
    setDayOfWeekActive() {
      this.SET_DAY_OF_WEEK_IS_ACTIVE(
        this.getDayOfWeekIndex(this.webtoonInfo.dayOfWeek)
      );
    },
    getDayOfWeekIndex(value) {
      switch (value) {
        case "MONDAY": {
          return 1;
        }
        case "TUESDAY": {
          return 2;
        }
        case "WEDNESDAY": {
          return 3;
        }
        case "THURSDAY": {
          return 4;
        }
        case "FRIDAY": {
          return 5;
        }
        case "SATURDAY": {
          return 6;
        }
        case "SUNDAY": {
          return 7;
        }
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
.overflow-hidden {
  white-space: nowrap; /* 줄 바꿈 방지 */
  overflow: hidden; /* 넘치는 부분 숨김 */
  text-overflow: ellipsis; /* 넘치는 부분에 ... 추가 */
}
</style>

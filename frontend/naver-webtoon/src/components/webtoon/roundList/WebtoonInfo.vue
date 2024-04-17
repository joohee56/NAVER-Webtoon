<template lang="ko">
	<div class="container">
    <div class="webtoon-thumbnail">
      <img :src="require(`@/assets/image/${webtoonInfo.posterStoreFileName}`)">
    </div>
    <div class="webtoon-info">
      <div class="webtoon-name">{{webtoonInfo.webtoonName}}</div>
      <div class="detail-line">
        <img :src="require(`@/assets/image/${webtoonInfo.profileStoreFileName}`)" class="profile-image">
        <div class="author-name">{{webtoonInfo.memberName}}</div><div>· 글/그림</div>
        <div v-if="webtoonInfo.dayOfWeek" class="day-of-week"> | {{webtoonInfo.dayOfWeek | dayOfWeekTitle}}웹툰 </div> <div>· {{webtoonInfo.ageLimit}}세 이용가</div>
        <div v-if="!webtoonInfo.dayOfWeek"> | 도전만화 ·</div>
        <div class="genre-wrap">
          <div v-if="!webtoonInfo.dayOfWeek" v-for="genre in webtoonInfo.genres">{{genre.title}}</div>
        </div>
      </div>
      <div class="summary">
        <div class="overflow-hidden">{{webtoonInfo.oneLineSummary}}</div>
        <div v-html="webtoonInfo.summary"></div>
      </div>
    </div>
	</div>
</template>

<script>
import { getWebtoonDetail } from "@/api/webtoon";
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
        genres: [],
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
      this.setDayOfWeekNavActive();
    } else {
      this.setGenreNavActive();
    }
  },
  methods: {
    async fetchWebtoonInfo() {
      try {
        const response = await getWebtoonDetail(this.$route.params.webtoonId);
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
    ...mapMutations("navStore", [
      "SET_DAY_OF_WEEK_IS_ACTIVE",
      "SET_GENRE_IS_ACTIVE",
    ]),
    setDayOfWeekNavActive() {
      this.SET_DAY_OF_WEEK_IS_ACTIVE(
        this.getDayOfWeekIndex(this.webtoonInfo.dayOfWeek)
      );
    },
    setGenreNavActive() {
      this.SET_GENRE_IS_ACTIVE(this.webtoonInfo.genres[0].name);
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
.container {
  padding: 30px 0;
  display: flex;
}
.webtoon-thumbnail img {
  width: 230px;
  border-radius: 5px;
  border: #efefef solid 1px;
  aspect-ratio: 480 / 623;
}
/* 웹툰 정보 */
.webtoon-info {
  margin-left: 20px;
  font-family: AppleSDGothicNeoM;
}
.webtoon-name {
  font-family: AppleSDGothicNeoB;
  font-size: 25px;
}
/* 웹툰 상세 정보 */
.detail-line {
  display: flex;
  margin-top: 10px;
}
.detail-line * {
  color: #999;
  margin-right: 5px;
  font-size: 18px;
}
.profile-image {
  width: 35px;
  aspect-ratio: 1/1;
  border-radius: 50%;
}
.detail-line .author-name {
  color: black;
  line-height: 31px;
}
.day-of-week {
  margin-top: 3px;
}
.genre-wrap {
  display: flex;
  line-height: 31px;
}
.genre-wrap div:not(:last-child)::after {
  content: ",";
}
/* 줄거리 */
.summary {
  font-size: 18px;
  margin-top: 15px;
}
.overflow-hidden {
  white-space: nowrap; /* 줄 바꿈 방지 */
  overflow: hidden; /* 넘치는 부분 숨김 */
  text-overflow: ellipsis; /* 넘치는 부분에 ... 추가 */
}
</style>

<template lang="ko">
	<div class="week-day-item-wrap":class="{today:isToday}">
		<h3 class="week-day-header"> {{headerTitle}} </h3>
		<ul class="webtoon-list-wrap">
			<li class="webtoon-item" v-for="webtoon in webtoons">
				<router-link :to="{name: webtoonType+'RoundList', params: {webtoonId:`${webtoon.webtoonId}`}}">
					<img :src="require(`@/assets/image/${webtoon.posterStoreFileName}`)">
				</router-link>
				<div class="webtoon-name-wrap overflow-hidden">
					<span class="up" v-if="webtoon.roundUpdateCount>0">UP</span>
					<router-link :to="{name: webtoonType+'RoundList', params: {webtoonId:`${webtoon.webtoonId}`}}">
						<span class="webtoon-name">{{webtoon.webtoonName}}</span>
					</router-link>
				</div>
        <div class="like-cnt-wrap" v-if="dayOfWeek===undefined">
          <i class="fa-solid fa-heart"></i>
          <div>{{webtoon.totalLikeCount}}</div>
        </div>
			</li> 
		</ul>	
	</div>
</template>

<script>
export default {
  props: ["headerTitle", "webtoons", "dayOfWeek"],
  computed: {
    isToday() {
      return new Date().getDay() === this.dayOfWeek;
    },
    webtoonType() {
      if (this.dayOfWeek !== undefined) {
        return "official";
      } else {
        return "challenge";
      }
    },
  },
};
</script>

<style scoped>
.week-day-item-wrap {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.week-day-header {
  font-family: AppleSDGothicNeoL;
  text-align: center;
  height: 45px;
  margin: 0;
  display: flex;
  align-items: center;
  padding-top: 5px;
  width: 100%;
  justify-content: center;
}
.webtoon-list-wrap {
  list-style: none;
  padding: 10px 0 23px;
  font-family: AppleSDGothicNeoM;
  display: block;
  margin: 0;
}
.webtoon-item {
  width: 160px;
}
.webtoon-item:not(:first-child) {
  margin-top: 25px;
}
.webtoon-item img {
  width: 160px;
  aspect-ratio: 480 / 623;
  border-radius: 4px;
}
.webtoon-item .up {
  color: #ff4d56;
  border-radius: 5px;
  border: #ff4d56 solid 0.5px;
  font-family: AppleSDGothicNeoEB;
  text-align: center;
  padding: 0 4px;
  margin-right: 5px;
  font-size: 14px;
}
.webtoon-name {
  font-size: 15px;
}
.webtoon-name:hover {
  text-decoration: underline;
}
.overflow-hidden {
  white-space: nowrap; /*줄 바꿈 방지*/
  overflow: hidden; /* 넘치는 부분 숨김 */
  text-overflow: ellipsis; /* 넘치는 부분에 ... 추가 */
}
.like-cnt-wrap {
  display: flex;
  font-size: 15px;
  margin-top: 5px;
  font-family: AppleSDGothicNeoM;
}
.like-cnt-wrap i {
  margin-right: 5px;
  font-size: 13px;
  color: #6e6d6d;
  line-height: 18px;
}

/* today */
.today {
  background-color: #b2eece;
}
.today .week-day-header {
  color: white;
  background-color: #00dc64;
}
</style>

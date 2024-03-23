<template lang="ko">
	<div class="week-day-item-wrap":class="{today:isToday}">
		<h3 class="week-day-header"> {{dayOfWeekHeader}} </h3>
		<ul class="webtoon-list-wrap">
			<li class="webtoon-item" v-for="webtoon in webtoons">
				<router-link :to="{name: 'roundList', params: {webtoonId:`${webtoon.webtoonId}`}}">
					<img :src="require(`@/assets/image/${webtoon.posterStoreFileName}`)">
				</router-link>
				<div>
					<!-- <span class="up">UP</span> -->
					<router-link :to="{name: 'roundList', params: {webtoonId:`${webtoon.webtoonId}`}}">
						<span class="webtoon-name">{{webtoon.webtoonName}}</span>
					</router-link>
				</div>
			</li> 
		</ul>	
	</div>
</template>

<script>
export default {
  props: ["dayOfWeekHeader", "webtoons", "dayOfWeek"],
  computed: {
    isToday() {
      return new Date().getDay() === this.dayOfWeek;
    },
  },
};
</script>

<style scoped>
.week-day-item-wrap {
  flex: 1;
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
  height: 207px;
  border-radius: 4px;
}
.webtoon-item .up {
  color: #ff4d56;
  border-radius: 5px;
  border: #ff4d56 solid 0.5px;
  font-family: AppleSDGothicNeoEB;
  text-align: center;
  padding: 1px 4px;
  margin-right: 5px;
  font-size: 14px;
}
.webtoon-name {
  font-size: 15px;
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

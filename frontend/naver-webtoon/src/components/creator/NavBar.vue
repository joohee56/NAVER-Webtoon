<template lang="ko">
  <div id="container">
		<div class="nav-wrap">
			<ul>
				<li>
					<router-link :to="{name: 'manage'}">
						<i class="fa-regular fa-folder-open"></i>
						작품 관리
					</router-link>
				</li>
				<li>
					<router-link :to="{name: 'manageComment'}">
						<i class="fa-regular fa-comment-dots"></i>
						댓글 관리
					</router-link>
				</li>
				<li>
					<router-link :to="{name: 'manageAlarm'}">
						<i class="fa-regular fa-bell"></i>
						알림
					</router-link>
				</li>
			</ul>
		</div>	
	</div>
</template>

<script>
export default {};
</script>

<style scoped>
#container {
  padding-left: calc((100% - 1140px) / 2);
  background-color: white;
  width: 10rem;
  border-right: 0.5px solid #ebebeb;
}
.nav-wrap {
  padding: 34px 19px 34px 0;
}

ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
ul li {
  padding: 10px 0;
  font-size: 17px;
  font-family: AppleSDGothicNeoR;
}
ul li i {
  margin-right: 10px;
}
.router-link-active {
  color: #00dc64;
  font-family: AppleSDGothicNeoB;
}
</style>

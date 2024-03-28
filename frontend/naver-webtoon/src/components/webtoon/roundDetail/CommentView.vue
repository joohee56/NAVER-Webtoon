<template lang="ko">
	<div class="container">

		<!-- 댓글 입력 -->
		<div>
			<div class="title">의견쓰기 1,385</div>
			<div class="comment-input-box">
				<div class="loginUser-info">
					<img
          :src="require(`@/assets/image/${loginUser.profileImage}`)"
          class="profile-image" />
				<div class="login-user-name">{{loginUser.userName}}</div>
				</div>
				<textarea v-model="content" placeholder="주제와 무관한 댓글이나 스포일러, 악풀은 경고조치 없이 삭제되며 징계 대상이 될 수 있습니다."></textarea>
			</div>
			<div>
				<button class="submit-btn" @click="saveComment">등록</button>
			</div>
		</div>

		<!-- 댓글 -->
		<div class="comment-wrap"> 
			<div v-for="comment in comments" class="comment-item">
				<div class="user-info">
					<div class="user-id">{{comment.userName}}({{comment.userId}})</div>
					<div class="update-date">{{comment.updateAt}}</div>
				</div>
				<div class="content">{{comment.content}}</div>
				<div class="btn-wrap">
					<button>답글</button>
					<button class="btn-like"><i class="fa-regular fa-thumbs-up"></i> {{comment.likeTotalCnt}}</button>
					<button><i class="fa-regular fa-thumbs-down"></i> {{comment.dislikeTotalCnt}}</button>
				</div>
			</div>
		</div>
		<button>더보기</button>
	</div>
</template>

<script>
import { postComment, getCommentsWithLogin } from "@/api/comment";
import { mapState } from "vuex";

export default {
  data() {
    return {
      content: "",
      comments: [],
      limit: 6,
    };
  },
  computed: {
    ...mapState("memberStore", ["loginUser"]),
  },
  mounted() {
    this.fetchCommentsWithLogin();
  },
  methods: {
    async fetchCommentsWithLogin() {
      try {
        const response = await getCommentsWithLogin(
          this.$route.params.roundId,
          0,
          this.limit
        );
        this.comments = response.data;
      } catch (error) {
        console.log(error);
      }
    },
    async saveComment() {
      const comment = {
        roundId: this.$route.params.roundId,
        content: this.content,
      };
      try {
        const response = await postComment(comment);
        location.reload();
        console.log(response.data);
      } catch (error) {
        console.log(error);
      }
    },
  },
};
</script>

<style scoped>
.container {
  font-family: AppleSDGothicNeoM;
}
/* 댓글 입력 */
.title {
  font-family: AppleSDGothicNeoB;
  font-size: 19px;
  margin: 70px 0 10px 5px;
}
.comment-input-box {
  border: 1px solid #efefef;
  border-radius: 7px;
  width: 97%;
  padding: 20px 20px;
}
.loginUser-info {
  display: flex;
  direction: column;
  align-items: center;
}
.profile-image {
  border-radius: 50%;
  width: 40px;
  height: 40px;
}
.login-user-name {
  margin-left: 7px;
  font-family: AppleSDGothicNeoSB;
}
.comment-input-box textarea {
  width: 98%;
  height: 70px;
  resize: none;
  margin-top: 10px;
  padding: 10px;
  border: none;
  font-size: 15px;
}
.comment-input-box textarea::placeholder {
  font-family: AppleSDGothicNeoM;
}
.submit-btn {
  display: flex;
  margin-left: auto;
  background: #00dc64;
  color: white;
  border: none;
  padding: 10px 25px;
  border-radius: 29px;
  font-size: 17px;
  margin-top: 10px;
}

/* 댓글 */
.comment-wrap {
  border: 1px solid #efefef;
  border-radius: 10px;
  margin-top: 70px;
}
.comment-item:not(:last-child) {
  border-bottom: 1px solid #efefef;
}
.comment-item {
  padding: 25px 20px 25px;
  display: grid;
  row-gap: 5px;
}
.comment-wrap > :nth-child(-n + 4) .content::before {
  content: "BEST";
  background-color: #ff4d56;
  color: white;
  font-size: 13px;
  margin-right: 10px;
  padding: 4px 9px 2px;
  border-radius: 8px;
}
.comment-wrap .content {
  line-height: 28px;
  margin-right: 10px;
}
.comment-item .user-info {
  display: flex;
  direction: column;
  align-items: end;
}
.comment-item .user-id {
  font-family: AppleSDGothicNeoB;
  font-size: 17px;
}
.comment-item .update-date {
  color: #989898;
  margin-left: 10px;
  font-size: 15px;
}
.comment-item .btn-wrap {
  display: flex;
  margin-top: 10px;
}
.comment-item button {
  background: none;
  border: 1px solid #d8d8d8;
  padding: 3px 10px;
  color: #989898;
  font-size: 15px;
}
.btn-like {
  margin-left: auto;
  margin-right: 10px;
}
</style>

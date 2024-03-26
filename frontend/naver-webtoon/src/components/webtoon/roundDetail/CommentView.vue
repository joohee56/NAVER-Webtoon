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
					<button>답글 {{comment.nestedCommentCnt}}</button>
					<button class="btn-like">좋아요 {{comment.likeCnt}}</button>
					<button>싫어요 {{comment.notLikeCnt}}</button>
				</div>
			</div>
		</div>
		<button>더보기</button>
	</div>
</template>

<script>
import { postComment } from "@/api/comment";
import { mapState } from "vuex";

export default {
  data() {
    return {
      content: "",
      comments: [
        {
          commentId: 1,
          userName: "핑",
          userId: "yuji****",
          updateAt: "2023-12-01 23:14",
          content: "아이저가 세레나 공주님으로 부르는거 좋다..",
          nestedCommentCnt: 6,
          likeCnt: 14285,
          notLikeCnt: 28,
        },
        {
          commentId: 2,
          userName: "핑",
          userId: "yuji****",
          updateAt: "2023-12-01 23:14",
          content: "드라마로 제작되면 좋겠다",
          nestedCommentCnt: 6,
          likeCnt: 14285,
          notLikeCnt: 28,
        },
        {
          commentId: 3,
          userName: "hyeon",
          userId: "nany****",
          updateAt: "2023-12-01 23:14",
          content: "너무 재밌어요 작가님 ㅜㅜ",
          nestedCommentCnt: 6,
          likeCnt: 14285,
          notLikeCnt: 28,
        },
        {
          commentId: 4,
          userName: "aas",
          userId: "aaso****",
          updateAt: "2023-12-01 23:14",
          content: "다음화...!!!",
          nestedCommentCnt: 6,
          likeCnt: 14285,
          notLikeCnt: 28,
        },
        {
          commentId: 5,
          userName: "aas",
          userId: "aaso****",
          updateAt: "2023-12-01 23:14",
          content: "너무 재밌당",
          nestedCommentCnt: 6,
          likeCnt: 14285,
          notLikeCnt: 28,
        },
        {
          commentId: 6,
          userName: "aas",
          userId: "aaso****",
          updateAt: "2023-12-01 23:14",
          content: "짱이햐",
          nestedCommentCnt: 6,
          likeCnt: 14285,
          notLikeCnt: 28,
        },
      ],
    };
  },
  computed: {
    ...mapState("memberStore", ["loginUser"]),
  },
  methods: {
    async saveComment() {
      const comment = {
        roundId: this.$route.params.roundId,
        content: this.content,
      };

      try {
        const response = await postComment(comment);
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
  row-gap: 12px;
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
}
.btn-like {
  margin-left: auto;
}
</style>

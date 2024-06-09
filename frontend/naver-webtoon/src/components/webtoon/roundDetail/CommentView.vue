<template lang="ko">
	<div class="container">

		<!-- 댓글 입력란-->
		<div>
			<div class="title">의견쓰기 {{totalCommentCount}}</div>
			<div class="comment-input-box">
				<div class="loginUser-info" v-if="loginUser.loginId">
					<img
          :src="require(`@/assets/image/${loginUser.profileImage}`)"
          class="profile-image" />
          <div class="login-user-name">{{loginUser.userName}}</div>
				</div>
        <div v-if="loginUser.loginId">
          <textarea v-model="content" placeholder="주제와 무관한 댓글이나 스포일러, 악풀은 경고조치 없이 삭제되며 징계 대상이 될 수 있습니다." class="editable-textarea" :class="{violation: content.length>inputContentLimit}"></textarea>
          <span class="input-letter-count">{{content.length}} / {{inputContentLimit}}</span>
        </div>
        <div @click="moveToLogin" class="uneditable-textarea" v-else>
          <textarea ></textarea>
          <label>댓글을 작성하려면 <router-link :to="{ name: 'login', params: { redirectUrl: this.$route.path }}">로그인</router-link> 해주세요.</label>
        </div>
			</div>
			<div>
				<button class="submit-btn" @click="saveComment">등록</button>
			</div>
		</div>

		<!-- 댓글 -->
		<div class="comment-list-wrap"> 
			<div class="comment-item" v-for="(comment, index) in comments">
        <div class="user-info-btn-wrap">
					<div class="user-id">{{comment.userName}}({{comment.userId}})</div>
					<div class="update-date">{{comment.updateAt}}</div>
          <button class="menu-btn" @click="clickMenu(index, false)" v-if="comment.userId===loginUser.loginId"><i class="fa-solid fa-ellipsis-vertical"></i></button>
          <button class="delete-btn" v-if="showMenuIndex===index" @click="deleteComment(comment.commentId)">삭제</button>
				</div>
				<div class="content" :class="{bestComment:isBestComment(index)}">{{comment.content}}</div>
				<div class="btn-wrap">
					<button @click="showReply(index, comment.commentId)">답글 {{comment.replyCnt}}</button>
					<button class="btn-like" :class="{isUserLikeActive:comment.isUserLike}" @click="clickCommentLike(comment.commentId, index, false)"><i class="fa-regular fa-thumbs-up"></i> {{comment.likeTotalCnt}}</button>
					<button :class="{isUserDislikeActive:comment.isUserDislike}" @click="clickCommentDislike(comment.commentId, index, false)"><i class="fa-regular fa-thumbs-down" ></i> {{comment.dislikeTotalCnt}}</button>
				</div>

        <!-- 답글 -->
        <div class="reply-comments-wrap" v-if="showReplyIndex===index">
          <!-- 답글 리스트 -->
          <div class="reply-comment" v-for="(reply, index) in replys">
            <span class="reply-comment-icon"></span>
            <div class="user-info-btn-wrap">
              <div class="user-id">{{reply.userName}}({{reply.userId}})</div>
              <button class="menu-btn" @click="clickMenu(index, true)" v-if="reply.userId===loginUser.loginId"><i class="fa-solid fa-ellipsis-vertical"></i></button>
              <button class="delete-btn" v-if="replyShowMenuIndex===index" @click="deleteReplyComment(reply.commentId, comment.commentId)">삭제</button>
            </div>
            <div class="content">{{reply.content}}</div>
            <div class="update-date">{{reply.updateAt}}</div>
            <div class="btn-wrap">
              <button class="btn-like" :class="{isUserLikeActive:reply.isUserLike}" @click="clickCommentLike(reply.commentId, index, true)"><i class="fa-regular fa-thumbs-up"></i> {{reply.likeTotalCnt}}</button>
              <button :class="{isUserDislikeActive:reply.isUserDislike}" @click="clickCommentDislike(reply.commentId, index, true)"><i class="fa-regular fa-thumbs-down" ></i> {{reply.dislikeTotalCnt}}</button>
            </div>
          </div>

          <!-- 답글 입력란 -->
          <div class="comment-input-box reply">
            <div class="loginUser-info" v-if="loginUser.loginId">
              <img
              :src="require(`@/assets/image/${loginUser.profileImage}`)"
              class="profile-image" />
              <div class="login-user-name">{{loginUser.userName}}</div>
            </div>
            <div v-if="loginUser.loginId">
              <textarea v-model="replyContent" placeholder="주제와 무관한 댓글이나 스포일러, 악풀은 경고조치 없이 삭제되며 징계 대상이 될 수 있습니다." class="editable-textarea" :class="{violation: replyContent.length>inputContentLimit}"></textarea>
              <span class="input-letter-count">{{replyContent.length}} / {{inputContentLimit}}</span>
            </div>
            <div @click="moveToLogin" class="uneditable-textarea" v-else>
              <textarea ></textarea>
              <label>댓글을 작성하려면 <router-link :to="{ name: 'login', params: { redirectUrl: this.$route.path } }">로그인</router-link> 해주세요.</label>
            </div>
          </div>

          <div>
            <button class="submit-btn" @click="saveReply(comment.commentId)">등록</button>
          </div>

          <!-- 답글 접기 -->
          <button class="close-reply-btn" @click="showReplyIndex=''">답글 접기 <i class="fa-solid fa-angle-up"></i></button>
        </div>
      </div>

    </div>
		<button @click="moreComments" class="more-comment-btn">더보기 <i class="fa-solid fa-chevron-down"></i></button>
	</div>
</template>

<script>
import {
  postComment,
  getComments,
  postCommentLike,
  postCommentDislike,
  deleteComment,
  postReply,
  getReply,
} from "@/api/comment";
import { mapState } from "vuex";

export default {
  data() {
    return {
      content: "",
      replyContent: "",
      inputContentLimit: 500,
      title: {
        content: "댓글",
        replyContent: "답글",
      },
      comments: [], //commentId, userId, userName, content, updateAt, likeTotalCnt, isUserLike, dislikeTotalCnt, isUserDislike, replyCnt
      totalCommentCount: 0,
      bestCommentCount: 4,
      replys: [],
      showMenuIndex: "",
      replyShowMenuIndex: "",
      showReplyIndex: "",
      offset: 0,
      limit: 6,
    };
  },
  computed: {
    ...mapState("memberStore", ["loginUser"]),
  },
  mounted() {
    this.fetchComments();
  },
  beforeDestroy() {
    // 컴포넌트가 파괴될 때 이벤트 리스너를 제거
    document.removeEventListener("click", this.hideMenuButton);
  },
  methods: {
    async fetchComments() {
      try {
        const response = await getComments(
          this.$route.params.roundId,
          this.offset,
          this.limit
        );
        console.log(response);
        if (response.status === 200) {
          this.comments = response.data.comments;
          this.totalCommentCount = response.data.totalCommentCount;
        }
      } catch (error) {
        console.log(error);
      }
    },
    initCommentOffset() {
      this.offset = 0;
      this.limit = 6;
    },
    async fetchReply(commentId) {
      try {
        const response = await getReply(commentId);
        console.log(response);
        if (response.status === 200) {
          this.replys = response.data;
        }
      } catch (error) {
        console.log(error);
      }
    },
    async showReply(index, commentId) {
      await this.fetchReply(commentId);
      this.showReplyIndex = index;
    },
    isBestComment(index) {
      return (
        index < this.bestCommentCount && this.comments[index].likeTotalCnt > 0
      );
    },
    async saveComment() {
      const comment = {
        roundId: this.$route.params.roundId,
        content: this.content,
      };
      try {
        const response = await postComment(comment);
        console.log(response);
        if (response.status === 200) {
          this.initCommentOffset();
          this.fetchComments();
          this.content = "";
        } else if (response.status === 400) {
          let code = response.data.code;
          if (code === "VALIDATION") {
            let errorMessage = "";
            for (const key in response.data.message) {
              errorMessage =
                this.title[key] +
                response.data.message[key] +
                errorMessage +
                "\n";
            }
            alert(errorMessage);
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    async saveReply(commentId) {
      const reply = {
        roundId: this.$route.params.roundId,
        replyContent: this.replyContent,
        commentId: commentId,
      };

      try {
        const response = await postReply(reply);
        console.log(response);
        if (response.status === 200) {
          this.fetchReply(commentId);
          this.replyContent = "";
        } else if (response.status === 400) {
          let code = response.data.code;
          if (code === "VALIDATION") {
            let errorMessage = "";
            for (const key in response.data.message) {
              errorMessage =
                this.title[key] +
                response.data.message[key] +
                errorMessage +
                "\n";
            }
            alert(errorMessage);
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    clickMenu(index, isReply) {
      if (isReply) {
        this.replyShowMenuIndex = index;
      } else {
        this.showMenuIndex = index;
      }
      document.addEventListener("click", this.hideMenuButton);
    },
    hideMenuButton(event) {
      const menuButton = document.querySelector(".menu-btn");
      if (menuButton && !menuButton.contains(event.target)) {
        this.showMenuIndex = "";
        this.replyShowMenuIndex = "";
      }
    },
    async deleteComment(commentId) {
      const ok = confirm("댓글을 삭제하시겠습니까?");
      if (ok) {
        const response = await deleteComment(commentId);
        console.log(response);
        if (response.status === 200) {
          this.fetchComments();
        } else if (response.staus === 400) {
          console.log(response.data.message);
        }
      }
    },
    async deleteReplyComment(replyCommentId, parentCommentId) {
      const ok = confirm("댓글을 삭제하시겠습니까?");
      if (ok) {
        const response = await deleteComment(replyCommentId);
        console.log(response);
        if (response.status === 200) {
          this.fetchReply(parentCommentId);
        } else if (response.staus === 400) {
          console.log(response.data.message);
        }
      }
    },
    async clickCommentLike(commentId, index, isReply) {
      try {
        const response = await postCommentLike(commentId);
        console.log(response.data);
        if (response.status === 200) {
          if (isReply) {
            this.replys[index].isUserLike = response.data.isUserLike;
            this.replys[index].likeTotalCnt = response.data.likeTotalCnt;
          } else {
            this.comments[index].isUserLike = response.data.isUserLike;
            this.comments[index].likeTotalCnt = response.data.likeTotalCnt;
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    async clickCommentDislike(commentId, index, isReply) {
      try {
        const response = await postCommentDislike(commentId);
        console.log(response.data);
        if (response.status === 200) {
          if (isReply) {
            this.replys[index].isUserDislike = response.data.isUserDislike;
            this.replys[index].dislikeTotalCnt = response.data.dislikeTotalCnt;
          } else {
            this.comments[index].isUserDislike = response.data.isUserDislike;
            this.comments[index].dislikeTotalCnt =
              response.data.dislikeTotalCnt;
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    moveToLogin() {
      alert("로그인을 하신 후 이용해 주시기 바랍니다.");
      this.$router.push({
        name: "login",
        params: { redirectUrl: this.$route.path },
      });
    },
    async moreComments() {
      const nextOffset = this.offset + this.limit;
      try {
        const response = await getComments(
          this.$route.params.roundId,
          nextOffset,
          this.limit
        );
        console.log(response);
        if (response.status === 200) {
          if (response.data.comments.length > 0) {
            for (const comment of response.data.comments) {
              this.comments.push(comment);
            }
            this.offset = nextOffset;
          } else {
            alert("더 이상 조회할 댓글이 없습니다.");
          }
        }
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
  width: 85rem;
  margin: 0 auto;
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
  height: 170px;
  padding: 10px 10px;
  position: relative;
  background-color: white;
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
.comment-input-box .editable-textarea {
  width: 98%;
  height: 70px;
  resize: none;
  margin-top: 10px;
  padding: 10px;
  border: none;
  font-size: 15px;
}
.input-letter-count {
  position: absolute;
  right: 20px;
  bottom: 10px;
  font-size: 13px;
  color: #b1b1b1;
}
.violation {
  border: 1px solid red !important;
}
.uneditable-textarea {
  position: relative;
  padding: 0 15px;
}
.uneditable-textarea textarea {
  width: 100%;
  height: 100%;
  border: none;
}
.uneditable-textarea label {
  overflow: hidden;
  position: absolute;
  top: 10px;
  right: 15px;
  bottom: 0;
  left: 15px;
  z-index: 10;
  border: none;
  font-size: 13px;
  color: #a1a1a1;
  line-height: 1.5;
  cursor: pointer;
}
.uneditable-textarea a:hover {
  text-decoration: underline;
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
  margin-top: 15px;
  margin-right: 20px;
  cursor: pointer;
}

/* 댓글 리스트  */
.comment-list-wrap {
  border: 1px solid #efefef;
  border-radius: 10px;
  margin-top: 90px;
  width: 98%;
}
.comment-item:not(:last-child) {
  border-bottom: 1px solid #efefef;
}
.comment-item {
  padding: 25px 20px 25px;
  display: grid;
  row-gap: 5px;
  position: relative;
}
.comment-list-wrap .bestComment::before {
  content: "BEST";
  background-color: #ff4d56;
  color: white;
  font-size: 13px;
  margin-right: 10px;
  padding: 4px 9px 2px;
  border-radius: 8px;
}
.comment-list-wrap .user-info-btn-wrap {
  display: flex;
}
.comment-list-wrap .user-id {
  font-family: AppleSDGothicNeoB;
  font-size: 17px;
  margin-right: 10px;
}
.comment-list-wrap .update-date {
  color: #989898;
  font-size: 15px;
  line-height: 25px;
}
.user-info-btn-wrap .menu-btn {
  background: none;
  border: none;
  font-size: 20px;
  margin-left: auto;
  cursor: pointer;
}
.user-info-btn-wrap .delete-btn {
  display: block;
  padding: 15px 38px 16px;
  border: 2px solid #d8d8d8;
  font-size: 15px;
  text-align: center;
  background: white;
  font-family: AppleSDGothicNeoSB;
  border-radius: 5px;
  position: absolute;
  right: 25px;
  top: 55px;
  cursor: pointer;
}
.comment-list-wrap .content {
  line-height: 30px;
  margin-right: 10px;
}
.comment-list-wrap .btn-wrap {
  display: flex;
  margin-top: 10px;
}
.btn-wrap > * {
  background: none;
  border: 1px solid #d8d8d8;
  padding: 3px 10px;
  color: #989898;
  font-size: 15px;
  cursor: pointer;
}
.btn-like {
  margin-left: auto;
  margin-right: 10px;
}
.isUserLikeActive {
  border: 1px solid #ff5151;
  color: #ff5151;
}
.isUserDislikeActive {
  border: 1px solid #385da3;
  color: #385da3;
}
.more-comment-btn {
  display: block;
  margin: 30px auto 150px;
  background-color: white;
  border: none;
  color: #999;
  cursor: pointer;
}

/* 답글 */
.reply-comments-wrap {
  background-color: #fafafa;
  border-top: 1px solid #e2e2e2;
  border-bottom: 1px solid #e2e2e2;
  margin-top: 10px;
}
.reply-comment {
  padding: 17px;
  padding-left: 40px;
  position: relative;
}
.reply-comment:not(:last-child) {
  border-bottom: 1px solid #e2e2e2;
}
.reply-comment-icon {
  position: absolute;
  top: 18px;
  left: 18px;
  width: 13px;
  height: 13px;
  background-position: -19px -125px;
  background-image: url(https://ssl.pstatic.net/static/comic/comment_new/sp_cbox.png);
  background-repeat: no-repeat;
  font-size: 0;
  line-height: 0;
}

/* 댓글 입력란 */
.comment-input-box.reply {
  width: 95%;
  margin: 0 auto;
  margin-top: 20px;
}

/* 댓글 접기 */
.close-reply-btn {
  display: block;
  margin: 20px auto;
  background: none;
  border: none;
  color: #989898;
  font-family: AppleSDGothicNeoM;
  cursor: pointer;
}
</style>

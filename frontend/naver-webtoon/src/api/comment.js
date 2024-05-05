import { jsonApiInstance } from "@/api/index";

const jsonApi = jsonApiInstance();

async function postComment(comment) {
  try {
    const response = await jsonApi.post("/comments", comment);
    return response;
  } catch (error) {
    return error.response;
  }
}

async function postReply(reply) {
  try {
    const response = await jsonApi.post("/comments/reply", reply);
    return response;
  } catch (error) {
    return error.response;
  }
}

async function deleteComment(commentId) {
  try {
    const response = await jsonApi.delete(`/comments/comment/${commentId}`);
    return response;
  } catch (error) {
    return error.response;
  }
}

async function getComments(roundId, offset, limit) {
  try {
    const response = await jsonApi.get(
      `/comments/${roundId}/${offset}/${limit}`
    );
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getReply(commentId) {
  try {
    const response = await jsonApi.get("/comments/reply", {
      params: {
        commentId: commentId,
      },
    });
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function postCommentLike(commentId) {
  try {
    const response = await jsonApi.post("/comment/like", null, {
      params: { commentId: commentId },
    });
    return response;
  } catch (error) {
    alert(error.response.data.message);
    console.log(error);
  }
}

async function postCommentDislike(commentId) {
  try {
    const response = await jsonApi.post(`/comment/dislike/${commentId}`, null);
    return response;
  } catch (error) {
    alert(error.response.data.message);
    console.log(error);
  }
}

export {
  postComment,
  getComments,
  postCommentLike,
  postCommentDislike,
  deleteComment,
  postReply,
  getReply,
};

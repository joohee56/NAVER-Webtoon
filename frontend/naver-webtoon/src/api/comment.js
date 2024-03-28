import { jsonApiInstance } from "@/api/index";
import { startSessionCheck } from "./loginSession";

const jsonApi = jsonApiInstance();

async function postComment(comment) {
  try {
    const response = await jsonApi.post("/comments", comment);
    startSessionCheck();
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getCommentsWithLogin(roundId, offset, limit) {
  try {
    const response = await jsonApi.get(
      `/comments/${roundId}/${offset}/${limit}`
    );
    startSessionCheck();
    return response;
  } catch (error) {
    console.log(error);
  }
}

export { postComment, getCommentsWithLogin };

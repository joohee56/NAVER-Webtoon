import { jsonApiInstance, fileApiInstance } from "@/api/index";
import { startSessionCheck } from "./loginSession";

const jsonApi = jsonApiInstance();
const fileApi = fileApiInstance();

async function getRoundsWithPaging(webtoonId, offset, limit, isDesc) {
  try {
    const response = await jsonApi.get(
      `/rounds/list/${webtoonId}/${offset}/${limit}/${isDesc}`
    );
    startSessionCheck();
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getRounds(webtoonId) {
  try {
    const response = await jsonApi.get(`/rounds/list/${webtoonId}`);
    startSessionCheck();
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getRoundDetail(roundId) {
  try {
    const response = await jsonApi.get(`/rounds/round/${roundId}`);
    startSessionCheck();
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function postRound(formData) {
  try {
    const response = await fileApi.post("/rounds", formData);
    startSessionCheck();
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function postRoundLike(roundId) {
  try {
    const response = await jsonApi.post("/round/like", null, {
      params: {
        roundId: roundId,
      },
    });
    startSessionCheck();
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getManageRound(webtoonId) {
  try {
    const response = await jsonApi.get(`/rounds/manage/round/${webtoonId}`);
    startSessionCheck();
    return response;
  } catch (error) {
    console.log(error);
  }
}

export {
  getRoundsWithPaging,
  getRoundDetail,
  getRounds,
  postRound,
  postRoundLike,
  getManageRound,
};

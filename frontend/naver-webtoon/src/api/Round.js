import { jsonApiInstance } from "@/api/index";
import { startSessionCheck } from "./loginSession";

const jsonApi = jsonApiInstance();

async function getRoundsWithPaging(webtoonId, offset, limit, isDesc) {
  try {
    const response = await jsonApi.get(
      `/rounds/${webtoonId}/${offset}/${limit}/${isDesc}`
    );
    startSessionCheck();
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getRounds(webtoonId) {
  try {
    const response = await jsonApi.get(`/rounds/${webtoonId}`);
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

export { getRoundsWithPaging, getRoundDetail, getRounds };

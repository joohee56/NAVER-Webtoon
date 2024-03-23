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

export { getRoundsWithPaging };

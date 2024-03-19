import { fileApiInstance, jsonApiInstance } from "@/api/index";
import { startSessionCheck } from "./loginSession";

const fileApi = fileApiInstance();
const jsonApi = jsonApiInstance();

async function postCreateWebtoon(webtoon) {
  try {
    const response = await fileApi.post("/webtoon", webtoon);
    startSessionCheck();
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getWebtoonAllByMember() {
  try {
    const response = await jsonApi.get("/webtoon");
    startSessionCheck();
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getOfficialWebtoonAll() {
  try {
    const response = await jsonApi.get("/webtoon/official");
    startSessionCheck();
    return response;
  } catch (error) {
    console.log(error);
  }
}

export { postCreateWebtoon, getWebtoonAllByMember, getOfficialWebtoonAll };

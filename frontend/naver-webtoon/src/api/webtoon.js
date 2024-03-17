import { fileApiInstance } from "@/api/index";
import { startSessionCheck } from "./loginSession";

const fileApi = fileApiInstance();

async function postCreateWebtoon(webtoon) {
  try {
    const response = await fileApi.post("/webtoon", webtoon);
    startSessionCheck();
    return response;
  } catch (error) {
    console.log(error);
  }
}

export { postCreateWebtoon };

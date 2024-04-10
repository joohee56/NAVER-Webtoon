import { fileApiInstance, jsonApiInstance } from "@/api/index";

const fileApi = fileApiInstance();
const jsonApi = jsonApiInstance();

async function postCreateWebtoon(webtoon) {
  try {
    const response = await fileApi.post("/webtoon", webtoon);
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getWebtoonAllByMember() {
  try {
    const response = await jsonApi.get("/webtoon");
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getOfficialWebtoonAll(genres, sorting) {
  try {
    const response = await jsonApi.post("/webtoon/official", genres, {
      params: {
        sorting: sorting,
      },
    });
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getOfficialWebtoonDetail(webtoonId) {
  try {
    const response = await jsonApi.get(`/webtoon/official/detail/${webtoonId}`);
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getOfficialWebtoonAllByDayOfWeek(dayOfWeek) {
  try {
    const response = await jsonApi.get(`/webtoon/official/${dayOfWeek}`);
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getCreateRoundInfo() {
  try {
    const response = await jsonApi.get("/webtoon/createRound");
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getWebtoonRanking(offset, limit) {
  try {
    const response = await jsonApi.get(`/webtoon/ranking/${offset}/${limit}`);
    return response;
  } catch (error) {
    console.log(error);
  }
}

export {
  postCreateWebtoon,
  getWebtoonAllByMember,
  getOfficialWebtoonAll,
  getOfficialWebtoonDetail,
  getCreateRoundInfo,
  getWebtoonRanking,
  getOfficialWebtoonAllByDayOfWeek,
};

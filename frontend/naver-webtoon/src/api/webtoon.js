import { fileApiInstance, jsonApiInstance } from "@/api/index";

const fileApi = fileApiInstance();
const jsonApi = jsonApiInstance();

async function postCreateWebtoon(webtoon) {
  try {
    const response = await fileApi.post("/webtoon", webtoon);
    return response;
  } catch (error) {
    return error.response;
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

async function getWebtoonDetail(webtoonId) {
  try {
    const response = await jsonApi.get(`/webtoon/detail/${webtoonId}`);
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getOfficialWebtoonAllByDayOfWeek(dayOfWeek, sorting) {
  try {
    const response = await jsonApi.get("/webtoon/official", {
      params: {
        dayOfWeek: dayOfWeek,
        sorting: sorting,
      },
    });
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

async function getWebtoonRanking(offset, limit, webtoonType) {
  try {
    const response = await jsonApi.get(
      `/webtoon/ranking/${offset}/${limit}/${webtoonType}`
    );
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getChallengeWebtoonAll(offset, limit, sorting) {
  try {
    const response = await jsonApi.get("/webtoon/all", {
      params: {
        offset: offset,
        limit: limit,
        sorting: sorting,
      },
    });
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getChallengeWebtoonByGenre(genre, sorting) {
  try {
    const response = await jsonApi.get(`/webtoon/genres/${genre}`, {
      params: {
        sorting: sorting,
      },
    });
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function getSearch(keyword, offset, limit) {
  try {
    const response = await jsonApi.get("/webtoon/search", {
      params: {
        keyword: keyword,
        offset: offset,
        limit: limit,
      },
    });
    return response;
  } catch (error) {
    return error.response;
  }
}

async function getSearchWebtoon(keyword, webtoonType, offset, limit) {
  try {
    const response = await jsonApi.get("/webtoon/search/webtoon", {
      params: {
        keyword: keyword,
        webtoonType: webtoonType,
        offset: offset,
        limit: limit,
      },
    });
    return response;
  } catch (error) {
    return error.response;
  }
}

async function getSearchCount(keyword) {
  try {
    const response = await jsonApi.get("/webtoon/search/count", {
      params: {
        keyword: keyword,
      },
    });
    return response;
  } catch (error) {
    return error.response;
  }
}

export {
  postCreateWebtoon,
  getWebtoonAllByMember,
  getOfficialWebtoonAll,
  getWebtoonDetail,
  getCreateRoundInfo,
  getWebtoonRanking,
  getOfficialWebtoonAllByDayOfWeek,
  getChallengeWebtoonAll,
  getChallengeWebtoonByGenre,
  getSearch,
  getSearchWebtoon,
  getSearchCount,
};

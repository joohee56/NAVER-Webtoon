import axios from "axios";
axios.defaults.withCredentials = true; // withCredentials 전역 설정

const config = {
  baseUrl: "http://localhost:8081/api/v1",
};

function jsonApiInstance() {
  const instance = axios.create({
    baseURL: config.baseUrl,
    headers: {
      "content-type": "application/json",
    },
  });
  return instance;
}

function formApiInstance() {
  const instance = axios.create({
    baseURL: config.baseUrl,
    headers: {
      "content-type": "application/x-www-form-urlencoded",
    },
  });
  return instance;
}

function fileApiInstance() {
  const instance = axios.create({
    baseURL: config.baseUrl,
    headers: {
      "content-type": "multipart/from-data",
    },
  });
  return instance;
}

export { jsonApiInstance, formApiInstance, fileApiInstance };

import axios from "axios";

const config = {
  baseUrl: "http://localhost:8081/api/v1",
};

function jsonApiInstance() {
  const instance = axios.create({
    baseURL: config.baseUrl,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

function formApiInstance() {
  const instance = axios.create({
    baseURL: config.baseUrl,
    headers: {
      "Content-type": "application/x-www-form-urlencoded",
    },
  });
  return instance;
}

export { jsonApiInstance, formApiInstance };

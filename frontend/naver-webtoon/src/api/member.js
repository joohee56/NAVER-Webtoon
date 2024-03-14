import { formApiInstance, jsonApiInstance, fileApiInstance } from "@/api/index";
import { startSessionCheck } from "./loginSession";

const formApi = formApiInstance();
const jsonApi = jsonApiInstance();
const fileApi = fileApiInstance();

async function postJoinMember(member) {
  try {
    return await formApi.post("/users/join", member);
  } catch (error) {
    console.log(error);
  }
}

async function postLogin(loginInfo) {
  try {
    const response = await formApi.post("/users/login", loginInfo);
    startSessionCheck();
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function checkDuplicatedLoginId(loginId) {
  try {
    return await jsonApi.get(`/users/duplicated/${loginId}`);
  } catch (error) {
    console.log(error);
  }
}

async function getUserInfo() {
  try {
    const response = await jsonApi.get("/users");
    startSessionCheck();
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function postProfileImage(formData) {
  try {
    const response = await fileApi.post("/users/profileImage", formData);
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function putUserInfo(userInfo) {
  try {
    const response = await jsonApi.post("/users/edit", userInfo);
    return response;
  } catch (error) {
    console.log(error);
  }
}

export {
  postJoinMember,
  checkDuplicatedLoginId,
  postLogin,
  getUserInfo,
  postProfileImage,
  putUserInfo,
};

import { formApiInstance, jsonApiInstance } from "@/api/index";

const formApi = formApiInstance();
const jsonApi = jsonApiInstance();

async function submitJoinMember(member) {
  try {
    return await formApi.post("/users/join", member);
  } catch (error) {
    console.log(error);
  }
}

async function submitLogin(loginInfo) {
  try {
    return await formApi.post("/users/login", loginInfo);
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

export { submitJoinMember, checkDuplicatedLoginId, submitLogin };

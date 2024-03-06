import { formApiInstance, jsonApiInstance } from "@/api/index";

const formApi = formApiInstance();
const jsonApi = jsonApiInstance();

async function submitJoinMember(member) {
  try {
    return await formApi.post("/users", member);
  } catch (error) {
    return error;
  }
}

async function checkDuplicatedLoginId(loginId) {
  try {
    return await jsonApi.get(`/users/duplicated/${loginId}`);
  } catch (error) {
    return error;
  }
}

export { submitJoinMember, checkDuplicatedLoginId };

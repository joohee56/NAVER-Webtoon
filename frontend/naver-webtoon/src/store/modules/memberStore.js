import { submitLogin } from "@/api/member";

const memberStore = {
  namespaced: true,
  state: {
    loginUser: {
      id: "",
      loginId: "",
      userName: "",
    },
  },
  mutations: {
    SET_LOGIN_USER(state, loginUser) {
      state.loginUser = loginUser;
    },
  },
  actions: {
    async SUBMIT_LOGIN({ commit }, loginIinfo) {
      try {
        const response = await submitLogin(loginIinfo);
        if (response.status === 200) {
          commit("SET_LOGIN_USER", response.data);
        }
        return response;
      } catch (error) {
        console.log(error);
      }
    },
  },
};

export default memberStore;

import { postLogin } from "@/api/member";

const memberStore = {
  namespaced: true,
  state: {
    loginUser: {
      loginId: "",
      userName: "",
    },
  },
  mutations: {
    SET_LOGIN_USER(state, loginUser) {
      state.loginUser = loginUser;
    },
    CLEAR_LOGIN_USER(state) {
      state.loginUser = null;
    },
  },
  actions: {
    async SUBMIT_LOGIN({ commit }, loginIinfo) {
      try {
        const response = await postLogin(loginIinfo);
        commit("SET_LOGIN_USER", response.data);
        console.log(response.data);
        return response;
      } catch (error) {
        console.log(error);
      }
    },
  },
};

export default memberStore;

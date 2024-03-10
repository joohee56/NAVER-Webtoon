import Vue from "vue";
import Vuex from "vuex";
import memberStore from "./modules/memberStore";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    memberStore,
  },
  plugins: [
    //여기에 쓴 모듈만 localStorage에 저장된다.
    createPersistedState({
      paths: ["memberStore"],
    }),
  ],
});

const navStore = {
  namespaced: true,
  state: {
    selectedIndex_dayOfWeek: 0,
    dayOfWeeks: [
      {
        title: "요일전체",
        isActive: true,
      },
      {
        title: "월",
        value: "MONDAY",
        isActive: false,
      },
      {
        title: "화",
        value: "TUESDAY",
        isActive: false,
      },
      {
        title: "수",
        value: "WEDNESDAY",
        isActive: false,
      },
      {
        title: "목",
        value: "THURSDAY",
        isActive: false,
      },
      {
        title: "금",
        value: "FRIDAY",
        isActive: false,
      },
      {
        title: "토",
        value: "SATURDAY",
        isActive: false,
      },
      {
        title: "일",
        value: "SUNDAY",
        isActive: false,
      },
      {
        title: "완결",
        value: "COMPLETE",
        isActive: false,
      },
    ],
    selectedIndex_category: 0,
    categorys: [
      {
        title: "웹툰",
        routerName: "main",
        isActive: true,
      },
      {
        title: "도전만화",
        routerName: "challengeMain",
        isActive: false,
      },
    ],
  },
  mutations: {
    SET_DAY_OF_WEEK_IS_ACTIVE(state, index) {
      state.dayOfWeeks[state.selectedIndex_dayOfWeek].isActive = false;
      state.dayOfWeeks[index].isActive = true;
      state.selectedIndex_dayOfWeek = index;
    },
    SET_CATEGORY_IS_ACTIVE(state, index) {
      state.categorys[state.selectedIndex_category].isActive = false;
      state.categorys[index].isActive = true;
      state.selectedIndex_category = index;
    },
  },
};
export default navStore;

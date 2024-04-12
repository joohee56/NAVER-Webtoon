const dayOfWeekStore = {
  namespaced: true,
  state: {
    selectedIndex: 0,
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
  },
  mutations: {
    SET_IS_ACTIVE(state, index) {
      state.dayOfWeeks[state.selectedIndex].isActive = false;
      state.dayOfWeeks[index].isActive = true;
      state.selectedIndex = index;
    },
  },
};
export default dayOfWeekStore;

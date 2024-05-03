const navStore = {
  namespaced: true,
  state: {
    selectedIndex_category: 0,
    categorys: [
      {
        title: "웹툰",
        routerName: "main",
      },
      {
        title: "도전만화",
        routerName: "challengeMain",
      },
    ],
    selectedIndex_dayOfWeek: 0,
    dayOfWeeks: [
      {
        title: "요일전체",
      },
      {
        title: "월",
        value: "MONDAY",
      },
      {
        title: "화",
        value: "TUESDAY",
      },
      {
        title: "수",
        value: "WEDNESDAY",
      },
      {
        title: "목",
        value: "THURSDAY",
      },
      {
        title: "금",
        value: "FRIDAY",
      },
      {
        title: "토",
        value: "SATURDAY",
      },
      {
        title: "일",
        value: "SUNDAY",
      },
      {
        title: "완결",
        value: "COMPLETE",
      },
    ],
    selected_genre: "ALL",
    genres: {
      ALL: {
        title: "전체",
        value: "ALL",
      },
      ROMANCE: {
        title: "로맨스",
        value: "ROMANCE",
      },
      FANTASY: {
        title: "판타지",
        value: "FANTASY",
      },
      ACTION: {
        title: "액션",
        value: "ACTION",
      },
      DAILY_LIFE: {
        title: "일상",
        value: "DAILY_LIFE",
      },
      COMEDY: {
        title: "개그",
        value: "COMEDY",
      },
      MARTIAL_ARTS_AND_HISTORICAL: {
        title: "무협/사극",
        value: "MARTIAL_ARTS_AND_HISTORICAL",
      },
      THRILLER: {
        title: "스릴러",
        value: "THRILLER",
      },
      SPORTS: {
        title: "스포츠",
        value: "SPORTS",
      },
      DRAMA: {
        title: "드라마",
        value: "DRAMA",
      },
      EMOTION: {
        title: "감성",
        value: "EMOTION",
      },
    },
    selectedIndex_search: 0,
  },
  mutations: {
    SET_CATEGORY_ACTIVE(state, index) {
      state.selectedIndex_category = index;
    },
    SET_DAY_OF_WEEK_ACTIVE(state, index) {
      state.selectedIndex_dayOfWeek = index;
    },
    SET_GENRE_ACTIVE(state, genre) {
      state.selected_genre = genre;
    },
    SET_SEARCH_ACTIVE(state, index) {
      state.selectedIndex_search = index;
    },
  },
};
export default navStore;

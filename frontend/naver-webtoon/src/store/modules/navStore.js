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
    selected_genre: "ALL",
    genres: {
      ALL: {
        title: "전체",
        value: "ALL",
        isActive: true,
      },
      ROMANCE: {
        title: "로맨스",
        value: "ROMANCE",
        isActive: false,
      },
      FANTASY: {
        title: "판타지",
        value: "FANTASY",
        isActive: false,
      },
      ACTION: {
        title: "액션",
        value: "ACTION",
        isActive: false,
      },
      DAILY_LIFE: {
        title: "일상",
        value: "DAILY_LIFE",
        isActive: false,
      },
      COMEDY: {
        title: "개그",
        value: "COMEDY",
        isActive: false,
      },
      MARTIAL_ARTS_AND_HISTORICAL: {
        title: "무협/사극",
        value: "MARTIAL_ARTS_AND_HISTORICAL",
        isActive: false,
      },
      THRILLER: {
        title: "스릴러",
        value: "THRILLER",
        isActive: false,
      },
      SPORTS: {
        title: "스포츠",
        value: "SPORTS",
        isActive: false,
      },
      DRAMA: {
        title: "드라마",
        value: "DRAMA",
        isActive: false,
      },
      EMOTION: {
        title: "감성",
        value: "EMOTION",
        isActive: false,
      },
    },
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
    SET_GENRE_IS_ACTIVE(state, genre) {
      state.genres[state.selected_genre].isActive = false;
      state.genres[genre].isActive = true;
      state.selected_genre = genre;
    },
  },
};
export default navStore;

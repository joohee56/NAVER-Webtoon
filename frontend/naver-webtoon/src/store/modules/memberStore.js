const memberStore = {
  namespaced: true,
  state: {
    loginUser: {
      loginId: "",
      userName: "",
      profileImage: "",
    },
  },
  mutations: {
    SET_LOGIN_USER(state, res) {
      state.loginUser.loginId = res.loginId;
      state.loginUser.userName = res.userName;
    },
    SET_PROFILE_IMAGE(state, profileImage) {
      if (profileImage === null || profileImage === undefined) {
        state.loginUser.profileImage = "default-profile-image.png";
      } else {
        state.loginUser.profileImage = profileImage;
      }
    },
    SET_USER_NAME(state, userName) {
      state.userName = userName;
    },
  },
};

export default memberStore;

const memberStoreCookie = {
  namespaced: true,
  state: {
    loginUser: {
      loginId: "",
      userName: "",
      profileImage: "",
      isProfileImageNull: "",
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
        state.loginUser.isProfileImageNull = true;
      } else {
        state.loginUser.profileImage = profileImage;
        state.loginUser.isProfileImageNull = false;
      }
    },
    SET_USER_NAME(state, userName) {
      state.loginUser.userName = userName;
    },
  },
};

export default memberStoreCookie;

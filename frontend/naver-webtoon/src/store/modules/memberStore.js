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
      if (profileImage === null) {
        state.loginUser.profileImage = "default-profile-image.png";
      } else {
        state.loginUser.profileImage = profileImage;
      }
      if (profileImage === "default-profile-image.png") {
        state.isProfileImageNull = true;
      } else {
        state.isProfileImageNull = false;
      }
    },
    SET_USER_NAME(state, userName) {
      state.loginUser.userName = userName;
    },
  },
};

export default memberStoreCookie;

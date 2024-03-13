<template lang="ko">
	<div class="container">
		<div class="content-wrap">
			<div class="side-bar">

				<div class="header-logo">
          <router-link to="/">N</router-link>
					<span class="title">네이버ID</span>
				</div>

				<div class="user-info">
					<div class="profile-image">
            <div :class={hidden:!isHidden}>
              <img :src="require(`@/assets/image/${loginUser.profileImage}`)">
            </div>
            <div class="preview" :class={hidden:isHidden}>
              <img :src="previewProfileImage">
            </div>
					</div>
					<div class="user-name">
						{{loginUser.userName}}
					</div>
					<div class="user-email">
						{{loginUserEtc.emailAddress}}
					</div>
					<div class="profile-image-btn-wrap">
						<input type="file" id="profile-image" @change="changeProfileImage" ref="image" hidden/>
            <label for="profile-image">사진 변경</label>
            <label @click="deleteProfileImage">삭제</label>
            <label id="submit-btn" @click="submitProfileImage" :class={hidden:isHidden}>적용</label>
					</div>
				</div>

				<div class="menu-footer-wrap">
					<div class="menu">
						<ul>
							<li>
								내프로필
							</li>
						</ul>
					</div>

					<footer>
						<div>
							<span>로그아웃</span> | <span>고객센터</span> 
						</div>
						<div class="footer-logo">
							<span>NAVER</span>
						</div>
					</footer>
				</div>
			</div>
			<div class="main-content">
				왜안되지
			</div>
		</div>
	</div>
</template>

<script>
import { getUserInfo, postProfileImage } from "@/api/member";
import { mapMutations, mapState } from "vuex";

export default {
  data() {
    return {
      loginUserEtc: {
        password: "",
        emailAddress: "",
        birthDate: "",
        gender: "",
        countryResidence: "",
        phoneNumber: "",
      },
      uploadProfileImage: null,
      previewProfileImage: null,
      isHidden: true,
    };
  },
  computed: {
    ...mapState("memberStore", ["loginUser"]),
  },
  watch: {
    previewProfileImage: function (val) {
      console.log(val);
      this.isHidden = val !== null ? false : true;
    },
  },
  async mounted() {
    try {
      const response = await getUserInfo();
      this.loginUserEtc = response.data;
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  },
  methods: {
    ...mapMutations("memberStore", ["SET_PROFILE_IMAGE"]),
    changeProfileImage() {
      console.log("change event 발생");
      this.uploadProfileImage = this.$refs.image.files[0];
      this.previewProfileImage = URL.createObjectURL(this.uploadProfileImage);
    },
    async submitProfileImage() {
      console.log("submit event 발생");

      const formData = new FormData();
      formData.append("profileImage", this.uploadProfileImage);

      try {
        const response = await postProfileImage(formData);
        this.SET_PROFILE_IMAGE(response.data);
        console.log(response.data);
        this.previewProfileImage = null;
      } catch (error) {
        console.log(error);
      }
    },
    deleteProfileImage() {
      console.log("delete image");
    },
  },
};
</script>

<style scoped>
.container {
  background-color: #f9fbfc;
}
.content-wrap {
  width: 1200px;
  margin: 0 auto;
  position: relative;
  display: flex;
}
.content-wrap::after {
  content: "";
  position: absolute;
  top: 0;
  left: -75%;
  right: 0;
  height: 100vh;
  width: 75%;
  background-color: #fff;
}

/* side-bar */
.side-bar {
  height: 100vh;
  background-color: #fff;
  width: 395px;
  box-shadow: 5px 1px 8px 0 rgba(0, 0, 0, 0.06);
  border-left: 1px solid rgba(0, 0, 0, 0.08);
  z-index: 1;
  padding: 0 20px;
}

/* logo */
.header-logo {
  margin-top: 20px;
}

.header-logo a {
  color: #03c75a;
  font-family: "Archivo Black", sans-serif;
  font-weight: 400;
  font-style: normal;
  font-size: 2rem;
}
.header-logo .title {
  font-family: AppleSDGothicNeoEB;
  font-size: 1.7rem;
  padding-left: 5px;
}

/* user-info */
.user-info {
  text-align: center;
}
.profile-image {
  display: flex;
  justify-content: center;
}
.profile-image img {
  width: 120px;
  height: 120px;
  object-fit: cover;
  border-radius: 50%;
  margin-top: 20px;
}
.user-name {
  font-size: 1.8rem;
  font-family: AppleSDGothicNeoB;
}
.user-email {
  color: gray;
}
.profile-image-btn-wrap {
  margin-top: 15px;
}

.profile-image-btn-wrap label {
  margin: 10px 5px;
  padding: 6px 10px;
  border-radius: 7px;
  font-family: AppleSDGothicNeoB;
  font-size: 15px;
  border: solid gray 0.5px;
  background-color: #f9fbfc;
  color: gray;
}
.hidden {
  display: none;
}

#submit-btn {
  background-color: #03c75a;
  color: white;
  border: #03c75a solid 0.5px;
  font-family: AppleSDGothicNeoM;
}

.menu-footer-wrap {
  margin-top: 30px;
  padding: 30px 39px;
}

/* menu */
.menu {
  border-bottom: #d9d9d9 solid 0.5px;
  padding-bottom: 39px;
}

.menu ul {
  font-size: 1.3rem;
  list-style: none;
  font-family: AppleSDGothicNeoL;
  font-weight: 200px;
  padding: 0;
  margin: 0;
  color: black;
}

/* footer */
footer {
  color: #b2b2b2;
  text-align: center;
  margin-top: 39px;
}

.footer-logo {
  font-family: "Archivo Black", sans-serif;
  font-weight: 400;
  font-style: normal;
  font-size: 1.5rem;
  margin-top: 10px;
}

/* main-content */
.main-content {
}
</style>

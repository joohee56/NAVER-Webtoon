<template lang="ko">
	<div class="container">
		<!-- header -->
		<div class="naver-logo">
			<div class="naver-logo-inner">
        <router-link to="/">NAVER</router-link>
			</div>
		</div>

		<!-- form -->
		<div class="join-form">

      <!-- 아이디, 비밀번호, 이메일주소 -->
			<div class="form-section">
				<div class="input-row" :class="{error: idErrorMessage!=''}" id="id-line">
					<i class="fa-regular fa-user icon-cell"></i>
					<input type="text" placeholder="아이디" v-model="user.loginId" @blur="idCheck">
					<div class="id-email">
						@naver.com
					</div>
				</div>
				<div class="input-row" :class="{error: pwErrorMessage!=''}" id="pw-line">
					<i class="fa-solid fa-lock icon-cell"></i>
					<input type="password" id="passwordInput" placeholder="비밀번호" v-model="user.password" @blur="pwCheck">
					<button type="button" class="btn-pw-show" :class="{hide:isHidePassword}" @click="toggleShowPw">
						<span class="blind">비밀번호 보기</span>
					</button>
				</div>
				<div class="input-row" id="email-line">
					<i class="fa-regular fa-envelope icon-cell"></i>
					<input type="text" placeholder="[선택] 이메일주소 (비밀번호 찾기 등 본인 확인용)" v-model="user.emailAddress">
				</div>
			</div>

      <!-- errorMessage -->
			<div class="error-message">
        <ul>
          <div :class="{blind: idErrorMessage==''}">
            <li>{{idErrorMessage}}</li>
          </div>
          <div :class="{blind: pwErrorMessage==''}">
            <li>{{pwErrorMessage}}</li>
          </div>
        </ul>
			</div>

      <!-- 이름, 생년월일, 성별, 거주지역, 휴대전화번호 -->
			<div class="form-section">
				<div class="input-row" id="name-line">
					<i class="fa-regular fa-user icon-cell"></i>
					<input type="text" placeholder="이름" v-model="user.name">
				</div>
				<div class="input-row" id="birth-date-line">
					<i class="fa-regular fa-calendar icon-cell"></i>
					<input type="text" placeholder="생년월일 8자리" v-model="user.birthDate">
				</div>
				<div class="input-row">
					<div class="gender">
						<ul class="gender-inner">
							<li>
								<input type="radio" id="genderM" value="MALE" class="radio-item blind" v-model="user.gender">
								<label for="genderM">남자</label>
							</li>
							<li>
								<input type="radio" id="genderW" value="FEMALE" class="radio-item blind" v-model="user.gender">
								<label for="genderW">여자</label>
							</li>
						</ul>
					</div>
					<div class="foreigner">
						<ul class="foreigner-inner">
							<li>
								<input type="radio" id="foreignerF" value="LOCAL" class="radio-item blind" v-model="user.countryResidence">
								<label for="foreignerF">내국인</label>
							</li>
							<li>
								<input type="radio" id="foreignerT"value="FOREIGNER" class="radio-item blind" v-model="user.countryResidence">
								<label for="foreignerT">외국인</label>
							</li>
						</ul>
					</div>
				</div>
				<div class="input-row">
					<i class="fa-solid fa-mobile-retro icon-cell"></i>
					<input type="tel" placeholder="휴대전화번호" v-model="user.phoneNumber">
				</div>
			</div>

      <!-- errorMessage -->
			<div class="error-message">
        <div v-html="section2ErrorMessage"></div>
			</div>

			<!-- submit button -->
			<button type="button" class="btn-join" @click="joinMember">가입하기</button>
		</div>
	</div>
</template>

<script>
import { postJoinMember, checkDuplicatedLoginId } from "@/api/member";

export default {
  data() {
    return {
      user: {
        loginId: "",
        password: "",
        emailAddress: "",
        name: "",
        birthDate: "",
        gender: "",
        countryResidence: "LOCAL",
        phoneNumber: "",
      },
      title: {
        loginId: "아이디",
        password: "비밀번호",
        emailAddress: "이메일",
        name: "이름",
        birthDate: "생년월일",
        gender: "성별",
        countryResidence: "거주지역",
        phoneNumber: "휴대전화번호",
      },
      idErrorMessage: "",
      pwErrorMessage: "",
      section2ErrorMessage: "",
      isHidePassword: true,
    };
  },
  methods: {
    async joinMember() {
      const pass = this.validateInput();
      if (!pass) {
        return;
      }

      const response = await postJoinMember(this.user);
      console.log(response);
      if (response.status === 200) {
        this.$router.push({
          name: "joinSuccess",
          params: { loginId: response.data.loginId, name: response.data.name },
        });
      } else if (response.status === 400) {
        const data = response.data;

        if (data.code === "VALIDATION") {
          let errorMessage = "";
          for (const key in data.message) {
            errorMessage =
              "<li>" +
              this.title[key] +
              data.message[key] +
              "</li>" +
              errorMessage;
          }
          this.section2ErrorMessage = errorMessage;
        } else if (data.code === "BUSINESS") {
          this.section2ErrorMessage = data.message;
        }
      }
    },
    idCheck() {
      if (this.user.loginId === "") {
        this.idErrorMessage = "아이디: 필수 정보입니다.";
      } else {
        this.checkDuplicatedLoginId();
      }
    },
    pwCheck() {
      if (this.user.password === "") {
        this.pwErrorMessage = "비밀번호: 필수 정보입니다.";
      } else {
        this.pwErrorMessage = "";
      }
    },
    async checkDuplicatedLoginId() {
      try {
        const result = await checkDuplicatedLoginId(this.user.loginId);
        console.log(result.data);
        if (result.data === true) {
          this.idErrorMessage =
            "아이디: 사용할 수 없는 아이디입니다. 다른 아이디를 입력해 주세요.";
          this.isPassedLoginId = false;
        } else {
          this.idErrorMessage = "";
          this.isPassedLoginId = true;
        }
      } catch (error) {
        console.log(error);
      }
    },
    validateInput() {
      if (this.user.loginId == "") {
        this.idCheck();
        return false;
      }
      if (this.user.password == "") {
        this.pwCheck();
        return false;
      }

      let errorMessage = "";
      for (let i = 2; i < this.user.length; i++) {
        if (this.user[i] === "") {
          errorMessage +=
            "<li>" + this.title[i] + " : " + "필수정보입니다.</li>";
        }
      }
      this.section2ErrorMessage = errorMessage;
      if (errorMessage !== "") {
        return false;
      } else {
        return true;
      }
    },
    toggleShowPw() {
      this.isHidePassword = !this.isHidePassword;
      var passwordInput = document.getElementById("passwordInput");
      if (this.isHidePassword) {
        passwordInput.type = "password";
      } else {
        passwordInput.type = "text";
      }
    },
  },
};
</script>

<style scoped>
.color {
  color: #888 #dadada #03c75a #e8f0fe;
}
.container {
  padding: 0;
  margin: 0 auto;
  width: 500px;
  font-size: 15px;
  color: #888;
}
.naver-logo-inner {
  width: 100%;
  height: 30px;
  padding: 19px 0 19px;
}
.naver-logo a {
  font-family: "Archivo Black", sans-serif;
  font-weight: 400;
  font-style: normal;
  color: #03c75a;
  display: inline-block;
  font-size: 1.5rem;
}

.join-form {
  padding: 30px 0 0 0;
}
.form-section {
  margin-bottom: 10px;
}
.input-row {
  display: flex;
  min-height: 50px;
  align-items: center;
  padding-right: 17px;
  box-sizing: border-box;
  width: 100%;
  border: solid #dbdbdb 0.5px;
}

.input-row:first-child {
  border-top-left-radius: 6px;
  border-top-right-radius: 6px;
}
.input-row:last-child {
  border-bottom-left-radius: 6px;
  border-bottom-right-radius: 6px;
}

.input-row:has(input:focus):not(.error) {
  border-color: #03c75a;
}

input[type="text"],
input[type="password"],
input[type="tel"] {
  border: none;
  width: 100%;
}
input::placeholder {
  color: #888;
}
input {
  margin-left: 15px;
  font-size: 16px;
}
input:focus {
  outline: none;
}
.icon-cell {
  color: #dadada;
  font-size: 20px;
  display: block;
  margin-left: 15px;
}
.blind {
  display: none;
}

button {
  background: none;
  border: none;
}
/* id */
.id-email {
  padding-left: 4px;
}
.btn-pw-show::before {
  background-position: -256px -296px;
  width: 30px;
  height: 30px;
  background-image: url(https://ssl.pstatic.net/static/nid/join/m_sp_06_realname_48b1e603.png);
  background-size: 372px 326px;
  display: inline-block;
  content: "";
  cursor: pointer;
}
/* pw */
.btn-pw-show.hide::before {
  background-position: -256px -264px;
  width: 30px;
  height: 30px;
  background-image: url(https://ssl.pstatic.net/static/nid/join/m_sp_06_realname_48b1e603.png);
  background-size: 372px 326px;
  display: inline-block;
  content: "";
  cursor: pointer;
}

/* gender, foreigner */
ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.gender,
.foreigner {
  width: 50%;
  padding-left: 9px;
}
.gender-inner,
.foreigner-inner {
  width: 100%;
  border-spacing: 0;
  display: flex;
}
.gender-inner li,
.foreigner-inner li {
  width: 50%;
  padding: 6px 0;
  text-align: center;
  font-size: 13px;
  border: solid #dbdbdb 0.5px;
}

.gender-inner li:first-child,
.foreigner-inner li:first-child {
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
}

.gender-inner li:last-child,
.foreigner-inner li:last-child {
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
}

.gender-inner li:has(input:checked),
.foreigner-inner li:has(input:checked) {
  border-color: #03c75a;
}

.btn-join {
  display: block;
  background-color: #03c75a;
  color: white;
  width: 100%;
  border-radius: 6px;
  padding: 14px 0;
  font-size: 18px;
  font-weight: 700;
}

/* error message */
.error-message {
  color: red;
  line-height: 25px;
  padding-bottom: 10px;
}
.error-message li::before {
  content: "\2022";
  margin-right: 10px;
}
.input-row.error {
  border-color: red;
}
.input-row.error .icon-cell,
.input-row.error input,
.input-row.error input::placeholder {
  color: red;
  text-decoration: underline;
}
.error-message blind {
  display: none;
}
</style>

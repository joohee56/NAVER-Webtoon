<template lang="ko">
	<div class="container">
		<!-- header -->
		<div class="naver-logo">
			<div class="naver-logo-inner">
				<a href="https://www.naver.com">NAVER</a>
			</div>
		</div>

		<!-- form -->
		<form method="POST" action="" class="join-form">
			<div class="form-section">
				<div class="input-row" id="id-line">
					<i class="fa-regular fa-user icon-cell"></i>
					<input type="text" placeholder="아이디">
					<div class="id-email">
						@naver.com
					</div>
				</div>
				<div class="input-row" id="pw-line">
					<i class="fa-solid fa-lock icon-cell"></i>
					<input type="password" placeholder="비밀번호">
					<div class="pw-check">
						<em class="safe blind">안전</em>
						<em class="dangerous blind">사용불가</em>
					</div>
					<button type="button" class="btn-pw-show hide">
						<span class="blind">비밀번호 보기</span>
					</button>
				</div>
				<div class="input-row" id="email-line">
					<i class="fa-regular fa-envelope icon-cell"></i>
					<input type="text" placeholder="[선택] 이메일주소 (비밀번호 찾기 등 본인 확인용)">
				</div>
			</div>

			<div class="error-message blind">
				<div>아이디: 사용할 수 없는 아이디입니다. 다른 아이디를 입력해 주세요.</div>
				<div>비밀번호: 8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.</div>
				<div>비밀번호: 필수 정보입니다.</div>
			</div>

			<div class="form-section">
				<div class="input-row" id="name-line">
					<i class="fa-regular fa-user icon-cell"></i>
					<input type="text" placeholder="이름">
				</div>
				<div class="input-row" id="birth-date-line">
					<i class="fa-regular fa-calendar icon-cell"></i>
					<input type="text" placeholder="생년월일 8자리">
				</div>
				<div class="input-row">
					<div class="gender">
						<ul class="gender-inner">
							<li>
								<input type="radio" id="genderM"value="M" class="radio-item blind">
								<label for="genderM">남자</label>
							</li>
							<li>
								<input type="radio" id="genderW"value="W" class="radio-item blind">
								<label for="genderW">여자</label>
							</li>
						</ul>
					</div>
					<div class="foreigner">
						<ul class="foreigner-inner">
							<li>
								<input type="radio" id="foreignerF" value="F" class="radio-item blind">
								<label for="foreignerF">내국인</label>
							</li>
							<li>
								<input type="radio" id="foreignerT"value="T" class="radio-item blind">
								<label for="foreignerT">외국인</label>
							</li>
						</ul>
					</div>
				</div>
				<div class="input-row">
					<i class="fa-solid fa-mobile-retro icon-cell"></i>
					<input type="tel" placeholder="휴대전화번호">
				</div>
			</div>

			<div class="error-message blind">
				<div>* 생년월일: 필수 정보입니다.</div>
				<div>* 휴대전화번호: 필수 정보입니다.</div>
			</div>

			<!-- submit button -->
			<button class="btn-join">가입하기</button>
		</form>
	</div>
</template>

<script>
export default {};
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
  border: solid #dbdbdb 0.5px;
  border-radius: 6px;
  margin-bottom: 10px;
}
.input-row {
  display: flex;
  min-height: 50px;
  align-items: center;
  padding-right: 17px;
  box-sizing: border-box;
  width: 100%;
}
.input-row:not(:last-child) {
  border-bottom: solid #dbdbdb 0.5px;
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
  display: table;
  border-collapse: collapse;
  border-spacing: 0;
}
.gender-inner li,
.foreigner-inner li {
  /* display: inline-block; */
  display: table-cell;
  width: 50%;
  padding: 6px 0;
  text-align: center;
  font-size: 13px;
  border-radius: 4px;
  border: solid #dbdbdb 0.5px;
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
</style>

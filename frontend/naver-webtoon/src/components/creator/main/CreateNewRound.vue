<template lang="ko">
	<div class="container">
		<div class="subject">
			신규 회차 등록
		</div>

		<div class="item-box">
			<ul>
				<li class="item-row">
					<p>작품명</p>
					<select class="webtoon-name-select">
						<option value="test" selected>테스트2</option>
					</select>
				</li>
				<li class="item-row">
					<p>회차 번호</p>
					<div>
						<input type="text" disabled placeholder="1">
						<div class="desription">
							<ul>
								<li>회차 번호는 순차적으로 자동 지정되기 때문에 임의로 설정이 불가능합니다.</li>
							</ul>
						</div>
					</div>
				</li>
				<li class="item-row">
					<p>회차명</p>
					<div>
						<input type="text" placeholder="회차명을 입력해 주세요.">
					</div>				
				</li>
			</ul>
		</div>

		<div class="item-box">
			<ul>
				<li class="item-row">
					<p>대표 이미지</p>
					<div>
						<div class="img-item-row">
							<div>회차 대표 이미지</div>
							<div>
								202 x 120
								<label>파일 선택</label>
							</div>
						</div>
						<div class="img-item-row">
							<div>SNS 공유용</div>
							<div>
								600 x 315
								<label>파일 선택</label>
							</div>
						</div>
					</div>
				</li>

				<li class="item-row">
					<p>원고 등록</p>
					<div>
						<div class="manuscript-item-row">
							<div>파일 목록</div>
						</div>
					</div>
				</li>

				<li class="item-row">
					<p>작가의 말</p>
					<div>
						<input type="text" placeholder="작가의 말을 입력해 주세요.">
					</div>
				</li>
			</ul>
		</div>

		<div class="item-box">
			<ul>
				<li class="item-row">
					<p>운영원칙 동의</p>
					<div>
						<input type="checkbox" id="operating-principle">
						<label for="operating-principle">불법 게시물을 등록할 경우, 게시중단 및 삭제될 수 있으며 형사 처분 대상이 될 수 있습니다.</label>
					</div>
				</li>
			</ul>
		</div>

		<div class="btn-wrap">
			<button class="cancel">취소</button>
			<button class="submit">등록</button>
		</div>

	</div>
</template>

<script>
export default {};
</script>

<style scoped>
.container {
  padding: 30px 30px;
  width: 60rem;
}
.subject {
  font-family: AppleSDGothicNeoSB;
  font-size: 1.3rem;
}
.item-box {
  background-color: white;
  border: 1px solid #ebebeb;
  border-radius: 5px;
  margin-top: 10px;
  font-family: AppleSDGothicNeoR;
  padding: 30px 30px;
}
ul {
  padding: 0;
  margin: 0;
  list-style: none;
}
.item-box .item-row {
  display: grid;
  grid-template-columns: 2fr 8fr;
}
.item-row p {
  font-family: AppleSDGothicNeoSB;
  font-size: 17px;
}
.item-row .webtoon-name-select {
  border: 1px solid #ebebeb;
  padding-left: 10px;
  border-radius: 4px;
}
.item-box .item-row:not(:last-child) {
  margin-bottom: 20px;
}
.item-row input[type="text"] {
  padding: 15px 15px;
  border: 1px solid #ebebeb;
  border-radius: 3px;
}
.item-row input::placeholder {
  color: #c5c3c3;
}
.item-row :disabled {
  background-color: white;
}
.item-row .desription {
  color: #b1b1b1;
  font-size: 13px;
  line-height: 18px;
  margin-top: 8px;
}

/* button */
.btn-wrap {
  margin-top: 10px;
  display: flex;
}
.btn-wrap button {
  font-family: AppleSDGothicNeoM;
  border-radius: 4px;
  padding: 13px 30px 10px;
  font-size: 14px;
  align-items: center;
  border: 1px solid rgba(0, 0, 0, 0.06);
  margin-left: 8px;
}
.btn-wrap .cancel {
  margin-left: auto;
  background-color: #e0e0e0;
}
.btn-wrap .submit {
  color: white;
  background-color: #00dc64;
}
</style>

<template lang="ko">
	<div class="create-new-webtoon-container">
		<div class="subject">
			신규 작품 등록
		</div>

    <!-- 운영원칙 -->
		<div class="item-box notice">
			<p><i class="fa-solid fa-bullhorn"></i>운영원칙</p>
			<div class="description">
        저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 게시물은 이용약관 및 관련 법률에 의해 제재를 받으실 수 있습니다. </br>
				성인물, 폭력물 등의 게시물은 통보 없이 삭제될 수 있습니다. <a href="https://comic.naver.com/creators/manage/terms">상세보기</a> >
			</div>
      <div class="agree-btn-wrap">
				<input type="radio" id="operatingPrinciple" v-model="operatingPrinciple" value="AGREE" hidden>
        <label for="operatingPrinciple">동의합니다.</label>
      </div>
		</div>

    <!-- 입력폼 -->
		<div class="item-box input-form">
			<ul>
				<li class="item-row">
					<p ref="name">작품명</p>
          <div class="input-text-wrap">
            <input type="text" placeholder="작품명을 입력해 주세요." v-model="webtoon.name" :class="{violation: webtoon.name.length>inputLimit.name}">
            <span class="input-letter-count">{{webtoon.name.length}} / {{inputLimit.name}}</span>
          </div>
				</li>
				<li class="item-row">
					<p ref="webtoonCategory">형식</p>
					<div class="category">
            <div>
              <input type="radio" id="episode" value="EPISODE" v-model="webtoon.webtoonCategory" hidden>
              <label for="episode">에피소드</label>
            </div>
            <div>
              <input type="radio" id="omnibus" value="OMNIBUS" v-model="webtoon.webtoonCategory" hidden>
              <label for="omnibus">옴니버스</label>
            </div>
            <div>
              <input type="radio" id="story" value="STORY" v-model="webtoon.webtoonCategory" hidden>
              <label for="story">스토리</label>
            </div>
					</div>
				</li>
				<li class="item-row">
					<p ref="genres">장르</p>
					<div class="genre">
            <div>
              <input type="checkbox" id="romance" value="ROMANCE" v-model="webtoon.genres" hidden>
              <label for="romance">로맨스</label>
            </div>
            <div>
              <input type="checkbox" id="fantasy" value="FANTASY" v-model="webtoon.genres" hidden>
              <label for="fantasy">판타지</label>
            </div>
            <div>
              <input type="checkbox" id="action" value="ACTION" v-model="webtoon.genres" hidden>
              <label for="action">액션</label>
            </div>
            <div>
              <input type="checkbox" id="dailyLife"value="DAILY_LIFE" v-model="webtoon.genres" hidden>
              <label for="dailyLife">일상</label>
            </div>
            <div>
              <input type="checkbox" id="thriller" value="THRILLER" v-model="webtoon.genres" hidden>
              <label for="thriller">스릴러</label>
            </div>
            <div>
              <input type="checkbox" id="comedy" value="COMEDY" v-model="webtoon.genres" hidden>
              <label for="comedy">개그</label>
            </div>
            <div>
              <input type="checkbox" id="historical" value="MARTIAL_ARTS_AND_HISTORICAL" v-model="webtoon.genres" hidden>
              <label for="historical">무협/사극</label>
            </div>
            <div>
              <input type="checkbox" id="drama" value="DRAMA" v-model="webtoon.genres" hidden>
              <label for="drama">드라마</label>
            </div>
            <div>
              <input type="checkbox" id="emotion" value="EMOTION" v-model="webtoon.genres" hidden>
              <label for="emotion">감성</label>
            </div>
            <div>
              <input type="checkbox" id="sports" value="SPORTS" v-model="webtoon.genres" hidden>
              <label for="sports">스포츠</label>
            </div>
					</div>
				</li>
				<li class="item-row">
					<p ref="tags">태그</p>
					<div>
            <div class="input-text-wrap">
              <input type="text" placeholder="내 작품을 가장 잘 표현해주는 태그를 입력해 주세요.">
              <span class="input-letter-count">{{webtoon.tags.length}} / 10</span>
            </div>
            <ul class="sub-description">
							<li>
								태그는 최대 10개까지 입력할 수 있으며, 등록한 태그는 작품 폼에서 노출됩니다.	
							</li>
							<li>
								원하는 단어를 입력한 뒤에 쉼표 혹은 엔터키를 입력하시면 태그가 등록됩니다. 
							</li>
						</ul>
					</div>
				</li>
				<li class="item-row">
					<p ref="oneLineSummary">작품 한 줄 요약</p>
					<div>
            <div class="input-text-wrap">
              <input type="text" placeholder="작품을 한 줄로 소개해 주세요." v-model="webtoon.oneLineSummary" :class="{violation: webtoon.oneLineSummary.length>inputLimit.oneLineSummary}">
              <span class="input-letter-count">{{webtoon.oneLineSummary.length}} / {{inputLimit.oneLineSummary}}</span>
            </div>
            <ul class="sub-description">
							<li>
								작품 소개가 필요한 곳에서 노출될 수 있습니다. 
							</li>
						</ul>
					</div>
				</li>
				<li class="item-row">
					<p ref="summary">줄거리</p>
					<div>
            <div class="input-text-wrap">
              <textarea placeholder="작품의 줄거리를 작성해 주세요." v-model="webtoon.summary" :class="{violation: webtoon.summary.length>inputLimit.summary}"></textarea>
              <span class="input-letter-count">{{webtoon.summary.length}} / {{inputLimit.summary}}</span>
            </div>
						<ul class="sub-description">
							<li>
								등록된 줄거리는 작품 폼에서 노출됩니다. 
							</li>
						</ul>
					</div>
				</li>
			</ul>
		</div>

    <!-- 이미지들 -->
		<div class="item-box input-form">
			<ul>
				<li class="item-row">
					<p>대표 이미지</p>
					<div>
						<div class="representative-img-wrap">
							<div class="representative-img-row">
								<div class="title" ref="posterImage">포스터형</div>
								<div class="img-input poster">
                  <!-- 이미지 선택 -->
									<div class="img-wrap" :class={blind:!isPosterSelect}>
										<label for="poster">
											<img :src="previewPoster">
										</label>
										<label class="delete-img">
											<i class="fa-regular fa-trash-can"></i>
										</label>
									</div>
                  <!-- 이미지 미선택  -->
									<div :class={blind:isPosterSelect}>
										<em>480 x 623</em>
										<label class="image-select-btn" for="poster">파일 선택</label>
										<input type="file" id="poster" ref="poster" accept=".jpg, .jpeg" @change="selectPosterImg" hidden>
									</div>
								</div>
							</div>

							<div class="representative-img-row">
								<div class="title" ref="horizontalImage">가로형</div>
								<div class="img-input horizontality">
									<div class="img-wrap" :class={blind:!isHorizontalSelect}>
										<label for="horizontal">
											<img :src="previewHorizontal">
										</label>
										<label class="delete-img">
											<i class="fa-regular fa-trash-can"></i>
										</label>
									</div>
									<div :class={blind:isHorizontalSelect}>
										<em>480 x 623</em>
										<label class="image-select-btn" for="horizontal">파일 선택</label>
										<input type="file" id="horizontal" ref="horizontal" @change="selectHorizontalImg" hidden>
									</div> 
								</div>
							</div>

						</div>

						<ul class="sub-description">
							<li>파일 용량 1MB 이하 / jpg만 업로드 가능</li>
							<li>포스터형은 작품 홈에서 대표 이미지로 활용됩니다. </li>
						</ul>

					</div>
				</li>
			</ul>
		</div>

    <!-- 버튼 -->
		<div class="btn-wrap">
			<button class="cancel">취소</button>
			<button class="submit" @click="createWebtoon">등록</button>
			<button class="submit">등록 후 1화 올리기</button>
		</div>
	</div>
</template>

<script>
import { postCreateWebtoon } from "@/api/webtoon";

export default {
  data() {
    return {
      operatingPrinciple: "",
      webtoon: {
        name: "",
        webtoonCategory: "EPISODE",
        tags: [],
        genres: [],
        oneLineSummary: "",
        summary: "",
        posterImage: null,
        horizontalImage: null,
      },
      inputLimit: {
        name: 30,
        oneLineSummary: 10,
        summary: 400,
      },
      isPosterSelect: false,
      isHorizontalSelect: false,
      previewPoster: null,
      previewHorizontal: null,
    };
  },
  watch: {
    previewPoster: function (val) {
      this.isPosterSelect = val !== null ? true : false;
    },
    previewHorizontal: function (val) {
      this.isHorizontalSelect = val !== null ? true : false;
    },
  },
  methods: {
    async createWebtoon() {
      if (this.validateInput()) {
        return;
      }

      const formData = new FormData();
      for (const key in this.webtoon) {
        formData.append(key, this.webtoon[key]);
      }

      try {
        const response = await postCreateWebtoon(formData);
        console.log(response.data);
        if (response.status === 200) {
          alert("작품 등록이 완료되었습니다. 도전만화에 노출됩니다.");
          this.$router.push({ name: "manage" });
        } else if (response.status === 400) {
          let errorMessage = "";
          for (const key in response.data) {
            errorMessage =
              this.$refs[key].innerText +
              response.data[key] +
              "\n" +
              errorMessage;
          }
          alert(errorMessage);
        }
      } catch (error) {
        console.log(error);
      }
    },
    validateInput() {
      if (this.operatingPrinciple === "") {
        alert("운영원칙 동의가 필요합니다.");
        return true;
      }
      if (this.webtoon.name === "") {
        alert("작품명을 입력해 주세요.");
        return true;
      }
      if (this.webtoon.name.length > this.inputLimit.name) {
        alert("작품명은 " + this.inputLimit.name + "자 이내로 입력해 주세요.");
        return true;
      }
      if (this.webtoon.genres.length === 0) {
        alert("장르를 선택해 주세요.");
        return true;
      }
      if (this.webtoon.oneLineSummary === "") {
        alert("작품 한 줄 요약을 입력해 주세요.");
        return true;
      }
      if (this.webtoon.oneLineSummary.length > this.inputLimit.oneLineSummary) {
        alert(
          "작품 한 줄 요약은 " +
            this.inputLimit.oneLineSummary +
            "자 이내로 입력해 주세요."
        );
        return true;
      }
      if (this.webtoon.summary === "") {
        alert("줄거리를 입력해 주세요.");
        return true;
      }
      if (this.webtoon.summary.length > this.inputLimit.summary) {
        alert(
          "줄거리는 " + this.inputLimit.summary + "자 이내로 입력해 주세요."
        );
        return true;
      }
      if (this.isPosterSelect === false) {
        alert("대표이미지 포스터형을 선택해 주세요.");
        return true;
      }
      if (this.isHorizontalSelect === false) {
        alert("대표이미지 가로형을 선택해 주세요.");
        return true;
      }

      return false;
    },
    selectPosterImg() {
      console.log("img changed");
      this.webtoon.posterImage = this.$refs.poster.files[0];
      this.previewPoster = URL.createObjectURL(this.webtoon.posterImage);
    },
    selectHorizontalImg() {
      console.log("img changed");
      this.webtoon.horizontalImage = this.$refs.horizontal.files[0];
      this.previewHorizontal = URL.createObjectURL(
        this.webtoon.horizontalImage
      );
    },
  },
};
</script>

<style scoped>
.create-new-webtoon-container {
  padding: 30px 30px;
  width: 60rem;
}
.subject {
  font-family: AppleSDGothicNeoSB;
  font-size: 1.3rem;
}
ul,
p {
  padding: 0;
  margin: 0;
}

/* 운영원칙 */
.item-box {
  background-color: white;
  border: 1px solid #ebebeb;
  border-radius: 5px;
  margin-top: 10px;
  font-family: AppleSDGothicNeoR;
}
.item-box p {
  font-family: AppleSDGothicNeoB;
  font-size: 15px;
}
i {
  color: #00dc64;
  margin-right: 5px;
}
.notice {
  padding: 30px 30px;
}
.notice .description {
  font-size: 15px;
  line-height: 22px;
  margin-top: 20px;
}
.notice .description a:hover {
  text-decoration: underline;
}
.notice .agree-btn-wrap {
  margin-top: 20px;
}

/* 입력 폼 */
.input-form {
  padding: 30px;
}
.item-box .item-row {
  display: grid;
  grid-template-columns: 1.7fr 8.3fr;
}
.item-box .item-row:not(:last-child) {
  margin-bottom: 40px;
}
.item-row input[type="text"],
.item-row textarea {
  padding: 13px 15px;
  width: 95%;
  border: 1px solid #e0e0e0;
  border-radius: 3px;
}
.item-row textarea {
  height: 180px;
}
.input-text-wrap {
  position: relative;
}
.item-row input[type="text"]:focus,
.item-row textarea:focus {
  outline: none;
  border: 1px solid #00dc64;
}
.item-row input::placeholder,
.item-row textarea::placeholder {
  color: #b1b1b1;
  font-family: AppleSDGothicNeoR;
  font-size: 14px;
}
.input-letter-count {
  position: absolute;
  right: 20px;
  bottom: 13px;
  font-size: 13px;
  color: #b1b1b1;
}
.violation {
  border: 1px solid red !important;
}

.item-row .category {
  display: grid;
  grid-template-columns: repeat(3, 90px);
  gap: 16px 30px;
  font-size: 15px;
}
input[type="radio"] + label::before {
  content: "";
  display: inline-block;
  width: 18px;
  border-radius: 50%;
  aspect-ratio: 1/1;
  background-color: white;
  border: 0.5px solid #efefef;
  margin-right: 7px;
  vertical-align: middle;
}
/* 체크된 상태일 때의 가상 요소 스타일 정의 */
input[type="radio"]:checked + label::before {
  content: "\2713";
  width: 18px;
  height: 18px;
  border-radius: 50%;
  color: white;
  background-color: #00dc64;
  border: 0.5px solid #efefef;
  margin-right: 7px;
  font-size: 16px;
  text-align: center;
  line-height: 18px;
  font-weight: 600;
}
.item-row .genre {
  display: grid;
  grid-template-columns: repeat(5, 90px);
  gap: 16px 30px;
  font-size: 15px;
}
.genre input[type="checkbox"] + label::before {
  content: "";
  display: inline-block;
  width: 17px;
  border-radius: 3px;
  aspect-ratio: 1/1;
  background-color: #fff;
  border: 1px solid #efefef;
  margin-right: 5px;
  vertical-align: middle;
}
/* 체크된 상태일 때의 가상 요소 스타일 정의 */
.genre input[type="checkbox"]:checked + label::before {
  background-color: #00dc64;
  border: 1px solid #00dc64;
  border-radius: 4px;
  content: "\2713";
  color: white;
  font-size: 17px;
  text-align: center;
  width: 17px;
  height: 17px;
  font-weight: 600;
  line-height: 19px;
}
.item-row .sub-description {
  color: #b1b1b1;
  font-size: 13px;
  line-height: 18px;
  margin-top: 8px;
}
.sub-description li {
  list-style: none;
  line-height: 19px;
}
.sub-description li::before {
  content: "·";
  display: inline-block;
  margin-right: 3px;
  vertical-align: middle;
}

/* 웹툰 대표 이미지 */
.representative-img-wrap {
  display: flex;
  height: 158px;
  column-gap: 12px;
  text-align: center;
}
.representative-img-row .title {
  font-size: 15px;
}
.representative-img-row .image-select-btn {
  margin-top: 10px;
  width: 67px;
  border: 1px solid #e0e0e0;
  border-radius: 3px;
  background-color: #fff;
  font-weight: 500;
  font-size: 13px;
  line-height: 26px;
  color: #999;
  text-align: center;
  cursor: pointer;
  padding: 5px;
}
.representative-img-row .img-wrap {
  width: 100%;
  height: 100%;
}
.representative-img-row img {
  cursor: pointer;
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.representative-img-row .delete-img {
  position: absolute;
  z-index: 1000;
  right: 0;
  bottom: 0;
  cursor: pointer;
  width: 26px;
  height: 26px;
  background-color: rgba(0, 0, 0, 0.324);
  display: flex;
  align-items: center;
  justify-content: center;
}
.delete-img i {
  color: white;
  font-size: 15px;
  margin: 0;
}

.representative-img-row em {
  font-size: 14px;
  line-height: 20px;
  color: #999;
  display: block;
}
.img-input {
  position: relative;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
  background: #f6f6f6;
  border: 1px dashed #e0e0e0;
  border-radius: 3px;
  box-sizing: border-box;
}
.img-input:hover {
  border: 1px dashed #00dc64;
}
.poster {
  aspect-ratio: 480 / 623;
  height: calc(100% - 26px);
  margin-top: 6px;
}
.horizontality {
  aspect-ratio: 434 / 330;
  height: calc(100% - 26px);
  margin-top: 6px;
}
.blind {
  display: none;
}

/* button */
.btn-wrap {
  margin-top: 10px;
  display: flex;
}
.btn-wrap button {
  font-family: AppleSDGothicNeoM;
  border-radius: 4px;
  padding: 13px 20px 10px;
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

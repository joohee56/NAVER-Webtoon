<template lang="ko">
	<div class="create-new-webtoon-container">
		<div class="subject">
			신규 작품 등록
		</div>

		<div class="item-box notice">
			<p><i class="fa-solid fa-bullhorn"></i> 운영원칙</p>
			<div class="description">저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 게시물은 이용약관 및 관련 법률에 의해 제재를 받으실 수 있습니다. </br>
				성인물, 폭력물 등의 게시물은 통보 없이 삭제될 수 있습니다. <a href="#">상세보기 ></a>
			</div>
			<label>
				<input type="radio"> 동의합니다. 
			</label>
		</div>

		<div class="item-box input-form">
			<ul>
				<li class="item-row">
					<p>작품명</p>
					<input type="text" placeholder="작품명을 입력해 주세요." v-model="webtoon.name">
				</li>
				<li class="item-row">
					<p>형식</p>
					<div class="type">
						<label><input type="radio" value="EPISODE" v-model="webtoon.webtoonType">에피소드</label>
						<label><input type="radio" value="OMNIBUS" v-model="webtoon.webtoonType"/>옴니버스</label>
						<label><input type="radio" value="STORY" v-model="webtoon.webtoonType"/>스토리</label>
					</div>
				</li>
				<li class="item-row">
					<p>장르</p>
					<div class="genre">
						<label><input type="checkbox" value="ROMANCE" v-model="webtoon.genres">로맨스</label>
						<label><input type="checkbox" value="FANTASY" v-model="webtoon.genres">판타지</label>
						<label><input type="checkbox" value="ACTION" v-model="webtoon.genres">액션</label>
						<label><input type="checkbox" value="DAILY_LIFE" v-model="webtoon.genres">일상</label>
						<label><input type="checkbox" value="THRILLER" v-model="webtoon.genres">스릴러</label>
						<label><input type="checkbox" value="COMEDY" v-model="webtoon.genres">개그</label>
						<label><input type="checkbox" value="MARTIAL_ARTS_AND_HISTORICAL" v-model="webtoon.genres">무협/사극</label>
						<label><input type="checkbox" value="DRAMA" v-model="webtoon.genres">드라마</label>
						<label><input type="checkbox" value="EMOTION" v-model="webtoon.genres">감성</label>
						<label><input type="checkbox" value="SPORTS" v-model="webtoon.genres">스포츠</label>
					</div>
				</li>
				<li class="item-row">
					<p>태그</p>
					<div>
						<input type="text" placeholder="내 작품을 가장 잘 표현해주는 태그를 입력해 주세요.">
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
					<p>작품 한 줄 요약</p>
					<div>
						<input type="text" placeholder="작품을 한 줄로 소개해 주세요." v-model="webtoon.oneLineSummary">
						<ul class="sub-description">
							<li>
								작품 소개가 필요한 곳에서 노출될 수 있습니다. 
							</li>
						</ul>
					</div>
				</li>
				<li class="item-row">
					<p>줄거리</p>
					<div>
						<textarea placeholder="작품의 줄거리를 작성해 주세요." v-model="webtoon.summary"></textarea>
						<ul class="sub-description">
							<li>
								등록된 줄거리는 작품 폼에서 노출됩니다. 
							</li>
						</ul>
					</div>
				</li>
			</ul>
		</div>

		<div class="item-box input-form">
			<ul>
				<li class="item-row">
					<p>대표 이미지</p>
					<div>

						<div class="representative-img-wrap">

							<div class="representative-img-row">
								<div>포스터형</div>
								<div class="img-input poster">
									<div class="img-wrap" :class={blind:!isPosterSelect}>
										<label for="poster">
											<img :src="previewPoster">
										</label>
										<label class="delete-img">
											<i class="fa-regular fa-trash-can"></i>
										</label>
									</div>
									<div :class={blind:isPosterSelect}>
										<em>480 x 623</em>
										<label class="image-select-btn" for="poster">파일 선택</label>
										<input type="file" id="poster" ref="poster" accept=".jpg, .jpeg" @change="selectPosterImg" hidden>
									</div>
								</div>
							</div>

							<div class="representative-img-row">
								<div>가로형</div>
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
							<li>가로형 대표 이미지를 업로드하시면 기타 이미지가 자동 생성됩니다.</li>
							<li>포스터형은 작품 홈에서 대표 이미지로 활용됩니다. </li>
						</ul>

					</div>
				</li>
			</ul>
		</div>

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
      webtoon: {
        name: "",
        webtoonType: "",
        tags: [],
        genres: [],
        oneLineSummary: "",
        summary: "",
        posterImage: null,
        horizontalImage: null,
      },
      isPosterSelect: false,
      isHorizontalSelect: false,
      previewPoster: null,
      previewHorizontal: null,
    };
  },
  watch: {
    previewPoster: function (val) {
      this.isPosterSelect = val != null ? true : false;
    },
    previewHorizontal: function (val) {
      this.isHorizontalSelect = val != null ? true : false;
    },
  },
  methods: {
    async createWebtoon() {
      console.log(this.webtoon);

      const formData = new FormData();
      for (const key in this.webtoon) {
        formData.append(key, this.webtoon[key]);
      }

      try {
        const response = await postCreateWebtoon(formData);
        console.log(response.data);
        if (response.status === 200) {
          alert(
            "작품 등록이 완료되었습니다. 신규 회차 등록 시, 도전만화에 노출됩니다."
          );
          this.$router.push({ name: "manage" });
        }
      } catch (error) {
        console.log(error);
      }
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
.item-box {
  background-color: white;
  border: 1px solid #ebebeb;
  border-radius: 5px;
  margin-top: 10px;
  font-family: AppleSDGothicNeoR;
}

.notice {
  display: grid;
  row-gap: 20px;
  padding: 30px 30px;
}
.notice .description {
  font-size: 13px;
}

.input-form {
  padding: 40px 30px;
}
.item-box li {
  display: flex;
}
.item-box p {
  font-family: AppleSDGothicNeoSB;
  font-size: 17px;
}
i {
  color: #00dc64;
  margin-right: 5px;
}
.item-box .item-row {
  display: grid;
  grid-template-columns: 2fr 8fr;
}
.item-box .item-row:not(:last-child) {
  margin-bottom: 40px;
}
.item-row input[type="text"],
.item-row textarea {
  padding: 13px 15px;
  width: 95%;
  border: 1px solid #ebebeb;
  border-radius: 3px;
}
.item-row input::placeholder,
.item-row textarea::placeholder {
  color: #b1b1b1;
}
.item-row .sub-description {
  color: #b1b1b1;
  font-size: 13px;
  line-height: 18px;
  margin-top: 8px;
}
.item-row .type,
.item-row .genre {
  display: grid;
  grid-template-columns: repeat(5, 90px);
  gap: 16px 30px;
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

/* 웹툰 대표 이미지 */
.representative-img-wrap {
  display: flex;
  height: 158px;
  column-gap: 12px;
  text-align: center;
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
</style>

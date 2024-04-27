<template lang="ko">
	<div class="container">

		<div class="subject">
			신규 회차 등록
		</div>
		<div class="item-box section1">
			<ul>
				<li class="item-row">
					<p>작품명</p>
					<select class="webtoon-name-select" @change="changeWebtoon" v-model="selectedWebtoonIndex">
						<option v-for="(webtoon, index) in webtoons" :value="index">{{webtoon.webtoonName}}</option>
					</select>
				</li>
				<li class="item-row">
					<p>회차 번호</p>
					<div>
						<div class="round-number">{{roundNumber}}</div>
						<div class="desription">
							<ul>
								<li>회차 번호는 순차적으로 자동 지정되기 때문에 임의로 설정이 불가능합니다.</li>
							</ul>
						</div>
					</div>
				</li>
				<li class="item-row">
					<p>회차명</p>
					<div class="input-text-wrap roundTitle">
						<input type="text" placeholder="회차명을 입력해 주세요." size="30" v-model="round.roundTitle" :class="{violation: round.roundTitle.length>inputLimit.roundTitle}">
            <span class="input-letter-count">{{round.roundTitle.length}} / {{inputLimit.roundTitle}}</span>
					</div>				
				</li>
			</ul>
		</div>

		<div class="item-box section2">
			<ul>

				<li class="item-row">
					<p>대표 이미지</p>
					<div>
						<div class="img-item-row">
							<div class="sub-title">회차 대표 이미지</div>

							<div class="img-input thumbnail">
								<!-- 이미지 선택 -->
								<div :class={blind:!isThumbnailSelect} class="img-wrap">
									<label for="thumbnail" class="thumbnail-img-label">
										<img :src="thumbnailPreview">
									</label>
									<label class="delete-img-btn">
										<i class="fa-regular fa-trash-can"></i>
									</label>
								</div>
								<!-- 이미지 미선택 -->
								<div :class={blind:isThumbnailSelect}>
									<em>202 x 120</em>
									<label for="thumbnail" class="file-select-btn">파일 선택</label>
									<input type="file" id="thumbnail" ref="thumbnail" @change="changeThumbnail" hidden>
								</div>
							</div>

						</div>
					</div>
				</li>

				<li class="item-row">
					<p>원고 등록</p>
					<div class="manuscript-item-wrap">
						
            <!-- 파일목록 -->
						<div class="manuscript-item-row">
							<div class="sub-title">파일 목록</div>
							<div class="file-list">
								<button v-for="(file, index) in round.manuscripts" @click="fileClick(file, index)" :class="{fileSelected:isFileSelected(index)}">{{file.name}}</button>
							</div>
              <div class="file-move-btn-wrap">
                <button><i class="fa-solid fa-arrow-up-short-wide" @click="moveToTheTop"></i></button>
                <button><i class="fa-solid fa-caret-up" @click="moveToOneTop"></i></button>
                <button><i class="fa-solid fa-caret-down" @click="moveToOneBottom"></i></button>
                <button><i class="fa-solid fa-arrow-down-short-wide" @click="moveToTheBottom"></i></button>
              </div>
							<div class="file-list-btn">
								<button>수정</button>
								<button>삭제</button>
								<label for="manuscript" class="upload-file-btn">원고 업로드</label>
								<input type="file" id="manuscript" @change="uploadManuscipt" ref="manuscipt" multiple hidden>
							</div>
							<div class="desription">
								<ul>
									<li>가로 사이즈는 690px만 가능합니다.</li>
									<li>파일 1개 용량 5MB, 총 용량 50MB이하로 제한 / jpg, gif 파일만 업로드 가능</li>
								</ul>
							</div>
						</div>

            <!-- 미리보기 -->
						<div class="manuscript-item-row">
							<div class="sub-title">미리보기</div>
							<div class="preview-img">
								<img :src="filePreview">
							</div>
							<div class="preview-img" hidden>
								<img :src="mergeImagePreview">
							</div>
              <button class="mergeFile-priview-btn" @click="showMergeManuscript">전체 미리보기</button>
						</div>
					</div>

				</li>

				<li class="item-row">
					<p>작가의 말</p>
					<div class="input-text-wrap author-note">
						<input type="text" placeholder="작가의 말을 입력해 주세요." v-model="round.authorNote" :class="{violation: round.authorNote.length>inputLimit.authorNote}"></input>
            <span class="input-letter-count">{{round.authorNote.length}} / {{inputLimit.authorNote}}</span>
					</div>
				</li>
			</ul>
		</div>

		<div class="item-box">
			<ul>
				<li class="item-row">
					<p>운영원칙 동의</p>
					<div class="operating-principle">
						<input type="checkbox" id="operating-principle" v-model="operatingPrinciple" hidden>
						<label for="operating-principle">불법 게시물을 등록할 경우, 게시중단 및 삭제될 수 있으며 형사 처분 대상이 될 수 있습니다.</label>
					</div>
				</li>
			</ul>
		</div>
    
		<div class="btn-wrap">
			<button class="cancel">취소</button>
			<button class="submit" @click="clickSubmitBtn">등록</button>
		</div>

    <!-- 이미지 업로드 진행 중 모달 -->
    <UploadProgress :show="showUploadProgress" @close="showUploadProgress = false">
      <template #header>
        <div class="icon">
          <i class="fa-solid fa-spinner"></i>
        </div>
      </template>
      <template #body>
        <div>이미지 업로드 중입니다.</div>
      </template>
    </UploadProgress>

    <!-- 회차 업로드 모달 -->
    <CancleConfirm :show="showUploadRoundModal" @close="showUploadRoundModal = false">
      <template #header>
        <div v-if="isCheckCreateRound">
          <div>회차를 등록하시겠습니까?</div>
        </div>
        <!-- eslint-disable-next-line-->
        <div v-else-if="isUploadingRound">
          <div>원고 등록 중입니다.</div>
          <div>잠시만 기다려 주세요.</div>
        </div>
        <div v-else-if="isUploadingError">
          <div>업로드 중 오류가 발생했습니다.</div>
        </div>
        <div v-else-if="isUploadDone">
          <div>회차 업로드에 성공하였습니다.</div>
        </div>
      </template>
      <template #body>
        <!-- eslint-disable-next-line-->
        <div v-if="isCheckCreateRound">
          <div class="modal-btn">
            <button @click="showUploadRoundModal = false">취소</button>
            <button class="submit" @click="submit">확인</button>
          </div>
        </div>
        <!-- eslint-disable-next-line-->
        <div v-else-if="isUploadingRound">
          <div class="uploading-modal-description">원고의 세로 길이에 따라 최대 5분까지 소요될 수 있습니다.</div>
        </div>
        <div v-else-if="isUploadingError">
          <div class="modal-btn">
            <button class="error" @click="resetCreateModalStatus">닫기</button>
          </div>
        </div>
        <!-- eslint-disable-next-line-->
        <div v-else-if="isUploadDone">
          <div class="modal-btn">
            <button class="submit" @click="moveToManageRoundView">확인</button>
          </div>
        </div>
      </template>
    </CancleConfirm>

    <!-- 이미지 업로드 완료 모달 -->
    <UploadDone :show="showUploadDone" @close="showUploadDone = false">
      <template #header>
          <i class="upload-done-icon fa-solid fa-circle-check"></i>
          <div class="upload-done-message">
            <div>이미지 업로드 완료되었습니다.</div>
            <div>미리보기를 통해 원고를 확인하세요.</div>
          </div>
      </template>
      <template #body>
        <!-- eslint-disable-next-line-->
        <button class="close-modal-btn" @click="showUploadDone=false">닫기</button>
      </template>
    </UploadDone>

	</div>
</template>

<script>
import { getCreateRoundInfo } from "@/api/webtoon";
import { postRound } from "@/api/round";
import UploadProgress from "../modal/UploadProgress.vue";
import UploadDone from "../modal/UploadDone.vue";
import CancleConfirm from "../modal/CancleConfirm.vue";

export default {
  data() {
    return {
      round: {
        roundTitle: "",
        thumbnail: "",
        manuscripts: [],
        mergeManuscript: "",
        authorNote: "",
      },
      inputLimit: {
        roundTitle: 35,
        authorNote: 100,
      },
      title: {
        roundTitle: "회차명",
        thumbnail: "대표 이미지",
        manuscripts: "원고 파일",
        mergeManuscript: "원고",
        authorNote: "작가의 말",
      }, 
      webtoons: [], //roundNumber, webtoonId, webtoonName
      selectedWebtoonIndex: "0",
      roundNumber: "",
      thumbnailPreview: "",
      isThumbnailSelect: false,
      filePreview: "",
      selectedFileIndex: "",
      mergeImagePreview: "",
      showUploadProgress: false,
      showUploadDone: false,
      height: 0,
      showUploadRoundModal: false,
      isCheckCreateRound: true,
      isUploadingRound: false,
      isUploadingError: false,
      isUploadDone: false,
      operatingPrinciple: false,
    };
  },
  components: {
    UploadProgress,
    UploadDone,
    CancleConfirm
  },
  mounted() {
    this.fetchWebtoonInfo();
  },
  watch: {
    thumbnailPreview: function (val) {
      this.isThumbnailSelect = val != null ? true : false;
    },
  },
  methods: {
    async fetchWebtoonInfo() {
      try {
        const response = await getCreateRoundInfo();
        console.log(response.data);
        this.webtoons = response.data;
        this.roundNumber = this.webtoons[0].roundNumber;
      } catch (error) {
        console.log(error);
      }
    },
    changeWebtoon() {
      this.roundNumber = this.webtoons[this.selectedWebtoonIndex].roundNumber;
    },
    changeThumbnail() {
      console.log("대표 이미지 변경");
      this.round.thumbnail = this.$refs.thumbnail.files[0];
      this.thumbnailPreview = URL.createObjectURL(this.round.thumbnail);
    },
    fileClick(file, index) {
      this.showPreview(file);
      this.selectedFileIndex = index;
    },
    isFileSelected(index) {
      return index === this.selectedFileIndex;
    },
    showPreview(file) {
      this.filePreview = URL.createObjectURL(file);
    },
    moveToTheTop() {
      if(this.selectedFileIndex !== "") {
        const backup = [...this.round.manuscripts];
        this.round.manuscripts = [];
        this.round.manuscripts[0] = backup[this.selectedFileIndex];
        for(let i = 0; i<backup.length; i++) {
          if(i===this.selectedFileIndex) {
            continue;
          }
          this.round.manuscripts.push(backup[i]);
        }
        this.selectedFileIndex = 0;
      }
    }, 
    moveToTheBottom() {
      if(this.selectedFileIndex !== "") {
        const backup = [...this.round.manuscripts];
        this.round.manuscripts = [];
        for(let i = 0; i<backup.length; i++) {
          if(i===this.selectedFileIndex) {
            continue;
          }
          this.round.manuscripts.push(backup[i]);
        }
        this.round.manuscripts[backup.length-1] = backup[this.selectedFileIndex];
        this.selectedFileIndex = backup.length-1;
      }
    },
    moveToOneTop() {
      if(this.selectedFileIndex !== "") {
        const backup = [...this.round.manuscripts];
        const target = Math.max(0, this.selectedFileIndex-1);
        this.round.manuscripts[target] = backup[this.selectedFileIndex];
        this.round.manuscripts[this.selectedFileIndex] = backup[target];

        this.selectedFileIndex = target;
      }
    },
    moveToOneBottom() {
      if(this.selectedFileIndex !== "") {
        const backup = [...this.round.manuscripts];
        const target = Math.min(backup.length-1, this.selectedFileIndex+1);
        this.round.manuscripts[target] = backup[this.selectedFileIndex];
        this.round.manuscripts[this.selectedFileIndex] = backup[target];

        this.selectedFileIndex = target;
      }
    },
    async uploadManuscipt() {
      if(this.$refs.manuscipt.files != null) {
        this.showUploadProgressModal();
        for (const file of this.$refs.manuscipt.files) {
          this.round.manuscripts.push(file);
        }
        this.showUploadDoneModal();
      }
    },
    async showMergeManuscript() {
      await this.mergeImages();
      var imageWin = new Image();        
      imageWin = window.open("", "", "width=700px");   
      imageWin.document.write("<html><body>");         
      imageWin.document.write("<img style='display: block; width: 500px; margin: 30px auto;' src='" + this.mergeImagePreview + "'>");         
      imageWin.document.write("</body><html>");         
      imageWin.document.title = "전체 미리보기";
    },
    showUploadProgressModal() {
      this.showUploadProgress = true;
    },
    showUploadDoneModal() {
      this.showUploadProgress = false;
      this.showUploadDone = true;
    },
    async calculateHeight() {
      this.height = 0;
      for(const file of this.round.manuscripts) {
        const image = await this.changeToImage(file);
        this.height += image.height;
      }
    },
    async mergeImages() {
      var canvas = document.createElement("canvas");
      canvas.width = 690; //가로 690px 고정
      await this.calculateHeight();
      canvas.height = this.height;

      var context = canvas.getContext("2d");
      context.globalCompositeOperation = "source-over";
      var offsetY = 0; //이미지 시작 y축 위치

      for(const file of this.round.manuscripts) {
        const image = await this.changeToImage(file);
        context.drawImage(image, 0, offsetY);
        offsetY += image.height
      }

      // 합쳐진 이미지 보여주기
      const dataURI = canvas.toDataURL("image/jpeg");
      this.mergeImagePreview = dataURI;
    },
    async changeToImage(file) {
      //읽기
      var reader = new FileReader();
      await new Promise((resolve) => {
        reader.readAsDataURL(file); //DataUrl 타입으로 읽기
        reader.addEventListener('load', ()=>{
          resolve();
        });
      });
      var tempImage = new Image(); //drawImage 메서드에 넣기 위해 이미지 객체화
      tempImage.src = reader.result;
      return tempImage;
    },
    clickSubmitBtn() {
      const pass = this.validateInput();
      if (!pass) {
        return;
      }

      this.showUploadRoundModal = true;
    },
    async submit() {
      this.isCheckCreateRound = false;
      this.isUploadingRound = true;

      if(this.mergeImagePreview === "") {
        await this.mergeImages();
      }

      this.changeManuscriptPreviewToFile();
      var formData = new FormData();
      formData.append(
        "webtoonId",
        this.webtoons[this.selectedWebtoonIndex].webtoonId
      );
      formData.append("roundTitle", this.round.roundTitle);
      formData.append("thumbnail", this.round.thumbnail);
      for (const file of this.round.manuscripts) {
        formData.append("manuscripts", file);
      }
      formData.append("mergeManuscript", this.round.mergeManuscript);
      formData.append("authorNote", this.round.authorNote);

      try {
        const response = await postRound(formData);
        console.log(response.data);
        if(response.status === 200) {
          this.isUploadingRound = false;
          this.isUploadDone = true;
        } else if(response.status === 400) {
          let data = response.data;
          if (data.code === "VALIDATION") {
            let errorMessage = "";
            for (const key in data.message) {
              errorMessage = this.title[key] + data.message[key] + "\n" + errorMessage;
            }
            alert(errorMessage);
          } else if(data.code === "BUSINESS") {
            alert(data.message);
          }
          this.isUploadingRound = false;
          this.isUploadingError = true;
        }
      } catch (error) {
        this.isUploadingRound = false;
        this.isUploadingError = true;
        console.log(error);
      }
    },
    validateInput() {
      if (
        !this.validateInputText(
          this.round.roundTitle,
          "회차명",
          this.inputLimit.roundTitle
        )
      ) {
        return false;
      }
      if(this.round.thumbnail === "") {
        alert("대표 이미지를 등록해주세요.");
        return false;
      }
      if (this.round.manuscripts.length === 0) {
        alert("원고를 등록해주세요.");
        return false;
      }
      if (
        !this.validateInputText(
          this.round.authorNote,
          "작가의 말",
          this.inputLimit.authorNote
        )
      ) {
        return false;
      }
      if(!this.operatingPrinciple) {
        alert("운영원칙 동의가 필요합니다.");
        return false;
      }
      
      return true;
    },
    validateInputText(field, fieldName, length) {
      if (field === "") {
        alert(fieldName + "을 입력해 주세요.");
        return false;
      }
      if (field.length > length) {
        alert(fieldName + "은 " + length + "자 이내로 입력해 주세요.");
        return false;
      }
      return true;
    },
    resetCreateModalStatus() {
      this.showUploadRoundModal=false;
      this.isCheckCreateRound=true;
      this.isUploadingError=false;
    },
    moveToManageRoundView() {
      this.showUploadRoundModal = false;
      this.$router.push({name: 'manageRound', params: {webtoonId: this.webtoons[this.selectedWebtoonIndex].webtoonId}});
    },
    changeManuscriptPreviewToFile() {
      var blobBin = atob(this.mergeImagePreview.split(",")[1]); // base64 데이터 디코딩
      var array = [];
      for (var i = 0; i < blobBin.length; i++) {
        array.push(blobBin.charCodeAt(i));
      }
      var myBlob = new Blob([new Uint8Array(array)], { type: "image/jpeg" }); // Blob 생성
      //BLolb -> file로 변경
      const manuscriptFileName = this.webtoons[this.selectedWebtoonIndex].webtoonName + "_" + this.roundNumber + "화_원고" + ".jpeg";
      this.round.mergeManuscript = new File([myBlob], manuscriptFileName);
    },
  },
};
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
/* 박스 */
.item-box {
  background-color: white;
  border: 1px solid #e0e0e0;
  border-radius: 5px;
  margin-top: 10px;
  font-family: AppleSDGothicNeoR;
  padding: 20px 30px;
}
ul {
  padding: 0;
  margin: 0;
  list-style: none;
}
.item-box .item-row {
  display: grid;
  grid-template-columns: 1.5fr 8.5fr;
}
.item-row p {
  font-family: AppleSDGothicNeoSB;
  font-size: 16px;
  margin: 0;
}
.item-box.section1 .item-row:not(:last-child) {
  margin-bottom: 10px;
}
.item-box.section2 .item-row:not(:last-child) {
  margin-bottom: 22px;
}
/* 작품명 */
.item-row .webtoon-name-select {
  border: 1px solid #e0e0e0;
  border-radius: 5px;
  width: 350px;
  font-size: 14px;
  font-family: AppleSDGothicNeoR;
  height: 50px;
  padding-left: 10px;
}

/* 회차 번호 */
.round-number {
  border: 1px solid #e0e0e0;
  border-radius: 5px;
  display: inline-block;
  padding: 10px 35px;
}

.item-row input[type="text"] {
  padding: 13px 15px;
  border: 1px solid #e0e0e0;
  border-radius: 3px;
}
.author-note {
  height: 70px;
  width: 100%;
}
.author-note input {
  width: 88%;
  height: 25px;
}
.author-note .input-letter-count {
  position: absolute;
  font-size: 13px;
  color: #b1b1b1;
  right: 0;
  bottom: 20px;
}
.item-row :disabled {
  background-color: white;
}
.input-text-wrap {
  position: relative;
}
.input-text-wrap.roundTitle {
  width: 330px;
}
.roundTitle .input-letter-count {
  position: absolute;
  right: 20px;
  bottom: 5px;
  font-size: 13px;
  color: #b1b1b1;
}
.violation {
  border: 1px solid red !important;
}
.item-row input[type="text"]:focus,
.item-row textarea:focus {
  outline: none;
  border: 1px solid #00dc64;
}
.item-row .desription {
  color: #b1b1b1;
  font-size: 13px;
  line-height: 18px;
  margin-top: 8px;
}

/* 대표이미지 */
.sub-title {
  color: #898787;
  font-size: 15px;
}
.blind {
  display: none;
}
.img-input {
  position: relative;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: #f6f6f6;
  border: 1px dashed #e0e0e0;
  border-radius: 3px;
  box-sizing: border-box;
}
.img-input:hover {
  border: 1px dashed #00dc64;
}
.thumbnail {
  aspect-ratio: 202 / 120;
  width: 202px;
  /* height: calc(100% - 26px); */
  margin-top: 6px;
}
.img-wrap {
  width: 100%;
  height: 100%;
}
.thumbnail-img-label {
  width: 100%;
  height: 100%;
  display: block;
}
.img-input img {
  cursor: pointer;
  object-fit: cover;
  width: 100%;
  height: 100%;
}
.delete-img-btn {
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
.delete-img-btn i {
  color: white;
  font-size: 15px;
  margin: 0;
}

.img-input em {
  font-size: 14px;
  line-height: 20px;
  color: #999;
  display: block;
}
.file-select-btn {
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

/* 원고 등록 */
.manuscript-item-wrap {
  display: grid;
  grid-template-columns: 1fr 1fr;
  column-gap: 15px;
}
.file-list {
  width: 100%;
  aspect-ratio: 1 / 0.89;
  border: 1px solid #e0e0e0;
  box-sizing: border-box;
}
.file-move-btn-wrap {
  width: 100%;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  height: 40px;
  border-left: 1px solid #e0e0e0;
  border-right: 1px solid #e0e0e0;
  border-bottom: 1px solid #e0e0e0;
  box-sizing: border-box;
}
.file-move-btn-wrap button {
  background-color: #f6f6f6;
  color: #999;
  border: none;
  font-size: 20px;
  cursor: pointer;
}
.file-list button {
  background: white;
  display: block;
  font-family: "AppleSDGothicNeoSB";
  font-size: 15px;
  color: #7f7f7f;
  padding: 7px;
  width: 100%;
  border: none;
  text-align: left;
  padding-left: 5px;
}
.file-list .fileSelected {
  background-color: rgba(0, 220, 100, 0.2);
}

.file-list button:first-child {
  margin-top: 10px;
}
.file-list-btn {
  margin-top: 10px;
  display: flex;
}
.file-list-btn button {
  background-color: #f6f6f6;
  border: #ebebeb 1px solid;
  font-family: AppleSDGothicNeoSB;
  font-size: 15px;
  padding: 0px 20px;
  border-radius: 5px;
  margin-right: 8px;
}
.upload-file-btn {
  font-family: AppleSDGothicNeoSB;
  font-size: 15px;
  padding: 10px 30px;
  border-radius: 6px;
  color: white;
  background-color: #00dc64;
  border: none;
  margin-left: auto;
}
.mergeFile-priview-btn {
  font-family: AppleSDGothicNeoSB;
  font-size: 15px;
  padding: 10px 30px;
  border-radius: 6px;
  color: white;
  background-color: #00dc64;
  border: none;
  margin-top: 10px;
  margin-left: auto;
  display: block;
}

.preview-img {
  width: 100%;
  aspect-ratio: 1 / 1;
  border: 1px solid #e0e0e0;
  background-image: linear-gradient(45deg, #efefef 25%, transparent 25%),
    linear-gradient(-45deg, #efefef 25%, transparent 25%),
    linear-gradient(45deg, transparent 75%, #efefef 75%),
    linear-gradient(-45deg, transparent 75%, #efefef 75%);
  overflow: auto;
  background-size: 20px 20px;
  background-position: 0 0, 0 10px, 10px -10px, -10px 0;
}
.preview-img img {
  width: 100%;
  vertical-align: top;
}

/* 운영원칙 동의 */
.operating-principle input[type="checkbox"] + label::before {
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
.operating-principle input[type="checkbox"]:checked + label::before {
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

/* 이미지 업로드 완료 모달 */
.upload-done-icon {
  color: #00dc64;
  font-size: 45px;
}
.upload-done-message {
  font-family: "AppleSDGothicNeoSB";
  font-size: 20px;
  margin-top: 13px;
}
.close-modal-btn {
  background-color: #f6f6f6;
  border: 1px solid #ebebeb;
  padding: 13px 27px;
  line-height: 20px;
  box-sizing: border-box;
  cursor: pointer;
  border-radius: 5px;
  font-family: "AppleSDGothicNeoM";
  font-size: 17px;
}

/* 회차 업로드 모달 */
.modal-btn button {
  font-family: AppleSDGothicNeoM;
  border-radius: 4px;
  padding: 13px 30px 10px;
  font-size: 16px;
  align-items: center;
  border: 1px solid rgba(0, 0, 0, 0.06);
  margin-left: 8px;
}
.modal-btn .submit {
  color: white;
  background-color: #00dc64;
}
.modal-btn .error {
  border-color: #ff5151;
  background-color: #ff51519a;
}
.uploading-modal-description {
  color: #c9c9c9;
  margin-bottom: 20px;
}
</style>

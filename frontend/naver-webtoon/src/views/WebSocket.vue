<template lang="ko">
	<div>
		websocket
	</div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  data() {
    return {
      rankings: [],
    };
  },
  created() {
    //해당 뷰가 생성되면 소켓 연결을 시도
    this.connect();
  },
  methods: {
    connect() {
      const serverURL = "http://localhost:8081/websocket";
      const socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소 : ${serverURL}`);
      //웹 소켓 연결
      this.stompClient.connect(
        {},
        (frame) => {
          //소켓 연결 성공
          this.connected = true;
          console.log("소켓 연결 성공", frame);

          //서버 구독
          this.stompClient.subscribe("/send", (res) => {
            console.log("웹소켓을 통해 받은 메시지", res.body);
            this.rankings = JSON.parse(res.body);
          });
        },
        (error) => {
          //소켓 연결 실패
          console.log("소켓 연결 실패", error);
          this.connected = false;
        }
      );
    },
  },
};
</script>

<style scoped></style>

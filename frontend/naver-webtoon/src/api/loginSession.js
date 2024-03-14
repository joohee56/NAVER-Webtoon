import Cookies from "js-cookie";

//세션 체크 로직 시작
const startSessionCheck = function () {
  console.log("startSessioncheck()");
  setTimeOffsetBetweenServerAndClient();
  checkSessionExpired();
};

//서버와 클라이언트의 시간차 계산 후 쿠키 생성
const setTimeOffsetBetweenServerAndClient = function () {
  let latestAccess = Cookies.get("latestAccess");
  latestAccess = latestAccess == undefined ? undefined : Math.abs(latestAccess);
  const clientTime = new Date().getTime();
  const clientTimeOffSet = clientTime - latestAccess;
  Cookies.set("clientTimeOffset", clientTimeOffSet);
};

//세션 만료 체크
const checkSessionExpired = function () {
  const sessionExpiry = Math.abs(Cookies.get("sessionExpiry"));
  let timeOffSet = Math.abs(Cookies.get("clientTimeOffset"));
  let localTime = new Date().getTime() - timeOffSet;

  let isExpired = localTime > sessionExpiry ? true : false;
  if (isExpired === true) {
    Cookies.remove("loginUser");
    location.href = "http://localhost:8080/login/main";
    return;
  } else {
    setTimeout(checkSessionExpired, 10 * 1000); //10초에 한번씩 타이머 설정
  }
};

export { startSessionCheck };

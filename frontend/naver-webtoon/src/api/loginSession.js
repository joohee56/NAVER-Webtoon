import Cookies from "js-cookie";

//세션 체크 로직 시작
const startSessionCheck = function () {
  setInterval(checkSessionExpired, 10 * 1000); //10초마다 체크
};

const checkSessionExpired = function () {
  const sessionExpiry = Math.abs(Cookies.get("sessionExpiry"));
  console.log(sessionExpiry);
  let localTime = new Date().getTime();
  console.log(new Date().getTime());

  let isExpired = localTime > sessionExpiry ? true : false;
  if (isExpired === true) {
    clearInterval(checkSessionExpired);
    Cookies.remove("loginUser");
    location.reload();
    alert("유효시간이 초과되었습니다. 다시 시도해주세요.");
  }
};

export { startSessionCheck };

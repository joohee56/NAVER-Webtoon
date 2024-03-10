import { formApiInstance, jsonApiInstance } from "@/api/index";

const formApi = formApiInstance();
const jsonApi = jsonApiInstance();

const setTimeOffsetBetweenServerAndClient = function () {
  let latestAccess = getCookie("latestAccess");
  latestAccess = latestAccess == undefined ? undefined : Math.abs(latestAccess);
  const clientTime = new Date().getTime();
  const clientTimeOffSet = clientTime - latestAccess;
  setCookie("clientTimeOffset", clientTimeOffSet);
};

const checkSessionExpired = async function () {
  const sessionExpiry = Math.abs(getCookie("sessionExpiry"));
  let timeOffSet = Math.abs(getCookie("clientTimeOffset"));
  let localTime = new Date().getTime() - timeOffSet;

  console.log("현재 시간:" + localTime);
  console.log("세션 만료 시간: " + sessionExpiry);
  let isExpired = localTime > sessionExpiry ? true : false;
  if (isExpired === true) {
    alert("장기간 사용하지 않아 세션이 만료되었습니다. 다시 로그인해주세요.");
    // go login();
  } else {
    console.log(isExpired);
    setTimeout(checkSessionExpired, 10 * 1000); //10초에 한번씩 타이머 설정
  }
};

// 주어진 이름의 쿠키를 반환
// 조건에 맞는 쿠키가 없다면 undefined를 반환한다
const getCookie = function (name) {
  const reg = "/([.$?*|{}()[]\\/+^])/g";
  let matches = document.cookie.match(
    new RegExp("(?:^|; )" + name.replace(reg, "\\$1") + "=([^;]*)")
  );
  return matches ? decodeURIComponent(matches[1]) : undefined;
};

function setCookie(name, value, options = {}) {
  options = {
    path: "/", // 경로 지정
    ...options,
  };

  if (options.expires instanceof Date) {
    options.expires = options.expires.toUTCString();
  }

  let updatedCookie =
    encodeURIComponent(name) + "=" + encodeURIComponent(value);

  for (let optionKey in options) {
    updatedCookie += "; " + optionKey;
    let optionValue = options[optionKey];
    if (optionValue !== true) {
      updatedCookie += "=" + optionValue;
    }
  }

  document.cookie = updatedCookie; // 새로 갱신
}

async function postJoinMember(member) {
  try {
    return await formApi.post("/users/join", member);
  } catch (error) {
    console.log(error);
  }
}

async function postLogin(loginInfo) {
  try {
    const response = await formApi.post("/users/login", loginInfo);
    setTimeOffsetBetweenServerAndClient();
    checkSessionExpired();
    return response;
  } catch (error) {
    console.log(error);
  }
}

async function checkDuplicatedLoginId(loginId) {
  try {
    return await jsonApi.get(`/users/duplicated/${loginId}`);
  } catch (error) {
    console.log(error);
  }
}

export { postJoinMember, checkDuplicatedLoginId, postLogin };

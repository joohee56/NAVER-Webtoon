import Vue from "vue";
import VueRouter from "vue-router";
import Cookies from "js-cookie";

// creator
import CreatorView from "@/views/creator/CreatorView";
import ManageWebtoon from "@/components/creator/main/ManageWebtoon";
import ManageRound from "@/components/creator/main/ManageRound";
import ManageComment from "@/components/creator/main/ManageComment";
import ManageAlarm from "@/components/creator/main/ManageAlarm";
import CreateNewWebtoon from "@/components/creator/main/CreateNewWebtoon";
import CreateNewOfficialWebtoon from "@/components/creator/main/CreateNewOfficialWebtoon";
import CreateNewRound from "@/components/creator/main/CreateNewRound";
import EditWebtoon from "@/components/creator/main/EditWebtoon.vue";
// user
import LoginView from "@/views/user/LoginView";
import JoinView from "@/views/user/JoinView";
import JoinSuccess from "@/views/user/JoinSuccess.vue";
import UserProfile from "@/views/user/UserProfile";
//webtoon
import WebtoonView from "@/views/webtoon/WebtoonView";
// official
import OfficialView from "@/views/webtoon/official/OfficialView";
import MainView from "@/views/webtoon/official/MainView";
import OfficialRoundList from "@/views/webtoon/official/OfficialRoundList";
import OfficialRoundDetail from "@/views/webtoon/official/OfficialRoundDetail";
import DayOfWeekView from "@/views/webtoon/official/DayOfWeekView.vue";
// challenge
import ChallengeView from "@/views/webtoon/challenge/ChallengeView.vue";
import ChallengeMain from "@/views/webtoon/challenge/ChallengeMain";
import ChallengeRoundList from "@/views/webtoon/challenge/ChallengeRoundList";
import ChallengeRoundDetail from "@/views/webtoon/challenge/ChallengeRoundDetail";
import GenreView from "@/views/webtoon/challenge/GenreView";
// search
import SearchResult from "@/views/webtoon/search/SearchResult";
import WholeResult from "@/components/webtoon/search/WholeResult";
import WebtoonWhole from "@/components/webtoon/search/WebtoonWhole";

Vue.use(VueRouter);

const checkLogin = (to, from, next) => {
  let redirectToLogin = false;
  if (Cookies.get("loginUser") === undefined) {
    redirectToLogin = true;
  }

  if (!redirectToLogin) {
    const loginId = JSON.parse(Cookies.get("loginUser")).memberStore.loginUser
      .loginId;
    if (loginId === "") {
      redirectToLogin = true;
    }
  }

  if (redirectToLogin) {
    console.log("HERE");
    next({
      name: "login",
      params: { redirectUrl: from.path },
    });
  } else {
    next();
  }
};
const checkUndeveloped = (to, from, next) => {
  alert("개발 진행 중인 기능입니다.");
  next(false);
};

const routes = [
  {
    path: "/",
    name: "home",
    component: WebtoonView,
    redirect: "/main",
    children: [
      {
        path: "/official",
        name: "official",
        component: OfficialView,
        children: [
          {
            path: "/main",
            name: "main",
            component: MainView,
          },
          {
            path: "/dayOfWeek/:dayOfWeek",
            name: "dayOfWeek",
            component: DayOfWeekView,
          },
          {
            path: "/webtoon/:webtoonId",
            name: "officialRoundList",
            component: OfficialRoundList,
          },
          {
            path: "/webtoon/:webtoonId/rounds/:roundId",
            name: "officialRoundDetail",
            component: OfficialRoundDetail,
          },
        ],
      },
      {
        path: "/challenge",
        name: "challenge",
        component: ChallengeView,
        children: [
          {
            path: "/challenge/main",
            name: "challengeMain",
            component: ChallengeMain,
          },
          {
            path: "/challenge/genres/:genre",
            name: "genreView",
            component: GenreView,
          },
          {
            path: "/challenge/:webtoonId",
            name: "challengeRoundList",
            component: ChallengeRoundList,
          },
          {
            path: "/challenge/:webtoonId/rounds/:roundId",
            name: "challengeRoundDetail",
            component: ChallengeRoundDetail,
          },
        ],
      },
      {
        path: "/searchResult",
        name: "searchResult",
        component: SearchResult,
        children: [
          {
            path: "/search/wholeResult/:keyword",
            name: "wholeResult",
            component: WholeResult,
          },
          {
            path: "/search/webtoonResult/:webtoonType/:keyword",
            name: "webtoonWhole",
            component: WebtoonWhole,
          },
        ],
      },
    ],
  },
  {
    path: "/login/:redirectUrl",
    name: "login",
    component: LoginView,
  },
  {
    path: "/join",
    name: "join",
    component: JoinView,
  },
  {
    path: "/join/success/:loginId/:name",
    name: "joinSuccess",
    component: JoinSuccess,
  },
  {
    path: "/profile",
    name: "userProfile",
    beforeEnter: checkLogin,
    component: UserProfile,
  },
  {
    path: "/creators/dashboard",
    name: "creatorDashboard",
    component: CreatorView,
    redirect: "/creators/manage",
    children: [
      {
        path: "/creators/manage",
        name: "manage",
        beforeEnter: checkLogin,
        component: ManageWebtoon,
      },
      {
        path: "/creators/comment",
        name: "manageComment",
        beforeEnter: checkUndeveloped,
        component: ManageComment,
      },
      {
        path: "/creators/alarm",
        name: "manageAlarm",
        beforeEnter: checkUndeveloped,
        component: ManageAlarm,
      },
      {
        path: "/creators/manage/round/:webtoonId/:webtoonType",
        name: "manageRound",
        beforeEnter: checkLogin,
        component: ManageRound,
      },
      {
        path: "/creators/manage/new/webtoon",
        name: "createNewWebtoon",
        beforeEnter: checkLogin,
        component: CreateNewWebtoon,
      },
      {
        path: "/creators/manage/new/webtoon/official",
        name: "createNewOfficialWebtoon",
        beforeEnter: checkLogin,
        component: CreateNewOfficialWebtoon,
      },
      {
        path: "/creators/manage/new/round/:webtoonId",
        name: "createNewRound",
        beforeEnter: checkLogin,
        component: CreateNewRound,
      },
      {
        path: "/creators/manage/edit/webtoon/:webtoonId",
        name: "editWebtoon",
        beforeEnter: checkLogin,
        component: EditWebtoon,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

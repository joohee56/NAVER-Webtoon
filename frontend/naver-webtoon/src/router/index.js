import Vue from "vue";
import VueRouter from "vue-router";
import Cookies from "js-cookie";

import WebtoonView from "@/views/WebtoonView";
import LoginView from "@/views/LoginView";
import MainView from "@/views/MainView";
import JoinView from "@/views/JoinView";
import JoinSuccess from "@/views/JoinSuccess.vue";
import UserProfile from "@/views/UserProfile";
import CreatorView from "@/views/CreatorView";
import ChallengeView from "@/views/ChallengeView.vue";
import ManageWebtoon from "@/components/creator/main/ManageWebtoon";
import ManageComment from "@/components/creator/main/ManageComment";
import ManageAlarm from "@/components/creator/main/ManageAlarm";
import ManageRound from "@/components/creator/main/ManageRound";
import CreateNewWebtoon from "@/components/creator/main/CreateNewWebtoon";
import CreateNewRound from "@/components/creator/main/CreateNewRound";
import OfficialRoundList from "@/views/OfficialRoundList";
import RoundDetail from "@/views/RoundDetail";
import DayOfWeekView from "@/views/DayOfWeekView.vue";
import OfficialView from "@/views/OfficialView";
import ChallengeMain from "@/views/ChallengeMain";
import GenreView from "@/views/GenreView";
import ChallengeRoundList from "@/views/ChallengeRoundList";

Vue.use(VueRouter);

const checkLogin = (to, from, next) => {
  const loginUser = Cookies.get("loginUser");
  if (loginUser === undefined) {
    next({
      name: "login",
      params: { redirectUrl: from.path },
    });
  } else {
    next();
  }
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
            name: "roundDetail",
            component: RoundDetail,
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
            path: "/challenge/:genre",
            name: "genreView",
            component: GenreView,
          },
          {
            path: "/challenge/:webtoonId",
            name: "challengeRoundList",
            component: ChallengeRoundList,
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
    beforeEnter: checkLogin,
    component: CreatorView,
    redirect: "/creators/manage",
    children: [
      {
        path: "/creators/manage",
        name: "manage",
        component: ManageWebtoon,
      },
      {
        path: "/creators/comment",
        name: "manageComment",
        component: ManageComment,
      },
      {
        path: "/creators/alarm",
        name: "manageAlarm",
        component: ManageAlarm,
      },
      {
        path: "/creators/manage/round/:webtoonId",
        name: "manageRound",
        component: ManageRound,
      },
      {
        path: "/creators/manage/new/webtoon",
        name: "createNewWebtoon",
        component: CreateNewWebtoon,
      },
      {
        path: "/creators/manage/new/round",
        name: "createNewRound",
        component: CreateNewRound,
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

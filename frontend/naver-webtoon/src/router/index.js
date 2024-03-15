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
Vue.use(VueRouter);

const checkLogin = (to, from, next) => {
  const loginUser = Cookies.get("loginUser");
  if (loginUser === undefined) {
    next({ name: "login", params: { redirectUrl: "main" } });
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
        path: "/main",
        name: "main",
        component: MainView,
      },
      {
        path: "/challenge",
        name: "challenge",
        component: ChallengeView,
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
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

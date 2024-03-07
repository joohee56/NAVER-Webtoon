import Vue from "vue";
import VueRouter from "vue-router";
import WebtoonView from "@/views/WebtoonView";
import LoginView from "@/views/LoginView";
import MainView from "@/views/MainView";
import JoinView from "@/views/JoinView";
import JoinSuccess from "@/views/JoinSuccess.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: WebtoonView,
    redirect: "/main",
    children: [
      {
        path: "/main",
        name: "main",
        component: MainView,
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
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

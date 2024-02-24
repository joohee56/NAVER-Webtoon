import Vue from "vue";
import VueRouter from "vue-router";
import WebtoonView from "@/views/WebtoonView";
import LoginView from "@/views/LoginView";
import MainView from "@/views/MainView";
import JoinView from "@/views/JoinView";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: WebtoonView,
    children: [
      {
        path: "/",
        components: {
          subView: MainView,
        },
      },
    ],
  },
  {
    path: "/login",
    component: LoginView,
  },
  {
    path: "/join",
    component: JoinView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

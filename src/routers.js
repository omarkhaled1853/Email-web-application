import SignUP from "./components/SignUP.vue";
import LogIn from "./components/LogIn.vue";
import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    name: "LogIn",
    component: LogIn,
    path: "/", // Corrected from "Path"
  },
  {
    name: "SignUp",
    component: SignUP,
    path: "/Sign-UP", // Corrected from "Path"
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

import SignUP from "./components/SignUP.vue";
import LogIn from "./components/LogIn.vue";
import HoMe from './components/Home.vue';
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
  {
    name:'HoMe',
    component:HoMe,
    path:'/Home'
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

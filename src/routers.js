import SignUP from "./components/SignUP.vue";
import LogIn from "./components/LogIn.vue";
import HoMe from './components/Home.vue';
import ContaCts from './components/Contacts.vue'
import FolDers from './components/Folders.vue'
import DrAft from './components/Draft.vue'
import SeNt from './components/Sent.vue'
import TrAsh from './components/Trash.vue'
import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    name: "LogIn",
    component: LogIn,
    path: "/", 
  },
  {
    name: "SignUp",
    component: SignUP,
    path: "/Sign-UP", 
  },
  {
    name:'HoMe',
    component:HoMe,
    path:'/Home'
  },
  {
    name:'ContaCts',
    component:ContaCts,
    path:'/Contacts'
  },
  {
    name:'FolDers',
    component:FolDers,
    path:'/Folder'
  },
  {
    name:'DrAft',
    component:DrAft,
    path:'/Draft'

  },
  {
    name:'SeNt',
    component:SeNt,
    path:'/Sent'

  },
  {
    name:'TrAsh',
    component:TrAsh,
    path:'/Trash'
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

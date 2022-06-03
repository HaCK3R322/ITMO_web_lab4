import { createRouter, createWebHashHistory } from 'vue-router'
import Auth from '../views/Auth.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Auth,
    beforeEnter: (to, from, next) => {
      if (localStorage.getItem("jwtToken") !== null) next({name: "app"})
      else next("auth");
    }
  },
  {
    path: '/auth',
    name: 'auth',
    component: Auth
  },
  {
    path: '/app',
    name: 'app',
    component: () => import('../views/Main.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router

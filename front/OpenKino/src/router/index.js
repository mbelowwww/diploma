import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'PageMain',
    component: () => import('../views/PageMain.vue')
  },
  {
    path: '/films',
    name: 'PageFilms',
    component: () => import('../views/PageFilms.vue')
  },
  {
    path: '/news',
    name: 'PageNews',
    component: () => import('../views/PageNews.vue')
  },
  {
    path: '/sessions',
    name: 'PageSessions',
    component: () => import('../views/PageSessions.vue')
  },
  {
    path: '/admin',
    name: 'PageAdmin',
    component: () => import('../views/PageAdmin.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

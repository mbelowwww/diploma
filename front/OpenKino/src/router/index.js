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
    component: () => import('../views/PageAdmin.vue'),
    meta: {
      isAdmin: true
    }
  },
  {
    path: '/userCabinet',
    name: 'PageUserCabinet',
    component: () => import('../views/PageUserCabinet.vue'),
    meta: {
      isAdmin: true
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// router.beforeEach((to, before, next) => {
//   if (to.matched.some(record => record.meta.isAdmin)) {
//     next({ path: '/admin' })
//   } else {
//     next({ path: '/' })
//   }
// })

export default router

import Vue from 'vue'
import Vuex from 'vuex'
import auth from '../store/modules/authorization.js'
import films from '../store/modules/films'
import ban from '../store/modules/ban'
import halls from '../store/modules/halls'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    auth, films, ban, halls
  }
})

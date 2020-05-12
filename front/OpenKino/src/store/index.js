import Vue from 'vue'
import Vuex from 'vuex'
import auth from '../store/modules/authorization.js'
import films from '../store/modules/films'
import ban from '../store/modules/ban'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    auth, films, ban
  }
})

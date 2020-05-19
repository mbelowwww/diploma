import { sessions } from '../../_api/api-urls'
import request from '../../_api/api-headers'

const sessionsModule = {
  namespaced: true,
  state: {
    listTypes: []
  },
  mutations: {
    setListTypes (state, data) {
      state.listTypes = data
    }
  },
  actions: {
    createSession (_, data) {
      return request().post(sessions, data)
    },
    getListTypes ({ commit }) {
      return request().get(sessions.LIST_TYPES).then((response) => {
        commit('setListTypes', response.data)
      })
    },
    addType (_, data) {
      return request().post(sessions.LIST_TYPES, data)
    }
  }
}

export default sessionsModule

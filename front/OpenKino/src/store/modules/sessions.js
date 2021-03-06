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
      return request().post(sessions.CREATE_SESSIONS, data)
    },
    getListTypes ({ commit }) {
      return request().get(sessions.LIST_TYPES).then((response) => {
        commit('setListTypes', response.data)
        return response.data
      })
    },
    addType (_, data) {
      return request().post(sessions.LIST_TYPES, data)
    },
    getListSessions (_, data) {
      return request().put(sessions.LIST_SESSION_TIME, data).then((response) => {
        return response
      })
    }
  }
}

export default sessionsModule

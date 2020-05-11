import { ban } from '../../_api/api-urls'
import request from '../../_api/api-headers'

const banModule = {
  namespaced: true,
  state: {

  },
  mutations: {

  },
  actions: {
    toBan (_, data) {
      return request().post(ban.TO_BAN, data).then((response) => {
        return response
      })
    },
    toNoBan (_, id) {
      return request().put(ban.TO_BAN + `/${id}`)
    }
  }
}

export default banModule

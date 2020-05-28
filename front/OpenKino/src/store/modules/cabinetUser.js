import { cabinetUser } from '../../_api/api-urls'
import request from '../../_api/api-headers'

const cabinetUserModule = {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    addCard (_, data) {
      return request().post(cabinetUser.ADD_CARD)
    },
    getListCards ({ commit }) {
      return request().get(cabinetUser.LIST_CARDS).then((response) => {
        return response.data
      })
    },
    getCardById (_, id) {
      return request().get(cabinetUser.ADD_CARD + '/id').then((response) => {
        return response.data
      })
    },
    donateCard (_, data) {
      return request().put(cabinetUser.ADD_CARD, {
        params: {
          numberCard: data.numberCard,
          money: data.money
        }
      }).then((response) => {
        return response
      })
    }
  }
}

export default cabinetUserModule

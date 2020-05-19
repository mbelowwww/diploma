import { halls } from '../../_api/api-urls'
import request from '../../_api/api-headers'

const hallsModule = {
  namespaced: true,
  state: {

  },
  mutations: {

  },
  actions: {
    createHall (_, data) {
      return request().post(halls.CREATE_HALL, data).then((response) => {
        return response
      })
    },
    getListHalls (_) {
      return request().get(halls.GET_ALL_HALLS).then((response) => {
        return response.data
      })
    },
    getHallById (_, id) {
      return request().get(halls.CREATE_HALL, {
        params: {
          idHall: id
        }
      }).then((response) => {
        return response.data
      })
    }
  }
}

export default hallsModule

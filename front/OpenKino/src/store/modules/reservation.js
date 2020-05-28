import { reservation } from '../../_api/api-urls'
import request from '../../_api/api-headers'

const reservationModule = {
  namespaces: true,
  state: {
  },
  mutations: {
  },
  actions: {
    createReservation (_, data) {
      return request().post(reservation.CREATE_RESERVATION, data)
    },
    getListReservationsById (_, id) {
      return request().get(reservation.LIST_RESERVATION_BY_ID, {
        params: {
          sessionId: id
        }
      }).then((response) => {
        return response.data
      })
    },
    getListPlacesById (_) {
      return request().get(reservation.LIST_PLACES_BY_ID).then((response) => {
        return response.data
      })
    }
  }
}
export default reservationModule

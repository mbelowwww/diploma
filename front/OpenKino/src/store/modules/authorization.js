import AXIOS from 'axios'
import { authorization } from '../../_api/api-urls'
import request from '../../_api/api-headers'
const auth = {
  state: {

  },
  mutations: {

  },
  actions: {
    userRegistration (_, data) {
      return request().post(authorization.REGISTRATION_USER, data).then((response) => {
        return response
      })
    }
  }
}
export {
  auth
}
export default auth

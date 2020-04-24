import AXIOS, { AxiosInstance } from 'axios'
import { setAuthorizationHeader } from './auth-helper'

const request: () => AxiosInstance = () => {
  const request: AxiosInstance = AXIOS.create({
    // baseURL: process.env.VUE_APP_AXIOS_URL,
    headers: {
      ...setAuthorizationHeader()
    }
  })

  request.interceptors.response.use((response) => {
    return response
  }, (error) => {
    return Promise.reject(error)
  })
  return request
}

export default request

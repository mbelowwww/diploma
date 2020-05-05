import { films } from '../../_api/api-urls'
import request from '../../_api/api-headers'

const filmsModule = {
  namespaced: true,
  state: {
  },
  mutations: {
  },
  actions: {
    addFilm (_, data) {
      return request().post(films.USE_FILMS, data).then((response) => {
        return response
      })
    },
    deleteFilm (_, id) {
      return request().post(films.USE_FILMS + `/${id}`).then((response) => {
        return response
      })
    },
    changeFilm (_, data) {
      return request().put(films.USE_FILMS, data).then((response) => {
        return response
      })
    },
    getFilm (_, data) {
      return request().get(films.USE_FILMS, {
        params: {
          name: data.name,
          limitAge: data.limitAge,
          rating: data.rating,
          genre: data.genre
        }
      }).then((response) => {
        return response
      })
    },
    addImages (_, data) {
      return request().post(films.ADD_IMAGE, data).then((response) => {
        return response
      })
    }
  }
}

export default filmsModule

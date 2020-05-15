const auth = {
  REGISTRATION_USER: '/kinouser',
  AUTHORIZATION_USER: '/auth',
  LIST_USERS: '/kinouser/all'
}
const films = {
  USE_FILMS: '/film',
  ADD_IMAGE: '/film/image'
}
const ban = {
  TO_BAN: 'ban'
}
const halls = {
  CREATE_HALL: 'hall',
  GET_ALL_HALLS: 'hall/all'
}

const url = {
  auth,
  films,
  ban,
  halls
}

export {
  auth,
  films,
  ban,
  halls
}

export default url

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
const sessions = {
  CREATE_SESSIONS: 'session',
  LIST_TYPES: 'session/type',
  LIST_SESSION_TIME: 'session/time'
}

const url = {
  auth,
  films,
  ban,
  halls,
  sessions
}

export {
  auth,
  films,
  ban,
  halls,
  sessions
}

export default url

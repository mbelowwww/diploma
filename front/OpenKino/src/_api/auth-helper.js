function setAuthorizationHeader () {
  const user = localStorage.getItem('token')

  if (user) {
    return { Authorization: `Bearer ${user}` }
  }
  return {}
}

export {
  setAuthorizationHeader
}

function setAuthorizationHeader () {
  const user: string = localStorage.getItem('token')

  if (user) {
    return { Authorization: `Bearer ${user}` }
  }
  return {}
}

export {
  setAuthorizationHeader
}

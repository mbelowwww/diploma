function keyToValue (item, key) {
  let value = ''
  const trees = key.split('.')

  trees.forEach((treesKey) => {
    if (typeof value === 'object' && value) {
      value = value[treesKey]
    } else {
      value = item[treesKey]
    }
  })
  return value
}

function dateToString (date) {
  let correctDate = new Date(date)
  const day = correctDate.getDate()
  const month = correctDate.getMonth() + 1
  const year = correctDate.getFullYear()
  const hours = correctDate.getHours()
  const minutes = correctDate.getMinutes()
  const seconds = correctDate.getSeconds()
  return `${day < 10 ? '0' : ''}${day}.${month < 10 ? '0' : ''}${month}.${year} ${hours < 10 ? '0' : ''}${hours}:${minutes < 10 ? '0' : ''}${minutes}:${seconds < 10 ? '0' : ''}${seconds}`
}

function serverDateApi (dateStr) {
  if (!dateStr) { return null }
  const parts = dateStr.split('.').map((item) => parseInt(item, 10))
  return new Date(parts[2], parts[1] - 1, parts[0])
}

export {
  keyToValue, dateToString, serverDateApi
}

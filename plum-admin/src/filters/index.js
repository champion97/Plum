export const formatDateFilter = (value) => {
  var date = new Date(value);
  return `${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()}`
}

export const formatDateTimeFilter = (value) => {
  var date = new Date(value);
  return `${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()} ${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`
}

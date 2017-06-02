export function timeAgo (time) {
  const between = Date.now() / 1000 - Number(time/1000)
  if (between>2592000) {
    return formatDate(time)
  }
  if (between < 3600) {
    return pluralize(~~(between / 60), ' 分钟前')
  } else if (between < 86400) {
    return pluralize(~~(between / 3600), ' 小时前')
  } else {
    return pluralize(~~(between / 86400), ' 天前')
  }
}

function pluralize (time, label) {
  if (time === 1) {
    return time + label
  }
  return time + label
}

function formatDate(time) {
  var date = new Date(time);
  var Y = date.getFullYear() + '-';
  var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
  var D = date.getDate();
  return Y+M+D;
}

import axios from 'axios'
import router from '../router/index'
import token from '../utils/token'
import setting from '../config/app'

const baseURL = process.env.NODE_ENV === 'production' ? setting.apiUrl.prod : setting.apiUrl.dev

var request = axios.create({
  baseURL: baseURL
});

request.defaults.headers.post['Content-Type'] = 'application/json'

/**
 * axios request拦截器
 */
request.interceptors.request.use(function (config) {
  if (config.url.indexOf('account/login') < 0 && token.isEmpty()) {
    logout()
    return Promise.reject('未登录')
  }
  config.headers.Authorization = `Bearer ${token.getToken()}`
  return config
}, function (error) {
  return Promise.reject(error)
});

/**
 *  axios response拦截器
 */
request.interceptors.response.use(function (response) {
  if (response.data.code === 102) {
    logout()
    return Promise.reject('token无效')
  }
  if (response.data.code !=0) {
    return Promise.reject(response.data.msg)
  }
  return response
}, function (error) {
  return Promise.reject(error)
});

var logout = () => {
  token.removeToken();
  router.push({
    path: '/login'
  });
}

export default request

import setting from '../config/app'

export default {
  isEmpty(){
    return !this.getToken()
  },
  setToken(tokenVal) {
    return localStorage.setItem(setting.tokenKey, tokenVal);
  },
  removeToken() {
    return localStorage.removeItem(setting.tokenKey)
  },
  getToken() {
    return localStorage.getItem(setting.tokenKey)
  }
}

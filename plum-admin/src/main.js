// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'

import * as filters from './filters/index'
Object.keys(filters).forEach(k => Vue.filter(k, filters[k])) // 注册过滤器

Vue.use(ElementUI)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
/*new Vue({
    router,
    render: h => h(App)
}).$mount('#app');*/

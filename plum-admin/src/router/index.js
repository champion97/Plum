import Vue from 'vue'
import Router from 'vue-router'
import token from '../utils/token'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  scrollBehavior: function (to, from, savedPosition) {
    return savedPosition || {x: 0, y: 0}
  },
  routes: [{
    path: '',
    redirect: '/summary'
  }, {
    path: '',
    component: resolve => require(['../components/common/Home.vue'], resolve),
    children: [{
      path: '/summary',
      meta: {requiresAuth: true},
      component: resolve => require(['../components/pages/Summary.vue'], resolve)
    }, {
      path: '/articles',
      meta: {requiresAuth: true},
      component: resolve => require(['../components/pages/article/Articles.vue'], resolve)
    }, {
      name: 'addArticle',
      path: '/articles/addArticle',
      meta: {requiresAuth: true},
      component: resolve => require(['../components/pages/article/AddArticle.vue'], resolve)
    }, {
      name: 'editArticle',
      path: '/articles/editArticle/:aid',
      meta: {requiresAuth: true},
      component: resolve => require(['../components/pages/article/EditArticle.vue'], resolve)
    }, {
      path: '/comments',
      meta: {requiresAuth: true},
      component: resolve => require(['../components/pages/comment/Comments.vue'], resolve)
    }, {
      name: 'category',
      path: '/categories',
      meta: {requiresAuth: true},
      component: resolve => require(['../components/pages/category/Categories.vue'], resolve)
    }, {
      name: 'tag',
      path: '/tags',
      meta: {requiresAuth: true},
      component: resolve => require(['../components/pages/tag/Tags.vue'], resolve)
    }
    ]
  }, {
    path: '/login',
    component: resolve => require(['../components/pages/Login.vue'], resolve)
  }]
})

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth && token.isEmpty()) { // 需要登录且未登录的
    next({
      path: '/login',
      query: {redirect: to.fullPath}
    })
  } else {
    next()
  }
})


export default router

import Vue from "vue";
import Router from "vue-router";

Vue.use(Router)

const PostList = () => import('../views/PostList.vue')
const Post = () => import('../views/Post.vue')
const Page = () => import('../views/Page.vue')

export default new Router({
  mode: 'history',
  scrollBehavior: () => ({ y: 0 }),
  routes: [
    { path: '/', component: PostList },
    { path: '/post/:id(\\d+)', component: Post },
    { path: '/page/:slug', component: Page },
    { path: '*', redirect: '/' }
  ]
})

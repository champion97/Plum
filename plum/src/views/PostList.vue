<template>
    <div class="container">
      <div class="post-published">
        <div class="row list-title">
          <p class="center"> 文章</p>
        </div>
        <p style="text-align: center" v-if="loading">加载中...</p>
        <table class="u-full-width">
          <tbody>
          <tr v-for="item in post">
            <td class="title">
              <router-link :to="'/post/'+ item.id">{{ item.title }}</router-link>
              <small v-if="item.commentNum>0">(item.commentNum)</small>
            </td>
            <td class="time">{{item.createdAt | timeAgo}}</td>
          </tr>
          </tbody>
        </table>
        <div style="margin: 0 auto; text-align: center" v-if="totalPages>1">
          <span v-if="totalPages>1&& currentPage>1" @click="fetchData(currentPage-1)"><a href="#">上一页</a></span>
          <span style="padding: 10px;">{{currentPage}}/{{totalPages}}</span>
          <span v-if="totalPages>currentPage" @click="fetchData(currentPage+1)"><a href="#">下一页</a></span>
        </div>
      </div>
    </div>
</template>

<script>
  import api from '../api/index'
  export default{
    name: 'post-list',
    data () {
      return {
        loading: false,
        post: '',
        currentPage: '',
        totalPages: ''
      }
    },
    created () {
      this.fetchData(1)
    },
    watch: {
      '$route': 'fetchData'
    },
    methods: {
      async fetchData (page) {
        this.loading = true
        let posts = await api.getArticles(page, 15)
        this.post = posts.data.data.content
        this.loading = false
        this.currentPage = posts.data.data.number + 1
        this.totalPages = posts.data.data.totalPages
      }
    }
  }
</script>

<style>
  table{
    margin-bottom: 100px;
  }
</style>

<template>
  <div class="container">
    <div class="post-content typo">
      <div style="text-align:center">
        <h1>{{ page.title }}</h1>
      </div>
      <div class="article-content" v-html="pageHTML">
      </div>
    </div>
  </div>
</template>

<script>
  import api from '../api/index'
  import { markdownToHTML } from '../utils/index'
  export default{
    name: 'page',
    data () {
      return {
        page: '',
        pageHTML: ''
      }
    },
    created () {
      this.getPage()
    },
    watch: {
      '$route': 'getPage'
    },
    methods: {
      async getPage () {
        let resp = await api.getSinglePage(this.$route.params.slug)
        this.page = resp.data.data
        this.pageHTML = markdownToHTML(resp.data.data.content)
        window.document.title = this.page.title
      }
    }
  }
</script>

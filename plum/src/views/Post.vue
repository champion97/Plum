<template>
    <div class="container">
      <div class="post-content typo">
        <div style="text-align:center">
          <h1>{{ post.title }}</h1>

          <p>{{ post.createdAt | timeAgo }}</p>
        </div>
        <p style="text-align: center" v-if="loading">加载中...</p>
        <div class="article-content" v-html="postHTML">
        </div>
        <div class="copyright" v-if="post">
          <ul>
            <li>分类：<span>{{ post.category.name }}</span></li>
            <li>标签：<span v-for="tag in post.tags">「{{ tag.name }}」</span></li>
          </ul>
        </div>
      </div>
    </div>
</template>

<script>
  import api from '../api/index'
  import {markdownToHTML} from '../utils/index'
  export default{
    name: 'post',
    data () {
      return {
        loading: false,
        post: '',
        postHTML: ''
      }
    },
    created () {
      this.fetchData()
    },
    watch: {
      '$route': 'fetchData'
    },
    methods: {
      async fetchData () {
        this.loading = true
        let post = await api.getArticle(this.$route.params.id)
        this.post = post.data.data
        this.postHTML = markdownToHTML(post.data.data.content)
        this.loading = false
        window.document.title = this.post.title
      }
    }
  }
</script>
<style>
  /**
 * Obsidian style
 * ported by Alexander Marenin (http://github.com/ioncreature)
 */
  /**
   * GitHub Gist Theme
   * Author : Louis Barranqueiro - https://github.com/LouisBarranqueiro
   */

  pre code {
    display: block;
    background: #f7f7f7;
    padding: 1em;
    color: #333333;
    overflow-x: auto;
  }


  pre {
    background: #f7f7f7 !important;
    font-family: 'Roboto Mono', Monaco, courier, monospace;
    font-size: 12px;
  }

  .hljs-comment,
  .hljs-meta {
    color: #969896;
  }

  .hljs-string,
  .hljs-variable,
  .hljs-template-variable,
  .hljs-strong,
  .hljs-emphasis,
  .hljs-quote {
    color: #df5000;
  }

  .hljs-keyword,
  .hljs-selector-tag,
  .hljs-type {
    color: #a71d5d;
  }

  .hljs-literal,
  .hljs-symbol,
  .hljs-bullet,
  .hljs-attribute {
    color: #0086b3;
  }

  .hljs-section,
  .hljs-name {
    color: #63a35c;
  }

  .hljs-tag {
    color: #333333;
  }

  .hljs-title,
  .hljs-attr,
  .hljs-selector-id,
  .hljs-selector-class,
  .hljs-selector-attr,
  .hljs-selector-pseudo {
    color: #795da3;
  }

  .hljs-addition {
    color: #55a532;
    background-color: #eaffea;
  }

  .hljs-deletion {
    color: #bd2c00;
    background-color: #ffecec;
  }

  .hljs-link {
    text-decoration: underline;
  }

</style>

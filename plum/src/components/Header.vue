<template>
  <header class="clearfix">
    <router-link to="/" class="logo">
      <div class="title">Rivaille's blog</div>
    </router-link>
    <nav>
      <ul>
        <li>
          <router-link to="/">文章</router-link>
        </li>
        <li>
          <router-link to="/page/friend" par>友链</router-link>
        </li>
        <li>
          <router-link to="/page/donate">赞助</router-link>
        </li>
        <li>
          <router-link to="/page/about">关于</router-link>
        </li>
      </ul>
    </nav>
  </header>
</template>
<script>
  export default{
    name: 'header',
  }
</script>

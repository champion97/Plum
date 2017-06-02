<template>
  <div id="app">
    <my-header></my-header>
    <transition name="fade" mode="out-in">
      <router-view></router-view>
    </transition>
    <my-footer></my-footer>
  </div>
</template>
<script>
  import Header from './components/Header.vue'
  import Footer from './components/Footer.vue'
  export default{
    name: 'app',
    components: {
      'my-header': Header,
      'my-footer': Footer
    }
  }
</script>


<template>
  <div :class="{'container': !isAdmin}">
    <AppCenter :isVertical="true" v-if="!isAdmin">
      <TheHeader/>
      <router-view></router-view>
<!--      <TheFooter/>-->
    </AppCenter>

    <Admin v-else/>
  </div>
</template>

<script>
import TheHeader from './components/single/TheHeader'
import TheFooter from './components/single/TheFooter'
import AppCenter from './components/common/group/AppCenter'
import Admin from './views/PageAdmin'
export default {
  components: {
    TheHeader, TheFooter, AppCenter, Admin
  },
  data () {
    return {
      isAdmin: false
    }
  },
  watch: {
    $route: {
      handler: function () {
        this.isAdmin = this.$route.path.includes('admin')
      }
    },
    isAdmin: {
      immediate: true,
      handler: function () {
        const userInfo = JSON.parse(localStorage.getItem('currentUser'))
        if (userInfo && userInfo !== {}) this.$store.dispatch('auth/getCurrentUser')
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  height: 100vh;
  width: 100vw;
  background: url('assets/img/bg.png');
}
</style>

<style lang="scss">
  .container {
    color: white;
    padding: 0;
    margin: 0;
  }
</style>

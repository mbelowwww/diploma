<template>
  <div :class="{container: !isAdmin}">
    <AppCenter :isVertical="true" v-if="!isAdmin">
      <TheHeader/>
      <router-view></router-view>
      <TheFooter/>
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
      // immediate: true,
      handler: function () {
        this.isAdmin = this.$route.path.includes('admin')
      }
    }
  }
}
</script>

<style lang="scss">
.container {
  height: 100vh;
  width: 100vw;
  background: url('assets/img/bg.png');
}
</style>

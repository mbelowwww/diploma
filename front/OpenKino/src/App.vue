<template>
  <div class="container">
    <AppCenter :vertical="true">
      <TheHeader/>
      <router-view></router-view>
      <TheFooter/>
    </AppCenter>
  </div>
</template>

<script>
import TheHeader from './components/single/TheHeader'
import TheFooter from './components/single/TheFooter'
import AppCenter from './components/common/group/AppCenter'

export default {
  components: {
    TheHeader, TheFooter, AppCenter
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

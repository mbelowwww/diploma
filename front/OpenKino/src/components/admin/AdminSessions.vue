<template>
  <div class="container-sessions">
    <AppSearch v-model="searchSession" placeholder="Поиск по фильму"/>
  </div>
</template>

<script>
import AppSearch from '../common/input/AppSearch'
export default {
  name: 'AdminSessions',
  components: { AppSearch },
  data () {
    return {
      searchSession: ''
    }
  }
}
</script>

<style scoped lang="scss">
  .container-sessions {
    color: #222222;
  }
</style>

<template>
  <div class="container-auth">
    <AppButton :buttons="valueAuthAndReg" :selected="selected" @click="selected = $event"/>
  </div>
</template>

<script>
import AppButton from '../common/button/AppButton'
export default {
  components: {

    AppButton
  },
  data () {
    return {
      valueAuthAndReg: [
        { val: 'Войти', key: 'entry', class: 'button-auth' },
        { val: 'Регистрация', key: 'registration', class: 'button-auth' }
      ],
      selected: {}
    }
  }
}
</script>

<style scoped>
  .container-auth {
    width: 100%;
    max-width: 500px;
    height: 300px;
    background: white;
    padding: 15px;
  }
</style>

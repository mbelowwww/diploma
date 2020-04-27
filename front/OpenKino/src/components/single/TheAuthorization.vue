<template>
  <div class="container-auth">
    <AppButton :buttons="valueAuthAndReg" :selected="selected" @click="selected = $event" class="container-auth__buttons"/>
    <TheLogin v-if="selected.key === 'entry' || !selected.key" @selectedLogin="entryUser"/>
    <TheRegistration v-else-if="selected.key === 'registration'" @selectedRegistration="registrationUser"/>
  </div>
</template>

<script>
import AppButton from '../common/button/AppButton'
import TheLogin from './TheLogin'
import TheRegistration from './TheRegistration'
export default {
  components: {
    AppButton,
    TheLogin,
    TheRegistration
  },
  data () {
    return {
      valueAuthAndReg: [
        { val: 'Войти', key: 'entry', class: 'button-auth' },
        { val: 'Регистрация', key: 'registration', class: 'button-auth' }
      ],
      selected: {
        class: '',
        key: '',
        val: ''
      }
    }
  },
  methods: {
    entryUser (value) {
      this.$store.dispatch('auth/userAuthorization', value)
    },
    registrationUser (value) {
      this.$store.dispatch('auth/userRegistration', value)
    }
  }
}
</script>

<style scoped lang="scss">
  .container-auth {
    width: 100%;
    max-width: 500px;
    min-height: 280px;
    background: white;
    padding: 15px;
    &__buttons {
      margin-bottom: 30px;
    }
  }
</style>

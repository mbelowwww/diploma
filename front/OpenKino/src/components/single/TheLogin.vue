<template>
  <div class="container-login">
    <AppInput v-for="auth in dataLogin" v-model="auth.value" :title="auth.title" :placeholder="auth.placeholder"/>
    <AppButton :selected="selected" :buttons="buttons" @click="selected = $event" class="container-login__buttons"/>
  </div>
</template>

<script>
import AppInput from '../common/input/AppInput'
import AppButton from '../common/button/AppButton'
export default {
  name: 'TheLogin',
  components: {
    AppInput,
    AppButton
  },
  data () {
    return {
      dataLogin: [
        {
          key: 1,
          value: '',
          placeholder: 'Логин',
          // title: 'Логин',
          mail: ''
        },
        {
          key: 2,
          value: '',
          placeholder: 'Пароль',
          // title: 'Пароль',
          password: ''
        }
      ],
      selected: {},
      buttons: [
        {
          val: 'Войти',
          key: 1,
          class: 'button-entry'
        },
        {
          val: 'Забыли пароль',
          key: 2,
          class: 'button-reset-password'
        }
      ]
    }
  }
}
</script>

<style scoped lang="scss">
.container-login {
  &__buttons {
    margin-top: 30px;
  }
}
</style>

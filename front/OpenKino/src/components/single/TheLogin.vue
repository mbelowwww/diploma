<template>
  <div class="container-login">
    <AppInput v-model="dataLogin.mail" :placeholder="inputConfig.mail.placeholder"/>
    <AppInput v-model="dataLogin.password" :placeholder="inputConfig.password.placeholder" type="password"/>
    <AppButton :selected="selected" :buttons="buttons" @click="selectedLogin" class="container-login__buttons"/>
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
      dataLogin: {
        mail: '',
        password: ''
      },
      selected: {},
      buttons: [
        {
          val: 'Войти',
          key: 'authorization',
          class: 'button-entry'
        },
        {
          val: 'Забыли пароль',
          key: 'resetPassword',
          class: 'button-reset-password'
        }
      ],
      inputConfig: {
        mail: {
          placeholder: 'Введите почту',
          title: ''
        },
        password: {
          placeholder: 'Введите пароль',
          title: ''
        }
      }
    }
  },
  methods: {
    selectedLogin (value) {
      this.selected = value
      this.$emit('selectedLogin', this.dataLogin)
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

<template>
  <div class="container-auth">
    <AppButton :buttons="valueAuthAndReg" :selected="selected" @click="selected = $event"/>
    <AppInput v-for="auth in valueAuthorization" v-model="auth.value" :placeholder="auth.placeholder"/>
  </div>
</template>

<script>
import AppInput from '../common/input/AppInput.vue'
import AppButton from '../common/button/AppButton'
export default {
  components: {
    AppInput,
    AppButton
  },
  data () {
    return {
      valueAuthorization: [
        {
          value: '',
          title: 'Логин',
          key: 1,
          placeholder: 'E-mail'
        },
        {
          value: '',
          title: 'Пароль',
          key: 2,
          placeholder: 'Пароль'
        }
      ],
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

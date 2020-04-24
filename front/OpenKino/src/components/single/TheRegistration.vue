<template>
  <div class="container-registration">
    <AppInput v-for="reg in dataRegistration" v-model="reg.value" :title="reg.title" :placeholder="reg.placeholder"/>
    <AppButton :selected="selected" :buttons="buttons" @click="selected = $event" class="container-registration__buttons"/>
  </div>
</template>

<script>
import AppInput from '../common/input/AppInput'
import AppButton from '../common/button/AppButton'
export default {
  name: 'TheRegistration',
  components: {
    AppInput,
    AppButton
  },
  data () {
    return {
      dataRegistration: [
        {
          key: 1,
          value: '',
          placeholder: 'Введите фамилию',
          // title: 'Фамилия',
          fName: ''
        },
        {
          key: 2,
          value: '',
          placeholder: 'Введите имя',
          // title: 'Имя',
          name: ''
        },
        {
          key: 3,
          value: '',
          placeholder: 'Введите отчество',
          // title: 'Отчество',
          lName: ''
        },
        {
          key: 4,
          value: '',
          placeholder: 'Введите пароль',
          // title: 'Пароль',
          password: ''
        },
        {
          key: 5,
          value: '',
          placeholder: 'Введите возраст',
          // title: 'Возраст',
          age: ''
        },
        {
          key: 6,
          value: '',
          placeholder: 'Введите телефон',
          // title: 'Номер телефона',
          phone: ''
        },
        {
          key: 7,
          value: '',
          placeholder: 'Введите email',
          // title: 'E-mail',
          mail: ''
        }
      ],
      selected: {},
      buttons: [
        {
          key: 'registration',
          val: 'Зарегистрироваться',
          class: 'button-entry'
        }
      ]
    }
  }
}
</script>

<style scoped lang="scss">
.container-registration{
  &__buttons {
    margin-top: 30px;
  }
}
</style>

<template>
  <div class="container-registration">
    <AppInput v-model="dataRegistration.fName" :placeholder="inputConfig.fName.placeholder"/>
    <AppInput v-model="dataRegistration.name" :placeholder="inputConfig.name.placeholder"/>
    <AppInput v-model="dataRegistration.lName" :placeholder="inputConfig.lName.placeholder"/>
    <AppInput v-model="dataRegistration.password" :placeholder="inputConfig.password.placeholder"/>
    <AppInput v-model="dataRegistration.age" :placeholder="inputConfig.age.placeholder"/>
    <AppInput v-model="dataRegistration.phone" :placeholder="inputConfig.phone.placeholder"/>
    <AppInput v-model="dataRegistration.mail" :placeholder="inputConfig.mail.placeholder"/>
    <AppButton :selected="selected" :buttons="buttons" @click="selectedRegistration" class="container-registration__buttons"/>
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
      dataRegistration: {
        fName: '',
        name: '',
        lName: '',
        password: '',
        age: '',
        phone: '',
        mail: ''
      },
      selected: {},
      buttons: [
        {
          key: 'registration',
          val: 'Зарегистрироваться',
          class: 'button-registration'
        }
      ],
      inputConfig: {
        fName: {
          placeholder: 'Введите фамилию',
          title: ''
        },
        name: {
          placeholder: 'Введите имя',
          title: ''
        },
        lName: {
          placeholder: 'Введите отчество',
          title: ''
        },
        password: {
          placeholder: 'Введите пароль',
          title: ''
        },
        age: {
          placeholder: 'Введите возраст',
          title: ''
        },
        phone: {
          placeholder: 'Введите телефон',
          title: ''
        },
        mail: {
          placeholder: 'Введите почту',
          title: ''
        }
      }
    }
  },
  methods: {
    selectedRegistration (value) {
      this.selected = value
      this.$emit('selectedRegistration', this.dataRegistration)
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

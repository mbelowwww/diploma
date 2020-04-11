<template>
  <div class="header">
    <div class="header__logo" @click="$router.push({name: 'PageMain'}).catch(() => {})">
      <img src="@/assets/img/logo.png" width="171px" height="79px" alt="logo">
    </div>
    <AppButton :buttons="buttons" :selected="selected" @click="selectedButton"/>
    <div class="header__user">
      <img src="@/assets/img/enter.png" class="header__user__enter" height="38px" width="38px" alt="login">
      <span>{{userLogin}}</span>
    </div>
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
      buttons: [
        {
          key: 'films',
          val: 'Фильмы',
          classProp: '',
          to: 'PageFilms'
        },
        {
          key: 'session',
          val: 'Сеансы',
          classProp: '',
          to: 'PageSessions'
        },
        {
          key: 'news',
          val: 'Новости',
          classProp: '',
          to: 'PageNews'
        },
        {
          key: 'more',
          val: 'Ещё',
          class: '',
          to: 'PageAdmin'
        }
      ],
      selected: '',
      userLogin: 'Вход'
    }
  },
  methods: {
    selectedButton (button) {
      this.selected = this.buttons.find(item => item.key === button.key)
      this.$router.push({ name: button.to })
    }
  }
}
</script>

<style scoped lang="scss">
.header {
  display: flex;
  height: 100px;
  color: white;
  width: 100%;
  margin-bottom: 10px;
  align-items: center;
  justify-content: space-between;
  &__logo {
    margin-right: 10px;
   }
  &__user {
    display: flex;
    align-items: center;
    &__enter {
      margin: 0 10px;
    }
  }
}
</style>

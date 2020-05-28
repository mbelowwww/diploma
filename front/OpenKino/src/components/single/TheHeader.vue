<template>
  <div class="header">
    <div class="header__logo" @click="$router.push({name: 'PageMain'}).catch(() => {})">
      <img src="@/assets/img/logo.png" width="171px" height="79px" alt="logo">
    </div>
    <AppButton :buttons="buttons" :selected="selected" @click="selectedButton($event)"/>
    <div class="header__user" @click="entryUser">
      <img src="@/assets/img/enter.png" class="header__user__enter" height="38px" width="38px" alt="login">
      <span>{{showCurrentUser}}</span>
    </div>
    <AppPopupWindow v-if="isWindowAuthorization" @close="isWindowAuthorization = false">
      <AppAuthorization/>
    </AppPopupWindow>
  </div>
</template>

<script>
import AppButton from '../common/button/AppButton'
import AppPopupWindow from '../popup/AppPopupWindow.vue'
import AppAuthorization from '../single/TheAuthorization'

export default {
  components: {
    AppButton,
    AppPopupWindow,
    AppAuthorization
  },
  data () {
    return {
      buttons: [
        {
          key: 'films',
          val: 'Фильмы',
          class: '',
          to: 'PageFilms'
        },
        {
          key: 'session',
          val: 'Сеансы',
          class: '',
          to: 'PageSessions'
        },
        {
          key: 'news',
          val: 'Новости',
          class: '',
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
      userLogin: 'Вход',
      isWindowAuthorization: false,
      isWindowPrimaryCabinet: false
    }
  },
  computed: {
    showCurrentUser () {
      const userInfo = JSON.parse(localStorage.getItem('currentUser'))
      return userInfo && userInfo.name ? userInfo.name : 'Вход'
    }
  },
  methods: {
    selectedButton (button) {
      this.selected = button
      this.$router.push({ name: button.to })
    },
    entryUser (value) {
      if (this.showCurrentUser === 'Вход') {
        this.isWindowAuthorization = true
      } else {
        this.$router.push({ name: 'PageUserCabinet' })
      }
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
    cursor: pointer;
    &__enter {
      margin: 0 10px;
    }
  }
}
</style>

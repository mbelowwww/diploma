<template>
  <div class="header">
    <div class="header__logo">
      <img src="@/assets/img/logo.png" width="171px" height="79px" alt="logo">
    </div>
    <AppButton :buttons="buttons" :selected="selected" @click="selectedButton"/>
    <div class="header__user">
      <img src="@/assets/img/enter.png" class="header__user__enter" height="38px" width="38px" alt="">
      <span>Вход</span>
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
          classProp: ''
        },
        {
          key: 'session',
          val: 'Сеансы',
          classProp: ''
        },
        {
          key: 'news',
          val: 'Новости',
          classProp: ''
        },
        {
          key: 'more',
          val: 'Ещё',
          class: ''
        }
      ],
      selected: ''
    }
  },
  methods: {
    selectedButton(button) {
      this.selected = this.buttons.find(item => item.key === button.key)
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

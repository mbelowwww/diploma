<template>
  <div class="container-sessions">
    <AppSearch v-model="searchSession" placeholder="Поиск по фильму"/>
    <button class="btn-primary" @click="isAddType = true">Добавить тип</button>
    <transition name="slide-popup-add-type">
      <AppPopupWindow v-if="isAddType" @close="isAddType = false">
        <div class="container-sessions__center">
          <div>
            <p class="container-sessions__center__title">Текущие типы фильмов</p>
            <li v-for="(item, index) in getListTypes" :key="item.id" class="container-sessions__center__item">
              {{`${index + 1})  ${item.name}`}}
            </li>
          </div>
          <div>
            <AppInput v-model="newType" placeholder="Новый тип фильма"/>
            <AppButton :buttons="buttons" :selected="selected" @click="saveType" :isBetween="true" class="container-login__buttons"/>
          </div>

        </div>
      </AppPopupWindow>
    </transition>

  </div>
</template>

<script>
import AppSearch from '../common/input/AppSearch'
import AppPopupWindow from '../popup/AppPopupWindow'
import AppInput from '../common/input/AppInput'
import AppButton from '../common/button/AppButton'
export default {
  name: 'AdminSessions',
  components: { AppButton, AppInput, AppPopupWindow, AppSearch },
  data () {
    return {
      searchSession: '',
      isAddType: false,
      newType: '',
      buttons: [
        {
          key: 'cancel',
          val: 'Закрыть',
          class: 'btn-primary'
        },
        {
          key: 'add',
          val: 'Сохранить',
          class: 'btn-primary'
        }
      ],
      selected: {}
    }
  },
  methods: {
    saveType (value) {
      this.selected = value
      const correctData = {
        name: this.newType
      }
      if (value.key === 'add') {
        this.$store.dispatch('sessions/addType', correctData).then(() => {
          this.$store.dispatch('sessions/getListTypes')
        })
      } else if (value.key === 'cancel') {
        this.isAddType = false
      }
    }
  },
  computed: {
    getListTypes () {
      return this.$store.state.sessions.listTypes
    }
  },
  watch: {
    listTypes: {
      immediate: true,
      handler: function (val) {
        this.$store.dispatch('sessions/getListTypes')
      }
    }
  }
}
</script>

<style scoped lang="scss">
  .container-sessions {
    color: #222222;
    &__center {
      background: white;
      height: 800px;
      max-width: 500px;
      padding: 10px;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      box-shadow: 15px 15px 15px #222222;
      flex-grow: 1;
      border-radius: 3px 50px 3px 3px;
      &__title {
        font-size: 24px;
        text-align: center;
        color: #ff9a12;
        letter-spacing: 2px;
        margin-bottom: 20px;
      }
      &__item {
        font-size: 22px;
        letter-spacing: 1px;
        padding: 5px 0;
      }
    }
  }

  .slide-popup-add-type-enter-active {
    transition: all .4s ease;
  }
  .slide-popup-add-type-leave-active {
    transition: all .4s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }
  .slide-popup-add-type-enter, .slide-popup-add-type-leave-to
    /* .slide-fade-leave-active до версии 2.1.8 */ {
    transform: translateX(10px);
    opacity: 0;
  }
</style>

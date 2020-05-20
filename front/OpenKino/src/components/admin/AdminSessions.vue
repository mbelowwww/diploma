<template>
  <div class="container-sessions">
    <AppInputGroup #group>
      <AppSearch v-model="searchSession" placeholder="Поиск по фильму"/>
      <AppDatePicker v-model="startSession" :inputConf="dateStart" style="margin: 15px 20px 0"/>
      <AppDatePicker v-model="endSession" :inputConf="dateEnd" style="margin: 15px 0 0 0"/>
    </AppInputGroup>

    <AppSelect v-model="createDataSession.type" inputKey="name" :values="listTypes" placeholder="Выберите тип фильма" classContainer="search__list"/>
    <AppSelect v-model="createDataSession.film" inputKey="name" :values="listFilms" placeholder="Выберите фильм" classContainer="search__list"/>
    <AppSelect v-model="createDataSession.hall" inputKey="number" :values="listHalls" placeholder="Выберите номер зала" classContainer="search__list" style="margin-bottom: 10px"/>
    <AppInput v-model.number="createDataSession.price" placeholder="Введите цену" classProp="container__input-line"/>
    <AppDatePicker v-model="createDataSession.time" :inputConf="createDate"/>
    <div class="wrapper-btn-sb">
      <button class="btn-primary" @click="isAddType = true">Добавить тип</button>
      <button class="btn-primary" @click="createSession(createDataSession)">Создать сеанс</button>
    </div>

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

  <transition name="slide-popup-add-type">
    <AppPopupWindow v-if="isAddSession" @close="isAddSession = false">
      <AppSelect v-model="createDataSession.type" inputKey="name" :values="listTypes" placeholder="Выберите тип фильма" classContainer="search__list"/>
      <AppSelect v-model="createDataSession.film" inputKey="name" :values="listFilms" placeholder="Выберите фильм" classContainer="search__list"/>
      <AppSelect v-model="createDataSession.hall" inputKey="number" :values="listHalls" placeholder="Выберите номер зала" classContainer="search__list" style="margin-bottom: 10px"/>
      <AppInput v-model.number="createDataSession.price" placeholder="Введите цену" classProp="container__input-line"/>
      <AppDatePicker v-model="createDataSession.time" :inputConf="createDate"/>
      <div class="btn-primary">
        <button class="btn-primary" @click="createSession(createDataSession)">Создать сеанс</button>
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
import AppInputGroup from '../common/group/AppInputGroup'
import AppDatePicker from '../common/input/AppDatePicker'
import AppSelect from '../common/input/AppSelect'
import { dateToString } from '../../_helper/project'

export default {
  name: 'AdminSessions',
  components: { AppSelect, AppDatePicker, AppInputGroup, AppButton, AppInput, AppPopupWindow, AppSearch },
  created () {
    this.$store.dispatch('sessions/getListTypes').then(response => this.listTypes = response)
    this.$store.dispatch('films/getFilm', '').then(response => this.listFilms = response)
    this.$store.dispatch('halls/getListHalls').then(response => this.listHalls = response)
  },
  data () {
    return {
      listTypes: [],
      listFilms: [],
      listHalls: [],
      searchSession: '',
      isAddType: false,
      isAddSession: false,
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
      selected: {},
      startSession: '',
      endSession: '',
      dateStart: {
        type: 'datetime',
        disabled: false,
        placeholder: 'Начало сеансы'
      },
      dateEnd: {
        type: 'datetime',
        disabled: false,
        placeholder: 'Конец сеансы'
      },
      createDate: {
        type: 'datetime',
        disabled: false,
        placeholder: 'Дата сеанса'
      },
      createDataSession: {
        time: '',
        type: {},
        film: {},
        hall: {},
        price: ''
      }
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
    },
    createSession (value) {
      console.log(value)
      let correctData = {
        price: this.createDataSession.price,
        idFilm: this.createDataSession.film.id,
        idHall: this.createDataSession.hall.id,
        idTypeSession: this.createDataSession.type.id,
        start: dateToString(this.createDataSession.time)
      }
      this.$store.dispatch('sessions/createSession', correctData)
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

<template>
  <div class="container-sessions">
    <AppInputGroup #group>
      <AppSearch v-model="searchSession" placeholder="Поиск по фильму"/>
      <AppDatePicker v-model="startSession" :inputConf="dateStart" style="margin: 15px 20px 0"/>
      <AppDatePicker v-model="endSession" :inputConf="dateEnd" style="margin: 15px 20px 0 0"/>
      <button class="btn-primary" @click="isAddSession = true">Добавить сеанс</button>
    </AppInputGroup>
    <div class="wrapper-films__header">
      <div v-for="item in listFilmHeader" :key="item.id" :style="getLengthRow" class="wrapper-films__header__item">{{item.name}}</div>
    </div>
    <AppList :list="listSession">
      <template #item="{item, index}">
        <div class="wrapper-films">
          <div class="wrapper-films__data" :style="getLengthRow">{{index + 1}}</div>
          <div class="wrapper-films__data" :style="getLengthRow">{{item.film.name ? item.film.name : 'Не введено'}}</div>
          <div class="wrapper-films__data" :style="getLengthRow">{{item.hall.name ? item.hall.name : 'Не введено'}}</div>
          <div class="wrapper-films__data" :style="getLengthRow">{{item.typeSession ? item.typeSession.name : ''}}</div>
          <div class="wrapper-films__data" :style="getLengthRow">{{item.end}}</div>
          <div class="wrapper-films__data" :style="getLengthRow">{{item.film.limitAge.age ? item.film.limitAge.age : 'Не введено'}}</div>
<!--          <div class="wrapper-films__data" :style="getLengthRow">-->
<!--            <img src="@/assets/img/change.png" height="20px" width="20px" alt="change" @click="isShowPanelChangeFilm = true, selectedFilm = item">-->
<!--          </div>-->
<!--          <div class="wrapper-films__data" :style="getLengthRow">-->
<!--            <img src="@/assets/img/delete.png" height="20px" width="20px" alt="delete">-->
<!--          </div>-->
        </div>
      </template>

    </AppList>
    <div class="wrapper-btn-fs">
      <button class="btn-primary" @click="isAddType = true">Добавить тип</button>
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
      <div class="container-sessions__center">
        <div>
          <p class="container-sessions__center__title">Создать новый сеанс:</p>
          <AppSelect v-model="createDataSession.type" inputKey="name" :values="listTypes" placeholder="Выберите тип фильма" classContainer="search__list"/>
          <AppSelect v-model="createDataSession.film" inputKey="name" :values="listFilms" placeholder="Выберите фильм" classContainer="search__list"/>
          <AppSelect v-model="createDataSession.hall" inputKey="number" :values="listHalls" placeholder="Выберите номер зала" classContainer="search__list" style="margin-bottom: 10px"/>
          <AppInput v-model.number="createDataSession.price" placeholder="Введите цену" classProp="container__input-line"/>
          <AppDatePicker v-model="createDataSession.time" :inputConf="createDate"/>
        </div>
        <div class="wrapper-btn">
          <button class="btn-primary" @click="createSession(createDataSession)">Создать сеанс</button>
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
import AppInputGroup from '../common/group/AppInputGroup'
import AppDatePicker from '../common/input/AppDatePicker'
import AppSelect from '../common/input/AppSelect'
import { dateToString } from '../../_helper/project'
import AppList from '../common/input/AppList'
export default {
  name: 'AdminSessions',
  components: { AppSelect, AppDatePicker, AppInputGroup, AppButton, AppInput, AppPopupWindow, AppSearch, AppList },
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
      startSession: new Date(2011, 0, 1, 0, 0, 0, 0),
      endSession: new Date(),
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
      },
      listFilmHeader: [
        {
          id: 1,
          name: 'Номер'
        },
        {
          id: 2,
          name: 'Название фильма'
        },
        {
          id: 3,
          name: 'Номер зала'
        },
        {
          id: 4,
          name: 'Тип'
        },
        {
          id: 5,
          name: 'Дата'
        },
        {
          id: 6,
          name: 'Возрастное ограничение'
        },
        // {
        //   id: 7,
        //   name: 'Редактировать'
        // },
        // {
        //   id: 8,
        //   name: 'Удалить'
        // }
      ],
      listSession: []
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
    },
    getLengthRow () {
      return `width: ${995 / this.listFilmHeader.length}px`
    }
  },
  watch: {
    listTypes: {
      immediate: true,
      handler: function (val) {
        this.$store.dispatch('sessions/getListTypes')
      }
    },
    startSession: {
      immediate: true,
      handler: function (val) {
        const correctData = {
          start: dateToString(this.startSession),
          end: dateToString(this.endSession)
        }
        this.$store.dispatch('sessions/getListSessions', correctData).then(response => this.listSession = response.data)
      }
    },
    endSession: {
      handler: function (val) {
        const correctData = {
          start: dateToString(this.startSession),
          end: dateToString(this.endSession)
        }
        this.$store.dispatch('sessions/getListSessions', correctData).then(response => this.listSession = response.data)
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
  @mixin flexStyle {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .wrapper-films {
    display: flex;

    &__header {
      @include flexStyle;
      margin-top: 10px;
      color: black;
      font-size: 18px;
      font-weight: 700;
      word-wrap: normal;
      &__item {
        @include flexStyle;
      }
    }
    &__data {
      @include flexStyle;
      padding: 5px;
      font-weight: 100;
      font-size: 16px;
      color: gray;
      letter-spacing: 1px;
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

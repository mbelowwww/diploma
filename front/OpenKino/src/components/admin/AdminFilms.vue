<template>
  <div class="container-films">
    <AppSearch v-model="searchFilm" :placeholder="inputConfig.inputFilms.placeholder"/>

    <button class="single-button" @click="addFilm">
      Добавить фильм
    </button>
    <!--popup-->
    <AppPopupWindow v-if="isAddFilm" @close="isAddFilm = false">
      <div class="popup-admin-film">
        <AppInput v-model="dataFilm.name" :placeholder="inputConfig.inputFilmName.placeholder" classProp="container__input-line"/>
        <AppInput v-model="dataFilm.description" :placeholder="inputConfig.inputFilmDescription.placeholder" classProp="container__input-line"/>
        <AppInput v-model="dataFilm.length" :placeholder="inputConfig.inputFilmLength.placeholder" classProp="container__input-line"/>
        <AppInput v-model="dataFilm.rating" :placeholder="inputConfig.inputFilmRating.placeholder" classProp="container__input-line"/>
        <AppSelect v-model="dataFilm.genre" :values="listGenre"/>
        <button class="single-button" @click="saveFilm">Сохранить</button>
      </div>
    </AppPopupWindow>
  </div>
</template>

<script>
import AppSearch from '../common/input/AppSearch'
import AppButton from '../common/button/AppButton'
import AppPopupWindow from '../popup/AppPopupWindow'
import AppInput from '../common/input/AppInput'
import AppSelect from '../common/input/AppSelect'
export default {
  name: 'AdminFilms',
  components: { AppSelect, AppInput, AppPopupWindow, AppButton, AppSearch },
  data () {
    return {
      searchFilm: '',
      inputConfig: {
        inputFilms: {
          placeholder: 'Поиск по названию фильма, номеру зала и даты'
        },
        inputFilmName: {
          placeholder: 'Название фильма'
        },
        inputFilmDescription: {
          placeholder: 'Описание'
        },
        inputFilmLength: {
          placeholder: 'Длительность фильма'
        },
        inputFilmRating: {
          placeholder: 'Рейтинг фильма'
        }
      },
      isAddFilm: false,
      dataFilm: {
        name: '',
        description: '',
        length: '',
        rating: '',
        genre: {},
        typeFilm: {},
        limitAge: {}
      },
      listGenre: [
        {
          name: 'Боевик',
          val: 'Боевик',
          key: 1
        },
        {
          name: 'Драма',
          val: 'Драма',
          key: 2
        },
        {
          name: 'Комедия',
          val: 'Комедия',
          key: 3
        },
      ]
    }
  },
  methods: {
    addFilm () {
      this.isAddFilm = true
    },
    saveFilm () {
      this.$store.dispatch('films/addFilm', this.dataFilm)
    }
  }
}
</script>

<style scoped lang="scss">
.container-films {
  color: #222222;
}
.single-button {
  width: 180px;
  min-height: 40px;
  padding: 10px;
  background: #3EB1FF;
  color: #222222;
  border: none;
  border-radius: 3px;
  font-size: 18px;
  &:hover {
    cursor: pointer;
    background: #0E649E;
    color: white;
  }
}
.popup-admin-film {
  max-width: 1000px;
  height: 600px;
  background: white;
  padding: 10px;
}
</style>

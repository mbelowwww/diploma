<template>
  <div class="container-films">
    <!-- TODO поиск по 4 параметрам, сейчас работает только по названию фильма -->
    <AppSearch v-model="searchFilm" :placeholder="inputConfig.inputFilms.placeholder"/>

    <div class="wrapper-films__header">
      <div v-for="item in listFilmHeader" :key="item.id" :style="getLengthRow" class="wrapper-films__header__item">{{item.name}}</div>
    </div>
    <AppList :list="listFilms">
    <template #item="{item, index}">
      <div class="wrapper-films">
        <div class="wrapper-films__data" :style="getLengthRow">{{index + 1}}</div>
        <div class="wrapper-films__data" :style="getLengthRow">{{item.name ? item.name : 'Не введено'}}</div>
        <div class="wrapper-films__data" :style="getLengthRow">{{item.genre.name ? item.genre.name : 'Не введено'}}</div>
        <div class="wrapper-films__data" :style="getLengthRow">{{item.rating ? item.rating : 'Не введено'}}</div>
        <div class="wrapper-films__data" :style="getLengthRow">{{item.length ? (item.length / 3600).toFixed(1) : 'Не введено'}}</div>
        <div class="wrapper-films__data" :style="getLengthRow">{{item.limitAge.age ? item.limitAge.age : 'Не введено'}}</div>
        <div class="wrapper-films__data" :style="getLengthRow">
          <img src="@/assets/img/change.png" height="20px" width="20px" alt="change" @click="isShowPanelChangeFilm = true, selectedFilm = item">
        </div>
        <div class="wrapper-films__data" :style="getLengthRow">
          <img src="@/assets/img/delete.png" height="20px" width="20px" alt="delete" @click="deleteFilm(item)">
        </div>
      </div>
    </template>

  </AppList>
    <div class="wrapper-btn">
      <button class="btn-primary" @click="addFilm">
        Добавить фильм
      </button>
    </div>

    <!--popup Add Film-->
    <AppPopupWindow v-if="isAddFilm" @close="isAddFilm = false">
      <div class="popup-admin-film">
        <!-- Можно было бы выделить в отдельный компонент -->
        <AppInput v-model="dataFilm.name" :placeholder="inputConfig.inputFilmName.placeholder" classProp="container__input-line"/>
        <AppInput v-model="dataFilm.description" :placeholder="inputConfig.inputFilmDescription.placeholder" classProp="container__input-line"/>
        <AppInput v-model="dataFilm.length" :placeholder="inputConfig.inputFilmLength.placeholder" classProp="container__input-line"/>
        <AppInput v-model="dataFilm.rating" :placeholder="inputConfig.inputFilmRating.placeholder" classProp="container__input-line"/>
        <AppSelect v-model="dataFilm.genre" :values="listGenre" :placeholder="inputConfig.inputFilmGenre.placeholder" classContainer="search__list"/>
        <AppSelect v-model="dataFilm.typeFilm" :values="listType" :placeholder="inputConfig.inputFilmType.placeholder" classContainer="search__list"/>
        <AppSelect v-model="dataFilm.limitAge" :values="listAge" :placeholder="inputConfig.inputFilmAge.placeholder" classContainer="search__list"/>

        <button class="btn-primary" @click="saveFilm">Сохранить</button>
      </div>
    </AppPopupWindow>
    <!--popup Change Film -->
    <AppPopupWindow v-if="isShowPanelChangeFilm" @close="isShowPanelChangeFilm = false">
      <div class="popup-admin-film">
        <AppInput v-model="selectedFilm.name" :placeholder="inputConfig.inputFilmName.placeholder" classProp="container__input-line"/>
        <AppInput v-model="selectedFilm.description" :placeholder="inputConfig.inputFilmDescription.placeholder" classProp="container__input-line"/>
        <AppInput v-model="selectedFilm.length" :placeholder="inputConfig.inputFilmLength.placeholder" classProp="container__input-line"/>
        <AppInput v-model="selectedFilm.rating" :placeholder="inputConfig.inputFilmRating.placeholder" classProp="container__input-line"/>
        <AppSelect v-model="selectedFilm.genre" :values="listGenre" :placeholder="inputConfig.inputFilmGenre.placeholder" classContainer="search__list"/>
        <AppSelect v-model="selectedFilm.typeFilm" :values="listType" :placeholder="inputConfig.inputFilmType.placeholder" classContainer="search__list"/>
        <AppSelect v-model="selectedFilm.limitAge" :values="listAge" :placeholder="inputConfig.inputFilmAge.placeholder" classContainer="search__list"/>

        <button class="btn-primary" @click="changeFilm">Изменить</button>
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
import AppList from '../common/input/AppList'
export default {
  name: 'AdminFilms',
  components: { AppList, AppSelect, AppInput, AppPopupWindow, AppButton, AppSearch },
  data () {
    return {
      searchFilm: '',
      inputConfig: {
        inputFilms: {
          placeholder: 'Поиск по названию фильма'
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
        },
        inputFilmGenre: {
          placeholder: 'Жанр фильма'
        },
        inputFilmType: {
          placeholder: 'Тип фильма'
        },
        inputFilmAge: {
          placeholder: 'Возрастные ограничения'
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
        }
      ],
      listType: [
        {
          name: '2D',
          val: '2D',
          key: 1
        },
        {
          name: '3D',
          val: '3D',
          key: 2
        },
        {
          name: '4D',
          val: '4D',
          key: 3
        }
      ],
      listAge: [
        {
          age: '+6',
          val: '+6',
          key: 1
        },
        {
          age: '+12',
          val: '+12',
          key: 2
        },
        {
          age: '+16',
          val: '+16',
          key: 3
        }
      ],
      listFilms: [],
      listFilmHeader: [
        {
          id: 1,
          name: 'Номер'
        },
        {
          id: 2,
          name: 'Название'
        },
        {
          id: 3,
          name: 'Жанр'
        },
        {
          id: 4,
          name: 'Рейтинг'
        },
        {
          id: 5,
          name: 'Длительность'
        },
        {
          id: 6,
          name: 'Возрастное ограничение'
        },
        {
          id: 7,
          name: 'Редактировать'
        },
        {
          id: 8,
          name: 'Удалить'
        }
      ],
      isShowPanelChangeFilm: false,
      selectedFilm: {}
    }
  },
  computed: {
    validSaveButton () {
      let isNoValid = false
      Object.values(this.dataFilm).forEach(item => {
        isNoValid = !item ? true : false
      })
      return isNoValid
    },
    getLengthRow () {
      return `width: ${995 / this.listFilmHeader.length}px`
    }
  },
  watch: {
    searchFilm: {
      immediate: true,
      handler: function (val) {
        this.$store.dispatch('films/getFilm', val).then(response => this.listFilms = response)
      }
    }
  },
  methods: {
    addFilm () {
      this.isAddFilm = true
    },
    saveFilm () {
      this.$store.dispatch('films/addFilm', this.dataFilm).then(() => {
        this.isAddFilm = false
        this.$store.dispatch('films/getFilm', this.searchFilm).then(response => this.listFilms = response)
      })
    },
    deleteFilm (value) {
      this.$store.dispatch('films/deleteFilm', value).then(() => {
        return this.$store.dispatch('films/getFilm', this.searchFilm).then(response => this.listFilms = response)
      })
    },
    changeFilm (value) {
      this.$store.dispatch('films/changeFilm', this.selectedFilm).then(() => {
        this.isShowPanelChangeFilm = false
        this.$store.dispatch('films/getFilm', this.searchFilm)
      })
    }
  }
}
</script>

<style scoped lang="scss">
  @mixin flexStyle {
    display: flex;
    justify-content: center;
    align-items: center;
  }
.container-films {
  color: #222222;
}

.popup-admin-film {
  max-width: 1000px;
  max-height: 550px;
  height: 100%;
  background: white;
  padding: 10px;
  overflow: auto;
  box-shadow: -5px -5px 5px gray;
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

</style>

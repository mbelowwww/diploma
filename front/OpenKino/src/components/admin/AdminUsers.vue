<template>
  <div class="container-users">
    <AppSearch v-model="searchUser" :placeholder="inputConfig.inputFilms.placeholder"/>

    <div class="wrapper-films__header">
      <div v-for="item in listUserHeader" :key="item.id" :style="getLengthRow" class="wrapper-films__header__item">{{item.name}}</div>
    </div>
    <AppList :list="getListUsers">
      <template #item="{item, index}">
        <div class="wrapper-films">
          <div class="wrapper-films__data" :style="getLengthRow">{{ index + 1 }}</div>
          <div class="wrapper-films__data" :style="getLengthRow">{{ item | getFIO }}</div>
          <div class="wrapper-films__data" :style="getLengthRow">
            <img src="@/assets/img/change.png" height="20px" width="20px" alt="change" @click="isShowPanelChangeFilm = true, selectedFilm = item">
          </div>
          <div class="wrapper-films__data" :style="getLengthRow">
            <IconBan @click="toBanUser(item)"/>
          </div>
        </div>
      </template>
    </AppList>
  </div>
</template>

<script>
import AppSearch from '../common/input/AppSearch'
import AppList from '../common/input/AppList'
import IconBan from '../icons/IconBan'
import { dateToString } from '../../_helper/project'

export default {
  name: 'AdminUsers',
  components: { IconBan, AppSearch, AppList },
  data () {
    return {
      searchUser: '',
      inputConfig: {
        inputFilms: {
          placeholder: 'Поиск по ФИО'
        }
      },
      listUserHeader: [
        {
          id: 1,
          name: 'Номер'
        },
        {
          id: 2,
          name: 'ФИО'
        },
        {
          id: 3,
          name: 'Подробнее'
        },
        {
          id: 4,
          name: 'Заблокировать'
        }
      ],
      listUsers: []
    }
  },
  computed: {
    getLengthRow () {
      return `width: ${995 / this.listUserHeader.length}px`
    },
    getListUsers () {
      return this.listUsers.filter(item => item.lName.toLocaleLowerCase().includes(this.searchUser.toLocaleLowerCase()) ||
        item.name.toLocaleLowerCase().includes(this.searchUser.toLocaleLowerCase()) ||
        item.fName.toLocaleLowerCase().includes(this.searchUser.toLocaleLowerCase()))
    }
  },
  filters: {
    getFIO (value) {
      return `${value.fName} ${value.name} ${value.lName}`
    }
  },
  watch: {
    searchUser: {
      immediate: true,
      handler: function (val) {
        this.$store.dispatch('auth/getListOfUsers').then(response => this.listUsers = response.data)
      }
    }
  },
  methods: {
    toBanUser (value) {
      let correctValue = {
        kinoUserId: 0,
        time: '',
        description: '',
        ban: false
      }
      correctValue.kinoUserId = value.id
      correctValue.description = 'Нарушил правила'
      correctValue.time = dateToString(new Date())
      correctValue.ban = true

      this.$store.dispatch('ban/toBan', correctValue)
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

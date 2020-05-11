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
            <img src="@/assets/img/change.png" height="20px" width="20px" alt="change" @click="isShowPopupChange = true, selectedUser = item">
          </div>
          <div class="wrapper-films__data" :style="getLengthRow">
            <IconBan v-if="item.action" @click="isShowPopup = true, selectedUser = item"/>
            <IconNoBan v-else @click="noToBan(item)"/>
          </div>
        </div>
      </template>
    </AppList>

    <AppPopupWindow v-if="isShowPopup" @close="isShowPopup = false">
      <div class="popup-admin-ban">
        <p class="popup-admin-ban__text">Вы решили заблокировать: {{ selectedUser | getFIO }}</p>
        <p class="popup-admin-ban__text popup-admin-ban__default">Введите причину блокировки:</p>
        <AppInput v-model="reasonBan" placeholder="Введите причину" classProp="container__input-line"/>
        <button class="single-button" @click="toBanUser(selectedUser)">Заблокировать</button>
      </div>
    </AppPopupWindow>

    <AppPopupWindow v-if="isShowPopupChange" @close="isShowPopupChange = false">
      <div class="wrapper-popup-change">
        <TheRegistration :dataRegistration="selectedUser" @changeUser="selectedUser = $event">
          <template #button>
            <button class="single-button" @click="changeUser(selectedUser)">Изменить</button>
          </template>
        </TheRegistration>
      </div>
    </AppPopupWindow>
  </div>
</template>

<script>
import AppSearch from '../common/input/AppSearch'
import AppList from '../common/input/AppList'
import IconBan from '../icons/IconBan'
import { dateToString } from '../../_helper/project'
import AppPopupWindow from '../popup/AppPopupWindow'
import IconNoBan from '../icons/IconNoBan'
import AppInput from '../common/input/AppInput'
import TheRegistration from '../single/TheRegistration'

export default {
  name: 'AdminUsers',
  components: { TheRegistration, AppInput, IconNoBan, AppPopupWindow, IconBan, AppSearch, AppList },
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
      listUsers: [],
      isShowPopup: false,
      selectedUser: {},
      reasonBan: '',
      isShowPopupChange: false
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
      correctValue.description = this.reasonBan
      correctValue.time = dateToString(new Date())
      correctValue.ban = true

      this.$store.dispatch('ban/toBan', correctValue).then(() => {
        this.$store.dispatch('auth/getListOfUsers').then(response => this.listUsers = response.data)
        this.isShowPopup = false
      })
    },
    noToBan (value) {
      this.$store.dispatch('ban/toNoBan', value.id).then(() => {
        this.$store.dispatch('auth/getListOfUsers').then(response => this.listUsers = response.data)
      })
    },
    changeUser (value) {
      this.$store.dispatch('auth/changeUser', value).then(() => {
        this.$store.dispatch('auth/getListOfUsers').then(response => this.listUsers = response.data)
        this.isShowPopupChange = false
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
.popup-admin-ban {
  max-width: 1000px;
  max-height: 200px;
  height: 100%;
  background: white;
  padding: 10px;
  box-shadow: -5px -5px 5px gray;
  &__text {
    font-size: 18px;
    color: red;
  }
  &__default {
    color: #222222;
  }
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
  margin: 40px 0;
  &:hover {
    cursor: pointer;
    background: #0E649E;
    color: white;
  }
}
.wrapper-popup-change {
  width: 100%;
  max-width: 500px;
  min-height: 280px;
  background: white;
  padding: 15px;
}
</style>

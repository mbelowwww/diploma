<template>
  <AppCenter>
    <div class="container-cabinet">
<!--      <transition name="slide-userInfo">-->
        <div class="container-cabinet__block userInformation">
          <h2 class="titleMain">{{informationOfUser}}</h2>
          <p class="titleList">{{$store.state.auth.currentUser.fName}}</p>
          <p class="titleList">{{$store.state.auth.currentUser.name}}</p>
          <p class="titleList">{{$store.state.auth.currentUser.lName}}</p>
          <p class="titleList">{{$store.state.auth.currentUser.age}}</p>
          <p class="titleList">{{$store.state.auth.currentUser.phone}}</p>
          <button class="btn-cabinet" style="margin-top: 40px" @click="isEditUserInfo = true">Редактировать</button>
        </div>
<!--      </transition>-->

      <div class="container-cabinet__block">
<!--        <transition name="show-title">-->
          <h2 class="titleMain" style="color: white; text-align: right">Личный кабинет</h2>
<!--        </transition>-->
<!--        <transition name="show-sections">-->
          <UserCabinetSection :headers="headers" :title="title" :array="listCards" @clickBtn="isCreateCard = true"/>
<!--        </transition>-->
<!--        <transition name="show-sections">-->
          <UserCabinetSection :headers="headersHistory" :title="titleHistory" :array="valuesHistory" nameButton="Подробнее"/>
<!--        </transition>-->
      </div>
    </div>
<!--  Изменение информации пользователя  -->
    <AppPopupWindow v-if="isEditUserInfo" @close="isEditUserInfo = false">
      <div class="wrapper-popup-change">
        <p class="titleMain" style="text-align: center">Редактирование пользователя</p>
        <AppInput v-model="editUserInfo.fName" placeholder="Введите фамилию"/>
        <AppInput v-model="editUserInfo.name" placeholder="Введите имя"/>
        <AppInput v-model="editUserInfo.lName" placeholder="Введите отчество"/>
        <AppInput v-model="editUserInfo.age" placeholder="Введите возраст"/>
        <AppInput v-model="editUserInfo.phone" placeholder="Введите телефон"/>
        <div class="wrapper-btn">
          <button class="btn-cabinet" @click="saveEditUserInfo(editUserInfo)">Сохранить</button>
        </div>
      </div>
    </AppPopupWindow>
<!--  Добавление карты  -->
    <AppPopupWindow v-if="isCreateCard" @close="isCreateCard = false">
      <div class="wrapper-popup-change">
        <p class="titleMain" style="text-align: center">Создание новой карты</p>
        <AppInput v-model="createCards.number" placeholder="Введите номер карты"/>
        <AppInput v-model="createCards.balance" placeholder="Введите баланс"/>
        <div class="wrapper-btn">
          <button class="btn-cabinet" @click="createCard(createCards)">Создать</button>
        </div>
      </div>
    </AppPopupWindow>
  </AppCenter>
</template>

<script>

import AppCenter from '../components/common/group/AppCenter'
import UserCabinetSection from '../components/userCabinet/UserCabinetSection'
import AppPopupWindow from '../components/popup/AppPopupWindow'
import AppInput from '../components/common/input/AppInput'
export default {
  name: 'PageCabinetUser',
  components: { AppInput, AppPopupWindow, UserCabinetSection, AppCenter },
  created () {
    this.$store.dispatch('cabinetUser/getListCards').then(response => this.listCards = response)
  },
  data () {
    return {
      informationOfUser: 'Информация о пользователе',
      headers: [
        {
          id: 1,
          val: 'Номер карты',
          value: 'id'
        },
        {
          id: 2,
          val: 'Баланс',
          value: 'balance'
        }
      ],
      headersHistory: [
        {
          id: 1,
          val: 'Фильм',
          value: 'val'
        },
        {
          id: 2,
          val: 'Дата',
          value: 'value'
        }
      ],
      title: 'Банковские карты',
      titleHistory: 'История',
      valuesHistory: [],
      editUserInfo: {
        fName: '',
        name: '',
        lName: '',
        age: '',
        phone: ''
      },
      isEditUserInfo: false,
      isCreateCard: false,
      createCards: {
        number: '',
        balance: ''
      },
      listCards: []
    }
  },
  methods: {
    saveEditUserInfo (value) {
      value.id = this.$store.state.auth.currentUser.id
      value.fName = value.fName ? value.fName : this.$store.state.auth.currentUser.fName
      value.name = value.name ? value.name : this.$store.state.auth.currentUser.name
      value.lName = value.lName ? value.lName : this.$store.state.auth.currentUser.lName
      value.age = value.age ? value.age : this.$store.state.auth.currentUser.age
      value.phone = value.phone ? value.phone : this.$store.state.auth.currentUser.phone
      this.$store.dispatch('auth/changeUser', value).then(() => {
        this.$store.dispatch('auth/getCurrentUser')
        this.isEditUserInfo = false
      })
    },
    createCard (value) {
      this.$store.dispatch('cabinetUser/addCard', value).then(() => {
        this.isCreateCard = false
        this.$store.dispatch('cabinetUser/getListCards').then(response => this.listCards = response)
      })
    }
  }
}
</script>

<style scoped lang="scss">
.container-cabinet {
  background-color: rgba(26, 17, 28, 0.9);
  width: 100%;
  height: 600px;
  display: flex;
  justify-content: space-between;
  padding: 20px;
}
.userInformation {
  padding: 30px 40px;
  margin-top: 40px;
}
.wrapper-popup-change {
  width: 100%;
  max-width: 500px;
  min-height: 280px;
  background-color: rgba(58, 32, 53, 1);
  padding: 15px;
}

.slide-infoUser-enter-active {
  transition: all .9s ease;
}
.slide-infoUser-leave-active {
  transition: all .9s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slide-infoUser-enter, .slide-infoUser-leave-to {
  transform: translateX(10px);
  opacity: 0;
}
/*!*title*!*/
/*.show-title-enter-active {*/
/*  transition: all .6s ease;*/
/*}*/
/*.show-title-leave-active {*/
/*  transition: all .6s cubic-bezier(1.0, 0.5, 0.8, 1.0);*/
/*}*/
/*.show-title-enter, .show-title-leave-to {*/
/*  transform: translateX(10px);*/
/*  opacity: 0;*/
/*}*/
/*!* sections *!*/
/*.show-sections-enter-active {*/
/*  transition: all .8s ease;*/
/*}*/
/*.show-sections-leave-active {*/
/*  transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);*/
/*}*/
/*.show-sections-enter, .show-sections-leave-to*/
/*  !* .slide-fade-leave-active до версии 2.1.8 *! {*/
/*  transform: translateX(10px);*/
/*  opacity: 0;*/
/*}*/
</style>

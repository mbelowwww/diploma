<template>
  <AppCenter>
    <div class="container-cabinet">
      <transition name="slide-userInfo">
        <div class="container-cabinet__block userInformation">
          <h2 class="titleMain">{{informationOfUser}}</h2>
          <p class="titleList">{{$store.state.auth.currentUser.fName}}</p>
          <p class="titleList">{{$store.state.auth.currentUser.name}}</p>
          <p class="titleList">{{$store.state.auth.currentUser.lName}}</p>
          <p class="titleList">{{$store.state.auth.currentUser.age}}</p>
          <p class="titleList">{{$store.state.auth.currentUser.mail}}</p>
          <button class="btn-cabinet" style="margin-top: 40px">Редактировать</button>
        </div>
      </transition>

      <div class="container-cabinet__block">
<!--        <transition name="show-title">-->
          <h2 class="titleMain" style="color: white; text-align: right">Личный кабинет</h2>
<!--        </transition>-->
<!--        <transition name="show-sections">-->
          <UserCabinetSection :headers="headers" :title="title" :array="values"/>
<!--        </transition>-->
<!--        <transition name="show-sections">-->
          <UserCabinetSection :headers="headersHistory" :title="titleHistory" :array="valuesHistory" nameButton="Подробнее"/>
<!--        </transition>-->
      </div>
    </div>
  </AppCenter>
</template>

<script>

import AppCenter from '../components/common/group/AppCenter'
import UserCabinetSection from '../components/userCabinet/UserCabinetSection'
export default {
  name: 'PageCabinetUser',
  components: { UserCabinetSection, AppCenter },
  data () {
    return {
      informationOfUser: 'Информация о пользователе',
      headers: [
        {
          id: 1,
          val: 'Номер карты',
          value: 'val'
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
      values: [
        {
          id: 3,
          val: '959594132',
          balance: '2050'
        },
        {
          id: 4,
          val: '56204633',
          balance: '11500'
        },
        {
          id: 5,
          val: '123123400',
          balance: '550'
        },
        {
          id: 6,
          val: '56204633',
          balance: '11500'
        }
      ],
      valuesHistory: [
        {
          id: 3,
          val: 'Человек-паук',
          value: '05.05.2020'
        }
      ]
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

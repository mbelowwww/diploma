<template>
  <div class="container-halls">
    <div class="container-halls__list-halls">
      <p class="container-halls__list-halls__text">Список залов:</p>
    </div>
    <div class="container-halls__create-hall">
      <div class="container-halls__head">
        <AppInput v-model.number="dataWidthAndHeight.width" placeholder="Введите ширину зала" inputSize="200"/>
        <AppInput v-model.number="dataWidthAndHeight.height" placeholder="Введите высоту зала" inputSize="200" class="container-halls__head__amount"/>
        <button class="single-button">Сформировать</button>
      </div>
      <AdminKitHall :widthHall="dataWidthAndHeight.width" :heightHall="dataWidthAndHeight.height">

      </AdminKitHall>
    </div>

  </div>
</template>

<script>
import AppInput from '../common/input/AppInput'
import AppInputGroup from '../common/group/AppInputGroup'
import AdminKitHall from './AdminKitHall'
export default {
  name: 'AdminHalls',
  components: { AdminKitHall, AppInputGroup, AppInput },
  data () {
    return {
      dataWidthAndHeight: {
        width: 0,
        height: 0
      }
    }
  }
}
</script>

<style scoped lang="scss">
  .container-halls {
    color: #222222;
    display: flex;
    &__list-halls {
      max-width: 300px;
      display: flex;
      justify-content: center;
      width: 100%;
      background: #f1f1f1;
      height: 100%;
      min-height: calc(100vh - 330px);
      margin-right: 10px;
      &__text {
        font-size: 22px;
        margin-top: 20px;
      }
    }
    &__head {
      display: flex;
      padding: 10px 0;
      &__amount {
        margin: 10px 20px 0;
      }
    }
  }
  .single-button {
    width: 100%;

    height: 40px;
    padding: 10px;
    background: #3EB1FF;
    color: #222222;
    border: none;
    border-radius: 3px;
    font-size: 18px;
    margin: 15px 0 0 0;
    &:hover {
      cursor: pointer;
      background: #0E649E;
      color: white;
    }
  }
</style>

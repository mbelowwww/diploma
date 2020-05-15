<template>
  <div class="container-halls">
    <div class="container-halls__list-halls">
      <p class="container-halls__list-halls__text">Список залов:</p>
      <li v-for="item in listAllHalls" :key="item.id" class="container-halls__list-halls__item">{{item.number}}</li>
    </div>
    <div class="container-halls__create-halls">
      <div class="container-halls__head">
        <AppInput v-model.number="dataWidthAndHeight.width" placeholder="Введите ширину зала" inputSize="200" class="container-halls__head__amount"/>
        <AppInput v-model.number="dataWidthAndHeight.height" placeholder="Введите высоту зала" inputSize="200" class="container-halls__head__amount"/>
        <AppInput v-model.number="dataWidthAndHeight.number" placeholder="Введите номер зала" inputSize="200" class="container-halls__head__amount"/>
        <button class="single-button" @click="toCreatePlaces(dataWidthAndHeight.width, dataWidthAndHeight.height)">Сформировать</button>
      </div>
      <AdminKitHall :places="createdArray" :checkedPlaces="listPlaces" @clickPlace="addPlace"/>
      <div class="container-halls__create-halls__footer">
        <button class="single-button" :disabled="listPlaces.length === 0 || dataWidthAndHeight.number === ''" @click="saveHall">Сохранить</button>
      </div>
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
        width: '',
        height: '',
        number: ''
      },
      createdArray: [],
      listPlaces: [],
      listAllHalls: []
    }
  },
  methods: {
    toCreatePlaces (width, height) {
      this.createdArray = []
      if (height && width) {
        for (let i = 0; i < width; i++) {
          this.$set(this.createdArray, i, [])
          for (let j = 0; j < height; j++) {
            this.$set(this.createdArray[i], j, { x: i + 1, y: j + 1 })
          }
        }
      }
    },
    addPlace (value) {
      const oldIndex = this.listPlaces.indexOf(value)
      if (oldIndex === -1) {
        this.listPlaces.push(value)
      } else {
        this.listPlaces.splice(oldIndex, 1)
      }
    },
    saveHall () {
      let correctData = {
        width: this.dataWidthAndHeight.width,
        height: this.dataWidthAndHeight.height,
        number: this.dataWidthAndHeight.number,
        price: '',
        places: this.listPlaces
      }
      this.$store.dispatch('halls/createHall', correctData).then(() => {
        this.$store.dispatch('halls/getListHalls').then(response => this.listAllHalls = response)
      })
    }
  },
  watch: {
    getListHalls: {
      immediate: true,
      handler () {
        this.$store.dispatch('halls/getListHalls').then(response => this.listAllHalls = response)
      }
    }
    // dataWidthAndHeight: {
    //   deep: true,
    //   immediate: true,
    //   handler (val) {
    //     if (val.height && val.width) {
    //       for (let i = 0; i < val.width; i++) {
    //         this.$set(this.createdArray, i, [])
    //         for (let j = 0; j < val.height; j++) {
    //           this.$set(this.createdArray[i], j, { x: j + 1, y: i + 1 })
    //         }
    //       }
    //     }
    //   }
    // }
  }
}
</script>

<style scoped lang="scss">
  .container-halls {
    color: #222222;
    display: flex;
    &__list-halls {
      min-width: 300px;
      max-width: 300px;
      display: flex;
      text-align: center;
      flex-direction: column;
      background: #f1f1f1;
      height: 100%;
      min-height: calc(100vh - 330px);
      margin-right: 10px;
      &__text {
        font-size: 22px;
        background: #222222;
        color: white;
        padding: 5px;
      }
      &__item {
        height: 30px;
        width: 100%;
        font-size: 24px;
        padding: 5px;
        text-align: center;
        line-height: 30px;
        color: #222222;
        &:hover {
          cursor: pointer;
        }
      }
    }
    &__head {
      display: flex;
      padding: 10px 0;
      &__amount {
        margin: 10px 20px 0 0;
      }
    }
    &__create-halls {
      &__footer {
        height: 59px;
        width: 100%;
        max-width: 1000px;
        display: flex;
        justify-content: flex-end;
        align-items: flex-end;
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

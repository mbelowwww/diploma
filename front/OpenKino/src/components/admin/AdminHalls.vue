<template>
  <div class="container-halls">
    <div class="container-halls__list-halls">
      <p class="container-halls__list-halls__text">Список залов:</p>
      <li v-for="item in listAllHalls" :key="item.id" class="container-halls__list-halls__item" @click="showHallItem(item.id)">{{item.number}}</li>
    </div>
    <div class="container-halls__create-halls">
      <div class="container-halls__head">
        <AppInput v-model.number="dataWidthAndHeight.width" placeholder="Введите ширину зала" inputSize="200" class="container-halls__head__amount"/>
        <AppInput v-model.number="dataWidthAndHeight.height" placeholder="Введите высоту зала" inputSize="200" class="container-halls__head__amount"/>
        <AppInput v-model.number="dataWidthAndHeight.number" placeholder="Введите номер зала" inputSize="200" class="container-halls__head__amount"/>
        <button class="btn-primary" @click="toCreatePlaces(dataWidthAndHeight.width, dataWidthAndHeight.height)">Сформировать</button>
      </div>
      <AdminKitHall v-if="listPlaces.length > 0" :places="createdArray" :checkedPlaces="listPlaces" @clickPlace="addPlace"/>
      <div class="container-halls__create-halls__footer">
        <button v-if="listPlaces.length > 0" class="btn-primary" :disabled="listPlaces.length === 0 || dataWidthAndHeight.number === ''" @click="saveHall">Сохранить</button>
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
  created () {
    this.$store.dispatch('halls/getListHalls').then(response => this.listAllHalls = response)
  },
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
            this.$set(this.createdArray[i], j, { x: i + 1, y: j + 1})
          }
        }
      }
      this.listPlaces = [].concat(...this.createdArray)
    },
    addPlace (value) {
      // const oldIndex = this.listPlaces.indexOf(value)
      const oldIndex = this.listPlaces.findIndex(item => item.x === value.x && item.y === value.y)
      if (oldIndex === -1) {
        this.listPlaces.push(value)
      } else {
        this.listPlaces.splice(oldIndex, 1)
      }
    },
    saveHall () {
      const correctData = {
        width: this.dataWidthAndHeight.width,
        height: this.dataWidthAndHeight.height,
        number: this.dataWidthAndHeight.number,
        price: '',
        places: this.listPlaces
      }
      this.$store.dispatch('halls/createHall', correctData).then(() => {
        this.$store.dispatch('halls/getListHalls').then(response => this.listAllHalls = response)
      })
    },
    showHallItem (id) {
      this.$store.dispatch('halls/getHallById', id).then((response) => {
        this.toCreatePlaces(response.width, response.height)
        this.dataWidthAndHeight.width = response.width
        this.dataWidthAndHeight.height = response.height
        this.dataWidthAndHeight.number = response.number
        this.listPlaces = response.places
      })
    }
  },
  // watch: {
  //   getListHalls: {
  //     immediate: true,
  //     handler () {
  //       this.$store.dispatch('halls/getListHalls').then(response => this.listAllHalls = response)
  //     }
  //   }
  // }
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
      min-height: calc(100vh - 250px);
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
        display: flex;
        justify-content: flex-end;
        align-items: flex-end;
      }
    }
  }

</style>

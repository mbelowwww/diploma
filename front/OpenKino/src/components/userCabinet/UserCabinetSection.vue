<template>
  <div class="container-item">
    <h2 class="container-item__title">{{title}}</h2>
    <div class="container-item__data">
      <div class="container-item__data__block" style="overflow: auto">
        <AppTable :header="headers" :array="array" headerValue="val"></AppTable>
      </div>
      <div class="container-item__data__block" style="display: flex; align-items: flex-end">
        <button class="btn-cabinet" @click="$emit('clickBtn')">{{nameButton}}</button>
      </div>
    </div>
  </div>
</template>

<script>
import AppList from '../common/input/AppList'
import AppTable from '../common/table/AppTable'

export default {
  name: 'UserCabinetSection',
  components: { AppTable, AppList },
  props: {
    title: {
      type: String,
      default: ''
    },
    headers: {
      type: Array,
      default: () => []
    },
    array: {
      type: Array,
      default: () => []
    },
    nameButton: {
      type: String,
      default: 'Добавить'
    }
  },
  data () {
    return {
    }
  },
  computed: {
    widthRow () {
      return `width: ${500 / 2}px`
    }
  }
}
</script>

<style scoped lang="scss">
  .container-item {
    border-radius: 5px;
    background-color: rgba(58, 32, 53, 0.85);
    padding: 15px 30px;
    max-width: 800px;
    width: 100%;
    height: 240px;
    margin-bottom: 30px;
    &__title {
      text-align: center;
      color: #F36D21;
      font-size: 24px;
      margin-bottom: 20px;
      font-weight: 100;
    }
    &__data {
      display: flex;
      justify-content: space-between;
      &__block {
        height: 150px;
      }
    }
  }
</style>

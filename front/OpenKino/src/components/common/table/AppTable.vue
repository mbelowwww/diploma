<template>
  <div class="container-table">
    <h2 class="container-table__title">{{title}}</h2>
    <div class="container-table__header" >
      <div v-for="item in header" :key="item && item.id ? item.id : item.key" :style="widthRow" class="container-table__header__item">{{item[headerValue]}}</div>
    </div>
    <div v-for="item in array" :key="item && item.id ? item.id : item.key" class="container-table__data">
      <div v-for="key in header" :key="key.id" :style="widthRow">
        <slot :name="key.value" :item="item">{{valueByKey(item, key.value)}}</slot>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AppTable',
  components: {},
  props: {
    header: {
      type: Array,
      default: () => []
    },
    title: {
      type: String,
      default: ''
    },
    width: {
      type: String,
      default: '500'
    },
    height: {
      type: String,
      default: '150'
    },
    headerValue: {
      type: String,
      default: 'name'
    },
    array: {
      type: Array,
      default: () => []
    },
    keys: {
      type: Array,
      default: () => []
    }
  },
  computed: {
    widthRow () {
      return `width: ${this.width / this.header.length}px`
    }
  },
  methods: {
    valueByKey (item, key) {
      let value = ''
      const trees = key.split('.')

      trees.forEach((treesKey) => {
        if (typeof value === 'object' && value) {
          value = value[treesKey]
        } else {
          value = item[treesKey]
        }
      })
      return value
    }
  }
}
</script>

<style scoped lang="scss">
  .container-table {
    color: white;
    &__title {
      color: #F36D21;
      font-size: 24px;
      margin-bottom: 15px;
      font-weight: 100;
      letter-spacing: 1px;
    }
    &__header {
      display: flex;
      font-size: 18px;
      margin-bottom: 20px;
      letter-spacing: 1px;
    }
    &__data {
      display: flex;
    }
  }
</style>

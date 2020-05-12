<template>
  <ul :class="classContainer">
    <li v-for="(item, index) in list"
        :key="index"
        class="sp-list__item"
        :class="[classItem, {'sp-list__item_selected': selected === item }]"
        @click="clickByItem($event, item)"
    >
      <slot name="item" :item="item" :index="index">
        {{item}}
      </slot>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'AppList',
  components: {},
  props: {
    list: {
      type: Array,
      require: true
    },
    classItem: {
      type: String,
      require: false,
      default: ''
    },
    selected: {
    },
    classContainer: {
      type: String,
      default: 'sp-list'
    },
    isDisabledClick: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
    }
  },
  methods: {
    clickByItem ($event, item) {
      $event.stopPropagation()
      this.$emit('click', item)
    }
  }
}
</script>

<style scoped lang="scss">
.sp-list {
  overflow: auto;

  height: 100%;
  &__item {
    cursor: pointer;
    margin-top: 10px;
    padding: 5px 5px;
    align-items: center;
    display: flex;
    flex-shrink: 0;
    /*&:hover {*/
    /*  background-color: #1EA4FF;*/
    /*  color: white;*/
    /*}*/

    &_selected {
      background-color: #1EA4FF;
      color: white;
    }
  }
}
.search__list {
  cursor: pointer;
  z-index: 200;
}
</style>

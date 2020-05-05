<template>
  <ul class="sp-list">
    <li v-for="(item, index) in list"
        :key="index"
        class="sp-list__item"
        :class="[classItem, {'sp-list__item_selected': selected === item}]"
        @click="$emit('click', item)"
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
      require: false
    },
    selected: {
    }
  },
  data () {
    return {
    }
  }
}
</script>

<style scoped lang="scss">
.sp-list {
  overflow: auto;
  flex-grow: 1;
  height: 1px;

  &__item {
    cursor: pointer;
    margin-top: 10px;
    padding: 5px 5px;
    align-items: center;
    display: flex;
    flex-shrink: 0;
    &:hover {
      background-color: #1EA4FF;
      color: white;
    }

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

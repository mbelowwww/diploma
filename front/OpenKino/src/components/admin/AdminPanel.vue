<template>
  <div class="container-panel">
    <div
      v-for="(item, index) in valuePanel"
      :key="item.key"
      :class="{'container-panel__selected': selected === item}"
      class="container-panel__menu"
      @click="$emit('click', item)">
      {{item.val}}
    </div>
  </div>
</template>

<script>
export default {
  name: 'AdminPanel',
  components: {},
  props: {
    valuePanel: {
      type: Array,
      require: true
    },
    selected: {
      type: Object,
      require: true
    }
  }
}
</script>

<style scoped lang="scss">
.container-panel {
  background: #222222;
  height: 70px;
  width: 100%;
  padding: 0 50px;
  display: flex;
  flex-direction: row;
  font-size: 18px;
  justify-content: center;
  letter-spacing: 1px;
  &__menu {
    width: 150px;
    height: 70px;
    border: 1px solid white;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
  }
  &__selected {
    background: white;
    color: #222222;
    border: 1px solid #222222;
    /*outline: 1px solid #222222;*/
  }
}
</style>

<template>
  <div class="container-kit-halls">
    <div v-for="(item, index) in places" :key="index">
      <div class="place" v-for="(item2, index2) in item" :key="index2" :class="{'actionPlace' : !!checkedPlaces.find(val => val.x === item2.x && val.y === item2.y)}" @click="$emit('clickPlace', item2)"></div>
    </div>

  </div>
</template>

<script>
export default {
  name: 'AdminKitHall',
  components: {},
  props: {
    places: {
      type: Array,
      default: () => []
    },
    checkedPlaces: {
      type: Array,
      default: () => []
    }
  },
  data () {
    return {
    }
  },
  methods: {
  }
}
</script>

<style scoped lang="scss">
.container-kit-halls {
  background: #f3f3f3;
  min-height: 500px;
  max-height: 500px;
  display: flex;
  overflow: auto;
}
.place {
  width: 20px;
  height: 20px;
  /*background: #f3f3f3;*/
  background: yellow;
  margin: 5px;
}
  .actionPlace {
    background: green;
  }
</style>

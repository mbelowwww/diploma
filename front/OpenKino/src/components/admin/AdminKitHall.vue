<template>
  <div class="container-kit-halls">
    <div v-for="(item, index) in places" :key="index">
      <div v-for="(item2, index2) in item"
           :key="index2"
           class="place"
           :class="{'actionPlace' : !!checkedPlaces.find(val => val.x === item2.x && val.y === item2.y)}"
           @click="$emit('clickPlace', item2)"
      >
      </div>
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
  max-height: 500px;
  max-width: 807px;
  display: flex;
  overflow: auto;
}
.place {
  width: 28px;
  height: 28px;
  /*background: #f3f3f3;*/
  background: #f3f3f3;
  border-radius: 2px;
  margin: 5px;
  border: 1px solid #5eaaff;
}
  .actionPlace {
    background: green;
  }
</style>
